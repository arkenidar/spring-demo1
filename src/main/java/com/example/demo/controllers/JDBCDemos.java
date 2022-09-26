package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JDBCDemos {
    @Autowired
    private JdbcTemplate jdbcTemplate; // https://spring.io/guides/gs/relational-data-access/

    // http://localhost:8080/db
    @GetMapping(value = "/db", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        String response = "";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from users_login");
        while (sqlRowSet.next()) {
            response += sqlRowSet.getString("user_name") + "\n";
        }
        return response;
    }

    public List<Customer> findAll() { // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/

        String sql = "SELECT * FROM " + Customer.TABLE;

        return jdbcTemplate.query( // or queryForObject()
                sql,
                (rs, rowNum) -> Customer.fromResultSet(rs)
        );
    }

    public Customer findById(int id) { // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/

        String sql = "SELECT * FROM " + Customer.TABLE + " WHERE id=?";

        return jdbcTemplate.queryForObject( // or query()
                sql,
                (rs, rowNum) -> Customer.fromResultSet(rs),
                id
        );
    }

    // http://localhost:8080/db2
    @GetMapping(value = "/db2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findAllToText() {
        String response = "";
        // SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet("select * from users_login");
        // while (sqlRowSet.next()) response+=sqlRowSet.getString("user_name")+"\n";
        List<Customer> customers = findAll();
        for (Customer customer : customers)
            response += customer + "\n";
        return response;
    }

    // http://localhost:8080/db3
    @GetMapping(value = "/db3")
    public List<Customer> findAllToJSON() {
        return findAll();
    }

    // http://localhost:8080/db4
    @GetMapping(value = "/db4")
    public Customer findByIdToJSON(@RequestParam(defaultValue = "1") int id) {
        try {
            return findById(id);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
