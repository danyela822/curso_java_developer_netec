package com.actividades.capitulo6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n//------------------------------- PREDICATE --------------------------------------//\n");

        Predicate<Integer> numeroPar = numero -> numero % 2 == 0;
        System.out.println("El numero 2 es par: "+numeroPar.test(2));

        System.out.println("\n//------------------------------- CONSUMER --------------------------------------//\n");

        List<String> lista = List.of("Elemento 1","Elemento 2","Elemento 3","Elemento 4");
        Consumer<List<String>> imprimir = item -> System.out.println(item);
        imprimir.accept(lista);

        System.out.println("\n//------------------------------- FUNCTION --------------------------------------//\n");

        List<Integer> numeros = Arrays.asList(1,2,3,4,5);
        Function<Integer, Integer> alCuadrado = n -> n * n;
        imprimir.accept(numeros.stream().map(numero -> alCuadrado.apply(numero)).map(numeroCadena -> numeroCadena.toString()).toList());

        System.out.println("\n//------------------------------- SUPPLIER --------------------------------------//\n");

        Supplier<Integer> randoms = () -> new Random().nextInt(50);
        List<Integer> numerosRandom = new ArrayList<>();

        for (int i = 0; i < 10; i++ ){
            numerosRandom.add(randoms.get());
        }
        imprimir.accept(numerosRandom.stream().map(numeroCadena -> numeroCadena.toString()).toList());

        System.out.println("\n//----------------------------- UNARYOPERATOR ------------------------------------//\n");

        List<String> cadenas = List.of("hello","world","hola","mundo");
        UnaryOperator<String> mayus = c -> c.toUpperCase();
        imprimir.accept(cadenas.stream().map(cadena -> mayus.apply(cadena)).toList());
    }
}