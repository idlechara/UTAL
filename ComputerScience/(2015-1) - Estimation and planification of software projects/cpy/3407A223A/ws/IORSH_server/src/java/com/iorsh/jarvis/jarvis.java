/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iorsh.jarvis;

import com.iorsh.lib.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jvarred
 */
@WebService(serviceName = "jarvis")
public class jarvis {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listaBloques")
    public java.util.ArrayList<Bloque> listaBloques() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listaEventos")
    public java.util.ArrayList<Evento> listaEventos() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listaRecursos")
    public java.util.ArrayList<Recurso> listaRecursos() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosAntesDe")
    public java.util.ArrayList<Evento> eventosAntesDe(@WebParam(name = "e") Evento e) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosDespu\u00e9sDe")
    public java.util.ArrayList<Evento> eventosDespuésDe(@WebParam(name = "e") Evento e) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosConcurrentesA")
    public java.util.ArrayList<Evento> eventosConcurrentesA(@WebParam(name = "e") ArrayList<Evento> e) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosPorRecurso")
    public java.util.ArrayList<Evento> eventosPorRecurso(@WebParam(name = "r") ArrayList<TipoRecurso> r) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosEnBloque")
    public java.util.ArrayList<Evento> eventosEnBloque(@WebParam(name = "b") ArrayList<Bloque> b) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosPorResponsable")
    public java.util.ArrayList<Evento> eventosPorResponsable(@WebParam(name = "p") ArrayList<Persona> p) {
        //TODO write your implementation code here:
        return null;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosEnDía")
    public java.util.ArrayList<Evento> eventosEnDía(@WebParam(name = "d") ArrayList<Timestamp> d) {
        //TODO write your implementation code here:
        return null;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "eventosEnSemana")
    public java.util.ArrayList<Evento> eventosEnSemana(@WebParam(name = "d") ArrayList<Timestamp> d) {
        //TODO write your implementation code here:
        return null;
    }
}
