package com.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import com.model.Employee;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {
    private static final long serialVersionUID = 1L;
    private Connection conn;

    public EmployeeServiceImpl() throws RemoteException {
        super();
        try {
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties");
            props.load(input);
            
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RemoteException("Database connection failed", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getDouble("salary")
                );
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RemoteException("Error fetching employees", e);
        }
        return employees;
    }
}