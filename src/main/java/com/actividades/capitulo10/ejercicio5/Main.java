package com.actividades.capitulo10.ejercicio5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("\n---------------- Combinación de Callable y Future ----------------\n");

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        SumaCallable sumaCallable = new SumaCallable(10, 20);

        // Enviar la tarea al ExecutorService y obtener el Future
        Future<Integer> future = executorService.submit(sumaCallable);

        // Obtener el resultado de la suma del Future
        System.out.println("La suma es: " + future.get());

        // Cerrar el ExecutorService
        executorService.shutdown();
    }
}

