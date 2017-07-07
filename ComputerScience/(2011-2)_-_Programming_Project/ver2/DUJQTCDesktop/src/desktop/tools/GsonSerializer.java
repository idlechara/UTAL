package desktop.tools;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import com.dataPack.Message;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class GsonSerializer {

    public static final String calendarDestination = "D:\\calendar.dat";
    public static final String publishDestination = "D:\\data.dta";
    public static final String draftDestination = "D:\\draft.dat";
            
    public static void serialize(Object target, String destination) throws Exception {
        FileOutputStream fos = new FileOutputStream(destination);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(target);
        oos.flush();
    }

    public static Object deSerialize(String destination) throws Exception {

        FileInputStream fis = new FileInputStream(destination);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

    public static void addDraftCalendarNotice(CalendarCachedMessage target) {
        ArrayList<CalendarCachedMessage> sto = new ArrayList<CalendarCachedMessage>();
        try {
            sto = (ArrayList<CalendarCachedMessage>)deSerialize(calendarDestination);
        } catch (Exception e) {
            System.err.println("Error en almacenamiento! ");
        } finally {
            sto.add(target);
            System.out.println("alm");
            try {
                serialize(sto, calendarDestination);
            } catch (Exception ex) {
                System.err.println("Error en almacenamiento! D: TToTT");
            }
        }
    }
    
    public static void addDraftNotice(Message target) {
        ArrayList<Message> sto = new ArrayList<Message>();
        try {
            sto = (ArrayList<Message>)deSerialize(draftDestination);
        } catch (Exception e) {
            System.err.println("Error en almacenamiento! ");
        } finally {
            sto.add(target);
            System.out.println("alm");
            try {
                serialize(sto, draftDestination);
            } catch (Exception ex) {
                System.err.println("Error en almacenamiento! D: TToTT");
            }
        }
    }

    public static void remplaceDraftCalendarNotice(CalendarCachedMessage target, int index) {
        ArrayList<CalendarCachedMessage> sto = new ArrayList<CalendarCachedMessage>();
        try {
            sto = (ArrayList<CalendarCachedMessage>)deSerialize(calendarDestination);
        } catch (Exception e) {
            System.err.println("Error en almacenamiento! ");
        } finally {
            sto.remove(index);
            sto.add(target);
            System.out.println("alm");
            try {
                serialize(sto, calendarDestination);
            } catch (Exception ex) {
                System.err.println("Error en almacenamiento! D: TToTT");
            }
        }
    }
    
    public static void remplaceDraftNotice(Message target, int index) {
        ArrayList<Message> sto = new ArrayList<Message>();
        try {
            sto = (ArrayList<Message>)deSerialize(draftDestination);
        } catch (Exception e) {
            System.err.println("Error en almacenamiento! ");
        } finally {
            sto.remove(index);
            sto.add(target);
            System.out.println("alm");
            try {
                serialize(sto, draftDestination);
            } catch (Exception ex) {
                System.err.println("Error en almacenamiento! D: TToTT");
            }
        }
    }
    
     public static void addNoticeToPublishedList(Message target){
                ArrayList<Message> sto = new ArrayList<Message>();
        try {
            sto = (ArrayList<Message>)deSerialize(publishDestination);
        } catch (Exception e) {
            System.err.println("Error en almacenamiento! ");
        } finally {
            sto.add(target);
            System.out.println("alm");
            try {
                serialize(sto, publishDestination);
            } catch (Exception ex) {
                System.err.println("Error en almacenamiento! D: TToTT");
            }
        }
    }    
    
    public static Message addPublishedCalendarNotice(CalendarCachedMessage c){
        String fecha = c.getFecha();
        int year=Integer.parseInt(fecha.split("-")[2]), 
                month=Integer.parseInt(fecha.split("-")[1]),
                date=Integer.parseInt(fecha.split("-")[0]);
        Message target = new Message(c.index, 10, date, month, year);
        target.add("Nombre del evento", c.getNombreEvento());
        target.add("Topico", c.getTopico());
        target.add("Fecha del evento", date+"/"+month+"/"+(year-1900));
        target.add("Archivo Adjunto",c.getArchivo());
        target.add("Observaciones", c.getObservaciones());
        //TODO el sistema para que android reconosca los archivos
        return target;
    }
}
