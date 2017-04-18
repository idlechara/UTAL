import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Bueno, me prohibieron el uso del ArrayList de java, pero nunca dijeron nada de 
 * que no podia hacer el propio mio, ya que como no encontre mis codigos viejos para 
 * reciclar, no me quedo de otra.
 * 
 * Esta basada en las declaraciones de la API de java, respecto a la clase ArrayList,
 * asi que intente dejarla lo mas parecida posible
 * 
 * @author Erik Andres Regla Torres
 * @param <E> El tipo de clase que se recibe
 * @version 0.4
 * @since 1.0
 */
public class ArrayList<E> extends AbstractList<E> implements List<E>,Iterable<E>
{
    private Object[] storage;
    private int lenght;

    /**
     * constructor del Arraylist
     * @param lenght el tamaño inicial
     */
    public ArrayList(int lenght)
    {
        super();
        if (lenght < 0){
            throw new IllegalArgumentException("Illegal Capacity: " + lenght);
        }
        this.storage = new Object[lenght];
        this.lenght = lenght;
    }   

    /**
     * Contructor por defecto de la clase
     */
    public ArrayList()
    {
        super();
        this.storage = new Object[1];
        this.lenght = 0;
    }

    /**
     * Metodo que se encarga de asegurar el espacio nesesario para
     * poder guardar arreglos, en caso de que el espacio solicitado sea menor que el espacio
     * actual, este metodo no hace nada
     * @param tamañoMinimo el tamaño solicitado
     */
    public void asegurarEspacio(int tamañoMinimo) {
        int tamañoOriginal = this.storage.length;
        if (tamañoMinimo > tamañoOriginal) {
            int tamañoNuevo = (tamañoOriginal * 3)/2 + 1;
            if (tamañoNuevo < tamañoMinimo){
                tamañoNuevo = tamañoMinimo;
            }
            this.storage = Arrays.copyOf(this.storage, tamañoNuevo);
        }
    }

    /**
     * regresa la longitud del arreglo
     * @return la longitud del arreglo
     */
    public int getLenght(){
        return this.lenght;
    }

    @Override
    public int size(){
        return this.lenght;
    }

    @Override
    public E get(int index) {
        return (E) this.storage[index];
    }

    @Override
    public boolean add(E e) {
        asegurarEspacio(this.lenght + 1);
        this.storage[this.lenght++] = e;
        return true;
    }

    //mas que un metodo remove, es un metodo POP!
    @Override
    public E remove(int index) {
        this.lenght++;
        E datos = (E) this.storage[index];
        int moverCuantos = this.lenght - index - 1;
        //por si no se entiende, esto es para MOVER los objetos dentro del arreglo! ¬¬
        //lo que hacer es tomar un "semi-arrgelo", y arrastrar lo a una posicion, copiandolo
        //claro que por lo mismo, al cabo de un rato hemos de borrar el sobrante (que es la 
        //copia del ultimo elemento.
        if (moverCuantos > 0){
            System.arraycopy(this.storage, index+1, this.storage, index, moverCuantos);
        }
        this.storage[--this.lenght] = null;

        return datos;
    }

    /**
     * bastante parecido a un metodo de busqueda, pero en vez de devolver un elemento, devuelve un
     * arraylist de elementos del mismo tipo del entregado.
     * @param objeto la instancia de objeto a buscar
     */
    public ArrayList getElement(Object objeto) {
        if (objeto == null) {
            return null;
        }
        else {
            ArrayList salida = new ArrayList(0);
            for (int index = 0; index < this.lenght; index++)
                if (objeto.equals(this.storage[index])) {
                    salida.add(this.remove(index));
            }
            return salida;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int contador = 0;

            @Override
            public boolean hasNext() {
                return (contador < storage.length);
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    E e = (E) storage[contador];
                    contador++;
                    return e;
                } 
                else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
