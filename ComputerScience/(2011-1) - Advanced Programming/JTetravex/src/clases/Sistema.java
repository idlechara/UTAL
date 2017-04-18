package clases;

import java.util.Random;

/**
 * Clase Sistema
 * Su funcion es controlar las funcionalidades y opciones del juego
 */
public class Sistema
{
    private int tamaño;//Tamaño actual de la matriz (cuadrada).
    private int horas;
    private int minutos;
    private int segundos;
    private Pieza[][] matrizOrdenada;
    private Pieza[][] matrizDesordenada;
    private Pieza[][] matrizFinal;
    private Pieza piezaAuxiliar;
    private int iPieza;
    private int jPieza;
    private boolean pausa;//Activado = true, Desactivado = false.
    private Random random;
    private int matrizActual;
    private int click;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Sistema
     * @param tamaño del tipo int contiene el tamaño de la matriz del juego
     * @param horas del tipo int almacena las horas del juego
     * @param minutos del tipo int almacena los minutos del juego
     * @param segundos del tipo int almacena los segundos del juego
     * @param matrizOrdenada del tipo Pieza crea una matriz con las piezas ordenadas
     * @param matrizDesordenada del tipo Pieza crea una matriz con las piezas desordenadas
     * @param matrizFinal del tipo Pieza contiene la matriz final del juego
     * @param piezaAuxiliar del tipo pieza contine una pieza auxiliar
     * @param iPieza del tipo int contiene la posicion de una pieza
     * @param jPieza del tipo int contiene la posicion de una pieza
     * @param pausa del tipo boolean controla si el juego está en ejecucion o pausado
     * @param randon del tipo Random obtiene un numero random
     * @param matrizActual del tipo int almacena la matriz actual
     * @param click del tipo int almacena el estado del click
     */
    public Sistema()
    {
        this.random = new Random();
        this.nuevoJuego(3);
    }
    
