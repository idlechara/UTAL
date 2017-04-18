/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iorsh.lib;

/**
 *
 * @author jvarred
 */
public abstract class Persona {
    private Integer id;
    private String nombre, apellido, email, teléfono;
    
    Persona(Integer id, String nombre, String apellido, String email, String teléfono){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.teléfono = teléfono;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTeléfono() {
        return teléfono;
    }
    
    
}
