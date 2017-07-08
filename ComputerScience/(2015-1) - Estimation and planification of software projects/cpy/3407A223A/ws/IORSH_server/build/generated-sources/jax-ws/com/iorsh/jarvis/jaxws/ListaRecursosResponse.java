
package com.iorsh.jarvis.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Recurso;

@XmlRootElement(name = "listaRecursosResponse", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaRecursosResponse", namespace = "http://jarvis.iorsh.com/")
public class ListaRecursosResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<Recurso> _return;

    /**
     * 
     * @return
     *     returns ArrayList<Recurso>
     */
    public ArrayList<Recurso> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<Recurso> _return) {
        this._return = _return;
    }

}
