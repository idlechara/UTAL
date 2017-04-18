package com.jvarred;

import java.util.LinkedList;
import java.util.Random;

public class Consumer implements Runnable{
    LinkedList<Long> resources;
    int threadId;

    Consumer(LinkedList resources, int threadId){
        this.resources = resources;
        this.threadId = threadId;
    }


    /**
     * Consume un elemento del conjunto de datos global. El tamaño de resources
     * limita las peticiones. El acceso está sincronizado respecto al miembro
     * resources en el cual el tamaño sirve para realizar un bloqueo activo.
     */
    void consume(){
        synchronized (this.resources) {
            if (this.resources.size() != 0)
                System.out.println("[threadId="+this.threadId+"]Consumed " + resources.pollFirst());
        }
    }

    @Override
    public void run() {
        while(true){
            long time = (new Random()).nextInt(1000)+1000;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consume();
        }
    }

}
