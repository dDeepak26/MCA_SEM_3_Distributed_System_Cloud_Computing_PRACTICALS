package com.server.dao;

import java.sql.*;

public class DateTimeDAOImpl implements DateTimeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/datetime_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    @Override
    public String getDay() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DAYNAME(NOW()) as day")) {
            if (rs.next()) {
                return rs.getString("day");
            }
        }
        return null;
    }

    @Override
    public String getTime() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TIME(NOW()) as time")) {
            if (rs.next()) {
                return rs.getString("time");
            }
        }
        return null;
    }

    @Override
    public String getDate() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DATE(NOW()) as date")) {
            if (rs.next()) {
                return rs.getString("date");
            }
        }
        return null;
    }
}
