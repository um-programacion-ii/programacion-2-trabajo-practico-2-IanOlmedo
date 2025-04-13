package Modelo;

public abstract class RecursoDigital {
    protected String titulo;
    protected String autor;

    public RecursoDigital(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarInformacion();

    // Getters comunes
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
}
