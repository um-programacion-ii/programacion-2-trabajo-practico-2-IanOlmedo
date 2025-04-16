package app;

import Gestores.GestorRecursos;
import consola.Consola;
import Gestores.GestorUsuarios;
import Modelo.*;

public class Main {

    public static void main(String[] args) {

        ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
        GestorUsuarios gestorUsuarios = new GestorUsuarios(notificador);
        GestorRecursos gestorRecursos = new GestorRecursos();

        System.out.println("--Registrar nuevo usuario--");
        Usuario nuevoUsuario = crearUsuarioDesdeConsola();
        gestorUsuarios.agregarUsuario(nuevoUsuario);

        System.out.println("\n--Registrar nuevo Recurso Digital--");
        RecursoDigital nuevoRecurso = crearRecursoDesdeConsola();
        if (nuevoRecurso != null) {
            gestorRecursos.agregarRecurso(nuevoRecurso);
        }

        // Mostrar datos registrados
        System.out.println("\n=== Recursos Digitales Registrados ===");
        gestorRecursos.listarRecursos();

        System.out.println("\n=== Usuarios Registrados ===");
        gestorUsuarios.listarUsuarios();
    }

    private static Usuario crearUsuarioDesdeConsola() {
        int id = Consola.leerEntero("Ingrese ID: ");
        String nombre = Consola.leerLinea("Ingrese nombre del usuario: ");
        String email = Consola.leerLinea("Ingrese email: ");
        return new Usuario(id, nombre, email);
    }

    private static RecursoDigital crearRecursoDesdeConsola() {
        System.out.println("Seleccione tipo de recurso digital:");
        System.out.println("1- Libro");
        System.out.println("2- Revista");
        System.out.println("3- Audiolibro");

        int opcion = Consola.leerEntero("Opción: ");
        String titulo = Consola.leerLinea("Título: ");
        String autor = Consola.leerLinea("Autor: ");

        switch (opcion) {
            case 1:
                int paginas = Consola.leerEntero("Cantidad de páginas: ");
                return new Libro(titulo, autor, paginas);
            case 2:
                int edicion = Consola.leerEntero("Número de edición: ");
                return new Revista(titulo, autor, edicion);
            case 3:
                int duracion = Consola.leerEntero("Duración en minutos: ");
                return new Audiolibro(titulo, autor, duracion);
            default:
                System.out.println("Opción no válida. No se creará el recurso.");
                return null;
        }
    }
}
