package Alertas;

import Gestores.GestorPrestamos;
import Gestores.GestorReservas;
import Modelo.*;
import consola.Consola;

import java.util.List;

public class AlertaDisponibilidad {

    private GestorReservas gestorReservas;
    private GestorPrestamos gestorPrestamos;

    public AlertaDisponibilidad(GestorReservas gestorReservas, GestorPrestamos gestorPrestamos) {
        this.gestorReservas = gestorReservas;
        this.gestorPrestamos = gestorPrestamos;
    }

    public void verificarDisponibilidad() {
        List<Reserva> reservasPendientes = gestorReservas.getReservasPendientes();

        for (Reserva reserva : reservasPendientes) {
            RecursoDigital recurso = reserva.getRecurso();

            if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
                mostrarAlertaDisponibilidad(reserva);

                String respuesta = Consola.leerLinea("¿Desea tomar el préstamo ahora? (s/n): ");
                if (respuesta.equalsIgnoreCase("s")) {
                    try {
                        gestorPrestamos.realizarPrestamo(reserva.getUsuario(), recurso);
                        gestorReservas.eliminarReserva(reserva);
                    } catch (Exception e) {
                        System.out.println("Error al realizar préstamo: " + e.getMessage());
                    }
                }
            }
        }
    }

    private void mostrarAlertaDisponibilidad(Reserva reserva) {
        System.out.println("\n <<RECURSO DISPONIBLE>> ");
        System.out.println("Recurso: " + reserva.getRecurso().getTitulo());
        System.out.println("Reservado por: " + reserva.getUsuario().getNombre());
    }
}
