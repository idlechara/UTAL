import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Clase proyector.
 * 
 * Es necesaria la creacion de una clase contenedora de las descripciones y definiciones
 * correspondientes a un proyector cualquiera, esta es capaz de manipular informacion basica 
 * acorde a las necesidades de quien las pida.
 * 
 * Posee los metodos suficientes para obtener y modificar los datos por defecto, 
 * capaz de relacionarse con las demas clases.
 * 
 * @author Sebastian Valenzuela
 * @version 0.1
 * 
 * 
 * @since 0.1
 */
public class Proyector 
{
    /**
     * String que almacena la marca del proyector.
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    String marca;
    
    /**
     * String que almacena la marca del proyector.
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    String modelo;
    
    /**
     * String que almacena el modelo del proyector.
     * 
     * @author Sebastian Valenzuela
     * since 0.1
     */
    String descripcion;
    
    /**
     * HashSet almacenador de todas las resoluciones de un proyector espesifico.
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    HashSet <String> resolucionesPermitidas;
    
    /**
     * tiempoDisponibilidad enlazado a la clase Tiempo con el fin de verificar si se encuentra
     * disponible o no.
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    Tiempo tiempoDisponibilidad;
    
   /**
    * Constructor de la clase Proyector, que inicializa todos los valores.
    * 
    * Recibe los datos para almacenarlos en los parametros inicializados.
    * El HashSet aun no se implementara aun, se modificaran sus parametros y funcionalidad
    * despues. Aun asi quedara inicializado HashSet.
    * 
    * @param marca El nombre de la marca del proyector.
    * @param modelo El nombre del modelo del proyector.
    * @param descripcion La descripcion del proyector.
    * @param resolucionesPermitidas Contenedora de las resoluciones permitidas del 
    * proyector
    * 
    * @author Sebastian Valenzuela
    * @since 0.1
    */
    
    public Proyector(String marca,String modelo,String descripcion/*,HashSet<String>resolucionesPermitidas*/)
        {
             this.marca=marca;
             this.modelo=modelo;
             this.descripcion=descripcion;
             this.resolucionesPermitidas= new HashSet <String>();
        }
    
    /**
     * Metodo getDescripcion que retorna la descripcion almacenada en el parametro
     * descripcion, guardado con anterioridad
     * 
     * @return descripcion descripcion del proyector
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Metodo modificador de la descripcion del proyector
     * 
     * @param descripcion la descripcion del proyector
     * @author Sebastian Valenzuela 
     * @since 0.1
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion=descripcion;
    }
    
    /**
     * Metodo getDisponibilidad que retorna la disponibilidad del proyector 
     * almacenada en el parametro disponibilidad, guardado con anterioridad
     * 
     * @return tiempoDisponibilidad disponibilidad del proyector
     * @author Sebastian Valenzuela
     * @since 0.1
     */        
    public Tiempo getDisponibilidad()
    {
        return tiempoDisponibilidad;
    }
    
    /** 
     * Metodo setDisponibilidad que retorna un booleano correspondiente a si se esta
     * o no disponible el proyector en un momento. 
     * Hay que modificar este metodo para la implementacion correcta
     * 
     * @param tiempoDisponibilidad tiempo disponible del proyector
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public boolean setDisponibilidad(Tiempo tiempoDisponibilidad)
    {
    	if(!tiempoDisponibilidad.isEmpty()){
    		this.tiempoDisponibilidad = tiempoDisponibilidad;
    		return true;
    	}
    	return false;
    }
    /**
     * Metodo getMarca que retorna la marca del proyector consultado.
     * 
     * @return marca marca del proyector
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public String getMarca()
    {
        return marca;
    }
    
    /**
     * Metodo setMarca , modifica la marca almacenada con anterioridad a una nueva
     * 
     * @param marca marca del proyecto que ahora es nueva.
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void setMarca(String marca)
    {
        this.marca=marca;    
    }
    
    /**
     * Metodo getModelo, muestra el modelo del proyector consultado
     * 
     * @return modelo modelo del proyector.
     */
    public String getModelo()
    {
        return modelo;    
    }
    
    /**
     * Metodo setModelo, modificador del modelo del proyector guardado con anterioridad
     * 
     * @param modelo modelo modificado del proyector
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void setModelo(String modelo)
    {
        this.modelo=modelo;
    }
}
