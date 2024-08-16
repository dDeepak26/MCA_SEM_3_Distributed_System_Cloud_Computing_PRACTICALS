package com.server;

import com.server.rmi.DateTimeService;
import com.server.rmi.DateTimeServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) {
		try {
            DateTimeService dateTimeService = new DateTimeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(4000);
            registry.rebind("DateTimeService", dateTimeService);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

}
