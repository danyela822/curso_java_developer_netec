package com.laboratorios.laboratorio10;

import com.laboratorios.entidades.Cuenta;
import com.laboratorios.entidades.CuentaDeAhorro;
import com.laboratorios.entidades.CuentaDeCheque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class CuentaCallable implements Callable<List<Cuenta>> {

    private String ruta;

    public CuentaCallable(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<Cuenta> call() throws Exception {
        List<Cuenta> cuentas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            List<String> cuentasString = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                cuentasString.add(line);
            }
            cuentas.addAll(crearCuentas(cuentasString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cuentas;
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
}
