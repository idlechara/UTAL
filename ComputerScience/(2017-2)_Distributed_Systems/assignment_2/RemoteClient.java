import java.util.ArrayList;

public class RemoteClient{
    private RemoteClient(){

    };

    public static void main(String[] args){
        try{
            RemoteInterface ri = (RemoteInterface)java.rmi.Naming.lookup("rmi://" + args[0] + ":" + args[1] + "/ulam");
            ArrayList result =  ri.ulam(10);
            System.out.print("Longitud secuencia: " + result.size() + "\n Secuencia: {");
            for(int i = 0; i<result.size(); i++){
                System.out.print(result.get(i));
                if(i < result.size() - 1){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
            
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}