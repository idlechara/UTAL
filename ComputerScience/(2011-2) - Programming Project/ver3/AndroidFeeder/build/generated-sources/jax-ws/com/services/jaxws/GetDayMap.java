
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getDayMap", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDayMap", namespace = "http://services.com/", propOrder = {
    "year",
    "month"
})
public class GetDayMap {

    @XmlElement(name = "year", namespace = "")
    private String year;
    @XmlElement(name = "month", namespace = "")
    private String month;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * 
     * @param month
     *     the value for the month property
     */
    public void setMonth(String month) {
        this.month = month;
    }

}
