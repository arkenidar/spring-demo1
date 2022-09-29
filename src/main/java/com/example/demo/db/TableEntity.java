package com.example.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableEntity<T> {
    String tableName();

    T fromResultSet(ResultSet resultSet, int rowNum) throws SQLException;

    String updateSQL();

    Object[] updateParams();

    String addSQL();

    PreparedStatement addParams(PreparedStatement preparedStatement) throws SQLException;

    String deleteSQL();

    Object[] deleteParams();
}
