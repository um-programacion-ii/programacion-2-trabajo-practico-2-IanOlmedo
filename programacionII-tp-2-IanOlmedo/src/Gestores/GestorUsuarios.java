package Gestores;
import java.util.ArrayList;
import Modelo.Usuario;
import Modelo.ServicioNotificaciones;


public class GestorUsuarios {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ServicioNotificaciones notificador;

    public GestorUsuarios(ServicioNotificaciones notificador){
        this.usuarios = new ArrayList<>();
        this.notificador = notificador;
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
        notificador.notificar("Usuario agregado: "+usuario.getNombre());
    }

    public void listarUsuarios(){
        for (Usuario u : usuarios){
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() + " | Email: " + u.getEmail());
        }
    }

    public Usuario buscarPorId(int id){
        for(Usuario u : usuarios){
            if (u.getId() == id) return u;
        }
        return null;
    }


}
