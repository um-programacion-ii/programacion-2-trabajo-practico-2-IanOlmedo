package Modelo;

public class Usuario {
    private Integer id;
    private String nombre;
    private String email;


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

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Email: " + email;
    }
}