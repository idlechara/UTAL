/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps.Structures;

/**
 *
 * @author Kynku
 */
public abstract class GenericQueue {
    protected PriorityContainer memory[];
    public int lenght;
    public int actualSize;
    
    abstract void push(PriorityContainer object);
    
    abstract public PriorityContainer pop();
    
    abstract public PriorityContainer peek(int index);
}
