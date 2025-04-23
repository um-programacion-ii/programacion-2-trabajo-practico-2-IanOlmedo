package Modelo;
import Modelo.ServicioNotificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones{
    @Override
    public void notificar(String mensaje){
        System.out.println("\n----------NOTIFICACION---------");
        System.out.println("Notificacion [SMS] "+ mensaje);
        System.out.println("-------------------------------");

    }
}
