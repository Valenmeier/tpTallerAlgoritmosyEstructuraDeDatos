package com.asistencia.gestores;

import com.asistencia.modelo.Alumno;
import com.asistencia.modelo.Asistencia;
import com.asistencia.modelo.Materia;
import com.asistencia.modelo.Persona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.asistencia.repositorio.RepositorioDeDatos.asistencias;
import static com.asistencia.repositorio.RepositorioDeDatos.materias;
import static com.asistencia.repositorio.RepositorioDeDatos.personas;

public class GestorAsistencia {
    private final Scanner teclado = new Scanner(System.in);

    public void registrar() {
        try {
            Alumno alumno = seleccionarAlumno();
            if (alumno == null) {
                System.out.println("Alumno no válido.");
                return;
            }
            Materia materia = seleccionarMateria();
            if (materia == null) {
                System.out.println("Materia no válida.");
                return;
            }
            System.out.print("Ingrese fecha (DD-MM-YYYY): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(teclado.nextLine(), formatter);
            System.out.print("¿Estuvo presente? 1-Sí 2-No: ");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            boolean presente = opcion == 1;

            Asistencia nueva = new Asistencia(alumno, materia, fecha, presente);
            for (int i = 0; i < asistencias.length; i++) {
                if (asistencias[i] == null) {
                    asistencias[i] = nueva;
                    System.out.println("Asistencia registrada correctamente.");
                    return;
                }
            }
            System.out.println("No hay espacio para nuevas asistencias.");
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida.");
        } catch (Exception e) {
            System.out.println("Error al registrar asistencia.");
            teclado.nextLine();
        }
    }

    private Alumno seleccionarAlumno() {
        System.out.println("Seleccione alumno:");
        for (int i = 0; i < personas.length; i++) {
            Persona p = personas[i];
            if (p instanceof Alumno) {
                System.out.println((i + 1) + "-" + p);
            }
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        if (opcion > 0 && opcion <= personas.length && personas[opcion - 1] instanceof Alumno) {
            return (Alumno) personas[opcion - 1];
        }
        return null;
    }

    private Materia seleccionarMateria() {
        System.out.println("Seleccione materia:");
        for (int i = 0; i < materias.length; i++) {
            if (materias[i] != null) {
                System.out.println((i + 1) + "-" + materias[i]);
            }
        }
        int opcion = teclado.nextInt();
        teclado.nextLine();
        if (opcion > 0 && opcion <= materias.length && materias[opcion - 1] != null) {
            return materias[opcion - 1];
        }
        return null;
    }

    public void listar() {
        ordenarPorFecha(asistencias);
        System.out.println("===============\nAsistencias\n===============");
        for (Asistencia asistencia : asistencias) {
            if (asistencia != null) {
                System.out.println(asistencia);
            }
        }
    }

    private void ordenarPorFecha(Asistencia[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] != null && arr[j + 1] != null && arr[j].getFecha().isAfter(arr[j + 1].getFecha())) {
                    Asistencia temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}