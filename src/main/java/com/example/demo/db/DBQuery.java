package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Component
public class DBQuery<T extends TableEntity<T>> {
    private final JdbcTemplate jdbcTemplate;
    private T instance;

    @Autowired
    DBQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public List<T> findAll() {

        String sql = "SELECT * FROM " + instance.tableName();

        return jdbcTemplate.query(
                sql,
                instance::fromResultSet
        );
    }

    public T findById(long id) throws EmptyResultDataAccessException { // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
        String sql = "SELECT * FROM " + instance.tableName() + " WHERE id=?";
        return jdbcTemplate.queryForObject(
                sql,
                instance::fromResultSet,
                id
        );
    }

    public void update(T object) {
        String sql = object.updateSQL();
        Object[] params = object.updateParams();
        jdbcTemplate.update(sql, params);
    }

    public long add(T object) throws SQLException {
        return updateWithKeys(object::addSQL, object::addParams);
    }

    private long updateWithKeys(operationSQL addSQL, operationParams addParams) throws SQLException {
        String sql = addSQL.get();

        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement = addParams.get(preparedStatement);

        preparedStatement.executeUpdate();

        ResultSet keys = preparedStatement.getGeneratedKeys();
        keys.next();
        return keys.getLong(1);
    }

    interface operationSQL {
        String get();
    }

    interface operationParams {
        PreparedStatement get(PreparedStatement params) throws SQLException;
    }

    public void delete(T object) {
        String sql = object.deleteSQL();
        Object[] params = object.deleteParams();
        jdbcTemplate.update(sql, params);
    }

}
