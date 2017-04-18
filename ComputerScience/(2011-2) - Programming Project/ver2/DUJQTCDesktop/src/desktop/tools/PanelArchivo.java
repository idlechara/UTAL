/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Camilo
 */
public class PanelArchivo extends JPanel implements ActionListener
{
    private JTextField nombreArchivo;
    private JButton botonArchivo,botonVerArchivo;
    private JFileChooser selectorArchivo;
    private int resultado;
    private File archivo;
    private final String webPath = "D:\\Development\\Sources\\Graphics Development\\NetBeans Projects\\AndroidFeeder\\build\\web\\data\\";

    public PanelArchivo()
    {
        this.setLayout(new MigLayout ());
        this.inicializarObjetos();
        this.agregarObjetos();
    }

        public boolean testFile(){  
        try {
            FileReader fr = new FileReader(this.getDirectoryNameArchivo());
        } catch (FileNotFoundException ex) {
            this.cleanArchivo();
            return false;      
        }
        return true;
               
    }

    private void inicializarObjetos()
    {
        this.nombreArchivo = new JTextField ( 30 );
        this.botonArchivo = new JButton( new ImageIcon ( "src/desktop/resources/nuevo archivo.png" ) );
        this.botonArchivo.addActionListener ( this );
        this.botonVerArchivo = new JButton ( "Ver Archivo" );
        this.botonVerArchivo.setEnabled ( false );
        this.botonVerArchivo.addActionListener ( this );


        this.selectorArchivo = new JFileChooser ();
        //FileFilter filtroPDF = new FileNameExtensionFilter ( "Archivo PDF (.pdf)" , "pdf" );

        //this.selectorArchivo.addChoosableFileFilter ( filtroPDF );
    }

    private void agregarObjetos()
    {
        this.add ( this.nombreArchivo );
        this.add ( this.botonArchivo , "wrap" );
        this.add ( this.botonVerArchivo , "right" );
    }

    public String getNameArchivo()
    {
        return this.archivo.getName();
    }

    public String getDirectoryNameArchivo()
    {
        return this.archivo.getAbsolutePath();
    }

    public void setNameArchivo(String name)
    {
        this.nombreArchivo.setText(name);
    }

    public File getArchivo()
    {
        return this.archivo;
    }
    
    public void setArchivo(File f)
    {
        this.archivo = f;
        this.botonVerArchivo.setEnabled(true);
    }

    private void setEditableTextField(File f)
    {
        if(f != null)
        {
            this.nombreArchivo.setEditable(false);
        }
    }

    public void cleanArchivo()
    {
        this.nombreArchivo.setText("");
        this.archivo = null;
        this.botonVerArchivo.setEnabled(false);
    }
    public void FileCopy(String sourceFile, String destinationFile)
    {
        System.out.println("Desde: " + sourceFile);
        System.out.println("Hacia: " + destinationFile);

        try
        {
            FileInputStream fis = new FileInputStream(sourceFile); //inFile -> Archivo a copiar
            FileOutputStream fos = new FileOutputStream(destinationFile); //outFile -> Copia del archivo
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            fis.close();
            fos.close();

        } catch(IOException e)
        {
                System.err.println("Hubo un error de entrada/salida!!!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource () == this.botonArchivo )
        {
            selectorArchivo.setFileSelectionMode ( JFileChooser.FILES_ONLY );
            this.resultado = selectorArchivo.showDialog ( null , "Cargar" );
            if ( resultado == JFileChooser.APPROVE_OPTION )
            {
                this.archivo = selectorArchivo.getSelectedFile();
                this.nombreArchivo.setText ( archivo.getAbsolutePath());
                this.FileCopy(this.archivo.getAbsolutePath(), this.webPath + this.archivo.getName());
                this.setEditableTextField(this.archivo);
            }
        }

        if ( archivo != null )
        {
            botonVerArchivo.setEnabled ( true );
        }
        if ( e.getSource ().equals ( this.botonVerArchivo ) )
        {

            if ( Desktop.isDesktopSupported () == true )
            {
                Desktop escritorio = Desktop.getDesktop ();

                if ( archivo != null )
                {
                    try
                    {
                        escritorio.open ( archivo );
                    }
                    catch ( Exception ex )
                    {
                        JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog ( this , "No hay archivo cargado " );
                }
            }
        }
    }

}
