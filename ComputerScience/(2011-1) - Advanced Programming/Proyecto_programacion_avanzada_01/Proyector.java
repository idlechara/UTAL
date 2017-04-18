import java.util.ArrayList;
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
 * Modificaciones: se a�adieron= getNombre y getDescripcion. parentesis(verificar los gets, quizas se me aya pasado uno xD) by seba
 * 
 * @author Sebastian Valenzuela
 * @version 0.1.3
 * @since 0.1
 */
public class Proyector 
{
	/**
	 * Booleano que marca la disponiblilidad de un proyector
	 * 
	 * @author Erik Andres Regla Torres
	 * @version 0.1
	 * @since 0.1.3
	 */
	private boolean avaliable;

	String pordefecto;
    /**
     * String que almacena la marca del proyector.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    String marca;
    
    /**
     * String que almacena la marca del proyector.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    String modelo;
    
    /**
     * String que almacena el modelo del proyector.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1 
     * @since 0.1
     */
    String descripcion;
    
    /**
     * ArrayList almacenador de todas las resoluciones de un proyector especifico.
     * 
     * @author Sebastian Valenzuela
     * @version 0.2
     * @since 0.1
     */
    ArrayList <String> resolucionesPermitidas=new ArrayList<String>();
    
    /**
     * tiempoDisponibilidad enlazado a la clase Tiempo con el fin de verificar si se encuentra
     * disponible o no.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    Tiempo tiempoDisponibilidad;
    
   /**
    * Constructor de la clase Proyector, que inicializa todos los valores.
    * 
    * Crea un Proyector por defecto con informacion basica.
    * 
    * Se modifico el estilo de almacenamiento de las resoluciones del proyector.
    * 
    * @param marca El nombre de la marca del proyector.
    * @param modelo El nombre del modelo del proyector.
    * @param descripcion La descripcion del proyector.
    * @param resolucionesPermitidas Contenedora de las resoluciones permitidas del 
    * proyector, sera un ArrayList de String.
    * 
    * @author Sebastian Valenzuela.
    * @version 0.2
    * @since 0.1
    */

    public Proyector()
    {
         this.pordefecto="proyector por defecto";
         this.marca="Generica";
         this.modelo="Generico";
         this.descripcion="Sirve con entradas VGA y DVI";
         resolucionesPermitidas.add("160x160");
         resolucionesPermitidas.add("320x320");
         resolucionesPermitidas.add("640x480");
         resolucionesPermitidas.add("800x600");
         resolucionesPermitidas.add("1024x768");
         resolucionesPermitidas.add("1152x864");
         resolucionesPermitidas.add("1366x768");
         resolucionesPermitidas.add("1600x1200");
         resolucionesPermitidas.add("1900x1400");
         
    }
    
    public Proyector(String marca, String modelo, String descripcion)
    {
         this.pordefecto="proyector que NO es por defecto";
         this.marca=marca;
         this.modelo=modelo;
         this.descripcion=descripcion;
         resolucionesPermitidas.add("320x240");
         resolucionesPermitidas.add("640x480");
         resolucionesPermitidas.add("800x600");
         resolucionesPermitidas.add("1024x768");
         resolucionesPermitidas.add("1152x864");
         resolucionesPermitidas.add("1366x768");
         resolucionesPermitidas.add("1600x900");
         resolucionesPermitidas.add("1600x1200");
         resolucionesPermitidas.add("1900x1400");
         
    }
  
    /**
     * Metodo getNombre que mostrara el nombre pro defecto del proyector
     * 
     * @return pordefecto el nombre del proyector
     * 
     * @author Sebastian Valenzuela.
     * @version 0.1
     * @since 0.1
     */
    public String getNombre()
    {
        return pordefecto;    
    }
    
    /**
     * Metodo getDescripcion que mostrara la descripcion del proyector
     * 
     * @return descripcion del proyector
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Metodo modificador de la descripcion del proyector.
     * 
     * @param descripcion :la descripcion del proyector.
     * 
     * @author Sebastian Valenzuela.
     * @version 0.1
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
     * @return tiempoDisponibilidad :disponibilidad del proyector
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */        
    public Tiempo getDisponibilidad()
    {
        return tiempoDisponibilidad;
    }
    
    /** 
     * Metodo setDisponibilidad que verifica si el proyector correspondiente esta
     * o no disponible  en un momento.
     * 
     * El parche aplicado es la solucion al error en caso de existir o de lo 
     * contrario retornara correctamente y prosegira a finalizar el metodo.
     * Se cambia el retorno booleano a no retornar, boolean ->void.
     * 
     * @param tiempoDisponibilidad :tiempo disponible del proyector
     * @throws UnsupportedOperationException 
     * @author Sebastian Valenzuela
     * @version 0.1.2
     * @return 
     * @since 0.1
     */
    public void setDisponibilidad(Tiempo tiempoDisponibilidad) throws UnsupportedOperationException
    {
        if(!tiempoDisponibilidad.isEmpty())
        {
            this.tiempoDisponibilidad = tiempoDisponibilidad;           
        }
        else 
        {
            throw new UnsupportedOperationException("El tiempo es invalido, prueba intentendando otra vez");
        }
    }
    
    /**
     * Metodo getMarca que retorna la marca del proyector consultado.
     * 
     * @return marca :marca del proyector
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    public String getMarca()
    {
        return marca;
    }
    
    /**
     * Metodo setMarca , modifica la marca almacenada con anterioridad a una nueva
     * 
     * @param marca :marca del proyecto que ahora es nueva.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    public void setMarca(String marca)
    {
        this.marca=marca;    
    }
    
    /**
     * Metodo getModelo, muestra el modelo del proyector consultado
     * 
     * @return modelo :modelo del proyector.
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    public String getModelo()
    {
        return modelo;
    }
    
    /**
     * Metodo setModelo, modificador del modelo del proyector guardado con anterioridad
     * 
     * @param modelo :modelo modificado del proyector
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    public void setModelo(String modelo)
    {
        this.modelo=modelo;
    }
    
    /**
     * getter de Avaliable
     * @return true si es que no esta ocupado
     * 
	 * @author Erik Andres Regla Torres
	 * @version 0.1
	 * @since 0.1.3
     */
    public boolean isAvaliable() {
		return avaliable;
	}
    
    /**
     * setea la disponiblilidad del proyector
     * @param avaliable true si esta disponible
     * 
	 * @author Erik Andres Regla Torres
	 * @version 0.1
	 * @since 0.1.3
     */
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}    
    
}
