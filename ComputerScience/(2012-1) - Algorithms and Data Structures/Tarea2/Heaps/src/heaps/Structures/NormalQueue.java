/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps.Structures;

/**
 *
 * @author Kynku
 */
public class NormalQueue extends GenericQueue {

    private int start;
    private int end;

    public NormalQueue(int size) {
        this.memory = new PriorityContainer[size];
        this.end = -1;
        this.start = 0;
        this.lenght = 0;
        this.actualSize=0;
    }

    public NormalQueue(PriorityContainer[] input) {
        this.actualSize=0;
        this.memory = input;
        this.end = -1;
        this.start = 0;
        this.lenght = 0;
    }

    @Override
    public void push(PriorityContainer target) {
        //System.out.println("start: " + this.start + " - end: " + this.end + " - size: " + this.size + " - lenght: " + this.memory.length + "operation: push!" );
        if (this.lenght == this.memory.length) {
            throw new StackOverflowError();
        }
        this.end = (this.end >= this.memory.length - 1) ? 0 : this.end + 1;
        this.lenght++;
        this.memory[this.end] = target;
        this.actualSize++;
    }

    @Override
    public PriorityContainer pop() {
        //System.out.println("start: " + this.start + " - end: " + this.end + " - size: " + this.size + " - lenght: " + this.memory.length + "operation: pop!" );
        if (lenght == 0) {
            return null;
        }

        this.lenght--;
        PriorityContainer returner = this.memory[this.start];
        this.start = (this.start >= this.memory.length - 1) ? 0 : this.start + 1;
        this.actualSize--;
        return returner;
    }

    @Override
    public String toString() {
        String out = "";

            out += "Priority Queue (Normal mode):\t{";
        if (this.start <= this.end) {
            for (int i = this.start; i < this.end; i++) {
                out += memory[i + 1].priority;
                if (i < this.end - 1) {
                    out += ", ";
                }
            }

            out += "}";
            return out;
        } else {
            for (int i = this.start; i < this.lenght; i++) {
                out += memory[i + 1].priority;
                if (i < this.lenght - 1) {
                    out += ", ";
                }
            }
            
            for (int i = 0; i <= this.end; i++) {
                out += ", ";
                out += memory[i].priority;
            }
            
            out += "}";
            return out;
        }
    }

    @Override
    public PriorityContainer peek(int index) {
        int pos = this.start + index;
        if(pos >= this.memory.length){
            return this.memory[(pos-this.memory.length)];
        }
        else return this.memory[pos];
    }
}
