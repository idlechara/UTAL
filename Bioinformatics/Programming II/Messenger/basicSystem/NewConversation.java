package basicSystem;
import java.net.*;

public class NewConversation implements Runnable {
	Socket Connection;
	
	public NewConversation(Socket Connect){
		this.Connection = Connect;
	}
	public void run() {
		// TODO Auto-generated method stub

	}

}
