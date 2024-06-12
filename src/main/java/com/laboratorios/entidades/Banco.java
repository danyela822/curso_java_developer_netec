package com.laboratorios.entidades;

import com.laboratorios.servicios.ServicioClientes;

import java.util.Objects;
import java.util.TreeSet;

public class Banco implements ServicioClientes {
    private String nombre;
    private Domicilio domicilio;
    private String rtc;
    private String telefono;
    private TreeSet<Cliente> clientes;

    public Banco(String nombre, Domicilio domicilio, String rtc, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rtc = rtc;
        this.telefono = telefono;
        this.clientes = new TreeSet<Cliente>();
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

    public String getRtc() {
        return rtc;
    }

    public void setRtc(String rtc) {
        this.rtc = rtc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TreeSet<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(TreeSet<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "\nnombre='" + nombre + '\'' +
                "domicilio=" + domicilio +
                "rtc='" + rtc + '\'' +
                "telefono='" + telefono + '\'' +
                "clientes=" + clientes +
                '}';
    }

    public String mostrarInfoBanco() {
        return "//------------------------------------- Informacion Banco --------------------------------------//"
                +"\n- nombre: " + nombre
                +"\n- domicilio: " + domicilio
                +"\n- rtc: " + rtc
                +"\n- telefono: " + telefono;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        if(clientes.isEmpty() || !clientes.contains(cliente)){
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Integer numero) {
        return clientes.removeIf(cliente -> Objects.equals(cliente.getNumero(), numero));
    }


    @Override
    public Cliente consultarCliente(Integer numero) {
        return clientes.parallelStream()
                .filter(cliente -> Objects.equals(cliente.getNumero(), numero))
                .findFirst()
                .orElse(null);
    }

    @Override
    public TreeSet<Cliente> obtenerClientes() {
        return new TreeSet<Cliente>(clientes);
    }

    @Override
    public Cliente buscarClientesPorRFC(String rfc) {
        return clientes.parallelStream()
                .filter(cliente -> cliente.getRfc().equals(rfc))
                .findFirst()
                .orElse(null);
    }
}
