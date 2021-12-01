package com.example.loginservice.Service;

import com.example.loginservice.Config.MessageConfig;
import com.example.loginservice.Modal.User;
import com.example.loginservice.Repository.usersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService  {

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


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       User user = userRepository.findByUserName(userName);

       List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());

        authorities.add((GrantedAuthority) () -> user.getRole().name());

        if( user.isPasswordExpired()){

            String message = user.getUserName() + " Your Password Expired";

            // Sending Message to Expiry Queue
            amqpTemplate.convertAndSend(MessageConfig.topicExchangeName, "demo.test", message);

            logger.trace( "Password Expired for " + user.getUserName());

            throw new CredentialsExpiredException("Password Expired");

        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
