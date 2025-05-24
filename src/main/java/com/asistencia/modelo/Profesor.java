/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asistencia.modelo;

/**
 * @author NCAULA401
 */
public class Profesor extends Persona {

    private String catedras;

    public Profesor(String catedras, String nombre, String apellido, int dni, String legajo) {
        super(nombre, apellido, dni, legajo);
        this.catedras = catedras;
    }

    public String getCatedras() {
        return catedras;
    }

    public void setCatedras(String catedras) {
        this.catedras = catedras;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Rol=Profesor" +
                "\n=================";
    }
}
