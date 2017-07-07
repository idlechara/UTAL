/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.util.ArrayList;

/**
 *
 * @author Zerling
 */
public class Evento
{
    private int id;
    private String nombre;
    private Persona responsable;
    private ArrayList <Bloque> bloques;    
    private String descripcion;
    private Recurso recursoAsociado;

    public Evento(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;    
        this.responsable = null;
        this.bloques = null;
        this.descripcion = null;
        this.recursoAsociado = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public ArrayList<Bloque> getBloques()
    {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques)
    {
        this.bloques = bloques;
    }

    public Persona getResponsable()
    {
        return responsable;
    }

    public void setResponsable(Persona responsable)
    {
        this.responsable = responsable;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public Recurso getRecursoAsociado() {
        return recursoAsociado;
    }

    public void setRecursoAsociado(Recurso recursoAsociado) {
        this.recursoAsociado = recursoAsociado;
    }

    //METODOS DELEGADOS
    
    //*******************RECURSO***********************
    public String getNombreRecurso()
    {
        return recursoAsociado.getNombre();
    }

    public TipoRecurso getTipoRecurso()
    {
        return recursoAsociado.getTipo();
    }

    //******************BLOQUE*****************

    public boolean isEmptyBloque()
    {
        return bloques.isEmpty();
    }

//    public boolean contains(Object o)
//    {
//        return bloques.contains(o);
//    }

    public Bloque getBloque(int index)
    {
        return bloques.get(index);
    }

    public boolean addBloque(Bloque e)
    {
        return bloques.add(e);
    }

    public Bloque removeBloque(int index)
    {
        return bloques.remove(index);
    }
    
    //***************PERSONA**********

    public int getIdPersona()
    {
        return responsable.getId();
    }

    public String getNombrePersona()
    {
        return responsable.getNombre();
    }

    public String getTelefonoPersona()
    {
        return responsable.getTelefono();
    }

    public String getEmailPersona()
    {
        return responsable.getEmail();
    }
   
}
