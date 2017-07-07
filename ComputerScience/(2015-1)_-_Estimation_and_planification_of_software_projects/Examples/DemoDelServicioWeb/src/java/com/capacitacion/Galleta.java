/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capacitacion;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jvarred
 */
@WebService(serviceName = "Galleta")
public class Galleta {
    
    ArrayList<String> arraylist = new ArrayList<>();
    int value = 0;
    
    private String holaMundo(){
        return arraylist.toString();
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        value++;
        arraylist.add(txt);
        System.out.println("hello()");
        return holaMundo();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sumarPositivos")
    public Integer sumarPositivos(  @WebParam(name = "a") Integer a, 
                                    @WebParam(name = "b") Integer b) 
                                    throws Exception {
        System.out.println("sumerPositivos()");
        Integer suma;
        
        if(a < 0)
            throw new Exception("El parámetro a es negativo");
        else if (b < 0)
            throw new Exception("El parámetro b es negativo");
        
        suma = a + b;
        return suma;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "hello_dos")
    public String hello_dos() {
        return "Hola dos";
    }
}
