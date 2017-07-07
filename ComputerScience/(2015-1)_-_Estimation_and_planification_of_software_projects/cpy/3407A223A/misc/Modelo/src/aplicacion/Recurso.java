/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author Zerling
 */
public class Recurso
{
   private int id;
   private String nombre;
   private String descripcion;
   private TipoRecurso tipo;
   

    public Recurso(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = null;
        this.tipo = null;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public TipoRecurso getTipo()
    {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo)
    {
        this.tipo = tipo;
    }
    
    public void setTipo(String tipo)
    {
        tipo.toLowerCase();
        switch (tipo){
            case "sala":    
                this.tipo = TipoRecurso.SALA;
                break;
            case "gimnasio":
                this.tipo = TipoRecurso.GIMNASIO;
                break;
            case "biblioteca":
                this.tipo = TipoRecurso.BIBLIOTECA;
                break;
            case"laboratorio":
                this.tipo = TipoRecurso.LABORATORIO;
                break;
            case "cancha":
                this.tipo = TipoRecurso.CANCHA;
                break;
            default: 
                this.tipo = null;
                break;
        }
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
  
}
