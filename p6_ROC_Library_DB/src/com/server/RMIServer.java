package com.server;
import com.service.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            BookService bookService = new BookServiceImpl();
            Registry registry = LocateRegistry.createRegistry(4000);
            registry.rebind("BookService", bookService);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.err.println("RMI Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
