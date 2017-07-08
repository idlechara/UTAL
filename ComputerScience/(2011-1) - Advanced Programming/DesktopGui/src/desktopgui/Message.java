package desktopgui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * La clase Message esta diseñada para poder almacenr informacion relativa a
 * los boletines. Usa un sistema de mapeo para almacenar los valores del 
 * mensaje, de modo que se puede decir que es conducido casi completamente
 * por mensajes.
 * 
 * Contiene un enum que solo es para identificar a que tipo de mensaje 
 * pertence,ya que el resto de la informacion esta guardada en el mapa.
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1
 */
public class Message implements Serializable{
    private static final String logRoute = "D:\\log.cfg", dataRoute = "D:\\";
    private HashMap<String, Object> storage;
    private MessageType type;

    /**
     * Constructor predefinido.
     * 
     * @param type El tipo del mensaje
     */
    public Message(MessageType type) {
        this.type = type;
        this.storage = new HashMap<String, Object>();
    }
    
    /**
     * Añade nuevo contenido al sistema usando un mapa. este mapa usa llaves,
     * las cuales bien pueden contener texto como tambien medios e imagenes.
     * 
     * @param key la llave del contenido
     * @param value el valor almacenado
     */
    public void add(String key, Object value){
        this.storage.put(key, value);
    }
    
    /**
     * Recupera contenido del sistema, para esto pregunta por cual es 
     * la llave del mismo.
     * 
     * @param key el contenido a recuperar
     * @return != null si el contenido existe
     */
    public Object get(String key){
        return this.storage.get(key);
    }
    
    /**
     * Pregunta si el mensaje seleccioniado pertenece a un u otra categoria.
     * 
     * @param compare la categoria a preguntar
     * @return true si pertenece a la categoria ingresada.
     */
    public boolean is(MessageType compare){
        return this.type.equals(compare);
    }
    
    public void saveToPersistence() throws IOException, ClassNotFoundException{
        //abre log de persistencia
        FileInputStream fis = new FileInputStream(logRoute);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        //carga persistencia
        PersistenceLog logEntry = (PersistenceLog) ois.readObject();
        //escrbie en la memoria
        String fileOutput = logEntry.addMessage();
        
        ois.close();
        fis.close();
        
        //recupera ultimo id
        //guarda y cierra todo
        FileOutputStream fos = new FileOutputStream(logRoute);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        //escribe la lista
        oos.writeObject(logEntry);
        
        fos.close();
        oos.close();
        
        //escribe en archivo
        fos = new FileOutputStream(dataRoute+ fileOutput +".dta");
        oos = new ObjectOutputStream(fos);
        
        oos.writeObject(this);
        
        oos.close();
        fos.close();
    }
    
    //recupera todos los mensajes... y para ver q wea, vo dale! :3
    public static ArrayList<Message> loadMessages() throws Exception{
        /*
         * ultiliza una ruta para poder cargar los datos.
         */
        //TODO sistema de carga
        ArrayList<Message> messageList = new ArrayList<Message>();
                //abre log de persistencia
        FileInputStream fis = new FileInputStream(Message.logRoute);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        //carga persistencia
        PersistenceLog logEntry = (PersistenceLog) ois.readObject();
        
        ois.close();
        fis.close();
        
        for(Integer i : logEntry.route){
            fis = new FileInputStream(dataRoute + i + ".dta");
            ois = new ObjectInputStream(fis);
            
            messageList.add( (Message)ois.readObject() );

            ois.close();
            fis.close();
        }
        
        return messageList;
    }
    
    protected class PersistenceLog implements Serializable{
        public ArrayList<Integer> route;

        public PersistenceLog() {
            this.route = new ArrayList<Integer>();
        }
        
        public String addMessage(){
            this.route.add((1 + this.route.get(this.route.size()-1)));
            return Integer.toString(1 + this.route.get(this.route.size()-1));
        }
    }
}
