import java.rmi.*;  
import java.rmi.registry.*; 
public class Client {
    public static void main(String[] args) {
        try {
            NameInterface stub = (NameInterface)Naming.lookup("rmi://localhost:4000/dd");
            System.out.println(stub.greeting("Deepak"));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
