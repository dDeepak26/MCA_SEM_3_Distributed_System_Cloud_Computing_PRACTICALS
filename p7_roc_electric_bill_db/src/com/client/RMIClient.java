package com.client;

import com.model.Bill;
import com.service.BillService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            BillService billService = (BillService) registry.lookup("BillService");
            
            List<Bill> bills = billService.getAllBills();
            for (Bill bill : bills) {
                System.out.println("Consumer Name: " + bill.getConsumerName());
                System.out.println("Bill Due Date: " + bill.getBillDueDate());
                System.out.println("Bill Amount: " + bill.getBillAmount());
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("RMI Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
