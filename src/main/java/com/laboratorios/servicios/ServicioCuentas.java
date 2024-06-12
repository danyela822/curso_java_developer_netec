package com.laboratorios.servicios;

import com.laboratorios.entidades.Cuenta;

import java.util.ArrayList;

public interface ServicioCuentas {
    boolean agregarCuenta(Cuenta cuenta);
    boolean cancelarCuenta(Integer numero);
    void abonarCuenta(Integer numero,Double abono);
    void retirar(Integer numero, Double retiro);
    ArrayList<Cuenta> obtenerCuentas();
}
