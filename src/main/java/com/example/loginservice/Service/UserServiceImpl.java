package com.example.loginservice.Service;

import com.example.loginservice.Config.MessageConfig;
import com.example.loginservice.Modal.User;
import com.example.loginservice.Repository.usersRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserDetailsService  {

    User user;

    String expiryMessage;

    @Autowired
     usersRepository userRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger logger =  LoggerFactory
            .getLogger(UserServiceImpl.class);


   public User findUserByUserName(String userName)
   {
      return userRepository.findByUserName(userName);

   }



    public String changePassword( String newPassword,String oldPassword) {

       logger.info(passwordEncoder.encode(oldPassword));
       logger.info(user.getPassword());

       if(passwordEncoder.matches(oldPassword, user.getPassword()))  {
           user.setPassword(passwordEncoder.encode(newPassword));

           user.setPasswordChangedTime(LocalDateTime.now());

           logger.info("Password Changed");

           userRepository.save(user);

           return "passwordchanged";
       }

       return "invalidpassword";

    }


    @RabbitListener(queues = MessageConfig.expiryQueueName)
    public void ReceiveMessage(String Message) {
        expiryMessage = Message;
        logger.info(expiryMessage);
    }

    public void setExpiryMessage(String expiryMessage) {
        this.expiryMessage = expiryMessage;
    }

    public void registerUser(User user)
    {
        user.setPasswordChangedTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordChangedTime(LocalDateTime.of(2021,9,19,9,32,00));
        userRepository.save(user);
    }


    public String getExpiryMessage() { return expiryMessage;}


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       user = userRepository.findByUserName(userName);

       logger.info(String.valueOf(user));

       logger.info("username is " + user.getUserName());

       logger.info("password is " + user.getPassword());

        if( user.isPasswordExpired()){

            String message = user.getUserName() + " Your Password Expired";

            logger.info("Password Expired");

            throw new CredentialsExpiredException("password expired");

        }

        amqpTemplate.convertAndSend(MessageConfig.topicExchangeName, "senduser.test", user.getUserName());

        return new PrincipalUser(user);
    }
}
