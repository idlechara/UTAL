package main;

import mosquito.TrainingProcess;
import trainer.Individual;
import trainer.Trainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by rjerez1992 on 23-11-2015.
 */
public class DeepChiaaSimple {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //It runs the simple trainer

        //Creates and individual
        Individual ind1 = new Individual();
        Individual ind2 = new Individual();

        //Loads the current bestGnome
        double[] gen = loadGen("src/main/currentGenome.gen");
        ind1.setFromGenome(gen);
        ind2.setFromGenome(gen);


        //Creates a new trainer
        Trainer trn = new Trainer();
        trn.setChaosRate(0.15); //15% of swapping values when mutating
        trn.setPopulationSize(8); //4 from hierarchy 6 from mutation after the first iteration
        trn.setNumGenerations(10); //It will generate 10 generations before stopping execution

        //Adds the starting pop
        trn.addIndividual(ind1);
        trn.addIndividual(ind2);

        System.out.println("Starting trainer");
        //Runs the trainer
        trn.start();
        trn.start();
        System.out.println("Trainer stopped");

        //Saves the best genome to the current best genome
        dumpToFile("src/main/currentGenome.gen", trn.getBestIndividual().toString());
    }

    static private double[] loadGen(String fileName){
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);
            read.useLocale(Locale.US);
            int nLines = read.nextInt();
            //System.out.println(nLines); Genome lenght
            double[] gen = new double[nLines];
            for (int i=0; i<nLines; i++){
                gen[i] = read.nextDouble();
            }
            read.close();
            return gen;
        }
        catch (FileNotFoundException ex){
            System.out.println("No encontro el archivo de gen especificado\n");
            System.exit(0);
        }
        return null;
    }

    public static void dumpToFile(String filePath, String s){
        PrintWriter out = null;
        try {
            out = new PrintWriter(filePath);
            out.println(s);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error en creación de archivos de salida");
            e.printStackTrace();
        }
    }
}
