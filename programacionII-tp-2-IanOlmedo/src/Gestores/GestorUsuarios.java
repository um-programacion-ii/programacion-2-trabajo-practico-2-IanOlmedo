package Gestores;
import java.util.ArrayList;
import Modelo.Usuario;


public class GestorUsuarios {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }


    public Usuario buscarPorId(int id){
        for(Usuario u : usuarios){
            if (u.getId() == id) return u;
        }
        return null;
    }

    public void listarUsuarios(){
        for (Usuario u : usuarios){
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() + " | Email: " + u.getEmail());
        }
    }
}
