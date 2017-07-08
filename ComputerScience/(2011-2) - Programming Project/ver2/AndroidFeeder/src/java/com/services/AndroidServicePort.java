/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.services_client.AndroidService;
import com.services_client.RequestNoticesResponse;
import com.services_client.TestResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * REST Web Service
 *
 * @author kinya
 */
@Path("androidserviceport")
public class AndroidServicePort {
    private AndroidService port;

    @Context
    private UriInfo context;

    /** Creates a new instance of AndroidServicePort */
    public AndroidServicePort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method requestNotices
     * @param from resource URI parameter
     * @param range resource URI parameter
     * @return an instance of javax.xml.bind.JAXBElement<com.services_client.RequestNoticesResponse>
     */
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    @Path("requestnotices/")
    public JAXBElement<RequestNoticesResponse> getRequestNotices(@QueryParam("from")
            @DefaultValue("0") int from, @QueryParam("range")
            @DefaultValue("0") int range) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.util.List<java.lang.Object> result = port.requestNotices(from, range);

                class RequestNoticesResponse_1 extends com.services_client.RequestNoticesResponse {

                    RequestNoticesResponse_1(java.util.List<java.lang.Object> _return) {
                        this._return = _return;
                    }
                }
                com.services_client.RequestNoticesResponse response = new RequestNoticesResponse_1(result);
                return new com.services_client.ObjectFactory().createRequestNoticesResponse(response);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method requestNoticesListByType
     * @param type resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("requestnoticeslistbytype/")
    public String getRequestNoticesListByType(@QueryParam("type")
            @DefaultValue("0") int type) {
        try {
            // Call Web Service Operation
            if (port != null) {
                int result = port.requestNoticesListByType(type);
                return new java.lang.Integer(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method requestNoticesList
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("requestnoticeslist/")
    public String getRequestNoticesList() {
        try {
            // Call Web Service Operation
            if (port != null) {
                int result = port.requestNoticesList();
                return new java.lang.Integer(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method requestFilteredNotices
     * @param type resource URI parameter
     * @param from resource URI parameter
     * @param range resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("requestfilterednotices/")
    public String getRequestFilteredNotices(@QueryParam("type")
            @DefaultValue("0") int type, @QueryParam("from")
            @DefaultValue("0") int from, @QueryParam("range")
            @DefaultValue("0") int range) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.requestFilteredNotices(type, from, range);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method test
     * @return an instance of javax.xml.bind.JAXBElement<com.services_client.TestResponse>
     */
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    @Path("test/")
    public JAXBElement<TestResponse> getTest() {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.util.List<java.lang.Object> result = port.test();

                class TestResponse_1 extends com.services_client.TestResponse {

                    TestResponse_1(java.util.List<java.lang.Object> _return) {
                        this._return = _return;
                    }
                }
                com.services_client.TestResponse response = new TestResponse_1(result);
                return new com.services_client.ObjectFactory().createTestResponse(response);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private AndroidService getPort() {
        try {
            // Call Web Service Operation
            com.services_client.AndroidService_Service service = new com.services_client.AndroidService_Service();
            com.services_client.AndroidService p = service.getAndroidServicePort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
