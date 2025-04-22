package Alertas;

import Modelo.Usuario;
import Modelo.RecursoDigital;

import java.time.LocalDateTime;

public class Recordatorio {
    private String mensaje;
    private NivelUrgencia urgencia;
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDateTime fecha;

    public Recordatorio(String mensaje, NivelUrgencia urgencia, Usuario usuario, RecursoDigital recurso) {
        this.mensaje = mensaje;
        this.urgencia = urgencia;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fecha = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public NivelUrgencia getUrgencia() {
        return urgencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recordatorio [").append(urgencia).append("] ");
        sb.append(mensaje);

        if (usuario != null) {
            sb.append(" | Usuario: ").append(usuario.getNombre());
        }

        if (recurso != null) {
            sb.append(" | Recurso: ").append(recurso.getTitulo());
        }

        return sb.toString();
    }
}
