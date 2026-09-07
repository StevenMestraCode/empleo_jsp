/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author ASUS
 */
// clase usuario que contiene lo atributos de la tabla usuario
public class usuario {
    private String id;
    private String clave;
    private String nombre;
    private String correo;
    private String rol;

// getter and setters para manejar los atributos.
// getter and setter ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
// getter and setter clave
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
// getter and setter nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
// getter and setter correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
// getter and setter rol
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
