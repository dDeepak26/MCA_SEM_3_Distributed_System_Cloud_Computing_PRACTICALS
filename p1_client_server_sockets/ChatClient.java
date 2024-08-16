import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            new Thread(new ReceiveMessagesHandler(socket)).start();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = inputReader.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            System.out.println("Error in client: " + e.getMessage());
        }
    }
    private static class ReceiveMessagesHandler implements Runnable {
        private Socket socket;

        public ReceiveMessagesHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.out.println("Error in receive handler: " + e.getMessage());
            }
        }
    }
}

