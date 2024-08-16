package com.service;

import com.model.Bill;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl extends UnicastRemoteObject implements BillService {
	private static final long serialVersionUID = 1L;

	public BillServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Bill> getAllBills() throws RemoteException {
        List<Bill> bills = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Electric_Bill";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Bill")) {

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setConsumerName(rs.getString("consumer_name"));
                bill.setBillDueDate(rs.getDate("bill_due_date"));
                bill.setBillAmount(rs.getBigDecimal("bill_amount"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RemoteException("Error retrieving bills", e);
        }

        return bills;
    }
}
