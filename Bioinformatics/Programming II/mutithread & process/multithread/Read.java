package multithread;

import java.io.*;
/*
 * Sin comentarios, es obio para que sirve xD
 */
public class Read {
	BufferedReader BR;
	
	Read(){
		InputStreamReader isr = new InputStreamReader(System.in);
		this.BR = new BufferedReader(isr);
	}
	
	public String string() throws IOException{
		return (this.BR.readLine());
	}
}
