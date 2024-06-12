package com.actividades.capitulo11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n------------------------------ Ejercicio 1: parallelStream ------------------------------\n");
        List<Integer> numeros = IntStream.range(1, 11).mapToObj(n -> n).toList();
        numeros.parallelStream()
                .forEach(numero -> System.out.println("Número: " + numero + ", Hilo: " + Thread.currentThread().getName()));


        System.out.println("\n------------------------ Ejercicio 2: Operaciones Determinísticas ------------------------\n");

        List<Integer> numeros2 = IntStream.range(0, 200)
                .map(i -> {
                    Random random = new Random();
                    return random.nextInt(100);
                })
                .mapToObj(n -> n)
                .toList();

        //Suma en paralelo
        int sumaParalela = numeros2.parallelStream().mapToInt(n -> n).sum();

        //Suma en serie
        int sumaSerie = numeros2.stream().mapToInt(n -> n).sum();

        System.out.println("Suma en paralelo: " + sumaParalela);
        System.out.println("Suma en serie: " + sumaSerie);

        //Promedio en paralelo
        double promedioParalelo = numeros2.parallelStream().mapToDouble(n -> n).average().orElse(0);

        //Promedio en serie
        double promedioSerie = numeros2.stream().mapToDouble(n -> n).average().orElse(0);

        System.out.println("Promedio en paralelo: " + promedioParalelo);
        System.out.println("Promedio en serie: " + promedioSerie);


        System.out.println("\n------------------------ Ejercicio 3: Operaciones No Determinísticas ---------------------\n");

        List<Integer> numeros3 = IntStream.range(1, 11).boxed().toList();

        //Buscar par en paralelo
        Integer numeroParParalelo = numeros3.parallelStream()
                .filter(numero -> numero % 2 == 0)
                .findAny()
                .orElse(null);

        System.out.println("Número par encontrado en paralelo: " + numeroParParalelo);

        //Buscar par en serie
        Integer numeroParSerie = numeros3.stream()
                .filter(numero -> numero % 2 == 0)
                .findAny()
                .orElse(null);

        System.out.println("Número par encontrado en serie: " + numeroParSerie);


        System.out.println("\n----------------------------------- Ejercicio 4: reduce() --------------------------------\n");

        List<Integer> numeros4 = IntStream.range(1, 51).boxed().toList();

        // Calcular la suma de los números
        int suma = numeros4.stream().reduce(0, (a, b) -> a + b);

        System.out.println("Suma: " + suma);

        List<String> cadenas = Arrays.asList("Hello", "world");

        // Concatenar las cadenas en una sola cadena
        String cadenaConcatenada = cadenas.stream().reduce(" ", (a, b) -> a + b);

        System.out.println("Cadena concatenada: " + cadenaConcatenada);

        System.out.println("\n------------------------------ Ejercicio 5: reduce en Paralelo ---------------------------\n");

        List<Persona> personas = Arrays.asList(
                new Persona("Ana", 40),
                new Persona("Andres", 35),
                new Persona("Eduardo", 30),
                new Persona("Carlos", 43)
        );

        // Sumar las edades en paralelo
        int sumaEdades = personas.parallelStream().mapToInt(p -> p.getEdad()).reduce(0, (a,b) -> a + b);

        System.out.println("Suma edades: " + sumaEdades);
    }
}