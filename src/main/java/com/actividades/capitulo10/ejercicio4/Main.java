package com.actividades.capitulo10.ejercicio4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n---------------- Buenas Prácticas en el Manejo de Hilos ----------------\n");

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        TareaConcurrencia tarea1 = new TareaConcurrencia("Tarea 1");
        TareaConcurrencia tarea2 = new TareaConcurrencia("Tarea 2");
        TareaConcurrencia tarea3 = new TareaConcurrencia("Tarea 3");
        TareaConcurrencia tarea4 = new TareaConcurrencia("Tarea 4");
        TareaConcurrencia tarea5 = new TareaConcurrencia("Tarea 5");

        executorService.execute(tarea1);
        executorService.execute(tarea2);
        executorService.execute(tarea3);
        executorService.execute(tarea4);
        executorService.execute(tarea5);

        executorService.shutdown();
    }
}

