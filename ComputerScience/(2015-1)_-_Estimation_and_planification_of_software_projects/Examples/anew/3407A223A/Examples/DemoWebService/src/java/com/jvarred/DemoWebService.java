/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvarred;

import java.sql.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jvarred
 */
@WebService(serviceName = "DemoWebService")
public class DemoWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "initialize")
    public String initialize() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/jvarred/test.db");
            System.out.println("Opened database successfully");
            Statement stmt = null;
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS STORAGEDATA "
                    + "(ID INT PRIMARY KEY     ,"
                    + " DATA           TEXT    NOT NULL)";
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
    @WebMethod(operationName = "insertString")
    public String insertString(@WebParam(name = "text") String text) {
        Connection c = null;
        Statement stmt = null;
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
            return e.getClass().getName() + ": " + e.getMessage();
        }
        return "OK, inserted";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllStrings")
    public String getAllStrings() {
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
