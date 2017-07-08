/**
 * La nececidad de llevar un registro de la informacion del torneo es clave. para esto fue implementada esta clase
 * la cual es encargada de administar el mismo
 * @author Erik Andres Regla Torres
 * @version 0.6.1
 * @since 1.0
 *
 */
public class Marcador
{
    private String ciudad;
    private int añoActual;
    private String nombreDelTorneo;
    private ArrayList<Team> equiposParticipantes;
    
    /**
     * Constructor por defecto de la clase marcador
     * @param ciudad la ciudad donde se efectuara el torneo
     * @param añoActual el año en el que se efectua
     * @param nombreDelTorneo el nombre del torneo
     */
    public Marcador(String ciudad, int añoActual, String nombreDelTorneo)
    {
        this.ciudad = ciudad;
        this.añoActual = añoActual;
        this.nombreDelTorneo = nombreDelTorneo;
        this.equiposParticipantes = new ArrayList();
    }
    
    /**
     * agrega un equipo a la lista del torneo
     * @param equipo el equipo a añadir
     */
    public void addEquipo(Team equipo) throws Exception{
        if(equipo.isValid()){
            this.equiposParticipantes.add(equipo);
        }
        else{
            throw new Exception();
        }
    }
    
    /**
     * recupera el equipo que gana el torneo
     * @return el equipo ganador
     */
    public Team getWinner() throws Exception{
        boolean empate = false;
        int valorTemporal = 0;
        //el equipo "dummy" es por si es que nadie gana... u.u
        Team equipoTemporal = new Team("Dummy",new Participante("Dummy","Dummy","Dummy",
        "Dummy","Dummy","Dummy",true),"Dummy");
        
        //primera iteracion seria: busqueda por numero de ejercicio resueltos
        for(Team i: equiposParticipantes){
            if(i.getResueltos() > valorTemporal){
                empate = false;
                valorTemporal = i.getResueltos();
                equipoTemporal = i;
            }
            if(i.getResueltos() == valorTemporal){
                empate = true;
            }
        }
        
        if(empate==false){
            return equipoTemporal;
        }
        
        //si hay empate/s, se pasa al criterio de busqueda por tiempo
        else{
            
            ArrayList<Team> equiposFinales = new ArrayList<Team>();
            int tiempoTemporal = 0;
            
            for(Team i: equiposParticipantes){
                if(i.getResueltos()==valorTemporal){
                    equiposFinales.add(i);
                    tiempoTemporal = i.getTiempo()+1;
                }
            }
            
            for(Team i: equiposFinales){
                if((i.getTiempo()>=0)&&(i.getTiempo() < tiempoTemporal)){
                    tiempoTemporal = i.getTiempo();
                    empate = false;
                    equipoTemporal = i;
                }
                if(i.getTiempo() == tiempoTemporal){
                    empate = true;
                }
            }
        
            if(empate == false){
                return equipoTemporal;
            }
        }
        return equipoTemporal;
    }
}