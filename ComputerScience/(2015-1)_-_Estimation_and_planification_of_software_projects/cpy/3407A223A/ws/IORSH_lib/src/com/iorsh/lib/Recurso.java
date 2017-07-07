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
public abstract class Recurso {
    private String nombre, descripción;
    private Integer id;
    private TipoRecurso tipo;
    
    Recurso(Integer id, String nombre, String descripción, TipoRecurso tipo){
        this.id = id;
        this.nombre = nombre;
        this.descripción = descripción;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripción() {
        return descripción;
    }
    
    public Integer getId() {
        return id;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }
}
