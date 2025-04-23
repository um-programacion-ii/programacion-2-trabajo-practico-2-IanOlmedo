package Gestores;

import Modelo.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;


public class GestorReservas {
    private Map<RecursoDigital, BlockingQueue<Reserva>> reservasPorRecurso;
    private ServicioNotificaciones notificador;
    private GestorPrestamos gestorPrestamos;
    private List<Reserva> reservas;

    public GestorReservas(ServicioNotificaciones notificador, GestorPrestamos gestorPrestamos) {
        this.reservasPorRecurso = new HashMap<>();
        this.notificador = notificador;
        this.gestorPrestamos = gestorPrestamos;
        this.reservas = new ArrayList<>();
    }

    public void reservarRecurso(Usuario usuario, RecursoDigital recurso) {
        reservasPorRecurso.putIfAbsent(recurso, new LinkedBlockingQueue<>());
        Reserva reserva = new Reserva(usuario, recurso);
        reservasPorRecurso.get(recurso).add(reserva);

        reservas.add(reserva); //agregar a reserva a la lista general de reservas

        System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Reserva registrada para " + recurso.getTitulo() + " por " + usuario.getNombre());
        notificador.notificar("Nueva reserva para " + recurso.getTitulo() + " por el usuario " + usuario.getNombre());
    }

    public Reserva obtenerProximaReserva(RecursoDigital recurso) {
        BlockingQueue<Reserva> cola = reservasPorRecurso.get(recurso);
        if (cola != null) {
            return cola.peek(); // sin remover
        }
        return null;
    }

    public void procesarReserva(RecursoDigital recurso, GestorPrestamos gestorPrestamos) {  //varios errores corregidos(ayudo gpt)
        BlockingQueue<Reserva> cola = reservasPorRecurso.get(recurso);
        if (cola != null && !cola.isEmpty()) {
            Reserva reserva = cola.peek(); // NO eliminamos aún

            try {
                gestorPrestamos.realizarPrestamo(reserva.getUsuario(), recurso);
                cola.poll(); // Solo eliminamos si el préstamo se realizó con éxito

                System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Reserva procesada: " + reserva);
                notificador.notificar("Reserva procesada: " + reserva);

            } catch (Exception e) {
                System.out.println("Error al procesar la reserva: " + e.getMessage());
            }

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

    public boolean tieneReservasPendientes(RecursoDigital recurso){
        return reservasPorRecurso.containsKey(recurso) && !reservasPorRecurso.get(recurso).isEmpty();
    }

    public List<Reserva> getReservasPendientes() {
        return reservas.stream()
                .filter(reserva -> reserva.getRecurso().getEstado() == EstadoRecurso.DISPONIBLE)
                .collect(Collectors.toList());
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva); // elimina de la lista general

        BlockingQueue<Reserva> cola = reservasPorRecurso.get(reserva.getRecurso());
        if (cola != null) {
            cola.remove(reserva); // elimina también de la cola correspondiente si está
        }
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

}
