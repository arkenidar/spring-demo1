package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@RestController
public class FreemarkerJDBCDemos {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/fm2")
    public ModelAndView freemarker2() {
        ModelMap myMap = new ModelMap();
        myMap.put("username", "Dario");
        List<String> list2 = new LinkedList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from users_login");
        while (sqlRowSet.next()) list2.add(sqlRowSet.getString("user_name"));
        //List<String> list = Arrays.asList(new String[]{"first", "second"});
        myMap.put("list", list2);
        return new ModelAndView("freemarker-demo", myMap);
    }
}