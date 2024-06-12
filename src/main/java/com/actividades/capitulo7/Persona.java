package com.actividades.capitulo7;

public class Persona implements Comparable<Persona>{
    private String nombre;
    private String apellido;
    private Integer edad;
    private String ciudad;

    public Persona(String nombre, String apellido, Integer edad, String ciudad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Persona{ NOMBRE: " + nombre  + ", APELLIDO: " + apellido + ", EDAD: " + edad + " ,CIUDAD: " + ciudad + " }\n";
    }

    @Override
    public int compareTo(Persona persona) {
        return this.edad.compareTo(persona.getEdad());
    }
}
