package com.laboratorios.laboratorio12;

import com.laboratorios.entidades.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MySQLConnection {

    // URL de la base de datos, usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Banco banco;

    public static void main(String[] args) {
        crearBanca();
        crearConexion();
    }

    public static void crearBanca (){
        Domicilio domicilio1 = new Domicilio("Calle 1",1, "Colonia 1", "Estado 1",111);
        Domicilio domicilio2 = new Domicilio("Calle 2",2, "Colonia 2", "Estado 2",222);
        Domicilio domicilio3 = new Domicilio("Calle 3",3, "Colonia 3", "Estado 3",333);
        Domicilio domicilioBanco = new Domicilio("Calle 4",4, "Colonia 4", "Estado 4",444);

        Cliente cliente1 = new Cliente(1,"Cliente 1", domicilio1, "rfc 1", "Telefono 1", LocalDate.of(1990,1,1));
        Cliente cliente2 = new Cliente(2,"Cliente 2", domicilio2, "rfc 2", "Telefono 2", LocalDate.of(1990,2,2));
        Cliente cliente3 = new Cliente(3,"Cliente 3", domicilio3, "rfc 3", "Telefono 3", LocalDate.of(1990,3,3));

        banco = new Banco("Banca Electronica",domicilioBanco,"rtc 4","Telefono 4");

        banco.getClientes().addAll(List.of(cliente1,cliente2,cliente3));
    }

    public static void crearConexion(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear una declaración
            statement = connection.createStatement();

            // Ejecutar una consulta
            resultSet = statement.executeQuery("SELECT * FROM banco.cuentas");

            // Procesar los resultados
            while (resultSet.next()) {
                asignarCuentas(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        banco.getClientes().forEach(cliente -> {
            cliente.getCuentas().forEach(cuenta -> {
                System.out.println("Cliente: " + cliente.getNumero() + " " + cuenta.toString());
            });
        });
    }

    public static void asignarCuentas(ResultSet resultSet) throws SQLException {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if(resultSet.getString("tipoCuenta").equals("CC")){
            Cuenta cuentaCC = new CuentaDeCheque(resultSet.getDouble("saldo"),resultSet.getInt("numero"), resultSet.getDouble("interes"));

            LocalDate fechaApertura = LocalDate.parse(resultSet.getString("fecha"),formato);
            cuentaCC.setFechaApertura(fechaApertura);

            Cliente cliente = banco.consultarCliente(resultSet.getInt("cliente"));
            if(cliente != null){
                cliente.getCuentas().add(cuentaCC);
            }else {
                System.out.println("Cliente: "+resultSet.getInt("cliente")+" no encontrado");
            }

        }else if(resultSet.getString("tipoCuenta").equals("CA")){
            Cuenta cuentaCA = new CuentaDeAhorro(resultSet.getDouble("saldo"),resultSet.getInt("numero"), resultSet.getDouble("interes"));

            LocalDate fechaApertura = LocalDate.parse(resultSet.getString("fecha"),formato);
            cuentaCA.setFechaApertura(fechaApertura);

            Cliente cliente = banco.consultarCliente(resultSet.getInt("cliente"));
            if(cliente != null){
                cliente.getCuentas().add(cuentaCA);
            }else {
                System.out.println("Cliente: "+resultSet.getInt("cliente")+" no encontrado");
            }
        }
    }
}

