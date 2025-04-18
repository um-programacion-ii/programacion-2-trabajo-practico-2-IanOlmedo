package consola;
import Gestores.GestorUsuarios;
import Modelo.Usuario;
import Excepciones.UsuarioNoEncontradoException;


public class BuscadorUsuario {
    public static void menuBusqueda(GestorUsuarios gestorUsuarios){
        System.out.println("\n-- Menu de Busqueda por Usuario --");
        int id = Consola.leerEntero("Ingresa el ID de la persona a buscar: ");

        try {
            Usuario usuario = gestorUsuarios.buscarPorId(id);
            System.out.println("Usuario encontrado:");
            System.out.println(usuario);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
