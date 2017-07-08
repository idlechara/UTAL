
package com.capacitacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.capacitacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloResponse_QNAME = new QName("http://capacitacion.com/", "helloResponse");
    private final static QName _HelloDosResponse_QNAME = new QName("http://capacitacion.com/", "hello_dosResponse");
    private final static QName _SumarPositivosResponse_QNAME = new QName("http://capacitacion.com/", "sumarPositivosResponse");
    private final static QName _SumarPositivos_QNAME = new QName("http://capacitacion.com/", "sumarPositivos");
    private final static QName _HelloDos_QNAME = new QName("http://capacitacion.com/", "hello_dos");
    private final static QName _Exception_QNAME = new QName("http://capacitacion.com/", "Exception");
    private final static QName _Hello_QNAME = new QName("http://capacitacion.com/", "hello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.capacitacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SumarPositivos }
     * 
     */
    public SumarPositivos createSumarPositivos() {
        return new SumarPositivos();
    }

    /**
     * Create an instance of {@link HelloDos }
     * 
     */
    public HelloDos createHelloDos() {
        return new HelloDos();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link HelloDosResponse }
     * 
     */
    public HelloDosResponse createHelloDosResponse() {
        return new HelloDosResponse();
    }

    /**
     * Create an instance of {@link SumarPositivosResponse }
     * 
     */
    public SumarPositivosResponse createSumarPositivosResponse() {
        return new SumarPositivosResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloDosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "hello_dosResponse")
    public JAXBElement<HelloDosResponse> createHelloDosResponse(HelloDosResponse value) {
        return new JAXBElement<HelloDosResponse>(_HelloDosResponse_QNAME, HelloDosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumarPositivosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "sumarPositivosResponse")
    public JAXBElement<SumarPositivosResponse> createSumarPositivosResponse(SumarPositivosResponse value) {
        return new JAXBElement<SumarPositivosResponse>(_SumarPositivosResponse_QNAME, SumarPositivosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumarPositivos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "sumarPositivos")
    public JAXBElement<SumarPositivos> createSumarPositivos(SumarPositivos value) {
        return new JAXBElement<SumarPositivos>(_SumarPositivos_QNAME, SumarPositivos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloDos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "hello_dos")
    public JAXBElement<HelloDos> createHelloDos(HelloDos value) {
        return new JAXBElement<HelloDos>(_HelloDos_QNAME, HelloDos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://capacitacion.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

}
