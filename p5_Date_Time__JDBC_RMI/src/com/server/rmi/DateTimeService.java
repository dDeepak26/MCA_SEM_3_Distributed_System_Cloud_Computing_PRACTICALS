package com.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DateTimeService extends Remote {
    String getDay() throws RemoteException;
    String getTime() throws RemoteException;
    String getDate() throws RemoteException;
}
