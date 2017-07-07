/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmlTransporter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author kinya
 */
@Root
public class Storage {

    @Attribute
    @ElementList(name = "mainList", data = true, type = Mapper.class)
    private ArrayList<Mapper> list = new ArrayList<Mapper>();

    public Storage() {
        this.list = new ArrayList<Mapper>();
    }

    public Storage(ArrayList<HashMap<String,String>> list) {
        this.pack(list);
    }

    public void add(Mapper target) {
        this.list.add(target);
    }

    public Mapper get(int index) {
        return this.list.get(index);
    }

    public void saveToFile(String destination) throws Exception {
        Serializer serializer = new Persister();
        File result = new File(destination);
        serializer.write(this, result);
    }

    public String saveToString() throws Exception {
        Serializer serializer = new Persister();

        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();
            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }
            @Override
            public String toString() {
                return this.string.toString();
            }
        };
        serializer.write(this, output);
        
        return output.toString();
    }

    public static Storage readFromFile(String sourcePath) throws Exception {
        Serializer serializer = new Persister();
        File source = new File(sourcePath);
        Storage output = serializer.read(Storage.class, source);
        return output;
    }

    public static Storage readFromString(String source) throws Exception {
        Serializer serializer = new Persister();
        Storage output = serializer.read(Storage.class, source);
        return output;
    }
    
    private void pack(ArrayList<HashMap<String,String>> map){
        for(HashMap m : map){
            this.list.add(new Mapper(m));
        }
    }
    public ArrayList<HashMap<String,String>> unpack(){
        ArrayList<HashMap<String,String>> output = new ArrayList<HashMap<String, String>>();
        for(Mapper m : this.list){
            output.add(m.getMap());
        }
        return output;
    }
//////    codigo de testeo
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws Exception {
//        Mapper save = new Mapper();
//        save.put("Neko1", "Kynku");
//        save.put("Neko 2", "nyam");
//        Mapper save2 = new Mapper();
//        save2.put("Nombre 1", "Erik");
//        save2.put("Nombre 2", "Andres asd as d asd asd\n asd as da sd\n aasdnnasjdas");
//        //HashMap asdf = new HashMap();
//        //asdf.put("neko", "kynku");
//        Storage sto = new Storage();
//        sto.add(save);
//        sto.add(save2);
//        sto.saveToFile("/media/Documents/testing.xml");
//
//        Storage unread = Storage.readFromFile("/media/Documents/testing.xml");
//
//
//        System.out.println("Neko1: " + unread.get(0).get("Neko1"));
//        System.out.println("Neko2: " + unread.get(0).get("Neko 2"));
//        System.out.println("Nombre 1: " + unread.get(1).get("Nombre 1"));
//        System.out.println("Nombre 2: " + unread.get(1).get("Nombre 2"));
//        System.out.println("Equals?" + unread.equals(sto));
//        System.out.println("Neko1: " + sto.get(0).get("Neko1"));
//        System.out.println("Neko2: " + sto.get(0).get("Neko 2"));
//        System.out.println("Nombre 1: " + sto.get(1).get("Nombre 1"));
//        System.out.println("Nombre 2: " + sto.get(1).get("Nombre 2"));
//        
//        System.out.println("########################################");
//        System.out.println("########################################");
//        System.out.println(unread.saveToString());
//        System.out.println("########################################");
//        System.out.println("########################################");
//        sto = Storage.readFromString(unread.saveToString());
//        System.out.println("########################################");
//        System.out.println("########################################");
//        
//        
//        System.out.println("Neko1: " + unread.get(0).get("Neko1"));
//        System.out.println("Neko2: " + unread.get(0).get("Neko 2"));
//        System.out.println("Nombre 1: " + unread.get(1).get("Nombre 1"));
//        System.out.println("Nombre 2: " + unread.get(1).get("Nombre 2"));
//        System.out.println("Equals?" + unread.equals(sto));
//        System.out.println("Neko1: " + sto.get(0).get("Neko1"));
//        System.out.println("Neko2: " + sto.get(0).get("Neko 2"));
//        System.out.println("Nombre 1: " + sto.get(1).get("Nombre 1"));
//        System.out.println("Nombre 2: " + sto.get(1).get("Nombre 2"));
//    }
}
