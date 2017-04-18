package Tarea2;

/**
 * Cola de prioridad (Priority Queue).
 *
 * @author Matias
 */
public class BinaryHeap {
    private int[] bh;
    private int N;
    private boolean maxFirst;

    /**
     * Inizializa un nuevo <b>BinaryHeap</b>, dando la capacidad inicial.
     *
     */
    public BinaryHeap(int MAX, boolean type) {
        bh = new int[MAX + 1];
        N = 0;
        maxFirst = type;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    
    public boolean isFull(){
        return ( N == bh.length-1 ) ? true : false; 
    }

    public int size() {
        return N;
    }
    
    public int rest(){
        return (bh.length-1)-N;
    }
    
    public int max() {
        if (isEmpty()) {
            System.err.println("Cola vacia.");
            return 0;
        }
        System.out.println();
        return bh[1];
    }

    private void resize(int capacidad) {
        int[] temp = new int[capacidad];
        for (int i = 1; i <= N; i++) {
            temp[i] = bh[i];
        }
        bh = temp;
    }

    /**
     * Se agraga un nuevo elemento al final de la cola. Luego se compara hasta
     * alcanzar una posición válida. Si la cola se encuentra en su maxima
     * capadidad, se crea una nueva con el doble de capacidad.
     *
     * @param x el nuevo elemento a insertar.
     */
    public boolean insert(int x) {
        if (N >= bh.length - 1) {
//            resize(2 * bh.length);
            return false;
        }
        bh[++N] = x;
        swim(N);
        return true;
    }

    /*
     * A nadar!. El nuevo elemento se inserta en la ultima posicion y comienza a
     * escalar. Como el sistema utilizado es "el mayor/menor al tope", se compara la
     * prioridad del nuevo elemento con su padre y se intercambia con este si es
     * mayor/menor, el proceso se repite hasta que no lo sea, o <b>index</b> ya no
     * sea mayor a 1.
     */
    private void swim(int index) {
        while (index > 1 && compare(index)) {
            //Intercambiar.
            this.swap(index / 2, index);
            //Reajustar indice.
            index = index / 2;
        }
    }

    private boolean compare(int index) {
        if (!maxFirst) {
            return (bh[index / 2] > bh[index]) ? true : false;
        } else {
            return (bh[index / 2] < bh[index]) ? true : false;
        }
    }

    private void swap(int top, int bot) {
        int temp = bh[bot];
        bh[bot] = bh[top];
        bh[top] = temp;
    }

    public int deleteMax() {
        int temp = bh[1];
        if (!isEmpty()) {
            bh[1] = bh[N--];
            int i = 1;
            while (i*2 <= N) {
                if (bh[i] > bh[i*2]) {
                    swap(i, i*2);
                    i = i*2;
                } else if (bh[i] > bh[i*2 + 1]) {
                    swap(i, i*2+1);
                    i = (i*2) + 1;
                } else {
                    return temp;
                }
            }
        }
        return temp;
    }

//    public void printHeap() {
//        int deep = getDeep();
//        int n = 1;
//        int maxSpaces = (int) Math.pow(2, deep);
//        System.out.println(" print BH for deep: " + deep);
//        for (int i = 0; i < deep; i++) {
//            int elements = (int) Math.pow(2, i);
//            int spacesToPrint = maxSpaces / (elements);
//            for (int j = 1; j <= elements; j++) {
//                String format = String.format(" %" + spacesToPrint + "d ", bh[n++]);
//                System.out.print(format);
//                if (j == elements / 2) {
//                    format = String.format(" %" + spacesToPrint + "s ", " ");
//                    System.out.print(format);
//                }
//            }
//            System.out.println();
//        }
//    }

//    public int getDeep() {
//        int deep = 1, index = N;
//        while (index > 1) {
//            index = index / 2;
//            deep++;
//        }
//        return deep;
//    }

//    public static void main(String[] args) {
//        BinaryHeap b = new BinaryHeap(100, true);
//        Random r = new Random();
//        for (int i = 1; i <= 20; i++) {
//            b.insert(i);
//        }
//        b.printHeap();
//        b.deleteMax();
//        b.printHeap();
//    }
}
