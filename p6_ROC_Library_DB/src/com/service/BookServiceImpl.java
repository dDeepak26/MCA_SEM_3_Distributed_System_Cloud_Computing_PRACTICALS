package com.service;
import com.model.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl extends UnicastRemoteObject implements BookService {
	private static final long serialVersionUID = 1L;

	public BookServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Book> getAllBooks() throws RemoteException {
        List<Book> books = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Library";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Book")) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("BookID"));
                book.setBookName(rs.getString("BookName"));
                book.setBookAuthor(rs.getString("BookAuthor"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RemoteException("Error retrieving books", e);
        }

        return books;
    }
}
