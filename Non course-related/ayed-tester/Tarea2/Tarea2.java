package Tarea2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matias
 */
public class Tarea2 {

    public static void main(String[] args) {
        //Valores por defecto.
        int especiales = 200; //E
        int estacionamientos = 1000; //N
        int subterraneos = 10; //S
        String archiSalida = "archiSalida";
        String bitacora = "bitacora";
        if (args.length == 0) {
            System.out.println("Error: No se han especificado correctamente los argumentos.\n"
                    + "Uso correcto:  tarea2 --especial E --maxEst N --subte S --filename archiSalida < bitacora\n"
                    + "Con: E           : Numero de estacionamientos especiales.\n"
                    + "     N           : Numero de estacionamientos (N>E)\n"
                    + "     S           : Numero de subterraneos.\n"
                    + "     archiSalida : Archivo de salida.\n"
                    + "     bitacora    : Bitacora de entrda.");
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            //Dado que podria incluir o no cada argumento en espedifico, se 
            //pregunta por todos los argumentos en cada iteracion.
            //Practico, aunque poco eficiente.
            try {
                if (args[i].equals("--especial")) {
                    especiales = Integer.valueOf(args[i + 1]);
                }
                if (args[i].equals("--maxEst")) {
                    estacionamientos = Integer.valueOf(args[i + 1]);
                }
                if (args[i].equals("--subte")) {
                    subterraneos = Integer.valueOf(args[i + 1]);
                }
                if (args[i].equals("--filename")) {
                    archiSalida = args[i + 1];
                }
                if (args[i].equals("<")) {
                    bitacora = args[i + 1];
                }
            } catch (Exception ex) {
                System.out.println("Error, argumento invalido: " + ex.getMessage()
                        + "\nSe usara el valor por defecto.");
            }
        }
        if (especiales >= estacionamientos) {
            System.out.println("Error. La cantidad de estacionamientos especiales debe ser"
                    + "\n menor a la cantidad de estacionamientos.");
            System.exit(0);
        }
        Tarea2 t2 = new Tarea2(especiales, estacionamientos, subterraneos, archiSalida, bitacora);
    }
    /**
     * Se basa en manejar los estacionamientos disponibles, a los usados. Si se
     * desea ingresar un vehiculo, se pregunta si el primer BH esta vacio, si lo
     * esta quiere decir que esta lleno, por tanto se repite el proceso hasta
     * encontrar uno disponible. En caso de encontrar uno, este heap estara
     * ordenado para extraer el menor, o para esta simulacion, el más cercano a
     * la salida.
     *
     * @param E Numero de estacionamientos especiales.
     * @param N Numero de estacionamientos normales.
     * @param S Numero de subterraneos.
     * @param archiSalida Archivo de Salida.
     * @param bitacora Archivo de entrada, simulacion.
     */
    private JVentana ventana;
    private String TITULO = "Tarea2";
    private CircularArray estEspeciales;
    private BinaryHeap[] subterraneos;
    private String salida;
    private String entrada;
    private int nEspeciales;
    private int nNormales;
    private int nIngresos = 0;
    private int nIngresosEsp = 0;
    private int nIngresosFallidos = 0;
    private int nIngresosFallidosEsp = 0;
    private int nSalidas = 0;
    private PrintWriter writer;
    private String paraArchSalida; //Se resetea en ingrasarBitacora() y se completa en ingreso()
    //y en ingrasarBitacora(), al final de cada linea, se guarda en el arch de texto.

