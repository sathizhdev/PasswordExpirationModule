package com.example.loginservice.Controller;


import com.example.loginservice.Modal.User;
import com.example.loginservice.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class userController {

    @Autowired
    UserServiceImpl userservice;

    @RequestMapping("/login")
    public ModelAndView toLoginPage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;

    }

    @RequestMapping("/Welcome")
    public ModelAndView Welcome()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Welcome");
        return mv;

    }



}
