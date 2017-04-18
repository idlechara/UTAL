/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dataPack.Message;
import com.persistence.NoticeArray;
import com.persistence.NoticeMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class Cache {

    transient public static ArrayList<Message> messageCache = new  ArrayList<Message>();
//    transient public static ArrayList<Message> messageCalendarCache = new ArrayList<Message>();
//    transient public static ArrayList<Message> messageAlumnosCache = new ArrayList<Message>();
//    transient public static ArrayList<Message> messageExAlumnosCache = new ArrayList<Message>();
    
    private static String route = "/media/Documents/Data.dta";
    public static int[] messageCount = {0,0,0,0,0,0,0,0,0,0,0};
    
    
    private static boolean isLoaded = false;
    
    @Deprecated
    private static Timer refreshTimer = null;

    public static void load(){
//        if(!isLoaded){
//           isLoaded = true;
        try {
            FileInputStream fis = new FileInputStream(route);
            ObjectInputStream ois =  new ObjectInputStream(fis);
            
            int[] count = {0,0,0,0,0,0,0,0,0,0,0};
            
            messageCache = (ArrayList<Message>) ois.readObject();
            
            for(Message m : messageCache){
//                if(m.type>= 0 && m.type < 6){
//                    messageAlumnosCache.add(m);
//                }
//                else if(m.type>= 6 && m.type < 10){
//                    messageExAlumnosCache.add(m);
//                }
//                else if(m.type == 10){
//                    messageCalendarCache.add(m);
//                }
                count[m.type]++;
            }
            messageCount = count;
            
        } catch (Exception ex) {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
//    }
//    
//    public static ArrayList<HashMap> getCachedMessages(){
//        if (refreshTimer == null) {
//            messageCache = Message.loadPublishedMessages();
//            
//            refreshTimer = new Timer(120000, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (Message.getLastPublishedLogEntry() != messageCache.size()) {
//                        messageCache = Message.loadPublishedMessages();
//                    }
//                }
//            });
//        
//        }
//        return messageCache;
//    }
}
