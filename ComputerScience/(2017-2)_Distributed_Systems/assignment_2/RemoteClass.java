import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteClass extends java.rmi.server.UnicastRemoteObject implements RemoteInterface{

    public RemoteClass() throws RemoteException{
        super();
    }

    /**
     * This problem is also known as 3n+1
     */
    public ArrayList<Integer> ulam(Integer target) throws RemoteException{
        System.out.println("Executed ulam(" + target + ")");
        ArrayList result = new ArrayList<Integer>();
        result.add(target);
        if(target <= 0) throw new RemoteException("N <= 0");
        while(target != 1){
            if((target % 2) == 0){
                target = target / 2;
            }
            else{
                target = (target * 3) + 1;
            }
            result.add(target);
        }
        return result;
    }

    public static void main(String[] args){
        try{
            RemoteInterface ri = new RemoteClass();
            java.rmi.Naming.rebind("rmi://" + java.net.InetAddress.getLocalHost().getHostAddress() +":" + args[0] + "/ulam", ri);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}