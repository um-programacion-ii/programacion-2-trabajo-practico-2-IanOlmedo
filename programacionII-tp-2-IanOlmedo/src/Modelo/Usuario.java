package Modelo;
import Alertas.*;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private NivelUrgencia nivelPreferido = NivelUrgencia.INFO;


    public Usuario(Integer id,String nombre, String email){
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NivelUrgencia getNivelPreferido() {
        return nivelPreferido;
    }

    public void setNivelPreferido(NivelUrgencia nivelPreferido) {
        this.nivelPreferido = nivelPreferido;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Email: " + email;
    }
}