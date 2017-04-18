
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "requestNoticesListYearMap", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNoticesListYearMap", namespace = "http://services.com/", propOrder = {
    "from",
    "year",
    "month",
    "day"
})
public class RequestNoticesListYearMap {

    @XmlElement(name = "from", namespace = "")
    private int from;
    @XmlElement(name = "year", namespace = "")
    private String year;
    @XmlElement(name = "month", namespace = "")
    private String month;
    @XmlElement(name = "day", namespace = "")
    private String day;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getDay() {
        return this.day;
    }

    /**
     * 
     * @param day
     *     the value for the day property
     */
    public void setDay(String day) {
        this.day = day;
    }

}
