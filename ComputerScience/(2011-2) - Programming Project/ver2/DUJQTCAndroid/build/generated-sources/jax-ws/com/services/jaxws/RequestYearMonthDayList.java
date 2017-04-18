
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestYearMonthDayList", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestYearMonthDayList", namespace = "http://services.com/", propOrder = {
    "year",
    "month"
})
public class RequestYearMonthDayList {

    @XmlElement(name = "year", namespace = "")
    private int year;
    @XmlElement(name = "month", namespace = "")
    private int month;

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

    /**
     * 
     * @return
     *     returns int
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * 
     * @param month
     *     the value for the month property
     */
    public void setMonth(int month) {
        this.month = month;
    }

}
