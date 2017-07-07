/**
 * Clase Sala
 * 
 * Clase manipuladora de la informacion de una sala la cual a su vez contiene la informacion
 * de la clase proyector, utilizandola de distinta manera.
 * 
 * Modificaciones: se a�adieron los metodos= getNumero,getDescripcion,getCapacidad y removerProyector. by Seba
 * 
 * @author Sebastian Valenzuela
 * @version 0.1
 * @since 0.1.2
 * 
 */
public class Sala 
{
    /**
     * Entero numerico que almacena la capacidad maxima de alumnos de una sala
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    int capacidad;
    
    /**
     * Entero numerico que almacena el numero descriptor de la sala
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    int numero;
    
    /**
     * En la Clase sala se contiene un proyector cualquiera de la clase Proyector, 
     * nombrandose aqui
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    Proyector proyector=null;
    
    /**
     * String encargado de guardar la informacion escencial de la sala
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    String descripcion;
    
    /**
     * Constructor de la clase Sala, inicializando con valores obtenidos cada parametro.
     * 
     * Crea una Sala que no contiene un Proyector.
     * 
     * @param capacidad Capacidad maxima de la Sala.
     * @param numero Numero distintivo de la Sala.
     * @param descripcion Descripcion principal de la Sala.
     * 
     * @author Sebastian Valenzuela.
     * @version 0.1
     * @since 0.1
     */
    public Sala (int capacidad, int numero, String descripcion)
    {
        this.capacidad = capacidad;
        this.numero = numero;
        this.descripcion = descripcion;
    }
    
    /**
     * Constructor de la clase Sala, inicializando con valores obtenidos cada parametro.
     * 
     * Crea una Sala que contiene un Proyector.
     * 
     * @param capacidad Capacidad maxima de la Sala.
     * @param numero Numero distintivo de la Sala.
     * @param descripcion Descripcion principal de la Sala.
     * @param proyector Proyector asignado a la Sala.
     * 
     * @author Sebastian Valenzuela.
     * @version 0.1
     * @since 0.1
     */
    public Sala (int capacidad, int numero, String descripcion, Proyector proyector)
    {
        this.capacidad = capacidad;
        this.numero = numero;
        this.descripcion = descripcion;
        this.proyector = proyector;
    }

    /**
     * Metodo booleano hasProyector que retorna el si posee o no un
     * proyector.
     * 
     * @return true or false: Segun la asignacion del proyector.
     * 
     * @author Sebastian Valenzuela.
     * @since 0.1
     */
    public boolean hasProyector()
    {
        if(this.proyector != null){
            return true;
        }
        return false;
    }
    
    /**
     * Metodo getNumero que mostrara el numero identificador de la sala
     * 
     * @return numero verificador
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */ 
    public int getNumero()
    {
        return numero;
    }
    
    /**
     * Metodo getDescripcion que mostrara la descripcion de la sala
     * 
     * @return descripcion de la sala
     * 
     * @author Sebastian Valenzuela
     * @since 0.1.2
     */ 
    public String getDescripcion()
    {
        return descripcion;    
        
    }
        
    /**
     * Metodo getCapacidad que mostrara la capacidad de la sala
     * 
     * @return capacidad de la sala
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */ 
    public int getCapacidad()
    {
        return capacidad;    
    }
    
    /**
     * Metodo modificador del Proyector asignado.
     * 
     * @param proyector : nuevo proyector asignado.
     * 
     * @author Sebastian Valenzuela.
     * @since 0.1
     */
    public void setProyector(Proyector proyector)
    {
    	if(this.proyector!=null){
    		if (proyector.isAvaliable()) {
				this.proyector = proyector;
				proyector.setAvaliable(false);
			}
    	}
    	else{
    		removeProyector();
    	}
    }
    
    /**
     * Metodo removerProyector que elimina el proyector almacenado
     * 
     * @param proyector ahora vacio o eliminado
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void removerProyector()//nuevo agregado
    {
        this.proyector=null;
        return;
    }
    
    /**
     * Metodo setter de miembro descripccion
     * 
     * @param descripcion el valor nuevo
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public void setDescripcion(String descripcion){
    	this.descripcion = descripcion;
    }
    
    /**
     * Metodo getLongDescription, el cual imprime una descripcion detallada
     * de los valores de la instancia del objeto sala.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1.2
     */
    public void getLongDescription(){
    	System.out.println("Detalles: ");
    	System.out.println("Capacidad: " + this.capacidad);
    	System.out.println("Numero asignado: " + this.numero);
    	System.out.println("Descripcion: " + this.descripcion);
    	System.out.print("Tiene proyector?: ");
    	if(this.hasProyector()){
    		System.out.println("si.");
    	}
    	else{
    		System.out.println("No.");
    	}
    	System.out.println();
    }
    
    /**
     * metodo remove proyector, el cual remueve el proyector de la sala y lo vuelve a 
     * habilitar para su uso
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1.2
     */
    public void removeProyector(){
    	if(hasProyector()){
    		this.proyector.setAvaliable(true);
    		this.proyector = null;
    	}
    	else{
    		System.out.println("No hay proyectores disponibles.");
    	}
    }

	public Proyector getProyector() {
		return this.proyector;
	}
}