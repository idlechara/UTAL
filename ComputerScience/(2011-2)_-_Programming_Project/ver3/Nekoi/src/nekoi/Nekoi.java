/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nekoi;

import com.dataTransporter.AndroConverter;
import com.dataTransporter.Transporter;
import com.message.Message;
import com.persistence.NoticeArray;
import com.persistence.NoticeMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kinya
 */
public class Nekoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        HashMap data = new HashMap();
////        data.put("nombre", "erik");
////        data.put("apellido", "regla");
////        
////        Transporter trans = new Transporter();
////        trans.add(data);
////        
////        System.out.println(AndroConverter.convertTo(trans));
//        
//        NoticeMap map = new NoticeMap();
//        map.type = 0;
//        map.put("type", "0");
//        map.put("Nombre del modulo", "Proyecto de programacion");
//        map.put("Seccion del modulo", "I , II");
//        map.put("Profesor encargado", "Kynku Nekoi");
//        map.put("Planificacion original", "Martes - bloque 6");
//        map.put("Planificacion nueva", "Sabado - bloque 1 (problem?)");
//        map.put("Obervacion", "Y asi con los profes troll");
//                
//        NoticeMap map2 = new NoticeMap();
//        map2.type = 0;
//        map2.put("type", "0");
//        map2.put("Nombre del modulo2", "Proyecto de programacion");
//        map2.put("Seccion del modulo2", "I , II");
//        map2.put("Profesor encargado2", "Kynku Nekoi");
//        map2.put("Planificacion original2", "Martes - bloque 6");
//        map2.put("Planificacion nueva2", "Sabado - bloque 1 (problem?)");
//        map2.put("Obervacion2", "Y asi con los profes troll");
//        
//        NoticeMap map3 = new NoticeMap();
//        map3.type = 1;
//        map3.put("type", "1");
//        map3.put("Nombre del modulo3", "Proyecto de programacion");
//        map3.put("Seccion del modulo3", "I , II");
//        map3.put("Profesor encargado3", "Kynku Nekoi");
//        map3.put("Planificacion original3", "Martes - bloque 6");
//        map3.put("Planificacion nueva3", "Sabado - bloque 1 (problem?)");
//        map3.put("Obervacion3", "Y asi con los profes troll");
//        
//        
//        NoticeMap map4 = new NoticeMap();
//        map4.type = 10;
//        map4.put("type", "10");
//        map4.put("year", "2011");
//        map4.put("month", "12");
//        map4.put("day", "31");
//        map4.put("Nombre del modulo3", "Proyecto de programacion");
//        map4.put("Seccion del modulo3", "I , II");
//        map4.put("Profesor encargado3", "Kynku Nekoi");
//        map4.put("Planificacion original3", "Martes - bloque 6");
//        map4.put("Planificacion nueva3", "Sabado - bloque 1 (problem?)");
//        map4.put("Obervacion3", "Y asi con los profes troll");
//        
//        
//        NoticeMap map5 = new NoticeMap();
//        map5.type = 10;
//        map5.put("type", "10");
//        map5.put("year", "2012");
//        map5.put("month", "12");
//        map5.put("day", "12");
//        map5.put("Nombre del modulo3", "Proyecto de programacion");
//        map5.put("Seccion del modulo3", "I , II");
//        map5.put("Profesor encargado3", "Kynku Nekoi");
//        map5.put("Planificacion original3", "Martes - bloque 6");
//        map5.put("Planificacion nueva3", "Sabado - bloque 1 (problem?)");
//        map5.put("Obervacion3", "Y asi con los profes troll");
//        
//        NoticeMap map6 = new NoticeMap();
//        map6.type = 10;
//        map6.put("type", "5");
//        map6.put("Y con eso se demuestra", "que el android solo interpreta el mapa D:");
//        
        com.dataPack.Message mes1 = new com.dataPack.Message(0, 0, 10, 10, 10);
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Informacion 1", "Respuesta1");
        mes1.add("Archivo Adjunto", "http://metalblasts.tumblr.com");
        com.dataPack.Message mes2 = new com.dataPack.Message(0, 2, 10, 10, 10);
        mes2.add("Informacion 2", "Respuesta2");
        com.dataPack.Message mes3 = new com.dataPack.Message(0, 3, 10, 10, 10);
        mes3.add("Informacion 3", "Respuesta3");
        com.dataPack.Message mes4 = new com.dataPack.Message(0, 10, 10, 10, 2010);
        mes4.add("Informacion 4", "Respuesta3");
        
        ArrayList<com.dataPack.Message> noticesList = new ArrayList<com.dataPack.Message>();
        noticesList.add(mes1);
        noticesList.add(mes2);
        noticesList.add(mes3);
        noticesList.add(mes1);
        noticesList.add(mes4);
        
        FileOutputStream fos = new FileOutputStream("/media/Documents/Data.dta");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        //NoticeArray noticesList = new NoticeArray();
//        noticesList.add(map);
//        noticesList.add(map2);
//        noticesList.add(map3);
//        noticesList.add(map4);
//        noticesList.add(map5);
//        noticesList.add(map6);
        
        oos.writeObject(noticesList);
        
      //  System.out.println("array:" + (ArrayList)noticesList);
    }
}
