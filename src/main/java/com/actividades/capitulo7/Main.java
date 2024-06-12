package com.actividades.capitulo7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static List<Persona> personas = Arrays.asList(
            new Persona("Juan", "Perez", 30, "TIJ"),
            new Persona("Luis", "Lopez", 24 ,"PUE"),
            new Persona("Diana", "Duran", 20 ,"CDMX"),
            new Persona("Mia", "Muñoz", 37,"TIJ"),
            new Persona("Ivan", "Hidalgo", 10,"PUE"),
            new Persona("Karen", "Flores", 27,"CDMX"),
            new Persona("Sol", "Suarez", 46,"TIJ"),
            new Persona("Liam", "Torres", 32,"PUE"),
            new Persona("Brian", "Bermudez", 25,"CDMX"),
            new Persona("Jaime", "Jimenez", 15,"TIJ"),
            new Persona("Esteban", "Caseres", 40,"PUE"),
            new Persona("Danilo", "Restrepo", 35,"CDMX"),
            new Persona("Amelia", "Gomez", 17,"TIJ"),
            new Persona("Estella", "Ortiz", 16,"PUE"),
            new Persona("Luisa", "Montes", 42,"CDMX"));

    public static void main(String[] args) {

        System.out.println("\n//------------------------------- PERSONAS MAYORES DE 25 --------------------------------------//\n");
        personas.stream().filter(persona -> persona.getEdad() > 25).forEach(persona -> System.out.print(persona));

        System.out.println("\n//----------------------- LISTA DE NOMBRES COMPLETOS EN MAYUSCULA ----------------------------//\n");
        Stream<String> nombresMayuscula = personas.stream().map(persona -> persona.getNombre().toUpperCase());
        List<String> litaNombresMayuscula = nombresMayuscula.collect(Collectors.toList());
        litaNombresMayuscula.forEach(nombre -> System.out.println(nombre));

        System.out.println("\n//------------------------------- LISTA ORDENADA POR EDAD --------------------------------------//\n");
        List<Persona> personasOrdenadas = personas.stream()
                .sorted()
                .peek(persona -> System.out.print(persona))
                .collect(Collectors.toList());

        System.out.println("\n//-------------------------- PERSONA DE CDMX MAYOR DE 30 AÑOS -------------------------------//\n");
        Optional<Persona> personaOptional = personas.stream().filter(persona -> persona.getCiudad().equals("CDMX") && persona.getEdad() > 30).findAny();
        System.out.print(personaOptional.get());

        System.out.println("\n//-------------------------- PERSONA AGRUPADAS POR CIUDAD DE ORIGEN -------------------------------//\n");

        Map<String, List<Persona>> personasCiudad = personas.stream().collect(Collectors.groupingBy(persona -> persona.getCiudad()));

        personasCiudad.entrySet().forEach((entry) -> {
            String clave = entry.getKey();
            List<Persona> valor = entry.getValue();
            System.out.println("Ciudad: " + clave + ", Personas: " + valor.stream().map(persona -> persona.getNombre()).toList());
        });

        System.out.println("\n//-------------------------------- PERSONA AGRUPADAS POR EDAD -------------------------------------//\n");

        Map<Boolean, List<Persona>> personasEdad = personas.stream().collect(Collectors.partitioningBy(persona -> persona.getEdad() > 30));

        personasEdad.entrySet().forEach((entry) -> {
            Boolean clave = entry.getKey();
            List<Persona> valor = entry.getValue();
            System.out.println("¿Mayor de 30?: " + clave + ", Personas: " + valor.stream().map(persona -> persona.getNombre()).collect(Collectors.toList()));
        });

        System.out.println("\n//---------- OPERACIONES COMBINADAS: LISTA DE PERSONAS MENORES DE EDAD QUE VIVEN EN TIJUANA  -------------//\n");

        personas.stream().filter(persona -> persona.getEdad() < 18 && persona.getCiudad().equals("TIJ"))
                .map(pNombre -> pNombre.getNombre()+" "+pNombre.getApellido())
                .sorted()
                .collect(Collectors.toList())
                .forEach(persona -> System.out.println(persona));

    }
}