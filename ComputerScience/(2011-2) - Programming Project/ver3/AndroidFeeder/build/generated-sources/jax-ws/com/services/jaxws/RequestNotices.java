
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestNotices", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNotices", namespace = "http://services.com/", propOrder = {
    "from",
    "range"
})
public class RequestNotices {

    @XmlElement(name = "from", namespace = "")
    private int from;
    @XmlElement(name = "range", namespace = "")
    private int range;

    /**
     * 
     * @return
     *     returns int
     */
    public int getFrom() {
        return this.from;
    }

    /**
     * 
     * @param from
     *     the value for the from property
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getRange() {
        return this.range;
    }

    /**
     * 
     * @param range
     *     the value for the range property
     */
    public void setRange(int range) {
        this.range = range;
    }

}
