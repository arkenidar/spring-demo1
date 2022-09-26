package com.example.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableEntity<T> {
    String tableName();

    T fromResultSet(ResultSet resultSet, int rowNum) throws SQLException;
}
