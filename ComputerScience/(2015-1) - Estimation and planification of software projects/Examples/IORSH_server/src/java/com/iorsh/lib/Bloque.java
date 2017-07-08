/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iorsh.lib;

import java.sql.Timestamp;

/**
 *
 * @author jvarred
 */
public abstract class Bloque {
    Integer id;
    Timestamp inicio, fin;

    public Bloque(Integer id, Timestamp inicio, Timestamp fin) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getInicio() {
        return inicio;
    }

    public Timestamp getFin() {
        return fin;
    }
    
    
    public abstract boolean comienzaAntesDe(Bloque b);
    public abstract boolean comienzaDespuésDe(Bloque b);
    public abstract boolean terminaAntesDe(Bloque b);
    public abstract boolean terminaDespuésDe(Bloque b);
}
