package com.asistencia.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Asistencia {
    private Alumno alumno;
    private Materia materia;
    private LocalDate fecha;
    private boolean presente;

    public Asistencia(Alumno alumno, Materia materia, LocalDate fecha, boolean presente) {
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = fecha;
        this.presente = presente;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isPresente() {
        return presente;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Alumno=" + alumno.getNombre() +
                ", Materia=" + materia.getNombreMateria() +
                ", Fecha=" + fecha.format(formatter) +
                ", Presente=" + presente;
    }
}