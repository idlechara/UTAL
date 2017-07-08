package com.jvarred;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    static final int MAX_THREADS = 4;
    public static void main(String[] args) throws InterruptedException {
        //inicialización de recursos disponibles, productor y arreglo de hilos a lanzar
        LinkedList<Long> resources = new LinkedList<>();
        Producer p = new Producer(resources);
        ArrayList<Thread> pool = new ArrayList<>();
        for(int i=0; i < MAX_THREADS; i++){
            pool.add(new Thread(new Consumer(resources, i)));
        }

        //lanzamiento de hilos
        Thread tp = new Thread(p);
        tp.start();
        for(int i=0; i < MAX_THREADS; i++){
            pool.get(i).start();
        }
        System.out.println("Iniciando");
    }
}
