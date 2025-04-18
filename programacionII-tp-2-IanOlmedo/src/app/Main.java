package app;

import Gestores.GestorRecursos;
import consola.BuscadorRecursos;
import consola.Consola;
import Gestores.GestorUsuarios;
import Modelo.*;


public class Main {

    public static void main(String[] args) {

        ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
        GestorUsuarios gestorUsuarios = new GestorUsuarios(notificador);
        GestorRecursos gestorRecursos = new GestorRecursos();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Registrar nuevo recurso digital");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Listar recursos");
            System.out.println("5. Buscar recursos");
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
                    gestorUsuarios.listarUsuarios();
                    break;
                case 4:
                    gestorRecursos.listarRecursos();
                    break;
                case 5:
                    BuscadorRecursos.menuBusqueda(gestorRecursos);
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