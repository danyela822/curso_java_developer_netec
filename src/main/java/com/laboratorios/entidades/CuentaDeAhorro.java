package com.laboratorios.entidades;

public class CuentaDeAhorro extends Cuenta{

    private Double tasaInteresMensual;

    public CuentaDeAhorro(Double saldo, Integer numero, Double tasaInteresMensual) {
        super(saldo, numero);
        this.tasaInteresMensual = tasaInteresMensual;
    }

    public Double calcularIntereses(){
        return 0.0;
    }
}
