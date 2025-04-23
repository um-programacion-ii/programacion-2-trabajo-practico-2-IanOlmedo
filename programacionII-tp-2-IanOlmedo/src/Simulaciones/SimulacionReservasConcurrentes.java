package Simulaciones;

import Gestores.*;
import Modelo.*;
import Modelo.Libro;

public class SimulacionReservasConcurrentes {
    public static void main(String[] args) throws InterruptedException {
        RecursoDigital recurso = new Libro("Crónica de una muerte anunciada", "Gabriel García Márquez", 150);
        Usuario usuario1 = new Usuario(1, "Ana", "ana@mail.com");
        Usuario usuario2 = new Usuario(2, "Luis", "luis@mail.com");
        Usuario usuario3 = new Usuario(3, "Marta", "marta@mail.com");

        ServicioNotificaciones notificador = new ServicioNotificacionesAsync(new ServicioNotificacionesEmail());
        GestorPrestamos gestorPrestamos = new GestorPrestamos(notificador);
        GestorReservas gestorReservas = new GestorReservas(notificador, gestorPrestamos);

        // Paso 1: Ana realiza un préstamo
        new Thread(() -> {
            try {
                gestorPrestamos.realizarPrestamo(usuario1, recurso);
            } catch (Exception e) {
                System.out.println("[Hilo: " + Thread.currentThread().getName() + "] " + e.getMessage());
            }
        }, "Usuario-Ana").start();

        // Pequeña pausa para garantizar orden
        Thread.sleep(100);

        // Paso 2: Luis y Marta reservan mientras el recurso está prestado
        Thread reservaLuis = new Thread(() -> gestorReservas.reservarRecurso(usuario2, recurso), "Usuario-Luis");
        Thread reservaMarta = new Thread(() -> gestorReservas.reservarRecurso(usuario3, recurso), "Usuario-Marta");

        reservaLuis.start();
        reservaMarta.start();

        reservaLuis.join();
        reservaMarta.join();

        // Paso 3: Ana devuelve el recurso
        new Thread(() -> {
            try {
                Thread.sleep(500); // Simulamos un pequeño delay
                gestorPrestamos.devolverRecurso(recurso);

                // Paso 4: Procesar la reserva del primero en la lista (Luis)
                gestorReservas.procesarReserva(recurso, gestorPrestamos);

                // Paso 5: El usuario al que se le asignó la reserva hace el préstamo
                gestorPrestamos.realizarPrestamo(usuario2, recurso);
            } catch (Exception e) {
                System.out.println("[Hilo: Procesador] " + e.getMessage());
            }
        }, "Procesador").start();
    }
}
