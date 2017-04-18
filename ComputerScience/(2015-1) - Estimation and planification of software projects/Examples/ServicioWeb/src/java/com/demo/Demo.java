/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.NotFoundException;

/**
 * 
 * Servicio web para pruebas en crash course
 * @author jvarred
 */
@WebService(serviceName = "Demo")
public class Demo {

    /**
     * Hola mundo versión web
     * @param txt el texto a procesar
     * @return Un hola mundo con el texto
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        System.out.println("Hello " + txt + " !");
        
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retornaArray")
    public ArrayList retornaArray() {
        
        Random rand = new Random();
        ArrayList arreglo = new ArrayList();
        arreglo.add(rand.nextInt());
        arreglo.add(rand.nextInt());
        arreglo.add(rand.nextInt());
        arreglo.add(rand.nextInt());
        return arreglo;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearBaseDatos")
    public String crearBaseDatos(@WebParam(name = "nombre") String nombre) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/jvarred/test.db");
            System.out.println("Opened database successfully");
            
            Statement stmt = null;
            stmt = c.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS STORAGEDATA "
                    + "(ID INT PRIMARY KEY, DATA TEXT NOT NULL)";
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return e.getClass().getName() + ": " + e.getMessage();
        }
        
        System.out.println("Opened database successfully");
        return "OK";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dummy")
    public String dummy() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertar")
    public Boolean insertar(@WebParam(name = "parametro") int parametro) {
        Connection c = null;
        Statement stmt = null;
        String text = Integer.toString(parametro);
        System.out.println("Param: " + text);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/jvarred/test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO STORAGEDATA VALUES (NULL, '" + text + "');";
            System.out.println("QUERY: " + sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "leerTodos")
    public String leerTodos() throws NotFoundException {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/jvarred/test.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM STORAGEDATA;" );
          String output = "";
          while ( rs.next() ) {
             int id = rs.getInt("id");
             String  name = rs.getString("data");
             System.out.print( "ID = " + id + ", DATA = " + name );
             System.out.println();
             output += "(" + id + ", "+ name +")";
          }
          rs.close();
          stmt.close();
          c.close();
          return output;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return e.getClass().getName() + ": " + e.getMessage();
        }
    }
}
