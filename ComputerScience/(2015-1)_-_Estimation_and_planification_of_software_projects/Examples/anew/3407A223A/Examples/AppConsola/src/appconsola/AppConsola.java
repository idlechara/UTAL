/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appconsola;

/**
 *
 * @author jvarred
 */
public class AppConsola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola mundo!");
        String response = crearBaseDatos("CarlosConK");
        insertar(030345126);
        System.out.println(leerTodos());
    }

    private static java.util.List<java.lang.Object> retornaArray() {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.retornaArray();
    }

    private static String leerTodos() {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.leerTodos();
    }

    private static Boolean insertar(int parametro) {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.insertar(parametro);
    }

    private static String hello(java.lang.String name) {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.hello(name);
    }

    private static String dummy() {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.dummy();
    }

    private static String crearBaseDatos(java.lang.String nombre) {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.crearBaseDatos(nombre);
    }
    
    
}