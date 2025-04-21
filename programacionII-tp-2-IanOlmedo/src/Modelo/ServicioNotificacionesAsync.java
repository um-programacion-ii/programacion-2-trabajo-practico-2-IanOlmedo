package Modelo;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import Modelo.ServicioNotificaciones;

public class ServicioNotificacionesAsync implements ServicioNotificaciones {
    private final ServicioNotificaciones decorado;
    private final ExecutorService executor = Executors.newFixedThreadPool(100); //2 hilos

    public ServicioNotificacionesAsync(ServicioNotificaciones decorado){
        this.decorado = decorado;
    }
    @Override
    public void notificar(String mensaje){
        executor.submit(() -> decorado.notificar(mensaje));
    }

    public void shutdown() {
        executor.shutdown(); // Para terminar los hilos
    }
}
