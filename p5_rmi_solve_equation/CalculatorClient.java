import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
 
public class CalculatorClient { 
    public static void main(String[] args) { 
        try { 
            Calculator c = (Calculator) Naming.lookup("rmi://localhost/CalculatorService"); 
                 int a = 10, b = 5;
            System.out.println("RMI Calculator");
            System.out.println("Addition of 10 and 5: " + c.add(a, b)); 
            System.out.println("Subtraction of 10 and 5: " + c.sub(a, b)); 
            System.out.println("Multiplication of 10 and 5: " + c.mul(a, b) ); 
            System.out.println( "Division of 10 and 5:" + c.div(a, b) ); 
        } 
        catch (MalformedURLException murle) { 
            System.out.println(); 
            System.out.println("MalformedURLException"); 
            System.out.println(murle); 
        } 
        catch (RemoteException re) { 
            System.out.println(); 
            System.out.println( "RemoteException"); 
            System.out.println(re); 
        } 
        catch (NotBoundException nbe) { 
            System.out.println(); 
            System.out.println("NotBoundException"); 
            System.out.println(nbe); 
        } 
        catch (java.lang.ArithmeticException ae) { 
            System.out.println(); 
            System.out.println( "java.lang.ArithmeticException"); 
            System.out.println(ae); 
        } 
    } 
} 

