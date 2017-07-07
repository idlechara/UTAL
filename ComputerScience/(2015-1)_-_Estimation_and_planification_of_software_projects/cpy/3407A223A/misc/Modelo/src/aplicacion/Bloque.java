/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.sql.Timestamp;

/**
 *
 * @author Zerling
 */
public class Bloque 
{
    private String id;
    private Timestamp inicio;
    private Timestamp fin;

    public Bloque(String id)
    {
        this.id = id;
        this.inicio = null;
        this.fin = null;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Timestamp getInicio()
    {
        return inicio;
    }

    public void setInicio(Timestamp inicio)
    {
        this.inicio = inicio;
    }

    public Timestamp getFin()
    {
        return fin;
    }

    public void setFin(Timestamp fin)
    {
        this.fin = fin;
    }
    
    
}