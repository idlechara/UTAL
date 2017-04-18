
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getMonthMap", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMonthMap", namespace = "http://services.com/")
public class GetMonthMap {

    @XmlElement(name = "year", namespace = "")
    private String year;

    /**
     * 
     * @return
     *     returns String
     */
    public String getYear() {
        return this.year;
    }

    /**
     * 
     * @param year
     *     the value for the year property
     */
    public void setYear(String year) {
        this.year = year;
    }

}
