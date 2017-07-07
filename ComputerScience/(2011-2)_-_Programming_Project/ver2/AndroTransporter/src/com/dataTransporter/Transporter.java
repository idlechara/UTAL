package com.dataTransporter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author kinya
 */
@Deprecated
@Root
public class Transporter{
    
    @ElementList
    private ArrayList<HashMap> collection;
    
    public void add(HashMap map){
        this.collection.add(map);
    }
    
    public HashMap get(int index){
        return this.collection.get(index);
    }
    
    public int getSize(){
        return this.collection.size();
    }
    
    public ArrayList<HashMap> getCollection(){
        return this.collection;
    }
    
    public void setCollection(ArrayList a){
        this.collection = a;
    }
    
    public Transporter(ArrayList a){
        this.collection = a;
    }
    
    public Transporter(){
        this.collection = new ArrayList<HashMap>();
    }

//    @Override
//    public Object getProperty(int i) {        
//        switch(i)
//        {
//        case 0:
//            return this.collection;
//        }
//        
//        return null;
//    }
//
//    @Override
//    public int getPropertyCount() {
//        return 1;
//    }
//
//    @Override
//    public void setProperty(int i, Object o) {        
//        switch(i)
//        {
//        case 0:
//            this.collection = (ArrayList<HashMap>)o;
//            break;
//        }
//    }
//
//    @Override
//    public void getPropertyInfo(int i, Hashtable hshtbl, PropertyInfo info) {        
//        switch(i)
//        {
//        case 0:
//            info.type = PropertyInfo.OBJECT_CLASS;
//            info.name = "ArrayList<HashMap>";
//            break;
//        default:break;
//        }
//    }
}
