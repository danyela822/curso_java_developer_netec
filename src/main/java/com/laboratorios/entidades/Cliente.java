package com.laboratorios.entidades;

import com.laboratorios.servicios.ServicioCuentas;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements ServicioCuentas, Comparable<Cliente>{

    private Integer numero;
    private String nombre;
    private Domicilio domicilio;
    private String rfc;
    private String telefono;
    private ArrayList<Cuenta> cuentas;
    private String fechaNacimiento;

    public Cliente(Integer numero, String nombre, Domicilio domicilio, String rfc, String telefono, String fechaNacimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentas = new ArrayList<Cuenta>();
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        if(cuentas.isEmpty() || !cuentas.contains(cuenta)){
            cuentas.add(cuenta);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelarCuenta(Integer numero) {
        return cuentas.removeIf(cuenta -> Objects.equals(cuenta.getNumero(), numero));
    }

    @Override
    public void abonarCuenta(Integer numero, Double abono) {
        cuentas.stream()
                .filter(cuenta -> Objects.equals(cuenta.getNumero(), numero))
                .findFirst()
                .ifPresent(cuenta -> cuenta.setSaldo(cuenta.getSaldo() + abono));
    }


    @Override
    public void retirar(Integer numero, Double retiro) {
        cuentas.stream()
                .filter(cuenta -> Objects.equals(cuenta.getNumero(), numero))
                .findFirst()
                .ifPresent(cuenta -> cuenta.setSaldo(cuenta.getSaldo() - retiro));
    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    @Override
    public int compareTo(Cliente otroCliente) {
        return numero - otroCliente.getNumero();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", numero: " + numero +
                ", nombre: " + nombre +
                ", domicilio: " + domicilio +
                ", rtc: " + rfc +
                ", telefono: " + telefono +
                ", cuentas: " + cuentas +
                ", fechaNacimiento: " + fechaNacimiento +
                '}';
    }
    public String mostrarInfoCuentas() {
        String info = "Cuentas: \n";
        for (Cuenta cuenta: cuentas){
            info += cuenta.toString() + "\n";
        }
        return info;
    }

    public String mostrarInfoCliente() {
        return "//------------------------------------- Informacion Cliente --------------------------------------//"
                +"\n- numero: " + numero
                +"\n- nombre: " + nombre
                +"\n- domicilio: " + domicilio
                +"\n- rtc: " + rfc
                +"\n- telefono: " + telefono
                +"\n- fecha de nacimiento: " + fechaNacimiento;
    }
}
