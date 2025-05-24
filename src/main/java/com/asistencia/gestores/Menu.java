package com.asistencia.gestores;

import java.util.Scanner;

public class Menu {
    Scanner teclado = new Scanner(System.in);
    GestorData gestorData = new GestorData();

    public void iniciarMenu() {
        boolean menuAbierto = true;
        do {
            try {
                System.out.println("\n============== Sistema de Registro de faltas ================");
                System.out.println("1- Gestionar personas.");
                System.out.println("2- Gestionar materias.");
                System.out.println("3- Finalizar programa.");
                System.out.print("Seleccione una opción:");
                int opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1 -> gestionarPersonas();
                    case 2 -> menuGeneral("materias");
                    case 3 -> {
                        System.out.println("\nFinalizando programa...");
                        menuAbierto = false;
                    }
                    default -> System.err.println("----Selecciona una opción válida.----\n");
                }

            } catch (Exception e) {
                System.err.println("----Selecciona una opción válida.----\n");
                teclado.nextLine();
            }
        } while (menuAbierto);


    }

    public void gestionarPersonas() {
        boolean menuAbierto = true;
        do {
            try {
                System.out.println("\n============== Gestor de personas ================");
                System.out.println("1- Listar personas.");
                System.out.println("2- Trabajar con alumnos.");
                System.out.println("3- Trabajar con profesores.");
                System.out.println("4- Volver atras.");
                System.out.print("Selecciona una opción:");
                int opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1 -> gestorData.listar();
                    case 2 -> menuGeneral("alumnos");
                    case 3 -> menuGeneral("profesores");
                    case 4 -> menuAbierto = false;
                    default -> System.err.println("----Selecciona una opción válida.----\n");
                }
                if (opcion != 4) {
                    menuAbierto = continuarMenu("Personas");
                }
            } catch (Exception e) {
                System.err.println("----Selecciona una opción válida.----\n");
                teclado.nextLine();
            }
        } while (menuAbierto);
    }


    public void menuGeneral(String mostrar) {
        boolean menuAbierto = true;
        do {
            try {
                System.out.println("\n============== Gestor de " + mostrar + " ================");
                System.out.println("1- Listar " + mostrar + ".");
                System.out.println("2- Añadir " + mostrar + ".");
                System.out.println("3- Modificar " + mostrar + ".");
                System.out.println("4- Eliminar " + mostrar + ".");
                System.out.println("5- Volver atras.");
                System.out.print("Selecciona una opción:");
                int opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1 -> gestorData.listar(mostrar);
                    case 2 -> gestorData.agregar(mostrar);
                    case 3 -> gestorData.actualizar(mostrar);
                    case 4 -> gestorData.eliminar(mostrar);
                    case 5 -> menuAbierto = false;
                    default -> System.err.println("----Selecciona una opción válida.----\n");
                }
                if (opcion != 5) {
                    menuAbierto = continuarMenu(mostrar);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.err.println("----Selecciona una opción válida.----\n");
                teclado.nextLine();
            }
        } while (menuAbierto);
    }

    private boolean continuarMenu(String menu) {
        boolean subMenu = true;
        boolean continuar = false;

        do {
            try {
                System.out.print("================\nDesea continuar con el gestor de " + menu + "?\n1-Si\n2-No\n" +
                        "Selecciona una opción:");
                int opcion = teclado.nextInt();
                teclado.nextLine();
                if (opcion == 1) {
                    continuar = true;
                    subMenu = false;
                } else if (opcion == 2) {
                    continuar = false;
                    subMenu = false;
                }
            } catch (Exception e) {
                System.err.println("----Selecciona una opción válida.----\n");
                teclado.nextLine();
            }
        } while (subMenu);
        return continuar;
    }
}

//        Alumno a1 = new Alumno("ING SOFT", "LUCAS", "COELI", 33545874, "IS-874");
//        a1.crearPersona(a1);
//        Alumno a2 = new Alumno("ING SOFT", "CARLOS", "RORIGUEZ", 33545888, "IS-888");
//        a2.crearPersona(a2);
//        a2.listraToadaslasPesonas();