
package com.iorsh.jarvis.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Evento;

@XmlRootElement(name = "eventosConcurrentesA", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosConcurrentesA", namespace = "http://jarvis.iorsh.com/")
public class EventosConcurrentesA {

    @XmlElement(name = "e", namespace = "")
    private ArrayList<Evento> e;

    /**
     * 
     * @return
     *     returns ArrayList<Evento>
     */
    public ArrayList<Evento> getE() {
        return this.e;
    }

    /**
     * 
     * @param e
     *     the value for the e property
     */
    public void setE(ArrayList<Evento> e) {
        this.e = e;
    }

}
