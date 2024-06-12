package com.actividades.capitulo10.ejercicio2;

class Tarea implements Runnable {

    private int numeroTarea;

    public Tarea(int numeroTarea) {
        this.numeroTarea = numeroTarea;
    }

    @Override
    public void run() {
        System.out.println("Ejecutando tarea " + numeroTarea + " en hilo: " + Thread.currentThread().getName());
    }
}

