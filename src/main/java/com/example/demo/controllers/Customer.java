package com.example.demo.controllers;

import java.time.LocalDateTime;

public class Customer {
    private long id;
    private String name;
    private int age;
    private LocalDateTime created_date;

    public Customer(long id, String name, int age, LocalDateTime created_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.created_date = created_date;
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
