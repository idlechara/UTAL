package multithread;

public class NewThread implements Runnable{

	Thread T;

	NewThread(){
		this.T = new Thread(this,"thread_1");
		this.T.run();
	}

	public void run() {
		try{
			for(int i=0; i<20; i++){
				System.out.println("Hilo hijo corre: " + i );
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e){
			System.out.println("El hilo hijo ha sido interrumpido");
		}
		System.out.println("Fin del Thread");
	}
}