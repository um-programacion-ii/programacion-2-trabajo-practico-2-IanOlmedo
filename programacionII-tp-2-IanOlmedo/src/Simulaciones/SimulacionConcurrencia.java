package Simulaciones;
import Modelo.*;
import Gestores.*;

//Esta clase valida la concurrencia
public class SimulacionConcurrencia {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario(1, "Ian", "ian@mail.com");
        Usuario usuario2 = new Usuario(2, "Pepe", "pepe@mail.com");

        RecursoDigital recurso = new Libro("El Principito", "Saint-Exupéry", 100);

        // Usamos un notificador simple que imprime en consola
        ServicioNotificaciones notificador = new ServicioNotificacionesAsync(new ServicioNotificacionesEmail());
        GestorPrestamos gestorPrestamos = new GestorPrestamos(notificador);

        Thread hilo1 = new Thread(() -> {
            try {
                gestorPrestamos.realizarPrestamo(usuario1, recurso);
            } catch (Exception e) {
                System.out.println("[Hilo-1] " + e.getMessage());
            }
        }, "Usuario-1");

        Thread hilo2 = new Thread(() -> {
            try {
                gestorPrestamos.realizarPrestamo(usuario2, recurso);
            } catch (Exception e) {
                System.out.println("[Hilo-2] " + e.getMessage());
            }
        }, "Usuario-2");

        hilo1.start();
        hilo2.start();
    }
}
