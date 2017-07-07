/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataPack;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kinya
 */
public class Message implements Serializable{
    public final int id;
    public final int type;
    public final int day;
    public final int month;
    public final int year;
    public ArrayList<String> keys;
    public ArrayList<String> values;
    public int lenght;
    
    public static final int CAMBIO_HORARIO = 0, SUSPENSION_CLASES = 1, SOLICITUD_AYUDANTES = 2,
            PLANIFICACION = 3, OFERTA_LABORAL_PRE = 4, AVISO_PRE = 5, OFERTA_LABORAL_POST = 6,
            OFERTA_POSTGRADO = 7, BECAS_ESTUDIO = 8, AVISOS_GENERALES = 9, CALENDARIO = 10;

    public Message(int id, int type, int day, int month, int year) {
        this.id = id;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.lenght = 0;
        this.keys = new ArrayList<String>();
        this.values = new ArrayList<String>();
    }

    
    
    public void add(String key, String value){
        this.keys.add(key);
        this.values.add(value);
        this.lenght = this.keys.size();
    }
    
    public String serialize(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public static Message reCreate(String source){
        Gson gson = new Gson();
        return gson.fromJson(source, Message.class);
    }
}
