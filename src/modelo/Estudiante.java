/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Estudiante {
    private int id, carnet, edad;
    private String nombre, apellido, unviersidad;
    private boolean estado;
    
    public Estudiante(){}

    public Estudiante(int id, int carnet, String nombre, String apellido, String unviersidad, int edad, boolean estado) {
        this.id = id;
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.unviersidad = unviersidad;
        this.edad = edad;
        this.estado = estado;
    }

    public Estudiante(int carnet, String nombre, String apellido, String unviersidad, int edad, boolean estado) {
        this.carnet = carnet;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.unviersidad = unviersidad;
        this.estado = estado;
    }

    public Estudiante(String nombre, String apellido, String unviersidad, int edad, boolean estado) {
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.unviersidad = unviersidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
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

    public String getUnviersidad() {
        return unviersidad;
    }

    public void setUnviersidad(String unviersidad) {
        this.unviersidad = unviersidad;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
