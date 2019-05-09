package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class GetDegreeStatisticsForDepartmentQuery extends AbstractSelectQuery<LinkedHashMap<String, Integer>> {
    private static final String SQL_QUERY = "SELECT r.name, count(1) d_count " +
            "FROM degree r " +
            "JOIN ( " +
            "SELECT l.degree " +
            "FROM department d " +
            "JOIN lector_department ld ON d.id = ld.department " +
            "JOIN lector l ON l.id = ld.lector " +
            "WHERE 1=1";
    private String departmentName;

    public GetDegreeStatisticsForDepartmentQuery(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    protected String getSQLQuery() {
        String result = SQL_QUERY;
        if (!departmentName.isEmpty()){
            result = result + " AND d.name = '" + departmentName + "'\n ";
        }
        return result + ") s ON r.id = s.degree GROUP by r.name \n ";
    }

    @Override
    protected LinkedHashMap<String, Integer> parseResultSet(ResultSet rs) throws SQLException {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        result.put(rs.getString("name"), rs.getInt("d_count"));
        return result;
    }
}
