package com.example.loginservice.Exception;


import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class CustomException {

    @ExceptionHandler(CredentialsExpiredException.class)
    public ModelAndView handleValidationException(CredentialsExpiredException c) {

        System.out.println("-----Inside Handler----");
        ModelAndView mv = new ModelAndView("resetPassword");
        return mv;

    }


}