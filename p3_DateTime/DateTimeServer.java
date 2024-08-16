import java.net.*;
import java.time.LocalDateTime;
public class DateTimeServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Deepak Durgam");
            System.out.println("Date Time Server started on port " + PORT);

            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            while (true) {
                socket.receive(receivePacket);

                // Get client address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Get current date and time
                LocalDateTime currentDateTime = LocalDateTime.now();
                String dateTimeString = currentDateTime.toString();

                // Send response to client
                byte[] sendData = dateTimeString.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);

                System.out.println("Sent date time to client: " + dateTimeString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

