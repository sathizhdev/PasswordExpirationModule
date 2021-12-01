package com.example.loginservice.Service;

import com.example.loginservice.Config.MessageConfig;
import com.example.loginservice.Modal.User;
import com.example.loginservice.Repository.usersRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService  {

    @Autowired
     usersRepository userRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static Logger logger =  LoggerFactory
            .getLogger(UserServiceImpl.class);


   public User findUserByUserName(String userName)
   {
      return userRepository.findByUserName(userName);

   }


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       User user = userRepository.findByUserName(userName);

       logger.info(String.valueOf(user));

       logger.info("username is " + user.getUserName());

       logger.info("password is " + user.getPassword());

        if( user.isPasswordExpired()){

            String message = user.getUserName() + " Your Password Expired";

            logger.info("Password Expired");
            
            // Message to Expiry Queue
            amqpTemplate.convertAndSend(MessageConfig.topicExchangeName, "demo.test", message);

            throw new CredentialsExpiredException("password expired");

        }

        return new PrincipalUser(user);
    }
}
