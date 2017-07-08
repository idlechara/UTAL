
package com.iorsh.jarvis.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Bloque;

@XmlRootElement(name = "listaBloquesResponse", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaBloquesResponse", namespace = "http://jarvis.iorsh.com/")
public class ListaBloquesResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<Bloque> _return;

    /**
     * 
     * @return
     *     returns ArrayList<Bloque>
     */
    public ArrayList<Bloque> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<Bloque> _return) {
        this._return = _return;
    }

}
