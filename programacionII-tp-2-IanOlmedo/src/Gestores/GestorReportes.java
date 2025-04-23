package Gestores;

import Modelo.RecursoDigital;
import Modelo.Usuario;

import java.util.List;
import java.util.concurrent.*;

public class GestorReportes {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    private final GestorPrestamos gestorPrestamos;
    private final GestorUsuarios gestorUsuarios;

    public GestorReportes(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios) {
        this.gestorPrestamos = gestorPrestamos;
        this.gestorUsuarios = gestorUsuarios;
    }

    public void generarReporteMasPrestadosAsync() {
        Runnable tarea = () -> {
            System.out.println("\n[INICIO] Generando reporte de recursos más prestados...");
            try {
                Thread.sleep(2);
                List<RecursoDigital> masPrestados = gestorPrestamos.obtenerRecursosMasPrestados();
                System.out.println("\n[REPORTE] Recursos más prestados:");
                for (RecursoDigital r : masPrestados) {
                    System.out.println("- " + r.getTitulo());
                }
            } catch (InterruptedException e) {
                System.err.println("Error al generar reporte: " + e.getMessage());
            }
            System.out.println("[FIN] Reporte generado.\n");
        };

        executor.submit(tarea);
    }

    public void generarReporteUsuariosActivosAsync() {
        Runnable tarea = () -> {
            System.out.println("\n[INICIO] Generando reporte de usuarios más activos...");
            try {
                Thread.sleep(2);
                List<Usuario> activos = gestorPrestamos.obtenerUsuariosMasActivos();
                System.out.println("\n[REPORTE] Usuarios más activos:");
                for (Usuario u : activos) {
                    System.out.println("- " + u.getNombre());
                }
            } catch (InterruptedException e) {
                System.err.println("Error al generar reporte: " + e.getMessage());
            }
            System.out.println("[FIN] Reporte generado.\n");
        };

        executor.submit(tarea);
    }

    public void generarReporteEstadisticasPorCategoriaAsync() {
        Runnable tarea = () -> {
            System.out.println("\n[INICIO] Generando estadísticas por categoría...");
            try {
                Thread.sleep(2); // Simula carga
                gestorPrestamos.mostrarEstadisticasPorCategoria(); // Usamos directamente el método que imprime
            } catch (InterruptedException e) {
                System.err.println("Error al generar reporte: " + e.getMessage());
            }
            System.out.println("[FIN] Reporte generado.\n");
        };

        executor.submit(tarea);
    }

    public void apagar() {
        executor.shutdown();
    }
}
