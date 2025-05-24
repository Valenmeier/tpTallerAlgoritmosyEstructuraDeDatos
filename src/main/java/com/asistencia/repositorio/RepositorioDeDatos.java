/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asistencia.repositorio;

import com.asistencia.modelo.Materia;
import com.asistencia.modelo.Persona;

/**
 * @author NCAULA401
 */
public abstract class RepositorioDeDatos {

    private final static int CANTIDAD_REGISTROS = 10;

    public static Persona[] personas = new Persona[CANTIDAD_REGISTROS];

    public static Materia[] materias = new Materia[CANTIDAD_REGISTROS];

    public static int indice = 0;


}
