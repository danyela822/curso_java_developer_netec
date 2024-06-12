package com.actividades.capitulo10.ejercicio4;

import java.util.Random;

public class TareaConcurrencia implements Runnable {

    // Atributo inmutable
    private final String nombreTarea;

    public TareaConcurrencia(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    @Override
    public void run() {
        // Variables locales para almacenar los resultados del procesamiento
        int resultado = operacion();

        System.out.println(nombreTarea + " - Resultado  " + resultado);
    }

    private int operacion() {
        Random random = new Random();
        int tiempo = random.nextInt(5000) + 1000;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tiempo;
    }
}

