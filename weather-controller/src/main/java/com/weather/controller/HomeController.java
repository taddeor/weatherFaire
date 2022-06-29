package com.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @GetMapping(value = "/index")
    public ModelAndView index(Principal principal){
        final String name = principal.getName();
        final ModelAndView index = new ModelAndView("index");
        index.addObject("logedin",name);
        return index;
    }

}
