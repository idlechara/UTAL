package ej_1;

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
}