    private Tarea2(int E, int N, int S, String archiSalida, String bitacora) {
        long startTime = System.currentTimeMillis();
        this.nEspeciales = E;
        this.nNormales = N;
        salida = archiSalida;
        entrada = bitacora;
        //Crear un arreglo circular de tamaño E (Estacionamientos especiales).
        estEspeciales = new CircularArray(E);
        //Crea el resto de estacionamientos usando Binary Heaps.
        subterraneos = new BinaryHeap[S];
        //Se comienza con el primero de los subterraneos.
        subterraneos[0] = crearBH(N - E);
        //Crear bitacora, test.
//        crearBitacora(N, 1);
        //Iniciar GUI.
        ventana = new JVentana(N, E, S, TITULO);
        //Ingresar todos los valores desde bitacora.
        this.ventana.setTitle(TITULO + " - Extrayendo datos...");
        ingrasarBitacora();
        //Check memory and time
        checkMemoryUsage(startTime);
        //Update GUI.
        ventana.updateGUI(nIngresos, nIngresosEsp, subterraneos, nIngresosFallidos, nIngresosFallidosEsp, nSalidas);
    }

    /*
     * Crea un binary heap y lo retorna.
     */
    private BinaryHeap crearBH(int estacionamientos) {
        BinaryHeap bh = new BinaryHeap(estacionamientos, false);
        for (int i = 1; i <= estacionamientos; i++) {
            bh.insert(i); //Se completan todos los estacionamientos disponibles.
        }
        return bh;
    }
    
