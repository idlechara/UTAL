import java.util.Random;

/**
 * Bueno, nada mas que decir respecto a esta clase, ya que es un juez
 * @author Erik Andres Regla Torres
 * @version 0.1
 * @since 1.0
 */
public class Juez
{
    static final Random pensamiento = new Random();
    

    /**
     * Regresa una cadena aleatoria, indicando el resultado de la evaluacion.
     * @return el veredicto del juez
     */
    public static String pensar(){
        switch(pensamiento.nextInt() % 6){
            case 0:
                return "Yes";
            case 1:
                return "Compilation Error";
            case 2:
                return "Run Time error";
            case 3:
                return "Time Limit Exceeded";
            case 4:
                return "Wrong Answer";
            case 5:
                return "Presentation Error";
            default:
                return "Presentation Error";
        }
    }
}
