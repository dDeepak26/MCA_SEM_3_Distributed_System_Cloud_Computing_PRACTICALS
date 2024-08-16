package com.client;

import com.server.rmi.DateTimeService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String[] args) {
    	try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            DateTimeService dateTimeService = (DateTimeService) registry.lookup("DateTimeService");

            String day = dateTimeService.getDay();
            String time = dateTimeService.getTime();
            String date = dateTimeService.getDate();

            System.out.println("Server Day: " + day);
            System.out.println("Server Time: " + time);
            System.out.println("Server Date: " + date);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }    }
}

