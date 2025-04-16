package Gestores;
import Modelo.RecursoDigital;
import java.util.ArrayList;


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

    public RecursoDigital buscarPorTiulo(String titulo){
        for (RecursoDigital r : recursos) {
            if (r.getTitulo().equalsIgnoreCase(titulo)){
                return r;
            }
        }
        return null;
    }


}
