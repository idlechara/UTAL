
package com.iorsh.jarvis.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.TipoRecurso;

@XmlRootElement(name = "eventosPorRecurso", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosPorRecurso", namespace = "http://jarvis.iorsh.com/")
public class EventosPorRecurso {

    @XmlElement(name = "r", namespace = "")
    private TipoRecurso r;

    /**
     * 
     * @return
     *     returns TipoRecurso
     */
    public TipoRecurso getR() {
        return this.r;
    }

    /**
     * 
     * @param r
     *     the value for the r property
     */
    public void setR(TipoRecurso r) {
        this.r = r;
    }

}
