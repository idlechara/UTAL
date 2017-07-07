/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import com.sun.org.glassfish.gmbal.AMXMetadata;
import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

/**
 *
 * @author kinya
 */
@Root(name="mapNode")
public class Mapper{
   @ElementMap(entry="value", key="key", attribute=true, keyType=String.class, valueType=String.class)
    private HashMap<String, String> map = new HashMap<String, String>();

    public Mapper() {
        this.map = new HashMap<String, String>();
    }

    public Mapper(HashMap map) {
        this.map = map;
    }
    
    public void put(String key,String value){
        this.map.put(key, value);
    }
    
    public String get(String key){
        return this.map.get(key);
    }
}
