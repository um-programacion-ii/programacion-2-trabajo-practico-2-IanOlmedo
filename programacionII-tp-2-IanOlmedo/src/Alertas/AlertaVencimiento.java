package Alertas;

import Gestores.GestorPrestamos;
import Modelo.Prestamo;
import Gestores.GestorReservas;
import consola.*;

import java.time.LocalDate;
import java.util.List;

public class AlertaVencimiento {

    private GestorPrestamos gestorPrestamos;
    private GestorReservas gestorReservas;

    public AlertaVencimiento(GestorPrestamos gestorPrestamos, GestorReservas gestorReservas) {
        this.gestorPrestamos = gestorPrestamos;
        this.gestorReservas = gestorReservas;
    }

    public void verificarAlertas() {
        List<Prestamo> prestamosActivos = gestorPrestamos.listarPrestamosActivos();

        for (Prestamo prestamo : prestamosActivos) {
            LocalDate hoy = LocalDate.now();
            LocalDate vencimiento = prestamo.getFechaVencimiento();

            if (vencimiento.equals(hoy) || vencimiento.equals(hoy.plusDays(1))) {
                mostrarAlerta(prestamo);

                String respuesta = Consola.leerLinea("¿Desea renovar este préstamo? (s/n): ");
                if (respuesta.equalsIgnoreCase("s")) {
                    renovarPrestamo(prestamo);
                }
            }
        }
    }

    private void mostrarAlerta(Prestamo prestamo) {
        System.out.println("\n-------- ALERTA DE VENCIMIENTO ------------️");
        System.out.println(prestamo);
        System.out.println("Fecha de vencimiento: " + prestamo.getFechaVencimiento());
        System.out.println("-------------------------------");
    }

    private void renovarPrestamo(Prestamo prestamo) {
        // Validar que el recurso no tenga reservas pendientes
        boolean tieneReservas = gestorReservas.tieneReservasPendientes(prestamo.getRecurso());

        if (tieneReservas) {
            System.out.println("¡No se puede renovar el préstamo!. El recurso tiene reservas pendientes.");
            return;
        }

        // Extender vencimiento 7 días más
        LocalDate nuevaFecha = prestamo.getFechaVencimiento().plusDays(1);
        prestamo.setFechaVencimiento(nuevaFecha);

        System.out.println("¡Préstamo renovado exitosamente!. Nuevo vencimiento: " + nuevaFecha);
    }
}
