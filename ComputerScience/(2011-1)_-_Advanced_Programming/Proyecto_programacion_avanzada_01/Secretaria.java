import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Clase secretaria.
 * 
 * Uno de los requerimientos del proyecto era implementar una 
 * clase secretaria, de modo que esta hiciera las gestiones para asi
 * poder realizar las gestiones y encontrar un proyector.
 * 
 * Esta secretaria aloja una lista de proyectores, y salas
 * administradads, posee un encargado y pertenece a un escuela;
 * a su vez tiene las capacidades de reservar proyectores,
 * verificar si posee el control sobre una determinada sala,
 * cambiar su encargado, a?adir y quitar de su control 
 * proyectores y salas.
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1
 * @since 0.1
 */
public class Secretaria{

    //(non-javadoc)
    //miembros de datos
    private ArrayList<Proyector> listaProyectores;
    
    //motivo: comodidad del metodo de impresion!
    private ArrayList<Sala> listaSalas;
    private String escuela, encargado;
    
    /**
     * Constructor de la clase Sercretaria. Inicializa los valores
     * por defecto de la instancia.
     * 
     * El metodo de operacion es simalar a las clases discutidas con
     * anterioridad en la documentacion del programa, la cual recibe los
     * parametros para inicializar los miembros de datos que sean posibles
     * y los de tipo coleccion como los {@link ArrayList} o los {@link HashMap}
     * son creados vacios.
     * 
     * La idea es la misma que en un caso real, en el cual se parte de la nada, y
     * se comienzan a agregar cosas deacuerdo al caso y la necesidad.
     * 
     * @param escuela El nombre de la escuela a la que pertenece la secretaria
     * @param encargado El nombre de/la encargado/a de la escuela
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
    */
    public Secretaria(String escuela, String encargado){
        this.escuela = escuela;
        this.encargado = encargado;
        this.listaProyectores = new ArrayList<Proyector>();
        this.listaSalas = new ArrayList<Sala>();
    }
    
    /**
     * Metodo reservarProyector, el cual realiza un manejo del tiempo
     * y de las listas de las que tiene control, para entregar un proyector
     * al usuario, o mejor dicho, si es que el usuario puede terner
     * el proyector o no.
     * 
     * El metodo de operacion varia un poco aca al de el resto de las
     * clases dise?adas. Primero se verifica que existan proyectores disponibles,
     * luego de validar eso, se valida el tiempo, intentando realizar una asignacion
     * en el espacio de tiempo dado. Si esta asignacion es realizada, entonces el
     * proyector le es entregado al usuario.
     * 
     * Para poder verificar esto, se recurre a un ciclo, el cual explora los
     * proyectores disponibles, hasta que alguno sea compatible con el tiempo dado.
     * si esto ocurre, se reserva, gracias a la dualidad de la implementacion de
     * los metodos de la clase Tiempo, de modo que si algo falla, en cualquier momento,
     * se le avisa al metodo invocador de la falla del procedimiento.
     * 
     * @param tiempo el tiempo que nececita el usuario
     * @throws UnsupportedOperationException si es que no pudo ser concretada la operacion
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
    */
    public void reservarProyector(Tiempo tiempo) throws UnsupportedOperationException{
        //para cada proyector que controle la secretaria...
        if(tiempo.isEmpty()){
            throw new UnsupportedOperationException("Se ha entregado un tiempo nulo");
        }
        for(Proyector proyector : this.listaProyectores){
            try{
               proyector.setDisponibilidad(tiempo);
               return;
            }
            catch(UnsupportedOperationException e){
            }
        }
        throw new UnsupportedOperationException("El proyector esta ocupado para el tiempo dado/No hay proyectores disponibles");
    }
    
    /**
     * Metodo verificador hasSala, el cual indica si es que esta secretaria,
     * o mejor dicho la instancia de la misma, posee el control de una sala determinada 
     * o no.
     * 
     * @param sala la sala a verificar su presencia
     * @return true si es que la sala esta dentro del control
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
    */
    public boolean hasSala(int numero){
        //TODO cambiar la implementacion
        /*(non-javadoc)
         * Ok, vamos al grano. aca la implemantacion ha de ser cambiada,
         * ya que si bien las secretarias administran los proyectores
         * las salas tambien lo son por ellas.
         * de hecho, un cambio mayor de la implementacion debe ser 
         * el uso de ecepciones y tambien la implementacion de 
         * que el metodo has sala, regrese la sala si es que la tiene.
         * (?de modo que el usuario pueda tambien reservar la sala?)
         */
    	for(Sala sala : listaSalas){
    		if(sala.getNumero()==numero){
    			return true;
    		}
    	}
		return false;
    }
    
