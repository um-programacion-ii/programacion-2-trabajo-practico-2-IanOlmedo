package Gestores;
import Excepciones.RecursoNoDisponibleException;
import Modelo.RecursoDigital;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos;

    public GestorRecursos(){
        recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital r : recursos) {
            r.mostrarInformacion();
        }
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
