package com.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(4000);
            registry.rebind("EmployeeService", employeeService);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
