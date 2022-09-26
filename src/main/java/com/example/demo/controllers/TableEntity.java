package com.example.demo.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TableEntity<T> {
    abstract String tableName();

    abstract T fromResultSet(ResultSet resultSet, int rowNum) throws SQLException;
}
