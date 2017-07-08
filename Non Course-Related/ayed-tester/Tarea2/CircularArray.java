package Tarea2;
/**
 *
 * @author Matias
 */
public class CircularArray {
    private Object[] list;
    private int ini;
    private int fin;
    private int nElem;
    private int maxElems;
    
   public CircularArray(int size){
       list = new Object[size];
       maxElems = size;
       ini = fin = nElem = 0;
   }
   
   public boolean isFull(){
       return ( nElem == maxElems ) ? true : false;
   }
   
   public int size(){
       return nElem;
   }
   
   /*
    * Insert at the top (first).
    */
   public void push(Object o){
       if( nElem == maxElems ){
           return;
       }
       ini = ( ini + maxElems - 1 ) % maxElems; nElem++;
   }
   
   /*
    * Removes and returns at the top element.
    */
   public Object pop(){
       if( nElem == 0 ){
           return null;
       }
       nElem--;
       ini = ( ini + 1 )%maxElems;
       return list[ ini ];
   }
   
   /*
    * Insert at the last pos.
    */
   public void inject(Object o){
       if( nElem == maxElems ){
           return;
       }
       list[fin] = o;
       fin = ( fin + 1 ) % maxElems; nElem++;
   }
   
   /*
    * Removes and return at the last pos.
    */
   public Object eject(){
       if( nElem == 0 ){
           return null;
       }
       fin = ( fin + maxElems - 1 )%maxElems; nElem--;
       return list[fin];
   }
}
