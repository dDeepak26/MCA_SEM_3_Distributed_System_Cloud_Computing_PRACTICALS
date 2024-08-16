import java.io.*;
import java.net.*;

public class MultiClientServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket);

            // Create a new thread to handle client communication
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();
        }
    }
}

class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Input stream to read from client
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Output stream to write to client
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                try {
                    // Convert received string to integer
                    int number = Integer.parseInt(inputLine);
                    // Calculate square of the number
                    int square = number * number;
                    // Send the square back to client
                    output.println(square);
                } catch (NumberFormatException e) {
                    // If input is not a number, handle the exception
                    output.println("Invalid input. Please enter a valid number.");
                }
            }

            // Close streams and socket
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
