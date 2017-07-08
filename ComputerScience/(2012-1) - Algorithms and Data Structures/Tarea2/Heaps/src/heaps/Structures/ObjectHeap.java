/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps.Structures;

import java.util.EmptyStackException;

/**
 *
 * @author Kynku
 */
public class ObjectHeap extends GenericQueue{

    
    public ObjectHeap(int size) {
        if(size==0) throw new EmptyStackException();
        this.lenght = size++;
        this.actualSize = 0;
        this.memory = new PriorityContainer[this.lenght];
        this.memory[0] = new PriorityContainer();
        this.memory[0].priority=Integer.MIN_VALUE;
    }

    public PriorityContainer get(int index){ 
        index++;
        if ((index >= this.lenght-1)||(index < 0))
            throw new ArrayIndexOutOfBoundsException(index);
        return  memory[index];
    }

    
    @Override
    public void push(PriorityContainer object){
        if(this.actualSize+1 == this.lenght) throw new StackOverflowError();
        int aux = ++this.actualSize;
        int parent = aux/2;
        
        while(object.priority < this.memory[parent].priority){
            this.memory[aux] = this.memory[parent];
            aux = parent;
            parent = aux/2;
        }
        
        this.memory[aux] = object;
    }
    
    public PriorityContainer findMin() throws EmptyStackException{
        if (this.actualSize == 0) throw new EmptyStackException();
        return this.memory[1];
    }
    
    @Override
    public PriorityContainer pop(){
        if(this.actualSize==0) return null;
        PriorityContainer returner = this.findMin();
        this.memory[1] = this.memory[this.actualSize--];
        this.percolateDown(1);
        //System.out.println("Retornando: " +returner.priority);
        return returner;
    }

    private void percolateDown(int index) {
        PriorityContainer aux = this.memory[index];
        int leftChild=0, rightChild=0, smaller=0;
        while(true){
            leftChild=2*index;
            rightChild=(2*index)+1;
            
            if(leftChild > this.actualSize-1) break;
            
            else if((leftChild == this.actualSize-1) || (this.memory[leftChild].priority < this.memory[rightChild].priority)){
                smaller = leftChild;
            }
            else smaller = rightChild;
            
            if(this.memory[smaller].priority < aux.priority){
                this.memory[index] = this.memory[smaller];
                index = smaller;
            }
            else break;
        }
        this.memory[index] = aux;
    }
    
    public ObjectHeap(PriorityContainer[] array){
        this(array.length);
        for (int i=1; i<this.lenght; i++) {
            this.memory[i]=array[i-1];
        }
        for(int i=this.actualSize/2; i==1;i--){
            this.percolateDown(i);
        }
    }
    
        @Override
    public String toString(){
        String out = "";
        out += "Priority Queue (Priority enabled):\t{";
        for(int i=0; i<this.actualSize; i++){
            out+=memory[i+1].priority;
            if(i<this.actualSize-1) out+=", ";
        }
        
        out += "}";
        return out;
    }

    @Override
    public PriorityContainer peek(int index) {
        return this.memory[index];
    }
}
