package Gestores;
import Modelo.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();
    private ServicioNotificaciones notificador;

    public GestorPrestamos(ServicioNotificaciones notificador){
        this.notificador = notificador;
        this.prestamos = new ArrayList<>();
    }

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws Exception {
        if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
            throw new Exception("El recurso no está disponible para préstamo.");
        }

        Prestamo nuevoPrestamo = new Prestamo(usuario, recurso);
        prestamos.add(nuevoPrestamo);
        recurso.setEstado(EstadoRecurso.PRESTADO);
        notificador.notificar("Se le realizo el prestamo de "+recurso.getTitulo()+ " a "+usuario.getNombre());
    }

    public void devolverRecurso(RecursoDigital recurso) throws Exception {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getRecurso().equals(recurso) && !p.estaDevuelto())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró préstamo activo para este recurso."));

        prestamo.registrarDevolucion();
        recurso.setEstado(EstadoRecurso.DISPONIBLE);
        notificador.notificar("Se devolvio "+recurso.getCategoria()+" " +recurso.getTitulo());

    }

    public void listarPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }
}
