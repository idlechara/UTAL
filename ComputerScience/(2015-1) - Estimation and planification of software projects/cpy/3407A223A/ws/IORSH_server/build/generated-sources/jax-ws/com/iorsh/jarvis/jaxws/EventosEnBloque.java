
package com.iorsh.jarvis.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iorsh.lib.Bloque;

@XmlRootElement(name = "eventosEnBloque", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosEnBloque", namespace = "http://jarvis.iorsh.com/")
public class EventosEnBloque {

    @XmlElement(name = "b", namespace = "")
    private ArrayList<Bloque> b;

    /**
     * 
     * @return
     *     returns ArrayList<Bloque>
     */
    public ArrayList<Bloque> getB() {
        return this.b;
    }

    /**
     * 
     * @param b
     *     the value for the b property
     */
    public void setB(ArrayList<Bloque> b) {
        this.b = b;
    }

}
