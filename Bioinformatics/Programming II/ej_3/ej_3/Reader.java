package ej_3;

import java.io.*;

public class Reader {
	String content;
	BufferedReader reader;

	public Reader(){
		this.content = "";
		InputStreamReader isr = new InputStreamReader(System.in); 
		this.reader = new BufferedReader(isr);
	}
	
	public Reader(String filepath) throws FileNotFoundException{
		this.content = "";
		FileReader fr = new FileReader(filepath);
		this.reader = new BufferedReader(fr);
	}
	
	public char readChar() throws IOException{
		return ((char)this.reader.read());
	}

	public String readString() throws IOException {
		return (reader.readLine());
	}

	public int readInt() throws IOException{
		int valor;
		String valor_s;
		valor_s = this.reader.readLine();
		valor = Integer.parseInt(valor_s);
		return valor;
	}
	
	public double readDouble() throws IOException{
		double valor;
		String valor_s;
		valor_s = this.reader.readLine();
		valor = Double.parseDouble(valor_s);
		return valor;
	}
}
