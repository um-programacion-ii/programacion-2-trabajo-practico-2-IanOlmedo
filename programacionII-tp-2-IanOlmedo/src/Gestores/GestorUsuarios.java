package Gestores;
import java.util.ArrayList;
import Modelo.Usuario;
import Modelo.ServicioNotificaciones;
import java.util.Map;
import java.util.HashMap;
import Excepciones.UsuarioNoEncontradoException;
import java.util.List;
import java.util.stream.Collectors;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios;
    private ServicioNotificaciones notificador;

    public GestorUsuarios(ServicioNotificaciones notificador){
        this.usuarios = new HashMap<>();
        this.notificador = notificador;
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.put(usuario.getEmail(), usuario);
        notificador.notificar("Usuario agregado: " + usuario.getNombre());
    }

    public void listarUsuarios(){
        for (Usuario u : usuarios.values()){
            System.out.println(u);
        }
    }

    public Usuario buscarPorEmail(String email){
        return usuarios.get(email);
    }

    public Usuario buscarPorId(int id) throws UsuarioNoEncontradoException {
        return usuarios.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado."));
    }

    public List<Usuario> getTodosLosUsuarios() {
        return usuarios.values().stream().
                collect(Collectors.toList());
    }
}

