package com.server.dao;

import java.sql.SQLException;

public interface DateTimeDAO {
    String getDay() throws SQLException;
    String getTime() throws SQLException;
    String getDate() throws SQLException;
}