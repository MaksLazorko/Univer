package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAverageSalaryForDepartmentQuery extends AbstractSelectQuery<Double> {
    private static final String SQL_QUERY = "SELECT ROUND(AVG(l.salary), 2) avg_salary\n " +
            "FROM department d\n " +
            "JOIN lector_department ld ON d.id = ld.department\n " +
            "JOIN lector l ON l.id = ld.lector\n " +
            "WHERE 1=1 ";
    private String departmentName;

    public GetAverageSalaryForDepartmentQuery(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    protected String getSQLQuery() {

        return departmentName.isEmpty()
                ? SQL_QUERY
                : SQL_QUERY + " AND d.name = '" + departmentName + "'";
    }

    @Override
    protected Double parseResultSet(ResultSet rs) throws SQLException {
        return rs.getDouble("avg_salary");
    }
}
