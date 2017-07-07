/**
 * Clase que guarda la informacion de el cada run enviado por el equipo, a fin de mantener un registro del mismo
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1
 * @since 1.0
 *
 */
public class Run
{
    private int numeroProblema;
    private String nombreProblema;
    private int tiempoDeEnvio;
    private String respuesta;
    
    /**
     * contructor por defecto de la clase Run. recibe parametros para todos los valores,
     * pero el valor de la respuesta es calculado al momento de crear la instancia.
     * @param numeroProblema el numero del problema a enviar
     * @param nombreProblema el nombre del problema a enviar
     * @param tiempoDeEnvio el tiempo al que fue enviado
     */
    public Run(int numeroProblema, String nombreProblema, int tiempoDeEnvio)
    {
        this.numeroProblema = numeroProblema;
        this.nombreProblema = nombreProblema;
        this.tiempoDeEnvio = tiempoDeEnvio;
        this.respuesta = Juez.pensar();
    }
    
    /**
     * retorna el valor de la respuesta dada por el juez a este run
     * @return la respuesta del juez
     */
    public String getRespuesta(){
        return this.respuesta;
    }
    
    /**
     * retorna el nombre del problema
     * @return el nombre del problema
     */
    public String getProblema(){
        return this.nombreProblema;
    }
    
    /**
     * retorna el tiempo de envio del problema en minutos
     * @return el tiempo del envio del problema
     */
    public int getTiempoEnvio(){
        return this.tiempoDeEnvio;
    }
}
