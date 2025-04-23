package Modelo;

public class ServicioNotificacionesEmail implements ServicioNotificaciones{
    @Override
    public void notificar (String mensaje){
        System.out.println("\n----------NOTIFICACION---------");
        System.out.println("Notificacion [Email] "+ mensaje);
        System.out.println("-------------------------------");

    }
}
