package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionDemos {
    @Autowired
    private HttpSession httpSession;

    @GetMapping(value = "/counter", produces = MediaType.TEXT_PLAIN_VALUE)
    public String counter() {
        int counter;
        if (httpSession.getAttribute("counter") == null)
            counter = 0;
        else
            counter = 1 + (int) httpSession.getAttribute("counter");
        httpSession.setAttribute("counter", counter);
        return counter + "\n";
    }
}