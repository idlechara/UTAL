package mosquito;

import trainer.Individual;
import trainer.Trainer;

import java.util.Collection;
import java.util.Stack;

/**
 * Clase encapsuladora de un proceso de entrenamiento. Implementa runnable para
 * poder separar la instancia del estado de ejecucion del hilo.
 */
public class TrainingProcess implements Runnable{
    Trainer trainer;
    public String name;

    public TrainingProcess(String name,double chaosRate, int populationSize /*48*/, int generationsPerIteration, int basePopulation){
        this.name = name;
        this.trainer = new Trainer();
        for(int i=0; i<basePopulation; i++){
            this.trainer.addIndividual(new Individual());   //two parents by default
        }
        //this.trainer.setBestPopulation(basePopulation);
        this.trainer.setChaosRate(chaosRate);
        this.trainer.setPopulationSize(populationSize);
        this.trainer.setNumGenerations(generationsPerIteration);
    }

    @Override
    public void run() {
        this.trainer.start();
    }

    public Collection<? extends Individual> getRandomIndividuals(int populationToMigrate) {
        return this.trainer.getRandomIndividuals(populationToMigrate);
    }

    public Individual getBestIndividual(){
        return this.trainer.getBestIndividual();
    }
}