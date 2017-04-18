
package com.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.ksoap2.serialization.KvmSerializable;

@XmlRootElement(name = "testKsoapResponse", namespace = "http://services.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testKsoapResponse", namespace = "http://services.com/")
public class TestKsoapResponse {

    @XmlElement(name = "return", namespace = "")
    private KvmSerializable _return;

    /**
     * 
     * @return
     *     returns KvmSerializable
     */
    public KvmSerializable getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(KvmSerializable _return) {
        this._return = _return;
    }

}