    public boolean hasSala(){
		if(this.listaSalas.size()>0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
     * Metodo addSala, el cual agrega una sala a la lista (y al mapa correpsondiente. 
     * previa validacion de la misma.
     * 
     * La metodologia utilizada aca es simple. se valida que en primer lugar, la sala exista,
     * luego se verifica que no este en la lista, y finalmente se agrega a la lista.
     * 
     * @param sala la sala a agregar al mapa
     * @param nombre el nombre de la sala a agregar al mapa
     * @return true si la operacion fue realizada con exito. false en caso contrario
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public boolean addSala(Sala sala, int numero){
        //si es que la sala ya existe por nombre o por clasificacion y existencia.
        if((sala != null) &&
                !hasSala(numero) && !this.listaSalas.contains(sala) ){
            this.listaSalas.add(sala);
            return true;
        }
        return false;
    }
     //* Metodo addProyector, el cual agrega un pryector a la lista de proyectores disponibles
     
     /**
     * y administrados por la secretaria.
     * 
     * La metodologia utilizada aca es la misma que en addSala.
     * 
     * @param proyector el proyector a agregar.
     * @return true si la operacion fue realizada con exito. false en caso contrario
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public boolean addProyector(Proyector proyector){
        if( (proyector != null)&&
                (this.listaProyectores.contains(proyector)) ){
            return false;
        }
        this.listaProyectores.add(proyector);
        return true;
    }

    /**
     * Metoodo removeProyector, el cual quita un proyector de la lista
     * de proyectores. Si el proyector objetivo no esta presente, este
     * metodo no hace nada.
     * 
     * @param proyector el proyector a quitar del sistema.
     * @return true si es que la operacion pudo ser concretada exitosamente.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public boolean removeProyector(int index){
        return null != this.listaProyectores.remove(index);
    }
    
    /**
     * Metoodo removeSala, el cual quita un sala de la lista
     * de salas. Si el sala objetivo no esta presente, este
     * metodo no hace nada.
     * 
     * @param sala la sala a quitar del sistema.
     * @return true si es que la operacion pudo ser concretada exitosamente.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public boolean removeSala(int index){
        if(this.listaSalas.remove(index)!=null){
            return true;
        }
        return false;
    }
    
    /**
     * Retorna el nombre del actual encargado de secretaria.
     * 
     * @return el nombre del actual encargado de secretaria
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public String getEncargado() {
        return encargado;
    }

    /**
     * Setea el nombre del actual encargado de secretaria.
     * 
     * @param encargado el nombre del actual encargado de secretaria.
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    /**
     * Retorna el nombre de la escuela a la que pertenece esta sercretaria.
     * 
     * @return el nombre de la escuela a la que pertenece esta sercretaria
     *  
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public String getEscuela() {
        return escuela;
    }
    
    ////////MODIFICACIONES//////////
    
    public boolean hasProyectores(){
    	if(this.listaProyectores.size()>0){
    		return true;
    	}
    	return false;
    }
    
    public void getSalasDescription(){
    	int i = 0;
    	if(this.listaSalas.size()>1){
    		for(Sala sala : listaSalas){
    			System.out.println("[" + i + "] Sala n° " + sala.numero + " // " + sala.descripcion);
    			i++;
    		}
    	}
    	else{
    		System.out.println("No hay salas disponibles.");
    	}
    }
    
    public void getProyectoresDescription(){
    	int i = 0;
    	if(this.listaProyectores.size()>1){
    		for(Proyector proyector : this.listaProyectores){
    			System.out.println("["+i+"] "+ proyector.marca + " - "+ proyector.modelo + "\n");
    			i++;
    		}
    	}
    	else{
    		System.out.println("No hay proyectores disponibles.");
    	}
    }
    //TODO un metodo que me imprima los proyectores poseidos por la sala.xD

    public int getSalasNum(){
    	return this.listaSalas.size();
    }
    public int getProyectorNum(){
    	return this.listaProyectores.size();
    }
}
