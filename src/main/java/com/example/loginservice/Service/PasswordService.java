package com.example.loginservice.Service;

import com.example.loginservice.Modal.User;
import com.example.loginservice.Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class PasswordService {

    @Autowired
    usersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    public void changePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setPasswordChangedTime(LocalDateTime.now());

        usersRepository.save(user);
    }




}
