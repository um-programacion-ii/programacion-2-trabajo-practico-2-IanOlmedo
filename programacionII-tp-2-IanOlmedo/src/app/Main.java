package app;

import consola.Consola;
import Gestores.GestorUsuarios;
import Modelo.Usuario;

public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        int id = Consola.leerEntero("Ingrese ID: ");
        String nombre = Consola.leerLinea("Ingrese nombre del usuario: ");
        String email = Consola.leerLinea("Ingrese email: ");

        Usuario u = new Usuario(id, nombre, email);
        gestorUsuarios.agregarUsuario(u);

        System.out.println("\nUsuarios registrados:");
        gestorUsuarios.listarUsuarios();
    }
}