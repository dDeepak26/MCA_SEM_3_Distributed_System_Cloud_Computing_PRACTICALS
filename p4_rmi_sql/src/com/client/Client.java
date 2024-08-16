package com.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import com.server.EmployeeService;
import com.model.Employee;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            EmployeeService employeeService = (EmployeeService) registry.lookup("EmployeeService");
            
            List<Employee> employees = employeeService.getAllEmployees();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
