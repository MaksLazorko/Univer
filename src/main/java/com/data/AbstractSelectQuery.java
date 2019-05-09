package com.data;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSelectQuery<T> {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/univer?useSSL=false"
            + "&useSSL=false"
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "123456";


    protected abstract String getSQLQuery();

    protected abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    public List<T> execute() {
        List<T> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(getSQLQuery());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(this.parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(statement);
        }
        return result;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void close(AutoCloseable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
