/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Spectre
 */
public class BD {

    /**
     * @param args the command line arguments
     */
    
    public static String ini(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\dataBase.db");
            System.out.println("Opened database successfully");
            Statement stmt = null;
            stmt = c.createStatement();
            String sql = "CREATE TABLE PERSONA " +
                   "(ID INT PRIMARY KEY    NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " TELEFONO            TEXT, " + 
                   " CORREO        TEXT)";
            String sql1= "CREATE TABLE BLOQUE " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " TIMESTAMP_INICIO           DATETIME    NOT NULL, " + 
                   " TIMESTAMP_TERMINO            DATETIME    NOT NULL); ";
            String sql2 = "CREATE TABLE RECURSO " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " DESCRIPCION            TEXT, " + 
                   " TIPO_RECURSO        TEXT)";
            String sql3 = "CREATE TABLE EVENTO " +
                   "(ID INT PRIMARY KEY      NOT NULL," +
                   " NOMBRE           TEXT    NOT NULL, " + 
                   " RESPONSABLE           INT, " +
                   " BLOQUES           INT, " +
                   " DESCRIPCION            TEXT, " + 
                   " CORREO        CHAR(50))";
                   
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return e.getClass().getName() + ": " + e.getMessage();
        }
        System.out.println("Opened database successfully");
        return "OK";
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        String execute = ini();
        System.out.println(execute);
    }
    
}
