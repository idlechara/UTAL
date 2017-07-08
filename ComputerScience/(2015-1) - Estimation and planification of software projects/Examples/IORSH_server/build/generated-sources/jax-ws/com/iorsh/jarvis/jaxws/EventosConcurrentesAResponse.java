
package com.iorsh.jarvis.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Evento;

@XmlRootElement(name = "eventosConcurrentesAResponse", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosConcurrentesAResponse", namespace = "http://jarvis.iorsh.com/")
public class EventosConcurrentesAResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList<Evento> _return;

    /**
     * 
     * @return
     *     returns ArrayList<Evento>
     */
    public ArrayList<Evento> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList<Evento> _return) {
        this._return = _return;
    }

}
