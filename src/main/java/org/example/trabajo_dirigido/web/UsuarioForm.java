package org.example.trabajo_dirigido.web;

import jakarta.ws.rs.FormParam;

/**
 * Bean para capturar los datos del formulario de usuario.
 * Usado con @BeanParam en el controlador.
 */
public class UsuarioForm {

    @FormParam("id")
    private Long id;

    @FormParam("nombre")
    private String nombre;

    @FormParam("email")
    private String email;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
