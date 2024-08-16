package com.service;
import com.model.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookService extends Remote {
    List<Book> getAllBooks() throws RemoteException;
}
