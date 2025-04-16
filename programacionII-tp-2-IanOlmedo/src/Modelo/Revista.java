package Modelo;
import Modelo.RecursoDigital;

public class Revista implements RecursoDigital {

    private String titulo;
    private String autor;
    private int edicion;

    public Revista(String titulo, String autor, int edicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Título de la revista: " + titulo +
                " | Autor: " + autor +
                " | Número de edición: " + edicion);
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    public int getEdicion() {
        return edicion;
    }
}
