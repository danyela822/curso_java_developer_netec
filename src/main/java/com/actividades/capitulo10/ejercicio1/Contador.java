package com.actividades.capitulo10.ejercicio1;

public class Contador implements Runnable {

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.print(i+" ");
            try {
                Thread.sleep(500); // Pausar la ejecución durante medio segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

