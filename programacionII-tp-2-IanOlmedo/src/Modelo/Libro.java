package Modelo;
import Modelo.RecursoDigital;

public class Libro implements RecursoDigital{
    private String titulo;
    private String autor;
    private int paginas;

    public Libro(String titulo, String autor, int paginas){
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;

    }
    @Override
    public void mostrarInformacion(){
        System.out.println("titulo del libro: "+ titulo+" | autor del libro: "+autor+" | Cantidad de paginas: "+paginas);
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