    private void crearBitacora(int N, int lineas) {
        PrintWriter writer = null;
        Random random = new Random();
        try {
            //Crea archivo.
            writer = new PrintWriter(System.getProperty("user.dir")+"/"+entrada, "UTF-8");
            int normales = 0;
            int especiales = 0;
            for (int i = 0; i < lineas; i++) {
                String linea = "";
                int j = 0;
                while( j<10 ){
                    int probEspecial = random.nextInt(6);
                    if ( random.nextInt(2) != 0 ) { // Ingreso.
                        if (probEspecial == 0) { //Ingresa un especial.
                            linea = linea + "I e ";
                            especiales++;
                            j++;
                        } else { //Ingresa un normal.
                            linea = linea + "I n ";
                            normales++;
                            j++;
                        }
                    } else { //Salida
                        if (probEspecial == 0) { //Sale un especial
                            if (especiales != 0) {
                                int pos = 1 + random.nextInt(especiales);
                                linea = linea + "S -1 " + pos + " ";
                                especiales--;
                                j++;
                            }
                        } else { //Sale un normal.
                            if (normales != 0) {
                                int pos = 1 + random.nextInt(normales);
                                int sub = (pos/N) + 1;
                                int est = pos % N;
                                linea = linea + "S -" + sub + " " + (est+this.nEspeciales) + " ";
                                normales--;
                                j++;
                            }
                        }
                    }
                }
                writer.println(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tarea2.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getMessage());
        }catch (Exception ex) {
            Logger.getLogger(Tarea2.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getMessage());
        }
        finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    /*
     * Desde el lector se recoge cada linea.
     * La linea se separa en substrings por espacios.
     * Luego va detectando, desde el comienzo 0, si el String es un "I" de ingreso.
     * Si es asi, utiliza el seguiente String[] y suma 2 al indice.
     * Por otra parte, la opcion seria salida, entonces extrae los 2 siguientes String[]
     * y suma 3 al indice, para continuar operando.
     * Todo esto hasta terminar con el largo de la linea y continuar con la otra,
     * en caso de existir.
     */
    private void ingrasarBitacora() {
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "/" + entrada);
            BufferedReader br = new BufferedReader(fr);
            writer = new PrintWriter(System.getProperty("user.dir")+"/"+salida, "UTF-8");
            leerArchivo(br);
            fr.close();
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tarea2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tarea2.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ventana.setTitle(TITULO + " - Finalizado");
    }
    
    private void leerArchivo(BufferedReader br){
        String linea;
        try {
            while ((linea = br.readLine()) != null) {
                paraArchSalida = "";
                String[] instrucciones = linea.split(" ");
                for (int i = 0; i < instrucciones.length;) {
                    if (instrucciones[i].equals("I")) { //Es un ingreso.
                        ingreso(instrucciones[i + 1]);
                        i = i + 2;
                    } else { //Es una salida.
                        salida((Integer.valueOf(instrucciones[i + 1]) * -1) - 1, Integer.valueOf(instrucciones[i + 2]));
                        i = i + 3;
                    }
                }
                this.writer.println(paraArchSalida);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tarea2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Ingresa un vehículo, que elimina un elemento del heap o array.
     * Con "t" equivalente a "e" o "n".
     * Para este caso, se resuelte el ingreso especial, que funciona a la inversa
     * del ingreso normal, ya que no se almacenan los estacionamientos disponibles,
     * sino que los vehículos estacionados, por tanto en caso de estar lleno, no es
     * posible realizarlo y deriva al ingreso normal.
     */
    private void ingreso(String t) {
        if (t.equals("n")) { //Es un vehiculo normal.
            ingresoNormal();
        } else { //Estacionamiento para casos especiales.
            if (!estEspeciales.isFull()) {
                this.estEspeciales.push(1);
                this.paraArchSalida = paraArchSalida + "-1 "+ nIngresosEsp+ " ";
                this.nIngresosEsp++;
            } else {
                this.nIngresosFallidosEsp++;
                ingresoNormal();
            }
        }
    }

    /*
     * Recorre el ultimo subterraneo no nulo.
     * Por otra parte, de no serlo, consulta si no esta vacio, o sea, ni no tiene
     * estacionamientos disponibles.
     * Al encontrar un subterraneo con estacionamientos disponibles, elimina el
     * de menor prioridad, o para este caso, el mas cercano a la salida.
     */
    private void ingresoNormal() {
        for (int i = 0; i < subterraneos.length; i++) {
            if (subterraneos[i] != null) {
                //Si esta vacio, no hay vacantes. Continuar busqueda.
                if (!subterraneos[i].isEmpty()) {
                    //No esta vacio, hay vacantes.
                    int n = subterraneos[i].deleteMax(); //Borramos el menor o mas cercano a la salida.
                    if( i == 0 ){
                        this.paraArchSalida = paraArchSalida + "-"+(i+1)+" "+ (n+nEspeciales)+ " ";
                    }else{
                        this.paraArchSalida = paraArchSalida + "-"+(i+1)+" "+ n + " ";
                    }
                    this.nIngresos++;
                    return; //Finalizamos.
                }
            } else { //Es null, por lo tanto no ha sido iniciado ese subterraneo.
                subterraneos[i] = this.crearBH(nNormales); //Instanciamos.
                subterraneos[i].deleteMax(); //Y quitamos el mejor est.
                this.nIngresos++;
                return;
            }
        }
        this.nIngresosFallidos++;
    }

    /*
     * Efecto contrario a ingresar.
     * Elimina elementos para la salida especial y agrega elementos para el
     * ingreso normal.
     */
    private void salida(int sub, int est) {
        try {
            if (sub == 0) { //Se trabaja especial acá, ya que se encuentran los est. esp.
                if (est <= this.nEspeciales) { //Salida especial.
                    this.estEspeciales.eject();
                    if (this.nIngresosEsp > 0) {
                        this.nIngresosEsp--;
                    }
                } else { //Salida normal.
                    //Agregamos nuevamente el estacionamiento como disponible.
                    if (this.subterraneos[sub].insert(est - nEspeciales)) {
                        this.nIngresos--;
                    }
                }
            } else {//Salida est normal.
                if (this.subterraneos[sub].insert(est)) {
                    this.nIngresos--;
                }
            }
            this.nSalidas++;
        } catch (Exception ex) {
        }
    }

    private void checkMemoryUsage(long startTime) {
        long total = Runtime.getRuntime().totalMemory();        
        long used  = total - Runtime.getRuntime().freeMemory();
        System.out.println("Memoria TOTAL: "+ total / (1024 * 1024) + " Mb");
        System.out.println("Memoria USADA: "+ used / (1024 * 1024) + " Mb");
        System.out.println("Tiempo: " + (System.currentTimeMillis() - startTime)/1000 + " seg.");
    }
}