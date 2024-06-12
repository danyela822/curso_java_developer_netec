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

    public static void main(String[] args) throws IOException {
        System.out.println("\n// ------------------------------------------ PRACTCA 8 --------------------------------------------  //");

        Domicilio domicilio5 = new Domicilio("Calle 5",5, "Colonia 5", "Estado 5",121);
        Domicilio domicilio6 = new Domicilio("Calle 6",6, "Colonia 6", "Estado 6",141);
        Domicilio domicilio7 = new Domicilio("Calle 7",7, "Colonia 7", "Estado 7",151);

        Cliente cliente1 = new Cliente(1,"Cliente 1", domicilio5, "rfc 1", "Telefono 1", LocalDate.of(1990,1,1));
        Cliente cliente2 = new Cliente(2,"Cliente 2", domicilio6, "rfc 2", "Telefono 2", LocalDate.of(1990,2,2));
        Cliente cliente3 = new Cliente(3,"Cliente 3", domicilio7, "rfc 3", "Telefono 3", LocalDate.of(1990,3,3));

        List<Cuenta> cuentas = crearCuentas(obtenerCuentas());

        cliente1.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 1).toList());
        cliente2.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 2).toList());
        cliente3.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 3).toList());

        List<Cliente> clientes = List.of(cliente1,cliente2,cliente3);
        clientes.forEach(cliente -> {
            System.out.println("NUMERO CLIENTE: "+cliente.getNumero());
            cliente.getCuentas().forEach(cuenta -> System.out.println(cuenta.toString()));
        });
    }

    public static List<Cuenta> crearCuentas(List<String> listaCuentas){

        List<Cuenta> cuentasAhorro = listaCuentas.stream().filter(cuentaAhorro -> cuentaAhorro.startsWith("CA"))
                .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
                .map(cuentaAH -> {
                    String datos[] = cuentaAH.split(",");
                    Cuenta ca = new CuentaDeAhorro(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[4].trim()),Double.parseDouble(datos[3].trim()));

                    String stringFecha = datos[1].trim();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaApertura = LocalDate.parse(stringFecha,formato);

                    ca.setFechaApertura(fechaApertura);
                    return ca;
                }).toList();

        List<Cuenta> cuentasCheque = listaCuentas.stream().filter(cuentaCheque -> cuentaCheque.startsWith("CC"))
                .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
                .map(cuentaCC -> {
                    String datos[] = cuentaCC.split(",");
                    Cuenta cc = new CuentaDeCheque(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[4].trim()),Double.parseDouble(datos[3].trim()));

                    String stringFecha = datos[1].trim();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaApertura = LocalDate.parse(stringFecha,formato);

                    cc.setFechaApertura(fechaApertura);
                    return cc;
                }).toList();

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.addAll(cuentasAhorro);
        cuentas.addAll(cuentasCheque);

        return cuentas;
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