package com.actividades.capitulo9;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n------------------------------ Diferencia entre dos Fechas ------------------------------\n");

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.of(2024, 12, 4);

        System.out.println("La diferencia en días entre: " + fechaInicio + " y " + fechaFin + " es: " + fechaInicio.until(fechaFin,ChronoUnit.DAYS) + " dias");

        System.out.println("\n------------------------ Convertir Fecha a Cadena en Formato ISO ------------------------\n");

        LocalDate fechaIso = LocalDate.now();
        DateTimeFormatter formatoIso = DateTimeFormatter.ISO_LOCAL_DATE;
        String stringFormato = fechaIso.format(formatoIso);

        System.out.println("Formato en string: "+stringFormato);

        // Convertir el string a LocalDate
        LocalDate fecha = LocalDate.parse(stringFormato, formatoIso);

        System.out.println("Fecha ISO: " + fecha);

        System.out.println("\n---------------------------------- Validar una Fecha ----------------------------------\n");

        String formatoPersonalizado = "2024-06-04";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaPersonalizada = LocalDate.parse(formatoPersonalizado,formato);

        System.out.println("El formato: "+fechaPersonalizada+" es valido");

        System.out.println("\n------------------------ Manejar Horarios de Diferentes Países ------------------------\n");

        ZoneId[] IdsZonas = {
                ZoneId.of("America/Los_Angeles"),
                ZoneId.of("Europe/London"),
        };

        ZonedDateTime zonas [] = new ZonedDateTime[2];

        for (int i = 0; i < IdsZonas.length; i++) {
            ZoneId zoneId = IdsZonas[i];zonas[i] = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), zoneId);
            System.out.println(zonas[i]);
        }
        System.out.println("La diferencia en días entre: " + zonas[0].getZone()+ " y " + zonas[1].getZone() + " es: " + zonas[0].until(zonas[1],ChronoUnit.HOURS) + " horas");}
}