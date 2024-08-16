package com.client;
import com.service.*;
import com.model.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4000);
            BookService bookService = (BookService) registry.lookup("BookService");
            
            List<Book> books = bookService.getAllBooks();
            for (Book book : books) {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Book Name: " + book.getBookName());
                System.out.println("Book Author: " + book.getBookAuthor());
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.err.println("RMI Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
