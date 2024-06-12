package com.actividades.capitulo10.ejercicio2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n----------------------- Uso de ExecutorService -----------------------\n");

        ExecutorService conjuntoHilos = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {

            Tarea tarea = new Tarea(i);

            conjuntoHilos.submit(tarea);
        }
        conjuntoHilos.shutdown();
    }
}
