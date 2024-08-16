package com.server.rmi;

import com.server.dao.DateTimeDAO;
import com.server.dao.DateTimeDAOImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class DateTimeServiceImpl extends UnicastRemoteObject implements DateTimeService {
    private DateTimeDAO dateTimeDAO;

    public DateTimeServiceImpl() throws RemoteException {
        super();
        this.dateTimeDAO = new DateTimeDAOImpl();
    }

    @Override
    public String getDay() throws RemoteException {
        try {
            return dateTimeDAO.getDay();
        } catch (SQLException e) {
            throw new RemoteException("Error retrieving day", e);
        }
    }

    @Override
    public String getTime() throws RemoteException {
        try {
            return dateTimeDAO.getTime();
        } catch (SQLException e) {
            throw new RemoteException("Error retrieving time", e);
        }
    }

    @Override
    public String getDate() throws RemoteException {
        try {
            return dateTimeDAO.getDate();
        } catch (SQLException e) {
            throw new RemoteException("Error retrieving date", e);
        }
    }
}
