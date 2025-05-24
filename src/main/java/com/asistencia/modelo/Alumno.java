/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asistencia.modelo;

/**
 * @author NCAULA401
 */
public class Alumno extends Persona {

    private String carrera;

    public Alumno(String carrera, String nombre, String apellido, int dni, String legajo) {
        super(nombre, apellido, dni, legajo);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", carrera='" + carrera + '\'' +
                "Rol= Alumno" +
                "\n================="
                ;
    }

    //    private boolean validarPersona(Persona persona) {
//        Alumno alu = (Alumno) persona;
//        if (alu == null || alu.getNombre() == null || alu.getNombre().isEmpty())
//            return false;
//        if (alu == null || alu.getApellido() == null || alu.getApellido().isEmpty())
//            return false;
//        if (alu == null || alu.getDni() < 0)
//            return false;
//        if (alu == null || alu.getLegajo() == null || alu.getLegajo().isEmpty())
//            return false;
//
//        return true;
//    }
}
