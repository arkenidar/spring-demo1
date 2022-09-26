package com.example.demo.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer {
    private final long id;
    private final String name;
    private final int age;
    private final LocalDateTime created_date;

    public Customer(long id, String name, int age, LocalDateTime created_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.created_date = created_date;
    }

    static Customer fromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getTimestamp("created_date").toLocalDateTime()
        );
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", created_date=" + created_date +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }
}
