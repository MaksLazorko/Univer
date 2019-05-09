package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetHeadOfDepartmentQuery extends AbstractSelectQuery<String> {
    private static final String SQL_QUERY = "SELECT l.name \n" +
            "FROM department d\n" +
            "JOIN lector l ON l.id = d.head\n" +
            "WHERE 1=1";
    private String departmentName;

    public GetHeadOfDepartmentQuery(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    protected String getSQLQuery() {

        return departmentName.isEmpty()
                ? SQL_QUERY
                : SQL_QUERY + " AND d.name = '" + departmentName + "'";
    }

    @Override
    protected String parseResultSet(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }
}
