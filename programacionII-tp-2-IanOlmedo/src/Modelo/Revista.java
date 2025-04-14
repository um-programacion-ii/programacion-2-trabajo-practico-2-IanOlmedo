package Modelo;
import Modelo.RecursoDigital;

public class Revista implements RecursoDigital{
    private String titulo;
    private String autor;
    private int edicion;

    public Revista(String titulo, String autor, int edicion){
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
    }

    @Override
    public void mostrarInformacion(){
        System.out.println("Titulo de revista: "+titulo+" | Autor de la revista: "+autor+" | Numero de edicion: "+edicion);
    }

    @Override
    public String getTitulo(){
        return titulo;
    }
    @Override
    public String getAutor(){
        return autor;
    }

}
