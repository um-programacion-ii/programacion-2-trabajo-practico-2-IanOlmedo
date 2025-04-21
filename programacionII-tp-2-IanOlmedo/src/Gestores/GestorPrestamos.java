package Gestores;
import Modelo.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GestorPrestamos {
    private List<Prestamo> prestamos;
    private ServicioNotificaciones notificador;

    public GestorPrestamos(ServicioNotificaciones notificador){
        this.notificador = notificador;
        this.prestamos = new ArrayList<>();
    }

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws Exception {
        synchronized (recurso) {
            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Intentando prestar: " + recurso.getTitulo());

            if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
                System.out.println("[Hilo: " + Thread.currentThread().getName() + "] El recurso no está disponible.");
                throw new Exception("El recurso no está disponible para préstamo.");
            }

            Prestamo nuevoPrestamo = new Prestamo(usuario, recurso);
            prestamos.add(nuevoPrestamo);
            recurso.setEstado(EstadoRecurso.PRESTADO);

            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Préstamo realizado: " + recurso.getTitulo() + " a " + usuario.getNombre());
            notificador.notificar("Se le realizo el prestamo de "+recurso.getTitulo()+ " a "+usuario.getNombre());
        }
    }

    public void devolverRecurso(RecursoDigital recurso) throws Exception {
        synchronized (recurso) {
            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Intentando devolver: " + recurso.getTitulo());

            Prestamo prestamo = prestamos.stream()
                    .filter(p -> p.getRecurso().equals(recurso) && !p.estaDevuelto())
                    .findFirst()
                    .orElseThrow(() -> new Exception("No se encontró préstamo activo para este recurso."));

            prestamo.registrarDevolucion();
            recurso.setEstado(EstadoRecurso.DISPONIBLE);

            System.out.println("[Hilo: " + Thread.currentThread().getName() + "] Recurso devuelto: " + recurso.getTitulo());
            notificador.notificar("Se devolvio "+recurso.getCategoria()+" " +recurso.getTitulo());
        }
    }

    public void listarPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }
}
