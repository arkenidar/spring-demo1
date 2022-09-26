package com.example.demo.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DBQuery<T extends TableEntity<T>> {
    private final JdbcTemplate jdbcTemplate; // https://spring.io/guides/gs/relational-data-access/
    private final T instance;

    public DBQuery(JdbcTemplate jdbcTemplate, T instance) {
        this.jdbcTemplate = jdbcTemplate;
        this.instance = instance;
    }

    public List<T> findAll() { // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/

        String sql = "SELECT * FROM " + instance.tableName();

        return jdbcTemplate.query( // or queryForObject()
                sql,
                instance::fromResultSet
        );
    }

    public T findById(int id) { // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/

        String sql = "SELECT * FROM " + instance.tableName() + " WHERE id=?";

        return jdbcTemplate.queryForObject( // or query()
                sql,
                instance::fromResultSet,
                id
        );
    }
}
