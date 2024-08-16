import java.io.*;
import java.net.*;
import java.util.*;

public class MessagingServer {
    private static final int PORT = 1234;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Messaging server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new thread to handle client communication
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all connected clients
    static void broadcastMessage(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }

    // Thread to handle communication with a single client
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter clientWriter;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                synchronized (clientWriters) {
                    clientWriters.add(clientWriter);
                }

                String inputLine;
                while ((inputLine = input.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    broadcastMessage(inputLine);
                }

                synchronized (clientWriters) {
                    clientWriters.remove(clientWriter);
                }

                input.close();
                clientWriter.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
