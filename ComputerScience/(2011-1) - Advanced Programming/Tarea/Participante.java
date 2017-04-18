/**
 * Clase que define un participante del icpc
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1.1
 * @since 1.0
 *
 */
public class Participante
{
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String fechaNacimiento;
    private String nickName;
    private String tallaPolera;
    private boolean isCoach;
    
     /**
     * Constructor por defecto de la clase participante
     * 
     * @param nombre nombre del participante
     * @param apelllidoMaterno el apellido materno del participante
     * @param apellidoPaterno el apellido paterno del participante
     * @param fechaNacimiento fecha de nacimiento del participante
     * @param nickName apodo del participante
     * @param tallaPolera la talla de la polera que usara
     * @param isCoach indica si es entrenador o participante regular
     */
    public Participante(String nombre, String apellidoMaterno,String apellidoPaterno,
    String fechaNacimiento,String nickName, String tallaPolera, boolean isCoach)
    {
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.nickName = nickName;
        this.tallaPolera = tallaPolera;
        this.isCoach = isCoach;
    }
    
     /**
     * Indica si un jugador es participante o no.
     * 
     * @return un booleano que indica si es entrenador o no 
     */
    public boolean isCoach(){
        return this.isCoach;
    }
}
