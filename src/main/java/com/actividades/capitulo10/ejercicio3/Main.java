package com.actividades.capitulo10.ejercicio3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n-------------------- Sincronización con CyclicBarrier --------------------\n");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        ExecutorService conjuntoHilos = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 4; i++) {
            Tarea tarea = new Tarea(i,cyclicBarrier);
            conjuntoHilos.execute(tarea);
        }
        conjuntoHilos.shutdown();
    }
}
