import java.rmi.*;

public interface NameInterface extends Remote
{
    public String greeting(String name) throws RemoteException;
}