package com.example.demo.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

@RestController
public class FreemarkerDemos {
    @GetMapping(value="/fm")
    public ModelAndView freemarker() {
        //return "temp\n";
        //Map<String,Object> myMap = new HashMap<String,Object>();
        ModelMap myMap=new ModelMap();
        myMap.put("username", "Dario");
        List<String> list = Arrays.asList(new String[]{"first", "second"});
        myMap.put("list", list);
        return new ModelAndView("freemarker-demo",myMap);
    }
}