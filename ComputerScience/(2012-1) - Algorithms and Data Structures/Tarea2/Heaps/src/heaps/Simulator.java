/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

import heaps.Structures.NormalQueue;
import heaps.Structures.ObjectHeap;
import heaps.Structures.PriorityContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kynku
 */
public final class Simulator {

    long initTime;
    int size;
    int step;
    int type;
    int maxTime;
    public static final int NORMAL = 1;
    public static final int PRIORITY = 0;
    NormalQueue normalQueue;
    ObjectHeap priorityQueue;
    PriorityContainer priorityContainer;
    Persona siguente, actual;
    public static String log;
    int[] numeroPersonas;
    int[] tiempoPersonas;
    int retardo;
    public static NewJFrame ventana = new NewJFrame();
    boolean verbose;

    public Simulator(int hours, int type, int retardo, boolean verbose) {
        this.ventana.setVisible(verbose);
        this.retardo = retardo;
        this.verbose = verbose;
        this.initTime = System.currentTimeMillis();
        this.step = 9 * 60 * 60;
        this.maxTime = this.step + hours * 60 * 60;
        this.size = (((hours * 60) * 140) - ((hours * 60) * 60)) / 60; //Tamaño en el peor caso
        this.printer("Size: " + this.size);
        this.type = type;
        if (this.type == NORMAL) {
            this.normalQueue = new NormalQueue(size);
            this.normalQueue.push(new Persona(this.step));
        } else {
            this.priorityQueue = new ObjectHeap(size);
            this.priorityQueue.push(new Persona(this.step));
        }
        this.siguente = new Persona(this.step);
        this.actual = this.popCurrent();
        this.numeroPersonas = new int[9];
        this.tiempoPersonas = new int[9];
        this.ventana.updateStep(step);
        this.ventana.updateTipoCola(type);
        this.ventana.updateRetardo(retardo);
        this.printer("Starting simulation:");
        this.printer("Hours: " + hours);
        this.printer("Type: " + ((type == NORMAL) ? "Normal Queue" : "Priority Queue"));
        this.printer("************************************************");
        if (retardo > 0) {
            while (nextStep()) {
                try {
                    Thread.sleep(retardo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            while (nextStep());
        }
        this.printInfo();
    }

    boolean nextStep() {
        this.ventana.updateStats(this.getInfo());
        this.ventana.updateStep(step);
        if(this.actual!=null)this.ventana.updateCurrent(actual);
        if (++this.step >= this.maxTime) {
            //System.out.print("\n Stage: Pop-ing!" + this.getElapsedHours() + "hours, " + this.getElapsedMinutes() + " minutes, " + this.getElapsedSeconds() + " seconds.");
            if (this.actual == null) {
                return false;
            }
            //this.printer(" - TimeLeft: " + this.actual.getTiempoDeAtencion());
            if (this.actual.stepTimeOut()) {
                this.actual = siguente;
                //this.printer("Entro una persona de la ventanilla!:\t" + this.actual.getPriority() );
                siguente = popCurrent();
            }
            if (siguente == null) {
                return false;
            }
        } else {
            if (this.siguente == null) {
                siguente = popCurrent();
            }
            //System.out.print("\n Stage: Open-queue!" + this.getElapsedHours() + "hours, " + this.getElapsedMinutes() + " minutes, " + this.getElapsedSeconds() + " seconds.");
            if (!(this.actual == null)) {
                //this.printer(" - TimeLeft: " + this.actual.getTiempoDeAtencion());
                if (this.actual.stepTimeOut()) {
                    this.actual = siguente;
                    //this.printer("Entro una persona de la ventanilla!:\t" + this.actual.getPriority() );
                    siguente = popCurrent();
                    //this.printer("Salio una persona de la ventanilla!");
                }
            } else {
                this.actual = siguente;
                siguente = popCurrent();
                //this.printer("Entro una persona a ventanilla!");
            }
            if (step % 60 == 0) {
                this.pushNew();
                //this.printer("Llego una nueva persona a la cola!:\t" + this.actual.getPriority() );
            }
        }
        if (step % 60 == 0) {
            this.printQueue();
        }
        return true;
    }

    private void printQueue() {
        if (this.type == NORMAL) {
            this.ventana.updateCola(this.normalQueue);
            //this.printer(this.normalQueue.toString());
        } else {
            this.ventana.updateCola(this.priorityQueue);
            //this.printer(this.priorityQueue.toString());
        }
    }

    public Persona getNext() {
        return this.siguente;
    }

    public Persona popCurrent() {
        Persona aux = (Persona) ((this.type == NORMAL) ? normalQueue.pop() : priorityQueue.pop());

        if (this.actual != null) {
            int grupo = ((this.actual.getEdad() - 1) / 10) - 1;
            this.tiempoPersonas[grupo] += this.step - this.actual.getTiempoDeLlegada();
            this.numeroPersonas[grupo]++;
        }
        return aux;
    }

    public void pushNew() {
        Persona aux = new Persona(this.step);
        //int grupo = ((aux.getEdad()-1)/10)-1;
        //this.numeroPersonas[grupo]++;
        //this.tiempoPersonas[grupo] += (this.step-aux.getTiempoDeLlegada());
        if (this.type == NORMAL) {
            this.normalQueue.push(aux);
        } else {
            this.priorityQueue.push(aux);
        }
    }

    public int getElapsedHours() {
        return (this.step / 3600);
    }

    public int getElapsedMinutes() {
        return (this.step % 3600) / 60;
    }

    public int getElapsedSeconds() {
        return this.step % 60;
    }

    public String transformElapsedHours(int target) {
        return (target / 3600) + " hours";
    }

    public String transformElapsedMinutes(int target) {
        return ((target % 3600) / 60) + " minutes";
    }

    public String transformElapsedSeconds(int target) {
        return (target % 60) + " seconds";
    }

    public final void printInfo() {
        this.printer("\n******************************************\nResults:");
        this.printer(getInfo());
        this.printer("Simulation time: " + this.getElapsedHours() + " hours, " + this.getElapsedMinutes() + " minutes, " + this.getElapsedSeconds() + "seconds.");
        this.printer("Execution time: " + (System.currentTimeMillis() - this.initTime) + " milisecs.\n");
    }

    public String getInfo(){
        String out = "";
        int prom;
                for (int i = 0; i < 9; i++) {
                    if(this.numeroPersonas[i]==0) continue;
                    prom = this.tiempoPersonas[i] / this.numeroPersonas[i];
                    out += ("[" + (1 + ((i + 1) * 10)) + "- " + ((i + 2) * 10) + "]:\t" + transformElapsedHours(prom) + " " + transformElapsedMinutes(prom) + " " + transformElapsedSeconds(prom) + " : "+ this.numeroPersonas[i] +" people.\n");
        }
                return out;
    }
    
    public void printer(String s) {
        if (!this.verbose) {
            System.out.println(s);
        } else {
            this.ventana.updateGuiOutput(s);
        }
    }
}
