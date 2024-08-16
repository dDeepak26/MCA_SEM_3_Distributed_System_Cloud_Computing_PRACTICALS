import java.rmi.*;  
import java.rmi.server.*;  
public class ImplNameInterface extends UnicastRemoteObject implements NameInterface{
    ImplNameInterface() throws RemoteException {
        super();
    }

    public String greeting(String name) throws RemoteException {
        return "Hello " + name + " this is RMI Example";
    }
}
