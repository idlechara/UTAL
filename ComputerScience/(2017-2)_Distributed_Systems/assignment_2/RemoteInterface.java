import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteInterface extends java.rmi.Remote {
    public ArrayList<Integer> ulam(Integer target) throws  RemoteException;
}