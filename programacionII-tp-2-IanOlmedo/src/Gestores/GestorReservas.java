package Gestores;

import Modelo.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.*;


public class GestorReservas {
    private Map<RecursoDigital, BlockingQueue<Reserva>> reservasPorRecurso = new HashMap<>();

    public void reservarRecurso(Usuario usuario, RecursoDigital recurso) {
        reservasPorRecurso.putIfAbsent(recurso, new LinkedBlockingQueue<>());  // ayudo IA
        Reserva reserva = new Reserva(usuario, recurso);
        reservasPorRecurso.get(recurso).add(reserva);
        System.out.println("Reserva registrada para " + recurso.getTitulo() + " por " + usuario.getNombre());
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
            Reserva reserva = cola.poll(); // saca al primero
            recurso.setEstado(EstadoRecurso.RESERVADO);
            System.out.println("Reserva procesada: " + reserva);
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
