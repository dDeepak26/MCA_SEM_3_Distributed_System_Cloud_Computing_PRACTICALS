import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost"; // Server address
        int serverPort = 12345; // Server port

        try (
            // Connect to server
            Socket socket = new Socket(serverAddress, serverPort);
            // Input stream to read from server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output stream to send data to server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            // Read user input from console
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server. Enter a number or 'exit' to quit.");

            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send user input to server
                output.println(userInput);

                // Receive server's response (square of the number)
                String response = input.readLine();
                System.out.println("Square of the Number: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
