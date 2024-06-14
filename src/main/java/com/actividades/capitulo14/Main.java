package com.actividades.capitulo14;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();
    }

    public static void ejercicio1() {

        System.out.println("\n------------------------------- EJERCICIO 1 ---------------------------------\n");

        Locale us = new Locale("en", "US");
        Locale fr = new Locale("fr", "FR");
        Locale jp = new Locale("ja", "JP");
        Locale mx = new Locale("es", "MX");

        List<Locale> locales = List.of(us,fr,jp,mx);

        locales.forEach(locale -> {
            System.out.println("LOCALE: " + locale);
            System.out.println("IDIOMA: " + locale.getLanguage());
            System.out.println("PAIS: " + locale.getCountry()+"\n");
        });

    }

    public static void ejercicio2(){

        System.out.println("\n------------------------------- EJERCICIO 2 ---------------------------------\n");

        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            properties.load(input);

            System.out.println("NOMBRE DE LA APP: " + properties.getProperty("appName"));
            System.out.println("VERSION: " + properties.getProperty("version"));
            System.out.println("AUTOR: " + properties.getProperty("author"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void ejercicio3(){

        System.out.println("\n------------------------------- EJERCICIO 3 ---------------------------------\n");

        // Cargar el ResourceBundle para el idioma por defecto (inglés)
        ResourceBundle bundleDefault = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());

        // Cargar el ResourceBundle para el idioma Frances
        ResourceBundle bundleFr = ResourceBundle.getBundle("MessagesBundle", new Locale("fr", "FR"));

        // Cargar el ResourceBundle para el idioma Aleman
        ResourceBundle bundleDe = ResourceBundle.getBundle("MessagesBundle", new Locale("de", "DE"));

        List<ResourceBundle> resourceBundles = List.of(bundleDefault,bundleFr,bundleDe);

        resourceBundles.forEach(resourceBundle -> {
            System.out.println("LOCALE: " + resourceBundle.getLocale().getLanguage());
            System.out.println("GREETING: " + resourceBundle.getString("greeting"));
            System.out.println("FAREWELL: " + resourceBundle.getString("farewell")+"\n");
        });
    }

    public static void ejercicio4(){

        System.out.println("\n------------------------------- EJERCICIO 4 ---------------------------------\n");

        double numero = 12345.67;

        List<Locale> locales = List.of(Locale.US,Locale.FRANCE,Locale.JAPAN);

        locales.forEach(locale -> {
            NumberFormat formatoNumero = NumberFormat.getCurrencyInstance(locale);
            String stringNumero = formatoNumero.format(numero);
            System.out.println(locale.getCountry().toUpperCase()+": " + stringNumero);
        });
    }
}

