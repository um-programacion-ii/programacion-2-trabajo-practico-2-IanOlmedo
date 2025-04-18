package consola;
import Gestores.GestorRecursos;
import Modelo.RecursoDigital;

import java.util.List;

public class BuscadorRecursos {
    public static void menuBusqueda(GestorRecursos gestorRecursos) {
        System.out.println("\n--- Búsqueda de Recursos ---");
        System.out.println("1. Buscar por título");
        System.out.println("2. Filtrar por categoría");
        System.out.println("3. Ordenar por autor");
        int opcion = Consola.leerEntero("Seleccione una opción: ");

        List<RecursoDigital> resultado;

        switch (opcion) {
            case 1:
                String titulo = Consola.leerLinea("Ingrese parte del título a buscar: ");
                resultado = gestorRecursos.buscarPorTitulo(titulo);
                break;
            case 2:
                String categoria = Consola.leerLinea("Ingrese categoría (Libro, Revista, Audiolibro): ");
                resultado = gestorRecursos.filtrarPorCategoria(categoria);
                break;
            case 3:
                resultado = gestorRecursos.ordenarPorAutor();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (resultado.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            resultado.forEach(RecursoDigital::mostrarInformacion);
        }
    }
}
