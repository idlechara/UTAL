package clases;

/**
* Clase Pieza
* Su funcion es controlar la orientacion y direccion de las piezas del juego
*/
public class Pieza
{
    private int superior;
    private int inferior;
    private int izquierda;
    private int derecha;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Pieza
     * @param superior del tipo int define la posicion de una pieza en el lado superior
     * @param inferior del tipo int define la posicion de una pieza en el lado inferior
     * @param izquierda del tipo int define la posicion de una pieza en el lado izquierdo
     * @param derecha del tipo int define la posicion de una pieza en el lado derecho
     */
    public Pieza(int superior, int inferior, int izquierda, int derecha)
    {
        this.superior = superior;
        this.inferior = inferior;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo superior
     * @param superior del tipo int indica la posicion de la pieza
     */
    public void setSuperior(int superior)
    {
        this.superior = superior;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo superior
     * @return superior El metodo retorna el valor que se encuentra almacenado en el atributo superior
     */
    public int getSuperior()
    {
        return this.superior;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo inferior
     * @param inferior del tipo int indica la posicion de la pieza
     */
    public void setInferior(int inferior)
    {
        this.inferior = inferior;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo inferior
     * @return inferior El metodo retorna el valor que se encuentra almacenado en el atributo inferior
     */
    public int getInferior()
    {
        return this.inferior;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo izquierda
     * @param izquierda del tipo int indica la posicion de la pieza
     */
    public void setIzquierda(int izquierda)
    {
        this.izquierda = izquierda;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo izquierda
     * @return izquierda El metodo retorna el valor que se encuentra almacenado en el atributo izquierda
     */
    public int getIzquierda()
    {
        return this.izquierda;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo derecha
     * @param derecha del tipo int indica la posicion de la pieza
     */
    public void setDerecha(int derecha)
    {
        this.derecha = derecha;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo derecha
     * @return derecha El metodo retorna el valor que se encuentra almacenado en el atributo derecha
     */
    public int getDerecha()
    {
        return this.derecha;
    }
}