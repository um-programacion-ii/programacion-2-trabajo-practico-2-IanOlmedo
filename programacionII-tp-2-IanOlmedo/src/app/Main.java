package app;

import Gestores.GestorRecursos;
import consola.Consola;
import Gestores.GestorUsuarios;
import Modelo.*;


public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();

        System.out.println("Agregar nuevo usuario: ");
        int id = Consola.leerEntero("Ingrese ID: ");
        String nombre = Consola.leerLinea("Ingrese nombre del usuario: ");
        String email = Consola.leerLinea("Ingrese email: ");

        Usuario u = new Usuario(id, nombre, email);
        gestorUsuarios.agregarUsuario(u);

        //--------------------------------------------------

        System.out.println("Agregar nuevo recurso digital");
        System.out.println("\nSeleccione tipo de recurso digital:");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");

        int opcion = Consola.leerEntero("Opción: ");
        RecursoDigital recurso = null;

        String titulo = Consola.leerLinea("Título: ");
        String autor = Consola.leerLinea("Autor: ");

        switch (opcion) {
            case 1:
                int paginas = Consola.leerEntero("Cantidad de páginas: ");
                recurso = new Libro(titulo, autor, paginas);
                break;
            case 2:
                int edicion = Consola.leerEntero("Número de edición: ");
                recurso = new Revista(titulo, autor, edicion);
                break;
            case 3:
                int duracion = Consola.leerEntero("Duración en minutos: ");
                recurso = new Audiolibro(titulo, autor, duracion);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        if (recurso != null) {
            gestorRecursos.agregarRecurso(recurso);
        }

//--------------------------------------------------------------------------------------


        System.out.println("\nRecursos digitales registrados: ");
        gestorRecursos.listarRecursos();
        System.out.println("\nUsuarios registrados:");
        gestorUsuarios.listarUsuarios();

    }
}