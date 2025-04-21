package Gestores;
import Modelo.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;

public class GestorPrestamos {
    private List<Prestamo> prestamos;
    private ServicioNotificaciones notificador;
    private Map<RecursoDigital, Integer> prestamosPorRecurso;
    private Map<Usuario, Integer> contadorUsuariosActivos;
    private Map<CategoriaRecurso, Integer> prestamosPorCategoria;


    public GestorPrestamos(ServicioNotificaciones notificador){
        this.notificador = notificador;
        this.prestamos = new ArrayList<>();
        this.prestamosPorRecurso = new HashMap<>();
        this.contadorUsuariosActivos = new HashMap<>();
        this.prestamosPorCategoria = new HashMap<>();
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

            // Aca registramos el contador
            prestamosPorRecurso.merge(recurso, 1, Integer::sum);
            // Y aca el contador de actividad
            contadorUsuariosActivos.put(usuario, contadorUsuariosActivos.getOrDefault(usuario, 0) + 1);

            //Contador de veces por categoria
            CategoriaRecurso categoria = recurso.getCategoria();
            prestamosPorCategoria.put(categoria, prestamosPorCategoria.getOrDefault(categoria, 0) + 1);

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

    public void mostrarRecursosMasPrestados(){
        System.out.println("-- Los recursos mas prestados --");
        prestamosPorRecurso.entrySet().stream()
                .sorted((e1, e2) ->e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> {
                    System.out.println(entry.getKey().getTitulo()+ " - Prestamos: "+entry.getValue());
                });
    }

    public void mostrarUsuariosMasActivos() {
            System.out.println("--- Usuarios más activos ---");
            contadorUsuariosActivos.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                    .forEach(e -> System.out.println(
                            e.getKey().getNombre() + " - Préstamos: " + e.getValue()));

    }

    public void mostrarEstadisticasPorCategoria() {
        System.out.println("--- Estadísticas por Categoría ---");
        for (Map.Entry<CategoriaRecurso, Integer> entry : prestamosPorCategoria.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " préstamos");
        }
    }

    public Map<RecursoDigital, Integer> getPrestamosPorRecurso() {
        return prestamosPorRecurso;
    }

    public Map<Usuario, Integer> getContadorUsuariosActivos() {
        return contadorUsuariosActivos;
    }

    public Map<CategoriaRecurso, Integer> getPrestamosPorCategoria() {
        return prestamosPorCategoria;
    }
}
