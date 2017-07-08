import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Sala 
{
    int capacidad;
    int numero;
    Proyector proyector;
    String descripcion;
    
    public Sala (int capacidad, int numero, String descripcion)
    {
        this.capacidad = capacidad;
        this.numero = numero;
        this.descripcion = descripcion;
    }
    
    public Sala (int capacidad, int numero, String descripcion, Proyector proyector)
    {
        this.capacidad = capacidad;
        this.numero = numero;
        this.descripcion = descripcion;
        this.proyector = proyector;
    }

    public boolean hasProyector()
    {
    	if(this.proyector != null){
    		return true;
    	}
    	return false;
    }
    
    public void setProyector(Proyector proyector)
    {
        this.proyector=proyector;   
    }

}