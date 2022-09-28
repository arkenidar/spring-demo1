package com.example.demo.controllers;

import com.example.demo.db.Customer;
import com.example.demo.db.DBQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JDBCDemos {
    //private final JdbcTemplate jdbcTemplate; // https://spring.io/guides/gs/relational-data-access/

    private final DBQuery<Customer> customerDBQuery;

    @Autowired
    public JDBCDemos(JdbcTemplate jdbcTemplateAutowired) {
        //jdbcTemplate = jdbcTemplateAutowired;
        customerDBQuery = new Customer().setupDBQuery(jdbcTemplateAutowired);
    }

    // http://localhost:8080/db2
    @GetMapping(value = "/db2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findAllToText() {
        return customerDBQuery.findAll().stream().map(Customer::toString).collect(Collectors.joining("\n"));
    }

    // http://localhost:8080/db3
    @GetMapping(value = "/db3")
    public List<Customer> findAllToJSON() {
        return customerDBQuery.findAll();
    }

    // http://localhost:8080/db4
    @GetMapping(value = "/db4")
    public Customer findByIdToJSON(@RequestParam(defaultValue = "1") int id) {
        try {
            return customerDBQuery.findById(id);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
