package Modelo;
import java.time.LocalDate;


public class Reserva {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso){
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDate.now();
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }
    @Override
    public String toString(){
        return "Reserva del recurso "+recurso.getTitulo()+
                ", hecha por el usuario "+usuario.getNombre()+
                ", el dia "+fechaReserva.toString();
    }
}
