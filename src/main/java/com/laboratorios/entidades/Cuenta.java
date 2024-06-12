package com.laboratorios.entidades;

public abstract class Cuenta implements Comparable<Cuenta>{

    private Integer numero;
    private String fechaApertura;
    private Double saldo;
    private String fechaCancelacion;

    public Cuenta(Double saldo, Integer numero) {
        this.saldo = saldo;
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public String toString() {
        return "{ numero: " + numero + ", fechaApertura: " + fechaApertura + ", saldo: " + saldo + ", fechaCancelacion: " + fechaCancelacion + " }";
    }

    @Override
    public int compareTo(Cuenta otraCuenta) {
        double a = otraCuenta.getSaldo() - saldo;
        return (int) a;
    }
}
