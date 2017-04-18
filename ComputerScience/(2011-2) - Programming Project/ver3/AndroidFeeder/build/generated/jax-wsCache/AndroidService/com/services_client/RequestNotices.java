
package com.services_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestNotices complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestNotices">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="range" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNotices", propOrder = {
    "from",
    "range"
})
public class RequestNotices {

    protected int from;
    protected int range;

    /**
     * Gets the value of the from property.
     * 
     */
    public int getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     */
    public void setFrom(int value) {
        this.from = value;
    }

    /**
     * Gets the value of the range property.
     * 
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     */
    public void setRange(int value) {
        this.range = value;
    }

}
