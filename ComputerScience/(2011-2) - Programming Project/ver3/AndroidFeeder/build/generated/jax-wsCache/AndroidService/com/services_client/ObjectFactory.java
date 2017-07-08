
package com.services_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.services_client package. 
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

    private final static QName _RequestFilteredNotices_QNAME = new QName("http://services.com/", "requestFilteredNotices");
    private final static QName _RequestNoticesResponse_QNAME = new QName("http://services.com/", "requestNoticesResponse");
    private final static QName _RequestNoticesListByTypeResponse_QNAME = new QName("http://services.com/", "requestNoticesListByTypeResponse");
    private final static QName _RequestFilteredNoticesResponse_QNAME = new QName("http://services.com/", "requestFilteredNoticesResponse");
    private final static QName _RequestNoticesList_QNAME = new QName("http://services.com/", "requestNoticesList");
    private final static QName _TestResponse_QNAME = new QName("http://services.com/", "testResponse");
    private final static QName _RequestNoticesListByType_QNAME = new QName("http://services.com/", "requestNoticesListByType");
    private final static QName _RequestNoticesListResponse_QNAME = new QName("http://services.com/", "requestNoticesListResponse");
    private final static QName _Test_QNAME = new QName("http://services.com/", "test");
    private final static QName _RequestNotices_QNAME = new QName("http://services.com/", "requestNotices");
    private final static QName _Exception_QNAME = new QName("http://services.com/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.services_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Test }
     * 
     */
    public Test createTest() {
        return new Test();
    }

    /**
     * Create an instance of {@link RequestNoticesListResponse }
     * 
     */
    public RequestNoticesListResponse createRequestNoticesListResponse() {
        return new RequestNoticesListResponse();
    }

    /**
     * Create an instance of {@link RequestNoticesListByType }
     * 
     */
    public RequestNoticesListByType createRequestNoticesListByType() {
        return new RequestNoticesListByType();
    }

    /**
     * Create an instance of {@link RequestNotices }
     * 
     */
    public RequestNotices createRequestNotices() {
        return new RequestNotices();
    }

    /**
     * Create an instance of {@link RequestNoticesList }
     * 
     */
    public RequestNoticesList createRequestNoticesList() {
        return new RequestNoticesList();
    }

    /**
     * Create an instance of {@link RequestFilteredNoticesResponse }
     * 
     */
    public RequestFilteredNoticesResponse createRequestFilteredNoticesResponse() {
        return new RequestFilteredNoticesResponse();
    }

    /**
     * Create an instance of {@link TestResponse }
     * 
     */
    public TestResponse createTestResponse() {
        return new TestResponse();
    }

    /**
     * Create an instance of {@link RequestNoticesResponse }
     * 
     */
    public RequestNoticesResponse createRequestNoticesResponse() {
        return new RequestNoticesResponse();
    }

    /**
     * Create an instance of {@link RequestFilteredNotices }
     * 
     */
    public RequestFilteredNotices createRequestFilteredNotices() {
        return new RequestFilteredNotices();
    }

    /**
     * Create an instance of {@link RequestNoticesListByTypeResponse }
     * 
     */
    public RequestNoticesListByTypeResponse createRequestNoticesListByTypeResponse() {
        return new RequestNoticesListByTypeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestFilteredNotices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestFilteredNotices")
    public JAXBElement<RequestFilteredNotices> createRequestFilteredNotices(RequestFilteredNotices value) {
        return new JAXBElement<RequestFilteredNotices>(_RequestFilteredNotices_QNAME, RequestFilteredNotices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNoticesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNoticesResponse")
    public JAXBElement<RequestNoticesResponse> createRequestNoticesResponse(RequestNoticesResponse value) {
        return new JAXBElement<RequestNoticesResponse>(_RequestNoticesResponse_QNAME, RequestNoticesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNoticesListByTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNoticesListByTypeResponse")
    public JAXBElement<RequestNoticesListByTypeResponse> createRequestNoticesListByTypeResponse(RequestNoticesListByTypeResponse value) {
        return new JAXBElement<RequestNoticesListByTypeResponse>(_RequestNoticesListByTypeResponse_QNAME, RequestNoticesListByTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestFilteredNoticesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestFilteredNoticesResponse")
    public JAXBElement<RequestFilteredNoticesResponse> createRequestFilteredNoticesResponse(RequestFilteredNoticesResponse value) {
        return new JAXBElement<RequestFilteredNoticesResponse>(_RequestFilteredNoticesResponse_QNAME, RequestFilteredNoticesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNoticesList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNoticesList")
    public JAXBElement<RequestNoticesList> createRequestNoticesList(RequestNoticesList value) {
        return new JAXBElement<RequestNoticesList>(_RequestNoticesList_QNAME, RequestNoticesList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "testResponse")
    public JAXBElement<TestResponse> createTestResponse(TestResponse value) {
        return new JAXBElement<TestResponse>(_TestResponse_QNAME, TestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNoticesListByType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNoticesListByType")
    public JAXBElement<RequestNoticesListByType> createRequestNoticesListByType(RequestNoticesListByType value) {
        return new JAXBElement<RequestNoticesListByType>(_RequestNoticesListByType_QNAME, RequestNoticesListByType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNoticesListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNoticesListResponse")
    public JAXBElement<RequestNoticesListResponse> createRequestNoticesListResponse(RequestNoticesListResponse value) {
        return new JAXBElement<RequestNoticesListResponse>(_RequestNoticesListResponse_QNAME, RequestNoticesListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Test }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "test")
    public JAXBElement<Test> createTest(Test value) {
        return new JAXBElement<Test>(_Test_QNAME, Test.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestNotices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "requestNotices")
    public JAXBElement<RequestNotices> createRequestNotices(RequestNotices value) {
        return new JAXBElement<RequestNotices>(_RequestNotices_QNAME, RequestNotices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
