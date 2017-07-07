/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iorsh.guidebot;
import com.iorsh.lib.*;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.sql.*;

/**
 *
 * @author jvarred
 */
//TODOS LOS METODOS Y OBJETOS DEBEN SER SINCRONIZADOS Y ESTÁTICOS
public abstract class Persistence {
    private static Connection conexión;
    public abstract void inicializar();
    public abstract ArrayList<Evento> obtenerEventos();
    public abstract Evento obtenerEvento(Integer id);
    public abstract ArrayList<Evento> obtenerEventos(Persona p);
    public abstract ArrayList<Evento> obtenerEventos(Recurso r);
    public abstract ArrayList<Bloque> obtenerBloques();
    public abstract Bloque obtenerBloque(Integer id);
    public abstract ArrayList<Bloque> obtenerBloques(Timestamp desde, Timestamp hasta);
    public abstract ArrayList<Bloque> obtenerBloquesDesde(Timestamp t);
    public abstract ArrayList<Bloque> obtenerBloquesHasta(Timestamp t);
    public abstract ArrayList<Persona> obtenerPersonas();
    public abstract Persona obtenerPersona(Integer id);
    public abstract ArrayList<Recurso> obtenerRecursos();
    public abstract Recurso obtenerRecurso(Integer id);
}
