package com.laboratorios.laboratorio10;

import com.laboratorios.entidades.Cuenta;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Cuenta>> future = executor.submit(new CuentaCallable("src/main/resources/cuentas.txt"));

        try {
            List<Cuenta> cuentas = future.get();
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
