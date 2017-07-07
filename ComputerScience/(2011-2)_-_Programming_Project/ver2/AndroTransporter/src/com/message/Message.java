package com.message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @version 12
 */
public class Message implements Serializable {

    /**
     * Constantes para valores de rescate de datos
     */
    public static final int CAMBIO_HORARIO = 0, SUSPENSION_CLASES = 1, SOLICITUD_AYUDANTES = 2,
            PLANIFICACION = 3, OFERTA_LABORAL_PRE = 4, AVISO_PRE = 5, OFERTA_LABORAL_POST = 6,
            OFERTA_POSTGRADO = 7, BECAS_ESTUDIO = 8, AVISOS_GENERALES = 9, CALENDARIO = 10;
    
    private static final String logRoute = "D:\\ProjectData\\log.cfg", logDraftRoute = "D:\\ProjectData\\log.cfg",
            dataRoute = "D:\\ProjectData\\data\\", draftRoute = "D:\\ProjectData\\drafts\\";
    
    private HashMap<String, Object> storage;
    private int type;
    private int id;
    private boolean draftingEnabled;

    /**
     * Constructor predefinido.
     * 
     * @param type El tipo del mensaje
     */
    public Message(int type) {
        this.type = type;
        this.storage = new HashMap<String, Object>();
        this.id = -1;
        this.draftingEnabled = true;
    }

    /**
     * Añade nuevo contenido al sistema usando un mapa. este mapa usa llaves,
     * las cuales bien pueden contener texto como tambien medios e imagenes.
     * 
     * @param key la llave del contenido
     * @param value el valor almacenado
     */
    public void add(String key, Object value) {
        this.storage.put(key, value);
    }

    /**
     * Recupera contenido del sistema, para esto pregunta por cual es 
     * la llave del mismo.
     * 
     * @param key el contenido a recuperar
     * @return != null si el contenido existe
     */
    public Object get(String key) {
        return this.storage.get(key);
    }

    /**
     * Pregunta si el mensaje seleccioniado pertenece a un u otra categoria.
     * 
     * @param compare la categoria a preguntar
     * @return true si pertenece a la categoria ingresada.
     */
    public boolean is(int compare) {
        return this.type == compare;
    }

