package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JDBCDemos {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // http://localhost:8080/db
    @GetMapping(value="/db", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        String response="";
        SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet("select * from users_login");
        while (sqlRowSet.next()){
            response+=sqlRowSet.getString("user_name")+"\n";
        }
        return response;
    }
}
