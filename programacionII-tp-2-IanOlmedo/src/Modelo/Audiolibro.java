package Modelo;
import Modelo.RecursoDigital;

public class Audiolibro implements RecursoDigital{
    private String titulo;
    private String autor;
    private int duracionMinutos;

    public Audiolibro(String titulo, String autor, int duracionMinutos) {
        this.titulo = titulo;
        this.autor = autor;
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Audiolibro - " + titulo + " | Autor del audiolibro: " + autor + " | Duración: " + duracionMinutos + " min");
    }

    @Override
    public String getTitulo(){
        return titulo;
    }
    @Override
    public String getAutor(){
        return autor;
    }

    private final CategoriaRecurso categoria = CategoriaRecurso.AUDIOLIBRO;

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }

}
