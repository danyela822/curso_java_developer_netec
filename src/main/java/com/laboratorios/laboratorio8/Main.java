package com.laboratorios.laboratorio8;

import com.laboratorios.entidades.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Banco banco;

    public static void main(String[] args) throws IOException {
        System.out.println("\n// ------------------------------------------ PRACTCA 8 --------------------------------------------  //");

        Domicilio domicilioBanco = new Domicilio("Calle 4",4, "Colonia 4", "Estado 4",444);
        Domicilio domicilio5 = new Domicilio("Calle 5",5, "Colonia 5", "Estado 5",121);
        Domicilio domicilio6 = new Domicilio("Calle 6",6, "Colonia 6", "Estado 6",141);
        Domicilio domicilio7 = new Domicilio("Calle 7",7, "Colonia 7", "Estado 7",151);

        Cliente cliente1 = new Cliente(1,"Cliente 1", domicilio5, "rfc 1", "Telefono 1", LocalDate.of(1990,1,1));
        Cliente cliente2 = new Cliente(2,"Cliente 2", domicilio6, "rfc 2", "Telefono 2", LocalDate.of(1990,2,2));
        Cliente cliente3 = new Cliente(3,"Cliente 3", domicilio7, "rfc 3", "Telefono 3", LocalDate.of(1990,3,3));

        banco = new Banco("Banca Electronica",domicilioBanco,"rtc 4","Telefono 4");
        banco.getClientes().addAll(List.of(cliente1,cliente2,cliente3));

        crearCuentas(obtenerCuentas());

        banco.getClientes().forEach(cliente -> {
            cliente.getCuentas().forEach(cuenta -> {
                System.out.println("Cliente: " + cliente.getNumero() + " " + cuenta.toString());
            });
        });

    }

    public static void crearCuentas(List<String> listaCuentas){

        listaCuentas.stream().filter(cuentaAhorro -> cuentaAhorro.startsWith("CA"))
            .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
            .forEach(cuentaAH -> {
                String datos[] = cuentaAH.split(",");
                Cuenta ca = new CuentaDeAhorro(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[0].trim()),Double.parseDouble(datos[3].trim()));

                String stringFecha = datos[1].trim();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fechaApertura = LocalDate.parse(stringFecha,formato);

                ca.setFechaApertura(fechaApertura);

                Cliente cliente = banco.consultarCliente(Integer.parseInt(datos[4].trim()));
                if(cliente != null){
                    cliente.getCuentas().add(ca);
                }else {
                    System.out.println("Cliente: " + datos[4].trim() + " no encontrado");
                }
            });

        listaCuentas.stream().filter(cuentaCheque -> cuentaCheque.startsWith("CC"))
            .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
            .forEach(cuentaCC -> {
                String datos[] = cuentaCC.split(",");
                Cuenta cc = new CuentaDeCheque(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[0].trim()),Double.parseDouble(datos[3].trim()));

                String stringFecha = datos[1].trim();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fechaApertura = LocalDate.parse(stringFecha,formato);

                cc.setFechaApertura(fechaApertura);

                Cliente cliente = banco.consultarCliente(Integer.parseInt(datos[4].trim()));
                if(cliente != null){
                    cliente.getCuentas().add(cc);
                }else {
                    System.out.println("Cliente: " + datos[4] + " no encontrado");
                }
            });
    }

    public static List<String> obtenerCuentas() throws IOException {

        String ruta = "src/main/resources/cuentas.txt";
        String lineaActual;
        List<String> cuentas = new ArrayList<String>();

        try (FileReader lectorArchivo = new FileReader(ruta);
             BufferedReader lectorLinea = new BufferedReader(lectorArchivo)) {

            while ((lineaActual = lectorLinea.readLine()) != null) {
                cuentas.add(lineaActual);
            }
            System.out.println("------------- TEXTO CUENTAS ------------");
            cuentas.forEach(cuenta -> System.out.println(cuenta));
        }
        return cuentas;
    }
}