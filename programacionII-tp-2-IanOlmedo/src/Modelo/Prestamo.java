package Modelo;
import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaVencimiento;
    private static final int DIAS_PRESTAMO = 7;  //Si se quiere testear (pornerlo en 1 o 0)

    public Prestamo(Usuario usuario, RecursoDigital recurso){
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null; // Se le asigna el dia de devolucion
        this.fechaVencimiento = fechaPrestamo.plusDays(DIAS_PRESTAMO);

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
    }

    public boolean estaDevuelto() {
        return fechaDevolucion != null;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento(){
        return fechaPrestamo.plusDays(DIAS_PRESTAMO);
    }

    @Override
    public String toString() {
        return "Prestamo: " +
                "usuario = " + usuario.getNombre() +
                ", recurso = " + recurso.getTitulo() +
                ", fecha de prestamo = " + fechaPrestamo +
                ", fecha de devolución = " + fechaDevolucion;
    }

}
