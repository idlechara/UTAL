package com.dataTransporter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dataTransporter.Transporter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author kinya
 */
@Deprecated
public class AndroConverter {

    public static String convertTo(Transporter target) throws Exception {
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


        serializer.write(target, output);
        
        return output.toString();
    }
}
