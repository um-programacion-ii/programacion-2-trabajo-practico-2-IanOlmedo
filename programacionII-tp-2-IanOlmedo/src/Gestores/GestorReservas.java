package Gestores;

import Modelo.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.*;


public class GestorReservas {
    private Map<RecursoDigital, BlockingQueue<Reserva>> reservasPorRecurso;
    private ServicioNotificaciones notificador;

    public GestorReservas(ServicioNotificaciones notificador) {
        this.reservasPorRecurso = new HashMap<>();
        this.notificador = notificador;
    }

    public void reservarRecurso(Usuario usuario, RecursoDigital recurso) {
        reservasPorRecurso.putIfAbsent(recurso, new LinkedBlockingQueue<>());  // ayudo IA
        Reserva reserva = new Reserva(usuario, recurso);
        reservasPorRecurso.get(recurso).add(reserva);
        System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Reserva registrada para " + recurso.getTitulo() + " por " + usuario.getNombre());
        notificador.notificar("Nueva reserva para " + recurso.getTitulo() + "' por el usuario " + usuario.getNombre());
    }

    public Reserva obtenerProximaReserva(RecursoDigital recurso) {
        BlockingQueue<Reserva> cola = reservasPorRecurso.get(recurso);
        if (cola != null) {
            return cola.peek(); // sin remover
        }
        return null;
    }

    public void procesarReserva(RecursoDigital recurso) {
        BlockingQueue<Reserva> cola = reservasPorRecurso.get(recurso);
        if (cola != null && !cola.isEmpty()) {
            Reserva reserva = cola.poll();
            recurso.setEstado(EstadoRecurso.RESERVADO);
            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Reserva procesada: " + reserva);
            notificador.notificar("Reserva procesada: " + reserva);
        } else {
            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] No hay reservas para procesar.");
        }
    }

    public void mostrarReservas(RecursoDigital recurso) {
        BlockingQueue<Reserva> cola = reservasPorRecurso.get(recurso);
        if (cola == null || cola.isEmpty()) {
            System.out.println("No hay reservas para " + recurso.getTitulo());
            return;
        }

        System.out.println("Reservas para " + recurso.getTitulo() + ":");
        for (Reserva r : cola) {
            System.out.println(r);
        }
    }
}