    public static int getLastPublishedLogEntry() {
        try {
            //abre log de persistencia
            FileInputStream fis = new FileInputStream(logRoute);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //carga persistencia

            Integer logEntry = (Integer) ois.readObject();

            ois.close();
            fis.close();

            //retorna ultima entrada.
            return logEntry;
        } catch (Exception e) {
            Logger.getLogger("Error en lectura de mensajes desde disco. \n" + Message.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;

    }

    public static int getLastDraftLogEntry() {
        try {
            //abre log de persistencia
            FileInputStream fis = new FileInputStream(logDraftRoute);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //carga persistencia
            Integer logEntry = (Integer) ois.readObject();

            ois.close();
            fis.close();

            //retorna ultima entrada.
            return logEntry;
        } catch (Exception e) {
            Logger.getLogger("Error en lectura de mensajes desde disco. \n" + Message.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;

    }

    /**
     * Permite guardar la instancia en un archivo. Mecanismo se encarga de 
     * determinar la ruta de salida de este.
     * 
     * La raiz para uso es la unidad "D\:", en la cual ha de existir una carpeta 
     * llamada "ProjectData", la cual a su vez aloja dos carpetas: "data" y "draft".
     * 
     * Este guarda en la carpeta data, la que es correspondiente a la de elementos
     * publicados.
     */
    public void saveToPublishPersistence() {
        //abre log de persistencia
        try {
            this.draftingEnabled = false;
            Integer logEntry = 0;
            try {
                FileInputStream fis = new FileInputStream(logRoute);
                ObjectInputStream ois = new ObjectInputStream(fis);

                //carga persistencia
                logEntry = (Integer) ois.readObject();
                //escrbie en la memoria

                ois.close();
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger("Archivo log no encontrado.. creando\n" + Message.class.getName()).log(Level.SEVERE, null, ex);
            }
            //recupera ultimo id
            //guarda y cierra todo
            FileOutputStream fos = new FileOutputStream(logRoute);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //escribe la lista
            oos.writeObject(++logEntry);

            fos.close();
            oos.close();

            this.id = logEntry;

            this.storage.put("id", "" + this.id);
            this.storage.put("type", ""+ this.type);
            
            //escribe en archivo
            fos = new FileOutputStream(dataRoute + logEntry + ".dta");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this.storage);

            oos.close();
            fos.close();

            this.saveToDraftPersistence();
        } catch (Exception ex) {
            Logger.getLogger("Error en escritura de mensajes a disco. \n" + Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite guardar la instancia en un archivo. Mecanismo se encarga de 
     * determinar la ruta de salida de este.
     * 
     * La raiz para uso es la unidad "D\:", en la cual ha de existir una carpeta 
     * llamada "ProjectData", la cual a su vez aloja dos carpetas: "data" y "draft".
     * 
     * Este guarda en la carpeta draft, la que es correspondiente a la de borradores.
     */
    public void saveToDraftPersistence() {
        //abre log de persistencia
        try {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            Integer logEntry = new Integer(this.id);
            if (logEntry == -1) {
                try {
                    FileInputStream fis = new FileInputStream(logDraftRoute);
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    //carga persistencia
                    logEntry = (Integer) ois.readObject();
                    //escrbie en la memoria

                    ois.close();
                    fis.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger("Archivo log no encontrado.. creando\n" + Message.class.getName()).log(Level.SEVERE, null, ex);
                }
                //recupera ultimo id
                //guarda y cierra todo
                fos = new FileOutputStream(logDraftRoute);
                oos = new ObjectOutputStream(fos);

                //escribe la lista
                oos.writeObject(++logEntry);

                fos.close();
                oos.close();

                this.id = logEntry;

            }
            //escribe en archivo
            fos = new FileOutputStream(draftRoute + logEntry + ".dta");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();
            fos.close();

        } catch (Exception ex) {
            Logger.getLogger("Error en escritura de mensajes a disco. \n" + Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recupera todos los mensajes publicados.
     * 
     * @return un ArrayList vacio si es que no fueron encontrados los mensajes.
     */
    synchronized public static ArrayList<HashMap> loadPublishedMessages() {
        /*
         * ultiliza una ruta para poder cargar los datos.
         */
        //TODO sistema de carga

        ArrayList<HashMap> messageList = new ArrayList<HashMap>();
        try {
            //abre log de persistencia
            FileInputStream fis = new FileInputStream(Message.logRoute);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //carga persistencia
            Integer logEntry = (Integer) ois.readObject();

            ois.close();
            fis.close();

            for (int i = 0; i < logEntry; i++) {
                fis = new FileInputStream(dataRoute + i + ".dta");
                ois = new ObjectInputStream(fis);

                messageList.add((HashMap) ois.readObject());

                ois.close();
                fis.close();
            }

        } catch (Exception ex) {
            Logger.getLogger("Error en lectura de mensajes desde disco. \n" + Message.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messageList;
    }

    /**
     * Carga todos los mensajes de tipo borrador. 
     * 
     * @return un Arraylist vacio si es que no encuentra mensajes.
     */
    synchronized public static ArrayList<Message> loadDraftMessages() {
        /*
         * ultiliza una ruta para poder cargar los datos.
         */
        //TODO sistema de carga

        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            //abre log de persistencia
            FileInputStream fis = new FileInputStream(Message.draftRoute);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //carga persistencia
            Integer logEntry = (Integer) ois.readObject();

            ois.close();
            fis.close();

            for (int i = 0; i < logEntry; i++) {
                fis = new FileInputStream(draftRoute + i + ".dta");
                ois = new ObjectInputStream(fis);

                messageList.add((Message) ois.readObject());

                ois.close();
                fis.close();
            }

        } catch (Exception ex) {
            Logger.getLogger("Error en lectura de mensajes desde disco. \n" + Message.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messageList;
    }

//    
//    public static String getDataRoute() {
//        return dataRoute;
//    }
//
//    public static String getLogRoute() {
//        return logRoute;
//    }
    /**
     * Retorna el tipo de mensaje que encapsula la instancia.
     * 
     * @return el tipo de mensaje que encapsula.
     */
    public int getType() {
        return type;
    }

    /**
     * Retorna el identificador de la instancia para carga e identificacion.
     * 
     * @return el id de la instancia
     */
    public long getId() {
        return id;
    }

    /**
     * Retorna el estado de la instancia respecto a que si es posible usarla como
     * borrador. se sugiere su uso como filtro para poder elejir que
     * mensajes han de visualizarse para su carga como borrador en la interfaz de escritorio
     * con edicion habilitada.
     * 
     * @return true si esta permitida la edicion
     */
    public boolean isDraftingEnabled() {
        return draftingEnabled;
    }
}
