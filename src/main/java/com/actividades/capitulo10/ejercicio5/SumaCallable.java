package com.actividades.capitulo10.ejercicio5;

import java.util.concurrent.Callable;

public class SumaCallable implements Callable<Integer> {

    private final int numero1;
    private final int numero2;

    public SumaCallable(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    @Override
    public Integer call(){
        try {
            Thread.sleep(3000);// Simular procesamiento
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return numero1 + numero2;
    }
}

