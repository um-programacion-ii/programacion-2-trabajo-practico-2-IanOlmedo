package consola;

import Gestores.*;
import Modelo.*;

public class MenuPrestamos {

    public static void mostrarMenu(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        System.out.println("\n--- MENÚ DE PRÉSTAMOS ---");
        System.out.println("1. Realizar préstamo");
        System.out.println("2. Devolver recurso");
        System.out.println("3. Listar préstamos");
        System.out.println("0. Volver");

        int opcion = Consola.leerEntero("Seleccione una opción: ");

        try {
            switch (opcion) {
                case 1:
                    int idUsuario = Consola.leerEntero("Ingrese ID del usuario: ");
                    Usuario usuario = gestorUsuarios.buscarPorId(idUsuario);
                    String titulo = Consola.leerLinea("Ingrese título del recurso: ");
                    RecursoDigital recurso = gestorRecursos.buscarPorTitulo(titulo).get(0); // Suponiendo uno solo
                    gestorPrestamos.realizarPrestamo(usuario, recurso);
                    System.out.println("Préstamo realizado con éxito.");
                    break;
                case 2:
                    String tituloDevolver = Consola.leerLinea("Ingrese título del recurso a devolver: ");
                    RecursoDigital recursoDevolver = gestorRecursos.buscarPorTitulo(tituloDevolver).get(0);
                    gestorPrestamos.devolverRecurso(recursoDevolver);
                    System.out.println("Recurso devuelto correctamente.");
                    break;
                case 3:
                    gestorPrestamos.listarPrestamos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
