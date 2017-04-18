/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

/**
 *
 * @author Camilo
 */
public class BloquesYSalas
{
        private int[] bloques = {1,2,3,4,5,6,7,8,9,10,11} ;
        private String sala;

    public BloquesYSalas(int[] bloque, String sala) 
    {
        this.bloques = bloque;
        this.sala = sala;
    }

    public int[] getBloque() {
        return bloques;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String toString() 
    {
        return "Bloque: " + bloques + "    -    " + "Sala: " + sala + "\n";
    }
}
