package Gestores;
import Alertas.*;

import Modelo.Usuario;
import Modelo.RecursoDigital;

import java.util.*;

public class GestorRecordatorios {
    private List<Recordatorio> historial = new ArrayList<>();

    public void enviarRecordatorio(String mensaje, NivelUrgencia urgencia, Usuario usuario, RecursoDigital recurso) {
        Recordatorio recordatorio = new Recordatorio(mensaje, urgencia, usuario, recurso); // <-- asegura que el constructor incluya usuario y recurso
        historial.add(recordatorio);

        if (usuario != null && urgencia.ordinal() >= usuario.getNivelPreferido().ordinal()) {
            System.out.println("\n RECORDATORIO " + urgencia + ": " + mensaje);
        }
    }

    public void enviarRecordatorioGeneral(String mensaje, NivelUrgencia urgencia, List<Usuario> usuarios) {
        // Mostrar mensaje general una sola vez
        System.out.println("\n[GENERAL] RECORDATORIO - " + urgencia + ": " + mensaje);

        // Luego, si hay usuarios registrados, se les asocia individualmente
        for (Usuario u : usuarios) {
            Recordatorio recordatorio = new Recordatorio(mensaje, urgencia, u, null);
            historial.add(recordatorio);

            if (urgencia.ordinal() >= u.getNivelPreferido().ordinal()) {
                System.out.println("[GENERAL] -> para " + u.getNombre() + ": " + mensaje);
            }
        }
    }

    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE RECORDATORIOS ---");
        for (Recordatorio r : historial) {
            System.out.println(r);
        }
    }
}
