package com.example.demo;


import com.example.demo.Classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/secure")
    public String admin(Principal principal, Model model) {
        User myuser = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("myuser", myuser);
        return "secure";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String processRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

}


