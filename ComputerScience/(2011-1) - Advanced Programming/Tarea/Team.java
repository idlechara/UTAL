/**
 * La clase equipo (Team), en la cual se agregan los participantes y se utilizan para 
 * competir en la icpc
 * 
 * @author Erik Andres Regla Torres
 * @version 0.2.1
 * @since 1.0
 *
 */
public class Team
{
    private String nombre;
    private Participante coach;
    private ArrayList<Participante> miembros;
    private String universidad;
    private RunArray runsEquipo;
    private int problemasResueltos;
    private int tiempoFinal;
    private boolean isValid;

    /**
     * constructor de la clase team
     * @param nombre el nombre del equipo
     * @param coach el coach del equipo
     * @param universidad la universidad a la que pertenece
     */
    public Team(String nombre, Participante coach, String universidad) throws Exception
    {
        if(coach.isCoach()){
            this.coach = coach;
            this.isValid = false;
            this.nombre = nombre;
            this.universidad = universidad;
            this.miembros = new ArrayList<Participante>();
            this.runsEquipo = new RunArray();
            this.problemasResueltos = 0;
            this.tiempoFinal = -1;
        }
        else{
            throw new Exception();
        }

    }

    /**
     * añade un miembro y valida el equipo
     * @param miembro el miembro a agregar
     */
    public void addMiembro(Participante miembro){
        this.miembros.add(miembro);
        this.isValid = true;
    }

    /**
     * le enseña un problema a un juez, rebiendo el resultado, y actualizando el "marcador"
     * @param numero numero del problema
     * @param nombre nombre del problema
     * @param tiempo tiempo en el que fue enviado
     */
    public void mostrarProblema(int numero, String nombre, int tiempo){
        Run enviar = new Run(numero,nombre,tiempo);
        tiempoFinal = runsEquipo.add(enviar);
    }

    /**
     * retorna el numero de problemas resueltos
     * @return el numero de problemas resueltos
     */
    public int getResueltos(){
        return this.problemasResueltos;
    }

    /**
     * regresa el tiempo de resolucion total de los problemas
     * @return el tiempo de resolucion total
     */
    public int getTiempo(){
        return this.tiempoFinal;
    }

    /**
     * indica si un equipo es valido para participar
     * @return la validacion
     */
    public boolean isValid(){
        return this.isValid;
    }
}
