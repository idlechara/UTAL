
package com.demo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.demo package. 
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

    private final static QName _LeerTodosResponse_QNAME = new QName("http://demo.com/", "leerTodosResponse");
    private final static QName _RetornaArrayResponse_QNAME = new QName("http://demo.com/", "retornaArrayResponse");
    private final static QName _DummyResponse_QNAME = new QName("http://demo.com/", "dummyResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://demo.com/", "helloResponse");
    private final static QName _Insertar_QNAME = new QName("http://demo.com/", "insertar");
    private final static QName _InsertarResponse_QNAME = new QName("http://demo.com/", "insertarResponse");
    private final static QName _Dummy_QNAME = new QName("http://demo.com/", "dummy");
    private final static QName _LeerTodos_QNAME = new QName("http://demo.com/", "leerTodos");
    private final static QName _CrearBaseDatosResponse_QNAME = new QName("http://demo.com/", "crearBaseDatosResponse");
    private final static QName _RetornaArray_QNAME = new QName("http://demo.com/", "retornaArray");
    private final static QName _Hello_QNAME = new QName("http://demo.com/", "hello");
    private final static QName _CrearBaseDatos_QNAME = new QName("http://demo.com/", "crearBaseDatos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.demo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dummy }
     * 
     */
    public Dummy createDummy() {
        return new Dummy();
    }

    /**
     * Create an instance of {@link LeerTodos }
     * 
     */
    public LeerTodos createLeerTodos() {
        return new LeerTodos();
    }

    /**
     * Create an instance of {@link Insertar }
     * 
     */
    public Insertar createInsertar() {
        return new Insertar();
    }

    /**
     * Create an instance of {@link InsertarResponse }
     * 
     */
    public InsertarResponse createInsertarResponse() {
        return new InsertarResponse();
    }

    /**
     * Create an instance of {@link LeerTodosResponse }
     * 
     */
    public LeerTodosResponse createLeerTodosResponse() {
        return new LeerTodosResponse();
    }

    /**
     * Create an instance of {@link RetornaArrayResponse }
     * 
     */
    public RetornaArrayResponse createRetornaArrayResponse() {
        return new RetornaArrayResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link DummyResponse }
     * 
     */
    public DummyResponse createDummyResponse() {
        return new DummyResponse();
    }

    /**
     * Create an instance of {@link CrearBaseDatos }
     * 
     */
    public CrearBaseDatos createCrearBaseDatos() {
        return new CrearBaseDatos();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link RetornaArray }
     * 
     */
    public RetornaArray createRetornaArray() {
        return new RetornaArray();
    }

    /**
     * Create an instance of {@link CrearBaseDatosResponse }
     * 
     */
    public CrearBaseDatosResponse createCrearBaseDatosResponse() {
        return new CrearBaseDatosResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeerTodosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "leerTodosResponse")
    public JAXBElement<LeerTodosResponse> createLeerTodosResponse(LeerTodosResponse value) {
        return new JAXBElement<LeerTodosResponse>(_LeerTodosResponse_QNAME, LeerTodosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornaArrayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "retornaArrayResponse")
    public JAXBElement<RetornaArrayResponse> createRetornaArrayResponse(RetornaArrayResponse value) {
        return new JAXBElement<RetornaArrayResponse>(_RetornaArrayResponse_QNAME, RetornaArrayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DummyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "dummyResponse")
    public JAXBElement<DummyResponse> createDummyResponse(DummyResponse value) {
        return new JAXBElement<DummyResponse>(_DummyResponse_QNAME, DummyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Insertar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "insertar")
    public JAXBElement<Insertar> createInsertar(Insertar value) {
        return new JAXBElement<Insertar>(_Insertar_QNAME, Insertar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "insertarResponse")
    public JAXBElement<InsertarResponse> createInsertarResponse(InsertarResponse value) {
        return new JAXBElement<InsertarResponse>(_InsertarResponse_QNAME, InsertarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dummy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "dummy")
    public JAXBElement<Dummy> createDummy(Dummy value) {
        return new JAXBElement<Dummy>(_Dummy_QNAME, Dummy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeerTodos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "leerTodos")
    public JAXBElement<LeerTodos> createLeerTodos(LeerTodos value) {
        return new JAXBElement<LeerTodos>(_LeerTodos_QNAME, LeerTodos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearBaseDatosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "crearBaseDatosResponse")
    public JAXBElement<CrearBaseDatosResponse> createCrearBaseDatosResponse(CrearBaseDatosResponse value) {
        return new JAXBElement<CrearBaseDatosResponse>(_CrearBaseDatosResponse_QNAME, CrearBaseDatosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornaArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "retornaArray")
    public JAXBElement<RetornaArray> createRetornaArray(RetornaArray value) {
        return new JAXBElement<RetornaArray>(_RetornaArray_QNAME, RetornaArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearBaseDatos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.com/", name = "crearBaseDatos")
    public JAXBElement<CrearBaseDatos> createCrearBaseDatos(CrearBaseDatos value) {
        return new JAXBElement<CrearBaseDatos>(_CrearBaseDatos_QNAME, CrearBaseDatos.class, null, value);
    }

}
