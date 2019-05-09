package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCountOfEmployeeForDepartmentQuery extends AbstractSelectQuery<Integer> {
    private static final String SQL_QUERY = "SELECT count(l.id) count_employee\n " +
            "FROM department d\n " +
            "JOIN lector_department ld ON d.id = ld.department\n " +
            "JOIN lector l ON l.id = ld.lector\n " +
            "WHERE 1=1 ";
    private String departmentName;

    public GetCountOfEmployeeForDepartmentQuery(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    protected String getSQLQuery() {

        return departmentName.isEmpty()
                ? SQL_QUERY
                : SQL_QUERY + " AND d.name = '" + departmentName + "'";
    }

    @Override
    protected Integer parseResultSet(ResultSet rs) throws SQLException {
        return rs.getInt("count_employee");
    }
}
