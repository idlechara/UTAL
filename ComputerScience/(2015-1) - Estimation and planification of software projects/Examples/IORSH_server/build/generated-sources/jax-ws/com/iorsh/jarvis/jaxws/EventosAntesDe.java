
package com.iorsh.jarvis.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Evento;

@XmlRootElement(name = "eventosAntesDe", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosAntesDe", namespace = "http://jarvis.iorsh.com/")
public class EventosAntesDe {

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
