/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iorsh.lib;

import java.util.ArrayList;

/**
 *
 * @author jvarred
 */
public abstract class Evento {
    Integer id;
    ArrayList<Bloque> bloques;
    Persona responsable;
    String descripción;
    ArrayList<Recurso> recursosAsociados;

    public Evento(Integer id, ArrayList<Bloque> bloques, Persona responsable, String descripción, ArrayList<Recurso> recursosAsociados) {
        this.id = id;
        this.bloques = bloques;
        this.responsable = responsable;
        this.descripción = descripción;
        this.recursosAsociados = recursosAsociados;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public String getDescripción() {
        return descripción;
    }

    public ArrayList<Recurso> getRecursosAsociados() {
        return recursosAsociados;
    }

    public abstract boolean comienzaAntesDe(Evento e);
    public abstract boolean comienzaDespuésDe(Evento e);
    public abstract boolean terminaAntesDe(Evento e);
    public abstract boolean terminaDespuésDe(Evento e);
}
