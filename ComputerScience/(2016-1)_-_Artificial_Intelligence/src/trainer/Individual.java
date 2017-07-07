
package trainer;

import ann.Brain;
import ann.Feature;
import engine.Board;

import java.util.ArrayList;

/**
 * Created by rjerez1992 on 17-10-2015.
 */
public class Individual{

    Brain network;

    public Individual(){
        //9 inputs, 3 layers, 1 output, 252 genomeLenght
        this.network = new Brain(3, 9, 1);
    }

    /**
     * Gets the genome that represents the current individual
     * @return genome
     */
    public double[] getGenome(){
        return this.network.getGenome();
    }

    /**
     * Sets the properties of the individual from a given genome
     */
    public void setFromGenome(double gen[]){
        this.network.setGenome(gen);
    }

    public Individual crossoverWith(Individual ind){
        double[] sGen = this.getGenome();
        double[] fGen = ind.getGenome();
        double[] nGen = new double[sGen.length];
        int halfPoint = sGen.length/2;
        int i;

        //Half point crossover
        for (i=0; i<halfPoint; i++){
            nGen[i] = sGen[i];
        }
        for (int j=i; j<sGen.length; j++){
            nGen[j] = fGen[j];
        }

        Individual newInd = new Individual();
        newInd.setFromGenome(nGen);
        return newInd;
    }

    public Individual getMutation(double mutationRate){
        double[] sGen = this.getGenome();
        double[] mut = new double[sGen.length];
        double random = Math.random();

        //Mutation process
        for (int i=0; i<sGen.length; i++){
            if (mutationRate > random){
                mut[i] = mutateGen(sGen[i]);
            }
            else{
                mut[i] = sGen[i];
            }
        }

        Individual newInd = new Individual();
        newInd.setFromGenome(mut);
        return newInd;
    }

    /**
     * Mutates a gen
     * @param gen
     * @return
     */
    private double mutateGen(double gen){
        double mutation = gen;
        double ran = Math.random()/4;
        double ranSel = Math.random();
        if (ranSel>0.5){
            if (mutation - ran > -1){
                return mutation-ran;
            }
            if (mutation + ran < 1){
                return mutation+ran;
            }
        }
        else{
            if (mutation + ran < 1){
                return mutation+ran;
            }
            if (mutation - ran > -1){
                return mutation-ran;
            }
        }
        return ran;

    }

    /**
     * Gets the evaluation result from the current individual
     * @return evaluation of the board
     */
    public double getResponse(Board b){
        //Gets the features from the board
        Feature feat = new Feature();
        ArrayList<Double> featList = feat.getFeatures(b);

        //Turns the features list into an array
        double[] input = new double[9];
        for (int i=0; i<9; i++){
            input[i] = featList.get(i);
        }

        //Normalization if needed
        //HERE

        //Returns the evaluation of the given board
        return this.network.query(input)[0];
    }

    @Override
    public String toString(){
        String resultado = "";
        resultado = resultado.concat(Integer.toString(this.getGenome().length));
        resultado = resultado.concat("\n");
        double[] newGen = this.network.getGenome();
        for (double db : newGen){
            resultado = resultado.concat(""+db+"");
            resultado = resultado.concat("\n");
        }
        return resultado;
    }
}