/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package day_of_sagitarius;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.*;
/**
 *
 * @author kuroneko
 */
public class netInfo {
    Socket conexion;
    ServerSocket servidor;
    String ip;
    int port;
    int type;
    BufferedReader input;
    PrintWriter output;
    ObjectInputStream ob_in;
    ObjectOutputStream ob_out;

    public netInfo(){
        this.ip = null;
        this.port = 0;
        this.type = 0;
        this.conexion = null;
    }

    public netInfo(String ip, int port) throws UnknownHostException, IOException{
        this.ip = ip;
        this.port = port;
        this.type = 0;
        this.conexion = new Socket(ip,port);
    }

    public netInfo(int port) throws IOException{
        this.port = port;
        this.type=1;
        this.servidor = new ServerSocket(port);
    }

    public void disconnect() throws IOException{
        this.input.close();
        this.output.close();
        this.ob_in.close();
        this.ob_out.close();
        this.conexion.close();
        if(this.type==0){
            this.servidor.close();
        }
    }

    public void sendPlayer(Jugador player) throws IOException{
        this.ob_out.writeObject(player);
        this.ob_out.flush();
    }

    public Jugador getPlayer() throws IOException, ClassNotFoundException{
        return ((Jugador)this.ob_in.readObject());
    }

    public void connect() throws IOException{
        if(this.type==1){
            this.conexion = this.servidor.accept();
        }
        this.ob_in = new ObjectInputStream(conexion.getInputStream());
        this.ob_out = new ObjectOutputStream(conexion.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(this.conexion.getInputStream()));
        this.output = new PrintWriter(this.conexion.getOutputStream());
    }
}
