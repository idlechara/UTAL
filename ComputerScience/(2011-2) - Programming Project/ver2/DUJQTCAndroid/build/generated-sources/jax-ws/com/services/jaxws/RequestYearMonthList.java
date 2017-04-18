
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestYearMonthList", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestYearMonthList", namespace = "http://services.com/")
public class RequestYearMonthList {

    @XmlElement(name = "year", namespace = "")
    private int year;

    /**
     * 
     * @return
     *     returns int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * 
     * @param year
     *     the value for the year property
     */
    public void setYear(int year) {
        this.year = year;
    }

}
