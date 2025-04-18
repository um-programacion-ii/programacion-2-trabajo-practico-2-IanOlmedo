package Gestores;
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

    public List<RecursoDigital> buscarPorTitulo(String titulo){
        return recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> filtrarPorCategoria(Enum categoria) {
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> ordenarPorAutor(){
        return recursos.stream()
                .sorted(Comparator.comparing(RecursoDigital::getAutor))
                .collect(Collectors.toList());
    }



}
