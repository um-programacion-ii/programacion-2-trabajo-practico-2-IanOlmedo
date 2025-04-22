package app;

import Gestores.GestorPrestamos;
import Gestores.GestorRecursos;
import Gestores.GestorReservas;
import consola.*;
import Gestores.GestorUsuarios;
import Modelo.*;
import Alertas.*;


public class Main {

    public static void main(String[] args) {

        ServicioNotificaciones notificador = new ServicioNotificacionesAsync(new ServicioNotificacionesEmail());  //Para mensajes asincronos
        GestorUsuarios gestorUsuarios = new GestorUsuarios(notificador);
        GestorRecursos gestorRecursos = new GestorRecursos(notificador);
        GestorPrestamos gestorPrestamos = new GestorPrestamos(notificador);
        GestorReservas gestorReservas = new GestorReservas(notificador, gestorPrestamos);
        BuscadorRecursos buscador = new BuscadorRecursos(gestorRecursos, gestorPrestamos);
        AlertaVencimiento alertaVencimiento = new AlertaVencimiento(gestorPrestamos, gestorReservas);


        boolean salir = false;

        while (!salir) {
            alertaVencimiento.verificarAlertas();

            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Registrar nuevo recurso digital");
            System.out.println("3. Ir a menu de prestamos");
            System.out.println("4. Reservas");
            System.out.println("5. Gestionar recursos");
            System.out.println("6. Menu de usuarios");
            System.out.println("7. Reportes");
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
                    MenuReservas.mostrarMenu(gestorReservas, gestorUsuarios, gestorRecursos, gestorPrestamos);
                    break;
                case 5:
                    buscador.menuBusqueda(gestorRecursos);
                    break;
                case 6:
                    BuscadorUsuario.menuBusqueda(gestorUsuarios, gestorPrestamos);
                    break;
                case 7:
                    BuscadorReportes.menuBusqueda(gestorUsuarios, gestorPrestamos);
                    break;
                case 0:
                    if (notificador instanceof ServicioNotificacionesAsync) {  //matar hilos
                        ((ServicioNotificacionesAsync) notificador).shutdown();
                    }
                    salir = true;
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");


            }
        }
    }

    private static Usuario crearUsuarioDesdeConsola() {
        int id = Consola.leerEntero("Ingrese DNI: ");
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