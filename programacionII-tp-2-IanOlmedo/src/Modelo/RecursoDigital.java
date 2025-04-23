package Modelo;

    public interface RecursoDigital {
        void mostrarInformacion();
        String getTitulo();
        String getAutor();
        CategoriaRecurso getCategoria();

        EstadoRecurso getEstado();    //Tenia un error con este get y set, y chat gpt me ayudo
        void setEstado(EstadoRecurso estado);
    }

