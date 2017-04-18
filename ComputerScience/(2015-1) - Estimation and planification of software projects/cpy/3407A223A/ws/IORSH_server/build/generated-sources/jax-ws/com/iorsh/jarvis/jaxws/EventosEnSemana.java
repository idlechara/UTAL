
package com.iorsh.jarvis.jaxws;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "eventosEnSemana", namespace = "http://jarvis.iorsh.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventosEnSemana", namespace = "http://jarvis.iorsh.com/")
public class EventosEnSemana {

    @XmlElement(name = "d", namespace = "")
    private ArrayList<Timestamp> d;

    /**
     * 
     * @return
     *     returns ArrayList<Timestamp>
     */
    public ArrayList<Timestamp> getD() {
        return this.d;
    }

    /**
     * 
     * @param d
     *     the value for the d property
     */
    public void setD(ArrayList<Timestamp> d) {
        this.d = d;
    }

}
