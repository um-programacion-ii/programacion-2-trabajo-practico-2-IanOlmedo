package consola;
import Gestores.GestorPrestamos;
import Gestores.GestorUsuarios;
import Modelo.Usuario;
import Excepciones.UsuarioNoEncontradoException;
import Excepciones.OpcionNoDisponibleExeption;


public class BuscadorUsuario {
    private GestorPrestamos gestorPrestamos;

    public BuscadorUsuario(GestorPrestamos gestorPrestamos){

        this.gestorPrestamos = gestorPrestamos;
    }


    public static void menuBusqueda(GestorUsuarios gestorUsuarios, GestorPrestamos gestorPrestamos) {
        System.out.println("\n-- Menu de Usuarios --");
        System.out.println("1. Buscar Usuario");
        System.out.println("2. Reporte de usuarios mas activos");
        System.out.println("0. Volver");
        int opcion = Consola.leerEntero("Seleccioná una opción: ");


        switch (opcion) {
            case 1:
                try {
                    int id = Consola.leerEntero("Ingresa el DNI de la persona a buscar: ");
                    Usuario usuario = gestorUsuarios.buscarPorId(id);
                    System.out.println("Usuario encontrado: ");
                    System.out.println(usuario);
                } catch (UsuarioNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                gestorPrestamos.mostrarUsuariosMasActivos();
                break;
        }
    }
}
