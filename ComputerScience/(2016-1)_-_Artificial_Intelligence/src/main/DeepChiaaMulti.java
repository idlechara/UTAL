package main;

import mosquito.ParallelEngine;
import mosquito.TrainingProcess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author rjerez1992
 */
public class DeepChiaaMulti {

    static String prefix = "";
    static int iterations = 20;
    static int cycles = 20;
    static int migrations = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParallelEngine engine = new ParallelEngine();
        TrainingProcess core1 = new TrainingProcess("Earth", 0.2, 48, 10, 2);
        TrainingProcess core2 = new TrainingProcess("Jupiter", 0.3, 48, 5, 3);
        TrainingProcess core3 = new TrainingProcess("Mars", 0.23, 48, 7, 4);
        TrainingProcess core4 = new TrainingProcess("Pluto", 0.26, 48, 2, 2);
        TrainingProcess core5 = new TrainingProcess("Ganymede", 0.29, 48, 9, 5);
        TrainingProcess core6 = new TrainingProcess("Venus", 0.1, 48, 2, 2);
        TrainingProcess core7 = new TrainingProcess("Saturn", 0.4, 48, 9, 5);

        engine.addNewProcess(core1);
        engine.addNewProcess(core2);
        engine.addNewProcess(core3);
        engine.addNewProcess(core4);
        engine.addNewProcess(core5);


        int round = 0;
        while(cycles-- != 0){
            if(cycles < 20)
                System.out.println("Migration phase started.");
                engine.migrate(migrations);
            for (int i=0; i<iterations; i++) {
                engine.iterate();
                System.out.println("dumping!");
                //once finished training...
                dumpToFile("Earth_"+round+".gen", core1.getBestIndividual().toString());
                dumpToFile("Jupiter_"+round+".gen", core2.getBestIndividual().toString());
                dumpToFile("Mars_"+round+".gen", core3.getBestIndividual().toString());
                dumpToFile("Pluto_"+round+".gen", core4.getBestIndividual().toString());
                dumpToFile("Ganymede_"+round+".gen", core5.getBestIndividual().toString());
                dumpToFile("Venus_"+round+".gen", core6.getBestIndividual().toString());
                dumpToFile("Saturn_"+round+".gen", core7.getBestIndividual().toString());
            }
        }

        //once finished training...
        dumpToFile("Earth_final.gen", core1.getBestIndividual().toString());
        dumpToFile("Jupiter_final.gen", core2.getBestIndividual().toString());
        dumpToFile("Mars_final.gen", core3.getBestIndividual().toString());
        dumpToFile("Pluto_final.gen", core4.getBestIndividual().toString());
        dumpToFile("Ganymede_final.gen", core5.getBestIndividual().toString());
        dumpToFile("Venus_final.gen", core6.getBestIndividual().toString());
        dumpToFile("Saturn_final.gen", core7.getBestIndividual().toString());

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
