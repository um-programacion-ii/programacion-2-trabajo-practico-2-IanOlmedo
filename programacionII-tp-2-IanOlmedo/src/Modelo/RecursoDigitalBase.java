package Modelo;

public abstract class RecursoDigitalBase implements RecursoDigital {
    private EstadoRecurso estado = EstadoRecurso.DISPONIBLE;

    public EstadoRecurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
}
