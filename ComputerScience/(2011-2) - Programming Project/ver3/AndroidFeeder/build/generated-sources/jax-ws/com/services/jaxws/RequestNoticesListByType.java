
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestNoticesListByType", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNoticesListByType", namespace = "http://services.com/")
public class RequestNoticesListByType {

    @XmlElement(name = "type", namespace = "")
    private int type;

    /**
     * 
     * @return
     *     returns int
     */
    public int getType() {
        return this.type;
    }

    /**
     * 
     * @param type
     *     the value for the type property
     */
    public void setType(int type) {
        this.type = type;
    }

}
