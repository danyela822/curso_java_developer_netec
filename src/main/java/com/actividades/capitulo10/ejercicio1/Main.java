package com.actividades.capitulo10.ejercicio1;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n--------------------- Uso de la Interfaz Runnable ---------------------\n");

        Contador contador = new Contador();

        // Crear un nuevo hilo y asociarlo al objeto Contador
        Thread hiloContador = new Thread(contador);

        // Iniciar la ejecución del hilo
        hiloContador.start();
    }
}