package com.example.demo.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer implements TableEntity<Customer> {
    // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
    public long id;
    public String name;
    public int age;
    public LocalDateTime created_date;

    public Customer() {
    }

    public Customer(long id, String name, int age, LocalDateTime created_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.created_date = created_date;
    }

    public DBQuery<Customer> setupDBQuery(JdbcTemplate jdbcTemplate) {
        DBQuery<Customer> dbQuery = new DBQuery<>(jdbcTemplate);
        dbQuery.setInstance(this);
        return dbQuery;
    }

    @Override
    public Customer fromResultSet(ResultSet resultSet, int rowNum) throws SQLException {
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

    @Override
    public String tableName() {
        return " \"Customer\" ";
    }
}
