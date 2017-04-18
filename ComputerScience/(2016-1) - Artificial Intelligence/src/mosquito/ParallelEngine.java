package mosquito;
import trainer.Individual;

import java.util.*;

/**
 * Motor para cálculo en paralelo. Ejecuta simultáneamente cada entrenador entregado con
 * addNewProcess(). Se crea una instancia UNICA luego se agregan entrenadores.
 *
 * Iterar en un for la funcion iterate() y cuando se quiera migrar poblacion, utilizar
 * migrate().
 */
public class ParallelEngine {

    public ArrayList<TrainingProcess> processes;
    public ParallelEngine(){
        this.processes = new ArrayList<>();
    };

    /**
     * Agrega un entrenador nuevo a la lista de procesos a ejecutar
     * @param target el entrenador a agregar a la lista de procesos concurrentes
     */
    public void addNewProcess(TrainingProcess target){
        this.processes.add(target);
    }

    /**
     * Ejecuta una iteración para cada entrenador. Cada entrenador puede tener sus
     * propias configuraciones independientes. Porfa, tengan cuidado de no dejar la
     * configuración de tiempo de algunas islas muy distantes sino se pierde tiempo.
     * La idea es que todas terminen medias parecidas.
     */
    public void iterate(){
        ArrayList<Thread> threads = new ArrayList<>();
        for(TrainingProcess tp : this.processes){
            Thread t = new Thread(tp);
            threads.add(t);
            System.out.println("Launching thread " + tp.name);
            t.start();
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Error during thread join for Training process!");
                e.printStackTrace();
            }
        }

        System.out.println("End of iteration.");
    }

    /**
     * Migrates a random sample of population between worlds.
     * @param populationToMigrate the number of individuals to migrate.
     */
    public void migrate(int populationToMigrate){
        ArrayList<Individual> reshuffleArray = new ArrayList<>();
        for(TrainingProcess tp : this.processes){
            reshuffleArray.addAll(tp.getRandomIndividuals(populationToMigrate));
        }

        long seed = System.nanoTime();
        Collections.shuffle(reshuffleArray, new Random(seed));

        int index = 0;
        for(Individual i : reshuffleArray){
            this.processes.get((index++) % this.processes.size()).trainer.addIndividual(i);
        }
    }

}
