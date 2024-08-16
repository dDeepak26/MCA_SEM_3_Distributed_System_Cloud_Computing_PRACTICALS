package com.server;

import com.service.BillService;
import com.service.BillServiceImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            BillService billService = new BillServiceImpl();
            Registry registry = LocateRegistry.createRegistry(4000);
            registry.rebind("BillService", billService);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.err.println("RMI Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
