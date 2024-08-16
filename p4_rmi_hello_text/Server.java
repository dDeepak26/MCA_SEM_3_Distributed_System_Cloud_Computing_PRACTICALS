import java.rmi.*;  
import java.rmi.registry.*; 
public class Server {
    public static void main(String[] args) {
        try {
            NameInterface stub = new ImplNameInterface();
            Registry registry = LocateRegistry.createRegistry(4000);
            registry.rebind("dd", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
