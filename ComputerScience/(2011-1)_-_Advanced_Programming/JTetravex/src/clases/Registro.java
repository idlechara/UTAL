package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
* Clase Registro
* Su funcion es controlar el registro del jugador el puntaje que obtiene en una partida
*/
public class Registro implements Serializable
{
    private ArrayList<String> nombres;
    private ArrayList<Integer> puntajes;
    private File url;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Registro
     * @param nombres del tipo ArrayList es un arreglo que almacena los nombres de los jugadores
     * @param puntajes del tipo ArrayList es un arreglo que almacena los puntajes de los jugadores
     * @param url del tipo File almacena los nombres y puntajes en un archivo de texto (.txt)
     */
    public Registro()
    {
        this.nombres = new ArrayList<String>();
        this.puntajes = new ArrayList<Integer>();
        this.url = new File("Archivo.txt");
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo nombres
     * @return nombres El metodo retorna un arreglo donde se almacenan los nombres
     */
    public ArrayList<String> getNombres()
    {
        return this.nombres;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo puntajes
     * @return puntajes El metodo retorna un arreglo donde se almacenan los puntajes
     */
    public ArrayList<Integer> getPuntajes()
    {
        return this.puntajes;
    }
    
    /**
     * Metodo que verifica el archivo donde se almacenan los puntajes
     * @return null
     */
    public Registro abrir()
    {
        try
        {
            if(this.url.exists())
            {
                Registro registro;
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(url));
                registro = (Registro) in.readObject();
                in.close();
                return registro;
            }
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo que guarda los puntajes del juego en un archivo de texto
     */
    public void guardar()
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.url));
            out.writeObject(this);
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo que ordena los nombres de los jugadores y sus puntajes de manera ordenada
     * @param segundos del tipo int almacena los segundos que transucurren en la partida
     * @param nombre del tipo String almacena el nombre del jugador de la partida
     */
    public void insertarOrdenado(int segundos, String nombre)
    {
        nombres.add(nombre);
        puntajes.add(segundos);

        int k = 0;
        int n = nombres.size();
        int t = puntajes.get(0);
        int j = 0;
        String m = nombres.get(0);

        while(k > n)
        {
            t = puntajes.get(k);
            m = nombres.get(k);
            
            for(j = k; j > 0 && puntajes.get(j-1) < t; --j)
            {
                puntajes.set(j, puntajes.get(j-1));
                nombres.set(j, nombres.get(j-1));
            }

            puntajes.set(j, t);
            nombres.set(j, m);
            k++;
        }

        if(nombres.size() == 11)
        {
            nombres.remove(10);
            puntajes.remove(10);
        }
    }
}