    /**
     * Metodo que crea una nueva partida del juego segun el tamaño
     * @param tamaño del tipo int almacena el tamaño de la partida
     */
    public void nuevoJuego(int tamaño)
    {
        this.tamaño = tamaño;
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
        this.matrizOrdenada = new Pieza[tamaño][tamaño];
        this.matrizDesordenada = new Pieza[tamaño][tamaño];
        this.matrizFinal = new Pieza[tamaño][tamaño];
        this.piezaAuxiliar = null;
        this.generarMatriz();
        this.desordenarMatriz();
        this.inicializarMatrizFinal();
        this.pausa = false;
        this.iPieza = 0;
        this.jPieza = 0;
        this.matrizActual = 0;
        this.click = 0;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo superior
     * @param superior del tipo int indica la posicion de la pieza
     */
    public void setTamaño(int tamaño)
    {
        this.tamaño = tamaño;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo tamaño
     * @return mataño El metodo retorna el valor que se encuentra almacenado en el atributo tamaño
     */
    public int getTamaño()
    {
        return this.tamaño;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo pausa
     * @param pausa del tipo boolean indica si el juego esta en pausa
     */
    public void setPausa()
    {
        if(this.pausa == true)
        {
            this.pausa = false;
        }
        else
        {
            this.pausa = true;
        }
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo pausa
     * @param pausa del tipo boolean indica si el juego esta en pausa
     */
    public void setPausa2()
    {
        this.pausa = true;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo pausa
     * @return pausa El metodo retorna el valor que se encuentra almacenado en el atributo pausa
     */
    public boolean getPausa()
    {
        return this.pausa;
    }
    
    /**
     * Metodo que genera una matriz al azar para comenzar una partida
     */
    public void generarMatriz()
    {
        int superior = 0;
        int inferior = 0;
        int izquierda = 0;
        int derecha = 0;
        for(int i = 0; i < tamaño; i++)
        {
            for(int j = 0; j < tamaño; j++)
            {
                if(i == 0 && j == 0)
                {
                    superior = this.random.nextInt(10);
                    inferior = this.random.nextInt(10);
                    izquierda = this.random.nextInt(10);
                    derecha = this.random.nextInt(10);
                }
                else if(i == 0 && j != 0)
                {
                    superior = this.random.nextInt(10);
                    inferior = this.random.nextInt(10);
                    izquierda = this.matrizOrdenada[0][(j-1)].getDerecha();
                    derecha = this.random.nextInt(10);
                }
                else if(i != 0 && j == 0)
                {
                    superior = this.matrizOrdenada[(i-1)][0].getInferior();
                    inferior = this.random.nextInt(10);
                    izquierda = this.random.nextInt(10);
                    derecha = this.random.nextInt(10);
                }
                else if(i != 0 && j != 0)
                {
                    superior = this.matrizOrdenada[(i-1)][j].getInferior();
                    inferior = this.random.nextInt(10);
                    izquierda = this.matrizOrdenada[i][(j-1)].getDerecha();
                    derecha = this.random.nextInt(10);
                }
                Pieza pieza = new Pieza(superior, inferior, izquierda, derecha);
                this.matrizOrdenada[i][j] = pieza;
            }
        }
    }
    
    /**
     * Metodo que desordena una matriz para comenzar una nueva partida
     */
    public void desordenarMatriz()
    {
        for(int i = 0; i < tamaño; i++)
        {
            for(int j = 0; j < tamaño; j++)
            {
                Pieza pieza = new Pieza(this.matrizOrdenada[i][j].getSuperior(), this.matrizOrdenada[i][j].getInferior(), this.matrizOrdenada[i][j].getIzquierda(), this.matrizOrdenada[i][j].getDerecha());
                this.matrizDesordenada[i][j] = pieza;
            }
        }
        Pieza pieza = new Pieza(0, 0, 0, 0);
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        for(int i = 0; i < (this.tamaño*this.tamaño); i++)
        {
            x = this.random.nextInt(tamaño);
            y = this.random.nextInt(tamaño);
            pieza = this.matrizDesordenada[x][y];
            x2 = this.random.nextInt(tamaño);
            y2 = this.random.nextInt(tamaño);
            this.matrizDesordenada[x][y] = this.matrizDesordenada[x2][y2];
            this.matrizDesordenada[x2][y2] = pieza;
        }
    }
    
    /**
     * Metodo que controla la matriz final de la partida
     */
    public void inicializarMatrizFinal()
    {
        for(int i = 0; i < this.tamaño; i++)
        {
            for(int j = 0; j < this.tamaño; j++)
            {
                this.matrizFinal[i][j] = null;
            }
        }
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo matrizOrdenada
     * @return matrizOrdenada El metodo retorna los valores que se encuentran almacenados en el atributo matrizOrdenada
     */
    public Pieza getPiezaMatrizOrdenada(int i, int j)
    {
        return this.matrizOrdenada[i][j];
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo matrizDesordenada
     * @return matrizDesordenada El metodo retorna los valores que se encuentran almacenados en el atributo matrizDesordenada
     */
    public Pieza getPiezaMatrizDesordenada(int i, int j)
    {
        return this.matrizDesordenada[i][j];
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo matrizFinal
     * @return matrizFinal El metodo retorna los valores que se encuentran almacenados en el atributo matrizFinal
     */
    public Pieza getPiezaMatrizFinal(int i, int j)
    {
        return this.matrizFinal[i][j];
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo piezaAuxiliar
     * @param piezaAuxiliar del tipo Pieza almacena la piezaAuxiliar
     */
    public void setPiezaAuxiliar1()
    {
        this.piezaAuxiliar = this.matrizFinal[this.iPieza][this.jPieza];
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo piezaAuxiliar
     * @param piezaAuxiliar del tipo Pieza almacena la piezaAuxiliar
     */
    public void setPiezaAuxiliar2()
    {
        this.piezaAuxiliar = this.matrizDesordenada[this.iPieza][this.jPieza];
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo piezaAuxiliar
     * @param piezaAuxiliar del tipo Pieza almacena la piezaAuxiliar
     */
    public void setPiezaAuxiliar()
    {
        this.piezaAuxiliar = null;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo piezaAuxiliar
     * @return piezaAuxiliar El metodo retorna el valor que se encuentra almacenado en el atributo piezaAuxiliar
     */
    public Pieza getPiezaAuxiliar()
    {
        return this.piezaAuxiliar;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo iPieza
     * @param iPieza del tipo int almacena la posicion de una pieza
     */
    public void setIPieza(int iPieza)
    {
        this.iPieza = iPieza;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo iPieza
     * @param iPieza del tipo int almacena la posicion de una pieza
     */
    public void setJPieza(int jPieza)
    {
        this.jPieza = jPieza;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo iPieza
     * @return iPieza El metodo retorna el valor que se encuentra almacenado en el atributo iPieza
     */
    public int getIPieza()
    {
        return this.iPieza;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo jPieza
     * @return jPieza El metodo retorna el valor que se encuentra almacenado en el atributo jPieza
     */
    public int getJPieza()
    {
        return this.jPieza;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo MmatrizActual
     * @param matrizActual del int almacena la matrizActual
     */
    public void setMatrizActual(int indice)
    {
        this.matrizActual = indice;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo matrizActual
     * @return matrizActual El metodo retorna el valor que se encuentra almacenado en el atributo matrizActual
     */
    public int getMatrizActual()
    {
        return this.matrizActual;
    }
    
    /**
     * Metodo que controla el movimiento de las piezas de una matriz a la otra
     */
    public void moverElemento()
    {
        for(int i = 0; i < this.tamaño; i++)
        {
            for(int j = 0; j < this.tamaño; j++)
            {
                if(this.piezaAuxiliar == this.matrizDesordenada[i][j])
                {
                    this.matrizDesordenada[i][j] = null;
                }
                else if(this.piezaAuxiliar == this.matrizFinal[i][j])
                {
                    this.matrizFinal[i][j] = null;
                }
            }
        }
        this.matrizFinal[this.iPieza][this.jPieza] = this.piezaAuxiliar;
        this.piezaAuxiliar = null;
        this.click = 0;
        this.matrizActual = 0;
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo click
     * @param click del tipo int almacena el estado del click
     */
    public void setClick(int click)
    {
        this.click = click;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo click
     * @return click El metodo retorna el valor que se encuentra almacenado en el atributo click
     */
    public int getClick()
    {
        return this.click;
    }
    
    /**
     * Metodo que retorna la informacion si la matriz esta completa
     * @return matrizCompleta del tipo boolean retorna true si la matriz esta completa en caso de no estarla retorna false
     */
    public boolean matrizCompleta()
    {
        boolean matrizCompleta = true;
        for(int i = 0; i < tamaño; i++)
        {
            for(int j = 0; j < tamaño; j++)
            {
                if(this.matrizFinal[i][j] == null)
                {
                    matrizCompleta = false;
                    break;
                }
            }
        }
        return matrizCompleta;
    }
    
    /**
     * Metodo que verifica si la matriz esta completa
     * @return matrixCorrecta del tipo boolean indica true si la matriz esta correcta y false en el caso que no este correcta
     */
    public boolean matrizCorrecta()
    {
        boolean matrizCorrecta = true;
        for(int i = 0; i < tamaño; i++)
        {
            for(int j = 0; j < tamaño; j++)
            {
                if(matrizFinal[i][j].getSuperior() != matrizOrdenada[i][j].getSuperior() || matrizFinal[i][j].getInferior() != matrizOrdenada[i][j].getInferior() || matrizFinal[i][j].getIzquierda() != matrizOrdenada[i][j].getIzquierda() || matrizFinal[i][j].getDerecha() != matrizOrdenada[i][j].getDerecha())
                {
                    matrizCorrecta = false;
                    break;
                }
            }
        }
        return matrizCorrecta;
    }
    
    /**
     * Metodo que controla los hints o pistas que se muestran al jugador durante la partida
     */
    public void hint()
    {
        boolean continuar = true;
        do
        {
            int x = this.random.nextInt(tamaño);
            int y = this.random.nextInt(tamaño);
            if(this.matrizFinal[x][y] == null || this.matrizOrdenada[x][y] == null)
            {
                this.matrizFinal[x][y] = this.matrizOrdenada[x][y];
                for(int i = 0; i < tamaño; i++)
                {
                    for(int j = 0; j < tamaño; j++)
                    {
                        if(this.matrizDesordenada[i][j] != null)
                        {
                            if(this.matrizOrdenada[x][y].getSuperior() == this.matrizDesordenada[i][j].getSuperior() && this.matrizOrdenada[x][y].getInferior() == this.matrizDesordenada[i][j].getInferior() && this.matrizOrdenada[x][y].getIzquierda() == this.matrizDesordenada[i][j].getIzquierda() && this.matrizOrdenada[x][y].getDerecha() == this.matrizDesordenada[i][j].getDerecha())
                            {
                                this.matrizDesordenada[i][j] = null;
                            }
                        }
                    }
                }
                continuar = false;
            }
        }while(continuar);
    }
    
    /**
     * Metodo que muestra la matriz por pantalla
     */
    public void mostrarMatriz()
    {
        this.matrizFinal = this.matrizOrdenada;
        for(int i = 0; i < tamaño; i++)
        {
            for(int j = 0; j < tamaño; j++)
            {
                this.matrizDesordenada[i][j] = null;
            }
        }
    }
}