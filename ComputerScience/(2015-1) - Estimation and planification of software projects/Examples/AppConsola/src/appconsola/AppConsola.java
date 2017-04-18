/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appconsola;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        while(true){
            hello(sc.nextLine());
        }
    }

    private static String hello(java.lang.String name) {
        com.demo.Demo_Service service = new com.demo.Demo_Service();
        com.demo.Demo port = service.getDemoPort();
        return port.hello(name);
    }
}