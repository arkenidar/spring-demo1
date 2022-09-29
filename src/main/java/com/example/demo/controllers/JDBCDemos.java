package com.example.demo.controllers;

import com.example.demo.db.Customer;
import com.example.demo.db.DBQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
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

    @GetMapping(value = "customers.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findAllToText() {
        return customerDBQuery.findAll().stream().map(Customer::toString).collect(Collectors.joining("\n"));
    }

    @GetMapping(value = "customers.json")
    public List<Customer> findAllToJSON() {
        return customerDBQuery.findAll();
    }

    @GetMapping(value = "customer_find.json")
    public Customer findByIdToJSON(@RequestParam long param_id) {
        try {
            return customerDBQuery.findById(param_id);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    @RequestMapping("customer_update.json")
    public Customer updateCustomer(@RequestParam(defaultValue = "1") long id) {
        Customer customer = customerDBQuery.findById(id);
        customer.age++; // for Testing
        customerDBQuery.update(customer);
        return customer;
    }

    @RequestMapping("customer_update.html")
    public ModelAndView customerUpdateHtml(
            @ModelAttribute Customer customer
            , @RequestParam(defaultValue = "-1") long param_id
            , @RequestParam(defaultValue = "update") String action
    ) throws SQLException {
        if (action.equals("update") && customer.id != 0)
            customerDBQuery.update(customer);
        else if (action.equals("add") && customer.id == 0 && param_id == -1)
            param_id = customerDBQuery.add(customer);

        if (param_id != -1 && (action.equals("update") || action.equals("add")))
            customer = customerDBQuery.findById(param_id);

        ModelAndView modelAndView = new ModelAndView("customer_thymeleaf");
        modelAndView.addObject("html_title", "customer update");
        modelAndView.addObject("action", "update");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping("customer_add.html")
    public ModelAndView customerAdd() {
        ModelAndView modelAndView = new ModelAndView("customer_thymeleaf");
        modelAndView.addObject("html_title", "customer add");
        modelAndView.addObject("action", "add");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @RequestMapping("customer_list.html")
    public ModelAndView listCustomersHtml(@RequestParam(defaultValue = "-1") long param_id) {
        try {
            if (param_id != -1)
                customerDBQuery.delete(customerDBQuery.findById(param_id));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
        }
        ModelAndView modelAndView = new ModelAndView("customer_freemarker");
        modelAndView.addObject("html_title", "customers");
        modelAndView.addObject("customers", customerDBQuery.findAll());
        return modelAndView;
    }
}
