
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestCalendarNotice", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestCalendarNotice", namespace = "http://services.com/", propOrder = {
    "index",
    "year",
    "month",
    "day"
})
public class RequestCalendarNotice {

    @XmlElement(name = "index", namespace = "")
    private int index;
    @XmlElement(name = "year", namespace = "")
    private int year;
    @XmlElement(name = "month", namespace = "")
    private int month;
    @XmlElement(name = "day", namespace = "")
    private int day;

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

    /**
     * 
     * @return
     *     returns int
     */
    public int getDay() {
        return this.day;
    }

    /**
     * 
     * @param day
     *     the value for the day property
     */
    public void setDay(int day) {
        this.day = day;
    }

}
