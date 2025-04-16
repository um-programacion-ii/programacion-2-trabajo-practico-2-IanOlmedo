package Modelo;
import Modelo.RecursoDigital;
import Modelo.Prestable;
import Modelo.Renovable;

public class Libro implements RecursoDigital, Prestable, Renovable{
    private String titulo;
    private String autor;
    private int paginas;
    private boolean prestado;

    public Libro(String titulo, String autor, int paginas){
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.prestado = false;

    }

    public int getPaginas(){
        return paginas;
    }

    @Override
    public void prestar(){
        if (!prestado){
            prestado = true;
            System.out.println("El libro a sido prestado.");
        } else {
            System.out.println("El libro ya esta prestado.");
        }
    }

    public void devolver(){
        if (prestado){
            prestado = false;
            System.out.println("El libro ya fue devuelto");
        } else {
            System.out.println("El libro no habia sioo prestado");
        }

    }
    @Override
    public boolean estaPrestado(){
        return prestado;
    }

    @Override
    public void renovar(){
        if (prestado){
            System.out.println("El prestamo del libro fue renovado.");
        } else {
            System.out.println("No se puede renovar el libro porque no esta prestado");
        }
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
