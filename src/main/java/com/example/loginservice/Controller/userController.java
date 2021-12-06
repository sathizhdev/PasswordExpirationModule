package com.example.loginservice.Controller;


import com.example.loginservice.Modal.User;
import com.example.loginservice.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping ("/resetPassword")
    public ModelAndView resetPassword()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("resetpassword");
        return mv;
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam String newPassword,@RequestParam String oldPassword)
    {

        ModelAndView mv = new ModelAndView();

        String result = userservice.changePassword(newPassword,oldPassword);

        System.out.println(result);
        if(result.equals("invalidpassword"))
        {

            mv.setViewName("redirect:/resetPassword?error=true");

            return mv;
        }

        mv.setViewName("redirect:/login");

        return mv;

    }

}
