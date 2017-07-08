
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestNotice", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNotice", namespace = "http://services.com/")
public class RequestNotice {

    @XmlElement(name = "index", namespace = "")
    private int index;

    /**
     * 
     * @return
     *     returns int
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * 
     * @param index
     *     the value for the index property
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
