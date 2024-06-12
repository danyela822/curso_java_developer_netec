package com.actividades.capitulo10.ejercicio3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Tarea implements Runnable {

    private int numeroTarea;
    private CyclicBarrier cyclicBarrier;

    public Tarea(int numeroTarea, CyclicBarrier cyclicBarrier) {
        this.numeroTarea = numeroTarea;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            int tiempo = random.nextInt(3000) + 1000;
            System.out.println("Ejecutando tarea " + numeroTarea + " en hilo: " + Thread.currentThread().getName() + " durante: " + tiempo);
            Thread.sleep(tiempo);
            System.out.println("Termino tarea " + numeroTarea + " en hilo: " + Thread.currentThread().getName());

            //Esperando los demas hilos
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

