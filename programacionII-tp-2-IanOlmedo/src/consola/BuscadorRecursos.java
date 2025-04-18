package consola;
import Excepciones.RecursoNoDisponibleException;
import Gestores.GestorRecursos;
import Modelo.RecursoDigital;
import Modelo.CategoriaRecurso;

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
                try {
                    resultado = gestorRecursos.buscarPorTitulo(titulo);
                } catch (RecursoNoDisponibleException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                break;
            case 2:
                System.out.println("Categorías disponibles:");
                for (CategoriaRecurso cat : CategoriaRecurso.values()) {
                    System.out.println("- " + cat);
                }
                String categoriaInput = Consola.leerLinea("Ingrese una categoría: ");

                try {
                    CategoriaRecurso categoriaEnum = CategoriaRecurso.valueOf(categoriaInput.toUpperCase());
                    resultado = gestorRecursos.filtrarPorCategoria(categoriaEnum);
                } catch (RecursoNoDisponibleException e) {
                    System.out.println(e.getMessage());
                    return;
                }
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
