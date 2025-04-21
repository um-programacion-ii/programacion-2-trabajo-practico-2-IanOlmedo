package consola;

import Gestores.*;
import Modelo.*;

public class MenuReservas {

    public static void mostrarMenu(GestorReservas gestorReservas, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n--- MENÚ DE RESERVAS ---");
            System.out.println("1. Reservar un recurso");
            System.out.println("2. Ver reservas de un recurso");
            System.out.println("3. Procesar próxima reserva");
            System.out.println("0. Volver");

            int opcion = Consola.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    reservarRecurso(gestorReservas, gestorUsuarios, gestorRecursos);
                    break;
                case 2:
                    verReservas(gestorReservas, gestorRecursos);
                    break;
                case 3:
                    procesarSiguienteReserva(gestorReservas, gestorRecursos);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void reservarRecurso(GestorReservas gestorReservas, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        int idUsuario = Consola.leerEntero("Ingrese el ID del usuario: ");
        try {
            Usuario usuario = gestorUsuarios.buscarPorId(idUsuario);

            String titulo = Consola.leerLinea("Ingrese el título del recurso: ");
            RecursoDigital recurso = gestorRecursos.buscarTituloExacto(titulo);

            gestorReservas.reservarRecurso(usuario, recurso);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verReservas(GestorReservas gestorReservas, GestorRecursos gestorRecursos) {
        String titulo = Consola.leerLinea("Ingrese el título del recurso: ");
        try {
            RecursoDigital recurso = gestorRecursos.buscarTituloExacto(titulo);
            gestorReservas.mostrarReservas(recurso);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void procesarSiguienteReserva(GestorReservas gestorReservas, GestorRecursos gestorRecursos) {
        String titulo = Consola.leerLinea("Ingrese el título del recurso: ");
        try {
            RecursoDigital recurso = gestorRecursos.buscarTituloExacto(titulo);
            gestorReservas.procesarReserva(recurso);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
