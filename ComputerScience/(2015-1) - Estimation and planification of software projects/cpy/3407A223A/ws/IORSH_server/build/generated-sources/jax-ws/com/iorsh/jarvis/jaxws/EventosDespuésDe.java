
package com.iorsh.jarvis.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Evento;

@XmlRootElement(name = "eventosDespu\u00e9sDe", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosDespu\u00e9sDe", namespace = "http://jarvis.iorsh.com/")
public class EventosDespuésDe {

    @XmlElement(name = "e", namespace = "")
    private Evento e;

    /**
     * 
     * @return
     *     returns Evento
     */
    public Evento getE() {
        return this.e;
    }

    /**
     * 
     * @param e
     *     the value for the e property
     */
    public void setE(Evento e) {
        this.e = e;
    }

}
