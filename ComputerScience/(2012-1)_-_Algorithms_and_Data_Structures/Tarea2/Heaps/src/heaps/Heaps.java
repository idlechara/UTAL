/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

/**
 *
 * @author Kynku
 */
public class Heaps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int horas=0, retardo=0, type=0;
        boolean verbose=false;
//        System.out.println(args.length);
//        for (String s: args){
//            System.out.println(s);
//        }
        try {
            if (args.length == 3) {
                horas = Integer.parseInt(args[0]);
                retardo = Integer.parseInt(args[2]);
                if (args[1].toLowerCase().equals("s")) {
                    type = Simulator.NORMAL;
                } else if (args[1].toLowerCase().equals("p")) {
                    type = Simulator.PRIORITY;
                } else {
                    throw new Exception();
                }
            }
            if (args.length == 4) {
                horas = Integer.parseInt(args[0]);
                retardo = Integer.parseInt(args[2]);
                if (args[1].toLowerCase().equals("s")) {
                    type = Simulator.NORMAL;
                } else if (args[1].toLowerCase().equals("p")) {
                    type = Simulator.PRIORITY;
                } else {
                    throw new Exception();
                }
                if (args[3].toLowerCase().equals("v")) {
                    verbose = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el paso de argumentos. Ejecucion terminada.");
            System.exit(1);
        }

        long init = System.currentTimeMillis();
//        for(int i=0; i<16;i++){
//            System.out.println("**************************************\n"
//                    + "********** Iteracion prioridad "+ i +" ************\n"
//                    + "**************************************");
//        new Simulator(12, Simulator.PRIORITY, 0, verbose);
//            System.out.println("**************************************\n"
//                    + "********** Fin Iteracion prioridad "+ i +" ************\n"
//                    + "**************************************\n\n");
//        }
                for(int i=0; i<16;i++){
            System.out.println("**************************************\n"
                    + "********** Iteracion normal "+ i +" ************\n"
                    + "**************************************");
        new Simulator(12, Simulator.NORMAL, 0, verbose);
            System.out.println("**************************************\n"
                    + "********** Fin Iteracion normal "+ i +" ************\n"
                    + "**************************************\n\n");
        }
        System.out.println("\n****************************************\n\nTotal execution time: " + (System.currentTimeMillis() - init) + " milisecs.");
    }
}
