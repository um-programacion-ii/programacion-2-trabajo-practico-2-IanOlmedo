package Gestores;
import Modelo.*;
import java.util.List;
import java.util.ArrayList;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws Exception {
        if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
            throw new Exception("El recurso no está disponible para préstamo.");
        }

        Prestamo nuevoPrestamo = new Prestamo(usuario, recurso);
        prestamos.add(nuevoPrestamo);
        recurso.setEstado(EstadoRecurso.PRESTADO);
    }

    public void devolverRecurso(RecursoDigital recurso) throws Exception {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getRecurso().equals(recurso) && !p.estaDevuelto())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró préstamo activo para este recurso."));

        prestamo.registrarDevolucion();
        recurso.setEstado(EstadoRecurso.DISPONIBLE);
    }

    public void listarPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }
}
