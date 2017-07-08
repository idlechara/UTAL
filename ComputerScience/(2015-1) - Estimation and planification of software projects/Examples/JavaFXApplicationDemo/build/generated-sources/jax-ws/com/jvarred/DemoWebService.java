
package com.jvarred;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DemoWebService", targetNamespace = "http://jvarred.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DemoWebService {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "initialize", targetNamespace = "http://jvarred.com/", className = "com.jvarred.Initialize")
    @ResponseWrapper(localName = "initializeResponse", targetNamespace = "http://jvarred.com/", className = "com.jvarred.InitializeResponse")
    @Action(input = "http://jvarred.com/DemoWebService/initializeRequest", output = "http://jvarred.com/DemoWebService/initializeResponse")
    public String initialize();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllStrings", targetNamespace = "http://jvarred.com/", className = "com.jvarred.GetAllStrings")
    @ResponseWrapper(localName = "getAllStringsResponse", targetNamespace = "http://jvarred.com/", className = "com.jvarred.GetAllStringsResponse")
    @Action(input = "http://jvarred.com/DemoWebService/getAllStringsRequest", output = "http://jvarred.com/DemoWebService/getAllStringsResponse")
    public String getAllStrings();

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://jvarred.com/", className = "com.jvarred.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://jvarred.com/", className = "com.jvarred.HelloResponse")
    @Action(input = "http://jvarred.com/DemoWebService/helloRequest", output = "http://jvarred.com/DemoWebService/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

    /**
     * 
     * @param text
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertString", targetNamespace = "http://jvarred.com/", className = "com.jvarred.InsertString")
    @ResponseWrapper(localName = "insertStringResponse", targetNamespace = "http://jvarred.com/", className = "com.jvarred.InsertStringResponse")
    @Action(input = "http://jvarred.com/DemoWebService/insertStringRequest", output = "http://jvarred.com/DemoWebService/insertStringResponse")
    public String insertString(
        @WebParam(name = "text", targetNamespace = "")
        String text);

}
