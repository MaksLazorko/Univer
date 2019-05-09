package com.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllLectorsByTemplateQuery extends AbstractSelectQuery<String> {
    private static final String SQL_QUERY = "SELECT name \n " +
            "FROM lector \n" +
            "WHERE 1=1";
    private String template;

    public GetAllLectorsByTemplateQuery(String template) {
        this.template = template;
    }

    @Override
    protected String getSQLQuery() {
        return template.isEmpty()
                ? SQL_QUERY
                : SQL_QUERY + " AND name LIKE '%" + template + "%'";
    }

    @Override
    protected String parseResultSet(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }
}
