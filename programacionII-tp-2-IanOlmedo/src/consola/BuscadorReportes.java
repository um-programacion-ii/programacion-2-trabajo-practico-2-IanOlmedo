package consola;

import Gestores.GestorPrestamos;
import Gestores.GestorUsuarios;
import Modelo.RecursoDigital;
import Modelo.Usuario;

import java.util.Map;

public class BuscadorReportes {

    public static void menuBusqueda(GestorUsuarios gestorUsuarios, GestorPrestamos gestorPrestamos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n-- MENÚ DE REPORTES --");
            System.out.println("1. Recursos más prestados");
            System.out.println("2. Usuarios más activos");
            System.out.println("3. Categorías más solicitadas");
            System.out.println("0. Volver");

            int opcion = Consola.leerEntero("Seleccioná una opción: ");

            switch (opcion) {
                case 1 -> {
                    if (gestorPrestamos.getPrestamosPorRecurso().isEmpty()) {
                        System.out.println("Hasta el momento no hay recursos prestados.");
                    } else {
                        gestorPrestamos.mostrarRecursosMasPrestados();
                    }
                }
                case 2 -> {
                    if (gestorPrestamos.getContadorUsuariosActivos().isEmpty()) {
                        System.out.println("Hasta el momento no hay usuarios registrados con préstamos.");
                    } else {
                        gestorPrestamos.mostrarUsuariosMasActivos();
                    }
                }
                case 3 -> {
                    if (gestorPrestamos.getPrestamosPorCategoria().isEmpty()) {
                        System.out.println("Hasta el momento no hay estadísticas por categorías.");
                    } else {
                        gestorPrestamos.mostrarEstadisticasPorCategoria();
                    }
                }
                case 0 -> volver = true;
                default -> System.out.println("No existe la opción " + opcion);
            }
        }

    }
}
