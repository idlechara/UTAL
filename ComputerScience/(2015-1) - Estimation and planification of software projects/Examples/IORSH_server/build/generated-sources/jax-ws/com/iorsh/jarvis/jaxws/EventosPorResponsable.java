
package com.iorsh.jarvis.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Persona;

@XmlRootElement(name = "eventosPorResponsable", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosPorResponsable", namespace = "http://jarvis.iorsh.com/")
public class EventosPorResponsable {

    @XmlElement(name = "p", namespace = "")
    private Persona p;

    /**
     * 
     * @return
     *     returns Persona
     */
    public Persona getP() {
        return this.p;
    }

    /**
     * 
     * @param p
     *     the value for the p property
     */
    public void setP(Persona p) {
        this.p = p;
    }

}
