package com.example.demo.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer implements TableEntity<Customer> {
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

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return "customers";
    }

    @Override
    public String updateSQL() {
        return "UPDATE " + tableName() + " SET name=?, age=? WHERE id=? ";
    }

    @Override
    public Object[] updateParams() {
        return new Object[]{name, age, id};
    }

    @Override
    public String addSQL() {
        return "INSERT INTO " + tableName() + " (name,age) VALUES (?,?)";
    }

    @Override
    public PreparedStatement addParams(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        return preparedStatement;
    }

    @Override
    public String deleteSQL() {
        return "DELETE FROM " + tableName() + " WHERE id=? ";
    }

    @Override
    public Object[] deleteParams() {
        return new Object[]{id};
    }
}
