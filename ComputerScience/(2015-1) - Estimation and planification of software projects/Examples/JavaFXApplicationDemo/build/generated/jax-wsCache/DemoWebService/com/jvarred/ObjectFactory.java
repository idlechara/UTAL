
package com.jvarred;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jvarred package. 
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

    private final static QName _Hello_QNAME = new QName("http://jvarred.com/", "hello");
    private final static QName _Initialize_QNAME = new QName("http://jvarred.com/", "initialize");
    private final static QName _InsertString_QNAME = new QName("http://jvarred.com/", "insertString");
    private final static QName _GetAllStringsResponse_QNAME = new QName("http://jvarred.com/", "getAllStringsResponse");
    private final static QName _GetAllStrings_QNAME = new QName("http://jvarred.com/", "getAllStrings");
    private final static QName _HelloResponse_QNAME = new QName("http://jvarred.com/", "helloResponse");
    private final static QName _InsertStringResponse_QNAME = new QName("http://jvarred.com/", "insertStringResponse");
    private final static QName _InitializeResponse_QNAME = new QName("http://jvarred.com/", "initializeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jvarred
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InitializeResponse }
     * 
     */
    public InitializeResponse createInitializeResponse() {
        return new InitializeResponse();
    }

    /**
     * Create an instance of {@link InsertStringResponse }
     * 
     */
    public InsertStringResponse createInsertStringResponse() {
        return new InsertStringResponse();
    }

    /**
     * Create an instance of {@link GetAllStrings }
     * 
     */
    public GetAllStrings createGetAllStrings() {
        return new GetAllStrings();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetAllStringsResponse }
     * 
     */
    public GetAllStringsResponse createGetAllStringsResponse() {
        return new GetAllStringsResponse();
    }

    /**
     * Create an instance of {@link InsertString }
     * 
     */
    public InsertString createInsertString() {
        return new InsertString();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link Initialize }
     * 
     */
    public Initialize createInitialize() {
        return new Initialize();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Initialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "initialize")
    public JAXBElement<Initialize> createInitialize(Initialize value) {
        return new JAXBElement<Initialize>(_Initialize_QNAME, Initialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "insertString")
    public JAXBElement<InsertString> createInsertString(InsertString value) {
        return new JAXBElement<InsertString>(_InsertString_QNAME, InsertString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStringsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "getAllStringsResponse")
    public JAXBElement<GetAllStringsResponse> createGetAllStringsResponse(GetAllStringsResponse value) {
        return new JAXBElement<GetAllStringsResponse>(_GetAllStringsResponse_QNAME, GetAllStringsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStrings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "getAllStrings")
    public JAXBElement<GetAllStrings> createGetAllStrings(GetAllStrings value) {
        return new JAXBElement<GetAllStrings>(_GetAllStrings_QNAME, GetAllStrings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "insertStringResponse")
    public JAXBElement<InsertStringResponse> createInsertStringResponse(InsertStringResponse value) {
        return new JAXBElement<InsertStringResponse>(_InsertStringResponse_QNAME, InsertStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jvarred.com/", name = "initializeResponse")
    public JAXBElement<InitializeResponse> createInitializeResponse(InitializeResponse value) {
        return new JAXBElement<InitializeResponse>(_InitializeResponse_QNAME, InitializeResponse.class, null, value);
    }

}
