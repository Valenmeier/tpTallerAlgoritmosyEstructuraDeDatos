package com.asistencia.gestores;

import com.asistencia.modelo.*;

import java.util.Scanner;
import java.util.Stack;

import static com.asistencia.repositorio.RepositorioDeDatos.materias;
import static com.asistencia.repositorio.RepositorioDeDatos.personas;

public class GestorData {
    private final Scanner teclado = new Scanner(System.in);
    private final Stack<Operacion> pilaDeshacer = new Stack<>();
    private final Stack<Operacion> pilaRehacer = new Stack<>();

    public void listar() {
        int cantidadPersonas = 0;
        System.out.println("===============\n\tPersonas\n===============");
        for (Persona persona : personas) {
            if (persona != null) {
                System.out.println(persona);
                cantidadPersonas++;
            }
        }
        if (cantidadPersonas == 0) {
            System.out.println("No hay personas inscriptas en la DB");
        }

    }

    public void listar(String data) {
        switch (data) {
            case "materias" -> {
                int cantidadMaterias = 0;
                System.out.println("===============\n\tMaterias\n===============");
                for (Materia materia : materias) {
                    if (materia != null) {
                        System.out.println(materia);
                        cantidadMaterias++;
                    }
                }
                if (cantidadMaterias == 0) {
                    System.out.println("No hay materias inscriptas en la DB");
                } else {
                    System.out.println("Total de materias= " + cantidadMaterias + ", materias permitidas en la db=" + materias.length);
                }
            }
            case "alumnos" -> {
                int cantidadAlumnos = 0;
                System.out.println("===============\nAlumnos\n===============");
                for (Persona persona : personas) {

                    if (persona instanceof Alumno) {
                        System.out.println(persona);
                        System.out.println("===============\n");
                        cantidadAlumnos++;
                    }
                }
                if (cantidadAlumnos == 0) {
                    System.out.println("No hay alumnos inscriptos en la DB");
                }
            }
            case "profesores" -> {
                int cantidadProfesores = 0;
                System.out.println("===============\nProfesores\n===============");
                for (Persona persona : personas) {
                    if (persona instanceof Profesor) {
                        System.out.println(persona);
                        System.out.println("===============\n");
                        cantidadProfesores++;
                    }
                }
                if (cantidadProfesores == 0) {
                    System.out.println("No hay profesores inscriptos en la DB");
                }
            }
            default -> System.err.println("Ha ocurrido un error, porfavor intentalo nuevamente");
        }
    }

    public void agregar(String data) {
        switch (data) {
            case "materias" -> {
                int cantidadEspacioOcupado = 0;
                for (Materia value : materias) {
                    if (value != null) {
                        cantidadEspacioOcupado++;
                    }
                }
                if (cantidadEspacioOcupado == materias.length) {
                    System.out.println("No queda espacio disponible para agregar otra materia.");
                } else {
                    Materia materia = crearMateria();
                    for (int i = 0; i < materias.length; i++) {
                        if (materias[i] == null) {
                            materias[i] = materia;
                            return;
                        }
                    }
                    System.out.println("La materia se ha agregado correctamente.");
                }
            }
            case "alumnos" -> {
                int cantidadEspacioOcupado = 0;
                for (Persona persona : personas) {
                    if (persona != null) {
                        cantidadEspacioOcupado++;
                    }
                }
                if (cantidadEspacioOcupado == personas.length) {
                    System.out.println("No queda espacio disponible para agregar otra persona.");
                } else {
                    Alumno alumno = crearAlumno();
                    for (int i = 0; i < personas.length; i++) {
                        if (personas[i] == null) {
                            personas[i] = alumno;
                            pilaDeshacer.push(new Operacion("alta", null, alumno.clone(), i));
                            pilaRehacer.clear();
                            System.out.println("El alumno se ha agregado correctamente.");
                            return;
                        }
                    }
                }
            }
            case "profesores" -> {
                int cantidadEspacioOcupado = 0;
                for (Persona persona : personas) {
                    if (persona != null) {
                        cantidadEspacioOcupado++;
                    }
                }
                if (cantidadEspacioOcupado == personas.length) {
                    System.out.println("No queda espacio disponible para agregar otra persona.");
                } else {
                    Profesor profesor = crearProfesor();
                    for (int i = 0; i < personas.length; i++) {
                        if (personas[i] == null) {
                            personas[i] = profesor;
                            pilaDeshacer.push(new Operacion("alta", null, profesor.clone(), i));
                            pilaRehacer.clear();
                            System.out.println("El profesor se ha agregado correctamente.");
                            return;
                        }
                    }
                }
            }
        }
    }

