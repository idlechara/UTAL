package trainer;

import java.util.*;

/**
 * Created by rjerez1992 on 15-10-2015.
 * TODO actualizar cambios
 */
public class Trainer {
    //Default values
    private static final int DEFAULTPOPSIZE = 12;

    private static final double DEFAULTCHAOSRATE = .25;

    private static final int DEFAULTNUMGEN = 10;

    private static final long DEFAULTEXETIME = -1;

    //User-specific values
    private int populationSize; //Hoy many different individuals should the trainer produce for every new generation
    /* In case there's fewer individuals than desired, it will automatically create the left ones */

    private double chaosRate; //How much chaos should the trainer induce into every mutation
    /* Bigger chaosRate reduces the possibility of ending up on a local peak, but it slows the overall evolution process */

    private int numGenerations; //How many generations should the trainer produce after shutdown
    /*
    -1 Means that the trainer will create as many generations as the time allows it to.
     */

    private long executionTime; //How much time may the trainer use to run (milliseconds)
    /*
    -1 Means that the trainer may use as much time as it needs
    If numGeneration is -1, executionTime won't allow -1 as possible value
    In the case the time is over and the total generations are not yet generated,
    The trainer will stop after ending the current generation
    */

    //Trainer specific stuff
    private ArrayList<Individual> startingPop;
    private long startTime;
    private Individual end1;
    private Individual end2;
    private Individual ind3;
    private Individual ind4;

    /**
     * Creates a new trainer
     */

    public Trainer(){
        this.startingPop = new ArrayList<>();
        this.populationSize  = DEFAULTPOPSIZE;
        this.chaosRate = DEFAULTCHAOSRATE;
        this.numGenerations = DEFAULTNUMGEN;
        this.executionTime = DEFAULTEXETIME;
    }

    /**
     * Setters and getters
     */

    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * Sets the population size
     * @param populationSize size of population (must be a pair)
     * @return whether or not the size was properly set
     */
    public boolean setPopulationSize(int populationSize) {
        if (populationSize%2==0) {
            this.populationSize = populationSize;
            return true;
        }
        return false;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * Sets the execution time
     * @param executionTime Time in milliseconds
     * @return Whether the execution time was accepted or not
     */
    public boolean setExecutionTime(long executionTime) {
        if (this.numGenerations > -1) {
            this.executionTime = executionTime;
            return true;
        }
        return false;
    }

    public int getNumGenerations() {
        return numGenerations;
    }

    public void setNumGenerations(int numGenerations) {
        this.numGenerations = numGenerations;
    }

    public double getChaosRate() {
        return chaosRate;
    }

    public void setChaosRate(double chaosRate) {
        this.chaosRate = chaosRate;
    }

    /**
     * Adds a new individual to the starting population
     * @param newIndividual
     */
    public void addIndividual(Individual newIndividual){
        this.startingPop.add(newIndividual);
    }

    /**
     * Returns the elapsed time since the start function was called
     * @return Time as ms
     */
    private long elapsedTime(){
        return System.currentTimeMillis()-this.startTime;
    }

    /**
     * Starts the execution of the trainer
     */
    public void start(){
        //Sets the values for the training
        int genCount = 0; //Generations counter
        Stack<Individual> populationStack = new Stack<>();
        Stack<Individual> roundWinners = new Stack<>();
        Random random = new Random();
        Individual lastWinner;
        Individual newIndividual;
        this.startTime = System.currentTimeMillis(); //Sets starting time

        //Fills the populationStack with the startingPopulation
        for (Individual i : this.startingPop){
            populationStack.push(i);
        }

        //Checks the stop condition; iterations or time
        while ((this.numGenerations!=-1 && this.numGenerations>genCount)||(this.executionTime!=-1 && this.executionTime>elapsedTime())) {
            System.out.printf("Evolving generation - N°%d\n", genCount);

            //Checks if the populationSize is the desired, if not; it will create mutations as fillers
            if (populationStack.size() < this.populationSize) {
                //We get new individuals based on mutations
                while(populationStack.size() < this.populationSize) {
                    //Gets a mutation from any of the last "parents"
                    newIndividual = populationStack.get(random.nextInt(2)).getMutation(this.chaosRate);
                    populationStack.push(newIndividual);
                }
            } else {
                while (populationStack.size() > this.populationSize) {
                    //Trims the population to the desired size
                    populationStack.pop();
                }
            }

            //Shuffles the stack to ensure properly tournament results
            Collections.shuffle(populationStack);

            //Tournament selection of the most suitable individuals
            while (populationStack.size()>2){
                //Continues the tournament until there's only two individuals left
                while(!populationStack.isEmpty() && populationStack.size() != 1){                                
                    //Matches the individuals until the stack is empty
                    lastWinner = Tournament.Match(populationStack.pop(), populationStack.pop());
                    roundWinners.push(lastWinner);
                }
                //Refills the population with the winners of the round
                for (Individual i : roundWinners){
                    populationStack.push(i);
                }
                roundWinners.clear();
            }

            //Set up of the new generation
            //Crossover the two best individuals
            newIndividual = populationStack.get(0).crossoverWith(populationStack.get(1));
            populationStack.push(newIndividual);

            newIndividual = populationStack.get(1).crossoverWith(populationStack.get(0));
            populationStack.push(newIndividual);

            //End (Mutation occurs at the beginning of each generation)
            genCount++;
        }

        //Saves the best two individual of the current generation
        //The best two are the ones at the positions 0 and 1
        end1 = populationStack.get(0);
        end2 = populationStack.get(1);

        //Sets the starting population again in case you want to rerun the start method
        this.startingPop.clear();
        this.startingPop.add(end1);
        this.startingPop.add(end2);
    }

    public Individual getBestIndividual(){
        return Tournament.Match(end1, end2);
    }

    public Collection<? extends Individual> getRandomIndividuals(int populationToMigrate) {
        //Sets the required population
        ArrayList<Individual> reqPop = new ArrayList<>();
        int swapMut = 0;

        reqPop.add(startingPop.get(0));
        reqPop.add(startingPop.get(1));
        reqPop.add(startingPop.get(0).crossoverWith(startingPop.get(1)));
        reqPop.add(startingPop.get(1).crossoverWith(startingPop.get(0)));

        while (reqPop.size() < populationToMigrate) {
            //Gets a mutation from any of the last "parents"
            reqPop.add(reqPop.get(swapMut).getMutation(this.chaosRate));

            //Swaps the individual to be mutated between the two best ones
            if (swapMut == 0)
                swapMut = 1;
            else
                swapMut = 0;
        }

        while (reqPop.size() > populationToMigrate){
            //Reduce the number of individuals
            if (reqPop.size()>2)
                reqPop.remove(reqPop.size()-1);
        }

        //Returns it
        return reqPop;
    }
}