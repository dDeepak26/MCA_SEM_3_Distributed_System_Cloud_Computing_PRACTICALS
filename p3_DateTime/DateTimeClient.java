import java.net.*;

public class DateTimeClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            System.out.println("Deepak Durgam");
            while (true) {
                // Send request to server (empty message)
                byte[] sendData = new byte[0];
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                socket.send(sendPacket);

                // Receive response from server
                byte[] receiveBuffer = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String dateTimeString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server date time: " + dateTimeString);

                // Sleep for a while before sending another request
                Thread.sleep(2000); // Wait for 2 seconds
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
