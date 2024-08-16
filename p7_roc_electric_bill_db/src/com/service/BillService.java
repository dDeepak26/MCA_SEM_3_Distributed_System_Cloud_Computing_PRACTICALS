package com.service;

import com.model.Bill;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BillService extends Remote {
    List<Bill> getAllBills() throws RemoteException;
}
