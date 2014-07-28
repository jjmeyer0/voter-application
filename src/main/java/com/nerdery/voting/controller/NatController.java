package com.nerdery.voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NatController {
    @RequestMapping("/")
    public ModelAndView welcomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
