package com.example.loginservice.Security;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws
            IOException,
            ServletException {

        logger.info("Inside Auth Handler");

        String redirectURL = "/login?error=true";

        if (e.getMessage().contains("expired")) {
            logger.info("Inside Expired");
            redirectURL = "/login?expired=true";
        }
        super.setDefaultFailureUrl(redirectURL);

        super.onAuthenticationFailure(request, response, e);

    }
    }