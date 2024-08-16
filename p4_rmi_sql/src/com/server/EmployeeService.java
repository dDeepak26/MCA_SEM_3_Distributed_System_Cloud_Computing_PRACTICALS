package com.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.model.Employee;

public interface EmployeeService extends Remote {
    List<Employee> getAllEmployees() throws RemoteException;
}