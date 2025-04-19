package app;

import Gestores.GestorPrestamos;
import Gestores.GestorRecursos;
import consola.BuscadorRecursos;
import consola.BuscadorUsuario;
import consola.Consola;
import Gestores.GestorUsuarios;
import Modelo.*;
import consola.MenuPrestamos;


public class Main {

    public static void main(String[] args) {

        ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
        GestorUsuarios gestorUsuarios = new GestorUsuarios(notificador);
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorPrestamos gestorPrestamos = new GestorPrestamos();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Registrar nuevo recurso digital");
            System.out.println("3. Ir a menu de prestamos");
            System.out.println("3. Gestionar recursos");
            System.out.println("4. Buscar Usuario");
            System.out.println("0. Salir");

            int opcion = Consola.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    Usuario nuevoUsuario = crearUsuarioDesdeConsola();
                    gestorUsuarios.agregarUsuario(nuevoUsuario);
                    break;
                case 2:
                    RecursoDigital nuevoRecurso = crearRecursoDesdeConsola();
                    if (nuevoRecurso != null) {
                        gestorRecursos.agregarRecurso(nuevoRecurso);
                    }
                    break;
                case 3:
                    MenuPrestamos.mostrarMenu(gestorPrestamos, gestorUsuarios, gestorRecursos);
                    break;
                case 4:
                    BuscadorRecursos.menuBusqueda(gestorRecursos);
                    break;
                case 5:
                    BuscadorUsuario.menuBusqueda(gestorUsuarios);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");


            }
        }
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