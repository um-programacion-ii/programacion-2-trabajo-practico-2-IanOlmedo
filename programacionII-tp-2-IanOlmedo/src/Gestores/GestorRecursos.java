package Gestores;
import Excepciones.RecursoNoDisponibleException;
import Modelo.RecursoDigital;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import Modelo.EstadoRecurso;

import Modelo.ServicioNotificaciones;
import Modelo.ServicioNotificacionesAsync;


public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos;
    private ServicioNotificaciones notificador;

    public GestorRecursos(ServicioNotificaciones notificador){
        this.recursos = new ArrayList<>();
        this.notificador = notificador;

    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
        notificador.notificar("Recurso agregado: "+recurso.getTitulo()+", que pertenece a la categoria "+recurso.getCategoria());

    }

    public void listarRecursos() {
        recursos.stream()
                .filter(r -> r.getEstado() == EstadoRecurso.DISPONIBLE)
                .forEach(RecursoDigital::mostrarInformacion);
    }


    public List<RecursoDigital> buscarPorTitulo(String titulo) throws RecursoNoDisponibleException {
        List<RecursoDigital> resultado = recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new RecursoNoDisponibleException("No se encontraron recursos con el título que contiene: " + titulo);
        }

        return resultado;
    }

    public RecursoDigital buscarTituloExacto(String titulo) throws Exception {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getTitulo().equalsIgnoreCase(titulo)) {
                return recurso;
            }
        }
        throw new Exception("Recurso con título \"" + titulo + "\" no encontrado.");
    }

    public List<RecursoDigital> filtrarPorCategoria(Enum categoria) throws RecursoNoDisponibleException {
        List<RecursoDigital> resultado = recursos.stream()
                .filter(r -> r.getCategoria() == categoria)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new RecursoNoDisponibleException("No se encontraron recursos en la categoría: " + categoria);
        }

        return resultado;
    }

    public List<RecursoDigital> ordenarPorAutor(){
        return recursos.stream()
                .sorted(Comparator.comparing(RecursoDigital::getAutor))
                .collect(Collectors.toList());
    }



}