    public void eliminar(String data) {
        switch (data) {
            case "materias" -> {
                int cantidadMaterias = 0;
                for (int i = 0; i < materias.length; i++) {
                    if (materias[i] != null) {
                        System.out.println((i + 1) + "-" + materias[i]);
                        cantidadMaterias++;
                    }
                }
                if (cantidadMaterias == 0) {
                    System.out.println("No hay materias para eliminar.");
                } else {
                    System.out.print("Selecciona una materia de las listadas para eliminar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        if (materias[(seleccion - 1)] != null) {
                            materias[(seleccion - 1)] = null;
                            System.out.println("Se ha eliminado correctamente");
                        } else {
                            System.out.println("Error al eliminar, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al eliminar, por favor intente de nuevo con números válidos.");
                    }
                }
            }

            case "alumnos" -> {
                int cantidadAlumnos = 0;
                for (int i = 0; i < personas.length; i++) {
                    if (personas[i] != null && personas[i] instanceof Alumno) {
                        System.out.println((i + 1) + "-" + personas[i]);
                        cantidadAlumnos++;
                    }
                }
                if (cantidadAlumnos == 0) {
                    System.out.println("No hay alumnos para eliminar.");
                } else {
                    System.out.print("Selecciona un alumno de los listados para eliminar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        int index = seleccion - 1;
                        if (personas[index] != null && personas[index] instanceof Alumno) {
                            Persona eliminada = personas[index];
                            personas[index] = null;
                            pilaDeshacer.push(new Operacion("baja", eliminada.clone(), null, index));
                            pilaRehacer.clear();
                            System.out.println("Se ha eliminado correctamente");
                        } else {
                            System.out.println("Error al eliminar, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al eliminar, por favor intente de nuevo con números válidos.");
                    }
                }
            }

            case "profesores" -> {
                int cantidadProfesores = 0;
                for (int i = 0; i < personas.length; i++) {
                    if (personas[i] != null && personas[i] instanceof Profesor) {
                        System.out.println((i + 1) + "-" + personas[i]);
                        cantidadProfesores++;
                    }
                }
                if (cantidadProfesores == 0) {
                    System.out.println("No hay profesores para eliminar.");
                } else {
                    System.out.print("Selecciona un profesor de los listados para eliminar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        int index = seleccion - 1;
                        if (personas[index] != null && personas[index] instanceof Profesor) {
                            Persona eliminada = personas[index];
                            personas[index] = null;
                            pilaDeshacer.push(new Operacion("baja", eliminada.clone(), null, index));
                            pilaRehacer.clear();
                            System.out.println("Se ha eliminado correctamente");
                        } else {
                            System.out.println("Error al eliminar, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al eliminar, por favor intente de nuevo con números válidos.");
                    }
                }
            }
        }
    }

    public void actualizar(String data) {
        switch (data) {
            case "materias" -> {
                int cantidadMaterias = 0;
                for (int i = 0; i < materias.length; i++) {
                    if (materias[i] != null) {
                        System.out.println((i + 1) + "-" + materias[i]);
                        cantidadMaterias++;
                    }
                }
                if (cantidadMaterias == 0) {
                    System.out.println("No hay materias para actualizar.");
                } else {
                    System.out.print("Selecciona una materia de las listadas para actualizar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        if (materias[seleccion - 1] != null) {
                            actualizarMateria(materias[seleccion - 1]);
                        } else {
                            System.out.println("Error al actualizar, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al actualizar, por favor intente de nuevo con números válidos.");
                    }
                }
            }

            case "alumnos" -> {
                int cantidadAlumnos = 0;
                for (int i = 0; i < personas.length; i++) {
                    if (personas[i] != null && personas[i] instanceof Alumno) {
                        System.out.println((i + 1) + "-" + personas[i]);
                        cantidadAlumnos++;
                    }
                }
                if (cantidadAlumnos == 0) {
                    System.out.println("No hay alumnos para actualizar.");
                } else {
                    System.out.print("Selecciona un alumno de los listados para actualizar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        int index = seleccion - 1;
                        if (personas[index] != null && personas[index] instanceof Alumno) {
                            Persona antes = personas[index].clone();
                            actualizarAlumno((Alumno) personas[index]);
                            Persona despues = personas[index].clone();
                            pilaDeshacer.push(new Operacion("modificacion", antes, despues, index));
                            pilaRehacer.clear();
                            System.out.println("Actualización registrada correctamente.");
                        } else {
                            System.out.println("Error al actualizar alumno, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al actualizar alumno, por favor intente de nuevo con números válidos.");
                    }
                }
            }

            case "profesores" -> {
                int cantidadProfesores = 0;
                for (int i = 0; i < personas.length; i++) {
                    if (personas[i] != null && personas[i] instanceof Profesor) {
                        System.out.println((i + 1) + "-" + personas[i]);
                        cantidadProfesores++;
                    }
                }
                if (cantidadProfesores == 0) {
                    System.out.println("No hay profesores para actualizar.");
                } else {
                    System.out.print("Selecciona un profesor de los listados para actualizar:");
                    int seleccion = teclado.nextInt();
                    teclado.nextLine();
                    try {
                        int index = seleccion - 1;
                        if (personas[index] != null && personas[index] instanceof Profesor) {
                            Persona antes = personas[index].clone();
                            actualizarProfesor((Profesor) personas[index]);
                            Persona despues = personas[index].clone();
                            pilaDeshacer.push(new Operacion("modificacion", antes, despues, index));
                            pilaRehacer.clear();
                            System.out.println("Actualización registrada correctamente.");
                        } else {
                            System.out.println("Error al actualizar profesor, intentalo nuevamente");
                        }
                    } catch (RuntimeException e) {
                        System.err.println("Ha ocurrido un error al actualizar profesor, por favor intente de nuevo con números válidos.");
                    }
                }
            }
        }
    }


    private Materia crearMateria() {
        String nombre;
        String horario;
        String catedra;
        System.out.print("Selecciona el nombre de la materia:");
        nombre = teclado.nextLine();
        System.out.print("Selecciona el horario de la materia:");
        horario = teclado.nextLine();
        System.out.print("Selecciona la catedra de la materia:");
        catedra = teclado.nextLine();
        return new Materia(nombre, catedra, horario);
    }

    private Alumno crearAlumno() {
        String carrera;
        String nombre;
        int dni;
        String apellido;
        String legajo;
        System.out.print("Selecciona el nombre del alumno:");
        nombre = teclado.nextLine();
        System.out.print("Selecciona el apellido del alumno:");
        apellido = teclado.nextLine();
        System.out.print("Selecciona la carrera del alumno:");
        carrera = teclado.nextLine();
        System.out.print("Ingresa el legajo del alumno:");
        legajo = teclado.nextLine();
        System.out.println("Ingresa el dni del alumno");
        dni = teclado.nextInt();
        teclado.nextLine();
        return new Alumno(carrera, nombre, apellido, dni, legajo);
    }

    private Profesor crearProfesor() {
        String carrera;
        String nombre;
        int dni;
        String apellido;
        String legajo;
        System.out.print("Selecciona el nombre del profesor:");
        nombre = teclado.nextLine();
        System.out.print("Selecciona el apellido del profesor:");
        apellido = teclado.nextLine();
        System.out.print("Selecciona la catedra y carrera del profesor:");
        carrera = teclado.nextLine();
        System.out.print("Ingresa el legajo del profesor:");
        legajo = teclado.nextLine();
        System.out.println("Ingresa el dni del profesor");
        dni = teclado.nextInt();
        teclado.nextLine();
        return new Profesor(carrera, nombre, apellido, dni, legajo);
    }

    private void actualizarMateria(Materia materia) {
        System.out.println(materia);
        boolean sinDecision = true;
        while (sinDecision) {
            System.out.println("Que deseas modificarle? 1-Nombre 2-Catedra 3-Horario 4-Nada");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Selecciona el nuevo nombre:");
                        String nuevoNombre = teclado.nextLine();
                        materia.setNombreMateria(nuevoNombre);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 2 -> {
                        System.out.println("Selecciona la nueva catedra:");
                        String nuevaCatedra = teclado.nextLine();
                        materia.setCatedra(nuevaCatedra);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 3 -> {
                        System.out.println("Selecciona el nuevo horario:");
                        String nuevoHorario = teclado.nextLine();
                        materia.setHorario(nuevoHorario);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 4 -> sinDecision = false;
                }
                if (opcion != 4) {
                    System.out.println("Quieres realizar algún otro cambio? 1- Si 2-No");
                    opcion = teclado.nextInt();
                    teclado.nextLine();
                    if (opcion == 2) {
                        sinDecision = false;
                    }
                }
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error en la actualización, intentalo nuevamente.");
            }
        }

    }

    private void actualizarAlumno(Alumno alumno) {
        System.out.println(alumno);
        boolean sinDecision = true;
        while (sinDecision) {
            System.out.println("Que deseas modificarle?" +
                    " 1-Nombre 2-Apellido 3-Carrera" +
                    " 4-DNI 5-Legajo 6-Nada");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Selecciona el nuevo nombre:");
                        String nuevoNombre = teclado.nextLine();
                        alumno.setNombre(nuevoNombre);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 2 -> {
                        System.out.println("Selecciona el nuevo apellido:");
                        String nuevaApellido = teclado.nextLine();
                        alumno.setApellido(nuevaApellido);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 3 -> {
                        System.out.println("Selecciona la nueva carrera:");
                        String nuevaCarrera = teclado.nextLine();
                        alumno.setCarrera(nuevaCarrera);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 4 -> {
                        System.out.println("Selecciona el nuevo DNI:");
                        int nuevoDni = teclado.nextInt();
                        teclado.nextLine();
                        alumno.setDni(nuevoDni);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 5 -> {
                        System.out.println("Selecciona el nuevo legajo:");
                        String nuevoLegajo = teclado.nextLine();
                        alumno.setLegajo(nuevoLegajo);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 6 -> sinDecision = false;
                }
                if (opcion != 4) {
                    System.out.println("Quieres realizar algún otro cambio? 1- Si 2-No");
                    opcion = teclado.nextInt();
                    teclado.nextLine();
                    if (opcion == 2) {
                        sinDecision = false;
                    }
                }
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error en la actualización, intentalo nuevamente.");
            }
        }

    }

    private void actualizarProfesor(Profesor profesor) {
        System.out.println(profesor);
        boolean sinDecision = true;
        while (sinDecision) {
            System.out.println("Que deseas modificarle?" +
                    " 1-Nombre 2-Apellido 3-Catedras" +
                    " 4-DNI 5-Legajo 6-Nada");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Selecciona el nuevo nombre:");
                        String nuevoNombre = teclado.nextLine();
                        profesor.setNombre(nuevoNombre);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 2 -> {
                        System.out.println("Selecciona el nuevo apellido:");
                        String nuevaApellido = teclado.nextLine();
                        profesor.setApellido(nuevaApellido);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 3 -> {
                        System.out.println("Selecciona la nueva catedra:");
                        String nuevaCatedra = teclado.nextLine();
                        profesor.setCatedras(nuevaCatedra);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 4 -> {
                        System.out.println("Selecciona el nuevo DNI:");
                        int nuevoDni = teclado.nextInt();
                        teclado.nextLine();
                        profesor.setDni(nuevoDni);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 5 -> {
                        System.out.println("Selecciona el nuevo legajo:");
                        String nuevoLegajo = teclado.nextLine();
                        profesor.setLegajo(nuevoLegajo);
                        System.out.println("Cambio realizado correctamente");
                    }
                    case 6 -> sinDecision = false;
                }
                if (opcion != 4) {
                    System.out.println("Quieres realizar algún otro cambio? 1- Si 2-No");
                    opcion = teclado.nextInt();
                    teclado.nextLine();
                    if (opcion == 2) {
                        sinDecision = false;
                    }
                }
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error en la actualización, intentalo nuevamente.");
            }
        }

    }

    public void deshacer() {
        if (pilaDeshacer.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        Operacion op = pilaDeshacer.pop();
        switch (op.getTipo()) {
            case "alta" -> personas[op.getIndice()] = null;
            case "baja" -> personas[op.getIndice()] = op.getEstadoAnterior().clone();
            case "modificacion" -> personas[op.getIndice()] = op.getEstadoAnterior().clone();
        }
        pilaRehacer.push(op);
        System.out.println("Operación deshecha correctamente.");
    }

    public void rehacer() {
        if (pilaRehacer.isEmpty()) {
            System.out.println("No hay operaciones para rehacer.");
            return;
        }

        Operacion op = pilaRehacer.pop();
        switch (op.getTipo()) {
            case "alta" -> personas[op.getIndice()] = op.getEstadoNuevo().clone();
            case "baja" -> personas[op.getIndice()] = null;
            case "modificacion" -> personas[op.getIndice()] = op.getEstadoNuevo().clone();
        }
        pilaDeshacer.push(op);
        System.out.println("Operación rehecha correctamente.");
    }

}
