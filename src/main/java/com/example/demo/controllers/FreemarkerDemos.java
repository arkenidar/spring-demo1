package com.example.demo.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class FreemarkerDemos {
    @GetMapping(value = "/freemarker-test.html")
    public ModelAndView freemarker() {
        ModelMap myMap = new ModelMap();
        myMap.put("username", "Dario");
        myMap.put("list", List.of("first", "second"));
        return new ModelAndView("test_freemarker", myMap);
    }
}