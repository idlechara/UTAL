package com.jvarred;
import java.util.LinkedList;
import java.util.Random;

public class Producer implements Runnable {
    LinkedList<Long> resources;
    final int MAX_ARRAY = 10;
    Producer(LinkedList resources){
        this.resources = resources;
    }

    /**
     * Agrega un elemento al conjunto de datos global. Este viene delimitado por
     * MAX_ARRAY, dentro de esta clase. El acceso está sincronizado respecto
     * al miembro resources, y por lo cual el tamaño de este ejerce un bloqueo
     * activo.
     *
     * @param i el número a insertar
     */
    public void produce(long i){
        synchronized (this.resources) {
            if(this.resources.size()>=10) return;
            this.resources.add(i);
            System.out.println("Produced " + this.resources.toString());
        }
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            long time = (new Random()).nextInt(500);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            produce(i++);
        }
    }
}
