import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

/**
 * Nunca nos dijeron que no podiamos implementar clases extra. por eso, para simplificarme las cosas, hice otra clase
 * la cual tiene como rol, encargarse de los problemas mantener los runs
 * 
 * @author Erik Andres Regla Torres
 * @version 0.9.1
 * @since 1.0
 *
 */
public class RunArray
{
    private ArrayList<Run> runs;
    private HashMap<String,Integer> problemasResueltos;
    private int tiempoTotalResolucion;
    
    /**
     * constructr por defecto de la clase RunArray, inicializa los valores por defecto
     */
    public RunArray()
    {
        this.runs = new ArrayList<Run>();
        this.problemasResueltos = new HashMap<String,Integer>();
        this.tiempoTotalResolucion = -1;
    }
    
    /**
     * metodo que agrega un  nuevo run a la lista, y actualiza la informacion correspondiente
     * @param run el run a agregar
     * @return el tiempo total calculado de las resoluciones.
     */
    public int add(Run run){
        if((problemasResueltos.containsKey(run.getRespuesta())==false)&&(run.getRespuesta().equals("Yes"))){
            this.problemasResueltos.put(run.getProblema(),run.getTiempoEnvio());
            tiempoTotalResolucion += run.getTiempoEnvio();
            
            for(Run problema: runs){
                if(problema.getProblema().equals(run.getProblema())==true){
                    tiempoTotalResolucion += 20;
                }
            }
        }
        
        this.runs.add(run);
        
        return tiempoTotalResolucion;
    }
}
