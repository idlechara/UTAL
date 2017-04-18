
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestMultiFilteredNotice", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestMultiFilteredNotice", namespace = "http://services.com/", propOrder = {
    "type",
    "index"
})
public class RequestMultiFilteredNotice {

    @XmlElement(name = "type", namespace = "")
    private String type;
    @XmlElement(name = "index", namespace = "")
    private int index;

    /**
     * 
     * @return
     *     returns String
     */
    public String getType() {
        return this.type;
    }

    /**
     * 
     * @param type
     *     the value for the type property
     */
    public void setType(String type) {
        this.type = type;
    }

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
