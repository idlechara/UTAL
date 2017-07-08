import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Clase Sistema
 * Es la clase que contiene los metodos de interaccion entre las clases y los objetos del sistema
 * La Clase administra con metodos las funcionalidadaes posibles, tanto de modificacion, adicion y remocion de nuevos objetos dentro de los contenedores
 * 
 * 
 * @author Eduardo Vallejo, Sebastian Valenzuela, Erik Regla
 * @version 1.2
 */
public class Sistema {
    /**
     * Arraylist graphMark
     * Aun arraylist que marca el recorrido de las secretarias. tal y como se discutio en 
     * el planning, se usara un medio para marcar de forma "grafica" el recorrido.
     * 
     * true = visitada
     * false = no visitada
     * 
     * @author Erik Regla
     * @version 0.1
     * @since 1.1
     */
    public static ArrayList<Boolean> graphMark;

    /**
     * Array List Secretaria 
     * Instancia de la clase ArrayList que almacena dentro de si objetos secretarias
     * que corresponden a las distintas secretarias de escuela del sistema de la universidad.
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     *
     */
    public static ArrayList<Secretaria> listaSecretarias;

    /**
     * Array List Proyector
     * Instancia de la clase ArrayList que almacena los proyectores en globalidad que contiene el sistema
     * y este a su vez los administra asignandolos a Salas o secretarias de escuela en particulas.
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     */
    public static ArrayList<Proyector> listaProyectores;
    /**
     * Array List Lista de Salas
     * Instancia de la clase ArrayList que contiene todos los objetos sala creados en la ejecucion del sistema.
     * 
     * @author Eduardo Vallejo
     * @version 1.1
     */
    public static ArrayList<Sala> listaSalas;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //    //METODO BORRADO POR QUE CARECE DE SENTIDO, ES INUTIL, Y NO ESTA DOCUMENTADO,
    //    //POR ENDE, NO SE PUEDE OCUPAR AUNQUE QUERAMOS!
    //    public static int validadorSecretarias[] = {0,0,0,0,0} ;

    //TODO por que esta wea?
    static Proyector pro; 
    // Validador de secretarias, marcamos las que ya se recorrieron. 
    public Sistema(){
        listaSalas=new ArrayList <Sala>();
        listaProyectores=new ArrayList <Proyector>();
        listaSecretarias = new ArrayList<Secretaria>() ;
        pro=new Proyector();

        //kynku:
        //modificacion para incializacion de el arrayde bools
        this.graphMark = new ArrayList<Boolean>();
    }

    /**
     * 
     * Esta funcion lee un numero entero y valida dentro de un rango si es valido o no.
     * 
     * @param minRange minimo del rango de numeros para validar
     * @param maxRange maximo del rango de numero a valdar.
     * @version 1.1
     * @author Eduardo Vallejo
     * @return el valor dentro del rango esperado
     * @throws IOException 
     * @throws NumberFormatException
     */
    public int leerInt ( int minRange, int maxRange){
        boolean loop = true;
        int valido = -1;
        System.out.println("Ingrese su opcion:  ");
        while(loop){
            try{
                valido = Integer.parseInt(br.readLine());
                if(valido < minRange || valido> maxRange){
                    throw new NumberFormatException();
                }
                loop=false;

            } 
            catch(NumberFormatException e){
                System.err.println("Error de ingreso , Intente nuevamente ");

            }
            catch(IOException e){
                //kynku: modifificando salida (formato)
                System.err.print("Error de entrada y salida, Intente nuevamente... ");

            }

        }
        return valido;
    }

    /**
     * Esta funcion toma  un string con un dia, y valida si este es correcto.
     * Convierte un dia a un numero en representacion numerica respecto a su posicion en la semana -1-
     * 
     * @author Eduardo Vallejo
     * @return el numero correspondiente al dia 
     * @throws IOException
     */

    public int leerDia (){
        String dia;
        String nombreDias[] =  {"lunes","martes","miercoles","jueves","viernes"};
        while(true){
            try{
                dia= br.readLine();
                for ( int i = 0 ; i<5 ; i++){
                    if(dia.trim().toLowerCase().equals(nombreDias[i])){
                        return i;
                    }
                }
                throw new IOException();

            } 
            catch(IOException e){
                System.err.println("Error de entrada y salida, Intente nuevamente");

            }

        }
    }

    /**
     * Esta funcion lee un string y  lo retorna 
     * Se encarga ella misma de validar dicho String y arrojar las
     * excepciones posibles
     * 
     * @param lectura  String que se leera para ser validado.  
     * @author Eduardo Vallejo
     * @version 1.0
     *
     */
    public String leerString(){
        String lectura;
        while(true){
            try{
                lectura = br.readLine();
                if(!lectura.equals("")&&lectura!=null){
                    return lectura;
                }
            }
            catch(IOException e){
                //kynku: modificacion de formato.
                System.err.print("Error de entrada y salida, Intente nuevamente...");

            }

        }
    }

    //	PARA QUE SIRVE ESTO?
    //    public void marcarRecorrido(Secretaria secretaria){
    //    	//Sistema.validadorSecretarias;
    //        // PENDIENTE    
    //    }
    //    
    //    
    //    
    //    //NO CUMPLE CON ESTANDARIZACION
    //    /** 
    //     * 
    //     */
    //    /**
    //     * Esta funcion agregar una secretaria al ArrayList de secretarias
    //     * @param secretaria secretaria que se agregara al ArrayList de secretarias de la clase Sistema.
    //     * @author Eduardo Vallejo 
    //     * @version alpha 1.0 beta super duper
    //     */
    //       
    //    public void agregarSecretaria(Secretaria secretaria){
    //        if ( buscarSecretaria(secretaria.getEscuela()) == false){
    //            listaSecretarias.add(secretaria);
    //            //kynku:
    //            //adicion de marca de grafo
    //            graphMark.add(false);
    //        }        
    //    }
    //    
    /**
     * Buscar Secretaria
     * 
     * Este metodo busca una secretaria dentro de la lista de secretarï¿½as a traves de su nombre de escuela
     * retorna 1 en caso de que ya se encuentre, de lo contrario retorna 0
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     * 
     */

    public boolean buscarSecretaria(String nombreEscuela){
        for (Secretaria sis : listaSecretarias ) {
            if (sis.getEscuela().equals(nombreEscuela) == true ){
                return true;
            }
        }
        return false;   
    }

    /**
     * Esta funcion borra una secretaria
     * se acompaï¿½a del metodo imprimirListaSecretarias para mostrar un menu
     * de seleccion las opciones.
     * 
     * @author Eduardo Vallejo
     * 
     *	//Excepcion eliminada, uso inesesario en codigo
     * ###########@#########throws IOException
     */
    public void borrarSecretaria() /*throws IOException*/{
        //linea removida para incrementar claridad de interfaz
        //    	this.imprimirListaSecretarias();
        //condicion inesesaria. reeplazada por if de validacion.
        //    	while (true)
        if(listaSecretarias.size()>0)
        {
            //    		try{ 
            this.imprimirListaSecretarias();
            //kynku:
            //    		modificaciones al source:
            //    		 lineas modificadas por motivos de rebustez
            //    		                int seleccion;
            //    		                seleccion = Integer.parseInt(br.readLine());
            //    		                if ( seleccion < 0 || seleccion>listaSecretarias.size() ){
            //                    throw new IOException();
            //                }
            int index = leerInt(0, listaSecretarias.size()-1);
            listaSecretarias.remove(listaSecretarias.get(index));
            graphMark.remove(index);
            //    		}   
            //Kynku: indice y manejo de excepcion correjido.
            //    		catch( IOException e){
            //    			System.out.println("Error de ingreso , seleccione nuevamente...");
            //
            //    		}
        }
        //condicion de salida, si es que esta vacio, muestra un mensaje (no-error)
        else{
            System.out.println("No hay secretarias disponibles para eliminacion.");
        }

    }
    /**
     * Imprimir Lista Secretarias
     * 
     * Este metodo muestra en pantalla por consola las secretarï¿½as de escuela disponibles. Mostrando su nombre
     * Tambien las enumera para que se realize una posterior seleccion por menu a traves de ese mismo numero entero.
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     */
    public void imprimirListaSecretarias(){
        //kynku: valor inicializacion cambiado a 0
        int i = 0 ;
        System.out.println("La Lista de Secretarï¿½as disponibles son:");
        for (Secretaria sis : listaSecretarias){
            //Kynku: correccion de formato. 
            System.out.println("["+i+"] " + sis.getEscuela());
            i++;
        }
    }

    /**
     * Imprimir Lista de Proyectores
     * Este metodo muestra en pantalla por consola los proyectores disponibles. Mostrando su nombre
     * Tambien los enumera para que se realize una posterior seleccion por menu a traves de ese mismo numero entero.
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     */

    public void imprimirListaProyectores(){
        //kynku: valor inicializacion cambiado a 0
        int i =  0 ;
        System.out.println("La Lista de Proyectores disponibles son:");
        for (Proyector pro : listaProyectores){
            //kynku: correcion formato
            System.out.println("["+i+"] " + pro.getMarca() + pro.getModelo() + "Disponible?: " + pro.isAvaliable());
            i++;
        }
    }

    /**
     * Metodo validacion: validar disponiblilidad,
     * el cual entrega un booleano que dennota
     * si es que existe algun proyector disponible.
     * 
     * @author Erik Regla
     * @version 0.1
     * @since 1.2
     */
    public boolean validarDisponiblilidadListaProyectores(){
        for (Proyector pro : listaProyectores){
            if(pro.isAvaliable()){
                return true;
            }
        }
        return false;
    }

    /**
     * Imprimir Lista Salas
     * 
     * Este metodo muestra en pantalla por consola las Salas disponibles. Mostrando su nombre
     * Tambien las enumera para que se realize una posterior seleccion por menu a traves de ese mismo numero entero.
     * 
     * @author Eduardo Vallejo
     * @version 1.0
     */
    public void imprimirListaSalas(){
        //    	//kynku: valor inicializacion cambiado a 0
        int i = 0 ;
        System.out.println("La Lista de Salas disponibles son:");
        for (Sala sal : listaSalas){
            //correccion formato
            System.out.println("["+i+"]"  + sal.getNumero());
            i++;
        }
    }
    ////METODO ELIMINADO POR INCHOERENTE
    //    public void editarSecretaria(){
    //        int lectura;// 
    //        System.out.println("Desea configurar Salas o Proyectores");
    ////        //kynku: correccion mayor:
    ////        System.out.println("[1] Salas");
    ////        System.out.println("[2] Proyector");
    ////        String nombreParcial;
    ////        int cualquierCosa;
    ////        Sala seleccionada;
    ////        Secretaria secre;
    ////        lectura = leerInt(0,2);
    //
    //        System.out.println("[1] Salas");
    //        System.out.println("[2] Proyector");
    //        String nombreParcial;
    //        int cualquierCosa;
    //        Sala seleccionada;
    //        Secretaria secre;
    //        lectura = leerInt(0,2);
    //        
    //        while ( lectura !=0){
    //            switch(lectura){
    //            case 1:
    //                this.mostrarOpcionesEdicion();
    //                lectura =leerInt(0,3);/*
    //                switch(lectura){
    //                case 0:
    //                    try{
    //                        System.out.println("Ingresel nombre de la sala a Agregar ");
    //                        nombreParcial= leerString();
    //                        System.out.println("Escoja una Sala");
    //                        imprimirListaSalas();
    //                        cualquierCosa = leerInt(0,listaSalas.size()-1) ;
    //                        seleccionada =listaSalas.get(cualquierCosa);
    //                        //Secretaria.addSala(seleccionada , nombreParcial);
    //                    
    //                    }
    //                    catch (IOException e){
    //                        System.out.println("Error de ingreso");
    //                    }
    //                case 1:
    //                    try{
    //                        System.out.println("Ingresel nombre de la sala Remover ");
    //                        nombreParcial= leerString();
    //                        //Secretaria.removeSala(nombreParcial);
    //                    
    //                    }
    //                    catch (IOException e){
    //                        System.out.println("Error de ingreso");
    //                    }
    //                    
    //
    //
    //                case 2:
    //
    //
    //                case 3:
    //                    return;
    //
    //
    //                }*/
    //
    //                break;
    //            case 2:
    //                this.mostrarOpcionesEdicion();
    //                lectura =leerInt(0,3);
    //                switch(lectura){
    //                case 0:
    //
    //
    //                case 1:
    //
    //
    //                case 2:
    //
    //
    //                case 3:
    //                    return;
    //
    //
    //                }
    //
    //                break;
    //            }
    //
    //                   
    //        }
    //
    //    }
    //    void agregarSalaSecretaria(){
    //        
    //            
    //        }    
    //
    //
    //        
    //        
    //    /**
    //     * Mostrar Opciones Edicion
    //     * 
    //     * Este metodo muestra las opciones de edicion para un submenu dentro de una secretarï¿½a, un proyector o una Sala.
    //     * 
    //     * @author Eduardo Vallejo
    //     * @version 1.0
    //     */
    //    
    //    void mostrarOpcionesEdicion(){
    //    	//indices cambiados. inician en 0
    //    	//        System.out.println("Seleccione una opcion:");
    //    	//        System.out.println("[1] Aï¿½adir");
    //    	//        System.out.println("[2] Remover");
    //    	//        System.out.println("[3] Editar");
    //    	//        System.out.println("[0] Salir");
    //    	//        System.out.println("Ingrese una opcion :");
    //    	System.out.println("Seleccione una opcion:");
    //    	System.out.println("[0] Aï¿½adir");
    //    	System.out.println("[1] Remover");
    //    	System.out.println("[2] Editar");
    //    	System.out.println("[3] Salir");
    //    	System.out.println("Ingrese una opcion :");
    //    }

    //AAAAAAAAAAAA

    //SEBA

    /**
     * Metodo agregarSala que agregara una sala al ArrayList de salas, y en caso de 
     * que exista una dara opciones para agregar una nueva, aï¿½ade las caracteristicas 
     * de la sala creada.
     * 
     * Primero maneja los parametros necesarios que utilizara, imprime la lista de las 
     * salas creadas hasta el momento y da la opcion de crear una nueva sala, de ser asi
     * pide los datos de la sala para completar su informacion, de lo contrario anunciara 
     * de que dicha sala ya existe y permitira intentar crear denuevo la sala.
     * 
     * @param leer1 Almacena temporalmente un string del numero de la sala
     * que se modificara a entero->almacenando en number
     * @param leer2 Almacena temporalmente un string de la capacidad de 
     * la sala que se modificara a entero-->almacenado en capacity
     * @param number Almacena el numero identificador de la sala
     * @param capacity Almacena la capacidad de alumnos de la sala
     * @param caract Almacena las caracteristicas de la sala
     * @param esta por defecto dice que una sala no esta, pero cambiara de acuerdo a un ciclo de verificacion
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void agregarSala()
    {
        String leer1; ///guarda el string q despues sera el numero de la sala
        String leer2;  ///guarda el string q despues sera la capacidad de la sala
        int number;  //guarda el numero de la sala
        int capacity; //guarda la capacidad de la sala
        String caract; //guarda las caracteristicas de la sala
        imprimirListaSalas(); //imprime la lista de las salas que estan asta el momento creadas

        try{     //verificando si ocurre una excepcion de la entrada         
            System.out.println("ingresa los datos de la nueva sala");
            System.out.println("");
            System.out.println("Ingrese un numero para la sala");
            leer1=br.readLine(); 
            number=Integer.parseInt(leer1); //se pasa del string del numero al entero del numero
            String esta="no esta";  //este parametro por defecto dice que una sala no esta
            for(int i =0; i<listaSalas.size();i++) //se inicia el ciclo de verificacion de que la sala no se encuentre
            {
                Sala sal=(Sala)listaSalas.get(i);       //probando una opcion
                if(sal.getNumero()==number)             //verificando esa opcion
                {
                    System.out.println("Esta sala ya existe, trata agregando otra sala");
                    i=listaSalas.size();             //como se encontro se asigna el valor limitador del ciclo para que se corte
                    esta="esta";                     //el parametro que decide si se encuentra ahora denota "esta"
                }
            }
            if(esta=="no esta")  //si no se encuentra...procede a la creacion
            {
                try //probando que los valores sean los que se piden y no otros
                {
                    System.out.println("ingrese la capacidad maxima de alumnos de la sala");
                    leer2=br.readLine(); //se lee y guarda el string e la capacidad
                    capacity=Integer.parseInt(leer2); //se lleva de string a entero
                    System.out.println("ingresa la descripcion basica de la sala");
                    caract=br.readLine(); //se lee y guardan las caracteristicas de la sala
                    listaSalas.add(new Sala(capacity,number,caract)); // se crea la nueva sala en el listado de salas
                }
                catch (IOException e)//captura la excepcion de entrada
                {
                    System.err.println("Porfevor ingrese solo el numero identificador");  
                    agregarSala(); //recursividad al metodo por un error en la entrada
                }
                catch (NumberFormatException c)//captura de la excepcion de no entero
                {
                    System.err.println("Error de ingreso, intentelo denuevo con otro valor");
                    agregarSala(); //recursividad al metodo por causa de una excepcion de la entrada
                }
            }
        }
        catch (IOException e) //captura la excepcion de entrada
        {
            System.err.println("Porfevor ingrese solo el numero identificador");    
            agregarSala();//recursividad al metodo por causa de un error de entrada
        }
        catch (NumberFormatException c) //captura la excepcion de no entero
        {
            System.err.println("Error de ingreso, intentelo denuevo con otro valor");
            agregarSala();//recursividad al metodo por causa de una excepcion
        }   

    }

    /**
     * Metodo removSala que elimina una posible sala, y en caso de no poder anuncia que no se pudo
     * y llama recursivamente hasta concretarlo.
     * 
     * @param leer1 almacena el string del numero verificador de la sala, despues se modificara a entero-->number
     * @param number almacena el entero ya modificado del numero verificador de la sala
     * @param noExiste por defecto anuncia que la sala no existe, pero por medio de una verificacion se puede modificar
     * @param sal se creara y modificara a medida que se comprueba la existencia de la sala
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void removSala()
    {  
        //kynku:
        //agregando validacion
        if (listaSalas.size()>0) {
            String leer1; //almacena el string del numero
            int number; //almacena el entero del numero ingresado
            String noExiste = "NO"; //por defecto dice que no existe pero se cambiara si es que existe la sala
            imprimirListaSalas(); //imprime las salas creadas hasta el momento
            try //probando 
            {
                System.out
                .println("Ingrese el numero de la sala que desea eliminar");
                leer1 = br.readLine(); //lectura y almacenamiento del numero verificador
                number = Integer.parseInt(leer1); //paso del string a entero del numero verificador de la sala
                for (int i = 0; i < listaSalas.size(); i++)//ciclo para verificar la existencia de la sala
                {
                    Sala sal = (Sala) listaSalas.get(i); //parametro temporal que se medira con el numero de la sala que se quiere verificar
                    if (sal.getNumero() == number) //se comprueba su existencia
                    {
                        listaSalas.remove(i);//si se encontro se borra
                        System.out.println("Sala removida con exito");
                        i = listaSalas.size();//se modifica el limitador del ciclo para que se corte
                        noExiste = "Si"; //ahora el parametro noExiste indica que si se encontraba la sala
                    }
                }
            } catch (IOException e)//captura la excepcion de entrada
            {
                System.err
                .println("Porfevor ingrese solo el numero identificador");
                removSala();//recursividad al metodo por un error en la entrada
            } catch (NumberFormatException c)//captura la excepcion de no entero
            {
                System.err
                .println("Error de ingreso, intentelo denuevo con otro valor");
                removSala();//recursividad al metodo por causa de una excepcion
            }
            if (noExiste == "NO")//si no se modifico indica que la sala no existia
            {
                System.out
                .println("No existe tal sala, intente con otro numero de sala");
                removSala();//se llama recursivamente para intentar denuevo con la eliminacion
            }
        }
        //kynku:
        //agregando validacion
        else{
            System.out.println("No hay salas disponibles...");
            return;
        }
    }

    /**
     * Metodo editarSala que busca una sala espesifica para modificar la posesion 
     * de un proyector, en caso de encontrarse se podra determinar volver a probar con otra sala.
     * 
     * @param noExiste anuncia por defecto que una sala no existe, pero que se modificara al verificar
     * @param leer1 almacena temporalmente el string leido del numero de la sala a modificar
     * @param opcion almacena el entero transformado proveniente de ->leer1<- del numero verificador de la sala
     * @param opcion2 almacena la opcion a seguir la edicion de la sala
     * 
     * @author Sebastian Valenzuela
     * @since 0.1
     */
    public void editarSala()
    {
        if (listaSalas.size()>0) {
            String noExiste = "NO"; //por defecto dice que la sala no existe, pero se modificara de acuerdo a la verificacion
            String leer1; //almacena el String verificdor de la sala que se llevara a entero despues
            int opcion; //almacena el entero identificador de la sala
            imprimirListaSalas();
            try//probando
            {
                System.out
                .println("selecciona el numero de la sala a editar");
                leer1 = br.readLine();//lectura del numero verificador
                opcion = Integer.parseInt(leer1);//traspaso del numero a entero
                for (int i = 0; i < listaSalas.size(); i++)//ciclo verificador y ejecutador
                {
                    Sala sal = (Sala) listaSalas.get(i);//parametro temporal que se medira con el numero de la sala que se quiere verificar  
                    if (sal.getNumero() == opcion)//se comprueba su existencia
                    {
                        noExiste = "Si";//ahora el parametro indica que si existe la sala
                        System.out
                        .println("[1] opcion, aï¿½ade un proyector");
                        System.out
                        .println("[2] opcion, elimina el proyector");
                        //kynku:
                        //modificacion al menu: aÃ±adir salir!
                        System.out.println("[0] Salir.");
                        int opcion2; //se define el parametro almacenador de la opcion a seguir            
                        opcion2 = leerInt(0, 2);//se llama al metodo leerInt para devolver solo una opcion correcta y adecuada a lo que se pide
                        //kynku:
                        //agregando salida del menu:
                        if (opcion2 == 0) {
                            return;
                        }
                        if (opcion2 == 1)//se elije la opcion aï¿½adir proyector
                        {
                            if (sal.hasProyector() == false)//se verifica la existencia de proyector en la sala actual
                            {
                                System.out
                                .println("Proyector aï¿½adido satisfactoriamente");
                                listaSalas.get(i).setProyector(pro);//se aï¿½ade el proyector
                            } else//de caso contrario el proyector existia asique se llama recursivamente para editar otra sala
                            {
                                System.out
                                .println("la sala ya contenia un proyector,prueba denuevo con otra sala");
                                editarSala();//recursion
                            }
                        }
                        if (opcion2 == 2)//se elije la opcion eliminar proyector de una sala
                        {
                            if (sal.hasProyector() == true)//se comprueba de que el proyector exista
                            {
                                sal.removerProyector();//accion de borrar el proyector
                                System.out
                                .println("Proyector sacado de la sala");
                            } else //en caso de no existir se anunciara y se proseguira con recursion para continuar la edicion
                            {
                                System.out
                                .println("No proyector en esta sala, trata denuevo con otra sala");
                                editarSala();//recursion
                            }
                        }
                    }
                }
            } catch (IOException e)//captura la excepcion de entrada
            {
                System.err
                .println("Porfevor ingrese solo el numero identificador");
                editarSala();//recursividad al metodo por un error en la entrada
            } catch (NumberFormatException c)//captura la excepcion de no entero
            {
                System.err
                .println("Error de ingreso, intentelo denuevo con otro valor");
                editarSala();//recursividad al metodo por causa de una excepcion
            }
            if (noExiste == "NO")//si no se modifico el parametro que comprueba la existencia de la sala, se llamara a recursion para volver a probar
            {
                System.out
                .println("la sala ingresada no se encuentra, por favor intente denuevo");
                editarSala();//reursion          
            }
        }
        //kynku:
        //adicion de verificacion.
        else{
            System.out.println("No hay salas disponibles...");
            return;
        }
    }

    //KINKUN
    /**
     * edicionSala() despliega un menu el cual permite modificar el contenido
     * de la lista de salas del sistema, permitiendo al usuario agregar,
     * borrar o editar los contenidos de la misma de forma interactiva.
     * 
     * 
     * @author Erik Andres Regla Torres
     * @version 0.2
     * @since 1.1
     */
    public void edicionSala(){
        //validacion de operacion 
        int salaIndex = 0, proyectorIndex = 0;
        boolean loop = true;
        if (listaSalas.size()>0) {
            while (true) {
                //Enunciado y lista opciones
                System.out.println("Menu Edicion Sistema: Sala");
                System.out.println("Ingrese sala a editar:");
                imprimirListaSalas();
                //seleccion de sala
                System.out.println("\nIngrese seleccion:");
                salaIndex = leerInt(0, listaSalas.size());
                //recuperacion
                Sala temporalReference = listaSalas.get(salaIndex);
                temporalReference.getLongDescription();
                //impresion segundo menu
                System.out.println("[1] AÃ±adir un proyector -sala-");
                System.out.println("[2] Quitar un proyector -sala-");
                System.out.println("[3] Editar un proyector -sala-");
                System.out.println("[4] Cambiar descripcion.");
                System.out.println("[0] Salir");
                //eleccion metodo
                switch (leerInt(0, 4)) {
                    case 1:
                    //validacion caso 1:
                    if(temporalReference.hasProyector()){
                        break;
                    }
                    else{
                        //validacion de presencia de proyectores
                        if(listaProyectores.size()>0 && validarDisponiblilidadListaProyectores()){
                            while (loop) {
                                //inicio algoritmo de proyeccion:
                                imprimirListaProyectores();
                                System.out.println("Seleccione proyector a agregar: ");
                                proyectorIndex = leerInt(0, listaProyectores.size() - 1);
                                //verificacion seleccion
                                if(listaProyectores.get(proyectorIndex).isAvaliable()){
                                    temporalReference.setProyector(listaProyectores.get(proyectorIndex));
                                    loop = false;
                                }
                                else{
                                    System.out.println("Ha ingresado una opcion invalida...");
                                }
                            }
                            break;
                        }
                        else{
                            System.out.println("No hay proyectores disponibles");
                            break;
                        }
                    }

                    //TODO ?? esto??
                    case 2:
                    temporalReference.removeProyector();
                    System.out.println("Proyector removido...");
                    break;
                    case 3:
                    if(temporalReference.hasProyector()){
                        System.out.println("Editar proyector -sala-...");
                        this.edicionProyectorEditDescripcion(temporalReference.getProyector());
                    }
                    else{
                        System.out.println("No hay proyectores disponibles...");
                    }
                    break;
                    case 4: 
                    System.out.println("Ingrese nueva descripcion: ");
                    temporalReference.setDescripcion(leerString());
                    break;
                    case 0:
                    return;
                }
            }
        }
        //ejecucion al caso analogo:
        else{
            System.out.println("No hay salas disponibles para edicion.");
        }
    }

    /**
     * edicionProyector() despliega un menu el cual permite modificar el contenido
     * de la lista de proyectores del sistema, permitiendo al usuario agregar,
     * borrar o editar los contenidos de la misma de forma interactiva.
     * 
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public void edicionProyector(){
        while (true) {
            //Enunciado y lista opciones
            System.out.println("Menu Edicion Sistema: Proyectores");
            System.out.println("Ingrese su opcion:");
            System.out.println("[1] Aï¿½adir un proyector -sistema-");
            System.out.println("[2] Quitar un proyector -sistema-");
            System.out.println("[3] Editar un proyector -sistema-");
            System.out.println("[0] Salir");
            //eleccion metodo
            switch (leerInt(0, 3)) {
                case 1:
                edicionProyectorAdd();
                break;
                case 2:
                edicionProyectorRemove();
                break;
                case 3:
                edicionProyectoEdit();
                break;
                case 0:
                return;
            }
        }

    }

    /**
     * edicionProyectoEdit(), depsliega un menu el cual muestra las opciones de edicion
     * de la lista de proyectores. Por motivos de implementacion, el unico campo disponible a
     * editar es el de Descripcion, el cual es una cadena la cual contiene informacion
     * miscelanea respecto del mismo.
     * 
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */
    public void edicionProyectoEdit() {     
        while (true) {
            //Enunciado y lista opciones
            System.out.println("Menu Edicion Sistema: Proyectores -> Edicion");
            System.out.println("Proyectores Disponibles:");
            this.imprimirListaProyectores();
            System.out.print("Ingrese proyector a editar... ");
            int proyector = this.leerInt(1, Sistema.listaProyectores.size());
            edicionProyectorEditDescripcion(Sistema.listaProyectores.get(proyector));
        }
    }

    /**
     * edicionProyectoEditDescripcion(), permite al usuario ingresar/modificar la descipcion de 
     * un proyector.
     * 
     * Dentro de la misma, se hace el manejo de errores, en este caso, el de E/S. y para darle un poco 
     * mas de dinamicidad y no se piense que el programa es tan cuadrado, se aï¿½adio el elemento de 
     * recursion.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */ 
    public void edicionProyectorEditDescripcion(Proyector proyector) {
        System.out.print("Ingrese nueva descripcion (" + proyector.getDescripcion() +"): ");
        try {
            proyector.setDescripcion(this.br.readLine());
        } 
        catch (IOException e) {
            System.err.print("Error de E/S... ");
            this.edicionProyectorEditDescripcion(proyector);
        }
    }

    /**
     * edicionProyectorRemove(), permite al usuario remover un proyector de la lista.
     * Aca el metodo de operacion mantiene la misma estandarizacion que los demas.
     * primero se evalua si existen proyectores, ya que no ser asi, se envia un mensaje al
     * usuario indicandole que no quedan proyectores, aunque esto no es un error.
     * Si existen proyectores, muestra una lista con los proyectores dispnibles y
     * le indica al usuario cual quiere remover.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */ 
    public void edicionProyectorRemove() {
        System.out.println("Menu Edicion Sistema: Proyectores -> Eliminar");
        if (Sistema.listaProyectores.size()>0) {
            System.out.println("Proyectores Disponibles:");
            this.imprimirListaProyectores();
            System.out.print("Ingrese proyector a eliminar... ");
            int proyector = this.leerInt(1, Sistema.listaProyectores.size());
            Sistema.listaProyectores.remove(proyector - 1);
        }
        else{
            System.out.println("No se encuentran proyectores disponibles.");
        }
    }

    /**
     * edicionProyectorAdd(), permite al ususario ingresar un proyector nuevo a la lista.
     * este metodo recibe los valores para iniciar la parametrizacion del objeto, y automaticamente
     * crea la instancia.
     * 
     * @author Erik Andres Regla Torres
     * @version 0.1
     * @since 0.1
     */ 
    public void edicionProyectorAdd() {
        System.out.println("Menu Edicion Sistema: Proyectores -> Agregar");
        System.out.println("Ingrese Marca: ");
        String marca = this.leerString();
        System.out.println("Ingrese Modelo de proyector: ");
        String modelo = this.leerString();
        System.out.println("Ingrese Descripcion: ");
        String descripcion = this.leerString();
        Sistema.listaProyectores.add(new Proyector(marca, modelo, descripcion));
    }

    //inicia el pandemonium
    public void menuPrincipal(){
        System.out.println("Tarea de programacion avanzada, version 1.2...");
        System.out.println();
        System.out.println("Ingrese modo de operacion");
        System.out.println("[1] Sistema");
        System.out.println("[2] Profesor");
        System.out.println("[0] Salir");
        System.out.println("\nIngrese su opcion: ");
        int opcion = leerInt(0, 2);
        switch(opcion){
            case 1:
            menuPrincipalSistema();
            break;
            case 2:
            //TODO menuprincipal del profesor... negociacion.! D:
            //	menuPrincipalProfesor();
            break;
            case 3:
            System.out.println("Ejecucion terminada.");
            System.exit(0);
        }
    }

    public void menuPrincipalSistema() {
        while(true){
            System.out.println("MenuPrincipal -> Sistema");
            System.out.println("\nIngrese operacion...");
            System.out.println("[1] Secretarias");
            System.out.println("[2] Salas");
            System.out.println("[3] Proyectores");
            System.out.println("[0] Salir");
            int opcion = leerInt(0, 2);
            switch(opcion){
                case 1:
                edicionSecretaria();
                break;
                case 2:
                edicionSalaSistema();
                break;
                case 3:
                edicionProyector();
                break;
                case 0:
                return;
            }
        }
    }

    public void edicionSalaSistema() {
        while (true) {
            //Enunciado y lista opciones
            System.out.println("Menu Edicion Sistema: Salas");
            System.out.println("Ingrese su opcion:");
            System.out.println("[1] Aï¿½adir una sala -sistema-");
            System.out.println("[2] Quitar una sala -sistema-");
            System.out.println("[3] Editar una sala -sistema-");
            System.out.println("[0] Salir");
            //eleccion metodo
            switch (leerInt(0, 3)) {
                case 1:
                agregarSala();
                break;
                case 2:
                removSala();
                break;
                case 3:
                edicionSala();
                break;
                case 0:
                return;
            }
        }
    }

    private void edicionSecretaria() {
        while (true) {
            //Enunciado y lista opciones
            System.out.println("Menu Edicion Sistema: Secretaria");
            System.out.println("Ingrese su opcion:");
            System.out.println("[1] Aï¿½adir secretaria -sistema-");
            System.out.println("[2] Quitar secretaria -sistema-");
            System.out.println("[3] Editar secretaria -sistema-");
            System.out.println("[0] Salir");
            //eleccion metodo
            switch (leerInt(0, 3)) {
                case 1:
                edicionSecretariaAdd();
                break;
                case 2:
                edicionSecretariaRemove();
                break;
                case 3:
                edicionSecretariaEdit();
                break;
                case 0:
                return;
            }
        }
    }

    private void edicionSecretariaEdit() {
        int secretariaIndex = 0;
        while (true) {
            //seleccion de secretaria
            System.out.println("Menu Edicion Sistema: Secretaria -> editar");
            System.out.println("Seleccione secretaria a editar: ");
            imprimirListaSecretarias();
            secretariaIndex = leerInt(0, listaSecretarias.size()-1);

            //menu de edicion!! D:
            System.out.println("\nMenu Edicion Sistema: Secretaria -> editar");
            System.out.println("[1] Agregar sala");
            System.out.println("[2] Agregar proyector");
            System.out.println("[3] Borrar sala");
            System.out.println("[4] Borrar proyector");
            System.out.println("[5] Editar encargado");
            System.out.println("[0] Salir");
            switch(leerInt(0, 5)){
                case 1:
                //validacion
                if(listaSalas.size()>1){
                    imprimirListaSalas();
                    System.out.println("Ingrese sala a aï¿½adir: ");
                    Sala temp = listaSalas.get(leerInt(0, listaSalas.size()));
                    listaSecretarias.get(secretariaIndex).addSala(temp, temp.getNumero());
                }
                else{
                    System.out.println("No hay salas disponibles.");
                }
                break;

                case 2:
                //validacion
                if(listaProyectores.size()>1){
                    imprimirListaProyectores();
                    System.out.println("Ingrese proyector a añadir: ");
                    Proyector temp = listaProyectores.get(leerInt(0, listaProyectores.size()));
                    listaSecretarias.get(secretariaIndex).addProyector(temp);
                }
                else{
                    System.out.println("No hay proyectores disponibles.");
                }
                break;
                case 3:
                if(listaSecretarias.get(secretariaIndex).hasSala()){
                    System.out.println("Lista de salas disponibles para " + listaSecretarias.get(secretariaIndex).getEscuela());
                    listaSecretarias.get(secretariaIndex).getSalasDescription();
                    System.out.println("Ingrese sala a quitar: ");
                    listaSecretarias.get(secretariaIndex).removeSala(leerInt(0, listaSecretarias.get(secretariaIndex).getSalasNum()-1));
                    System.out.print("Sala removida.");
                }
                else{
                    System.out.println("No hay salas disponibles.");
                }
                break;
                case 4:
                if(listaSecretarias.get(secretariaIndex).hasProyectores()){
                    System.out.println("Lista de proyectores disponibles para " + listaSecretarias.get(secretariaIndex).getEscuela());
                    listaSecretarias.get(secretariaIndex).getProyectoresDescription();
                    System.out.println("Ingrese proyector a quitar: ");
                    listaSecretarias.get(secretariaIndex).removeProyector((leerInt(0, listaSecretarias.get(secretariaIndex).getProyectorNum()-1)));
                    System.out.print("Proyector removido.");
                }
                else{
                    System.out.println("No hay salas disponibles.");
                }
                break;
                case 5:
                System.out.println("Ingrese nuevo encargado: ");
                listaSecretarias.get(secretariaIndex).setEncargado(leerString());
                break;
                case 0:
                return;
            }
        }
    }

    private void edicionSecretariaRemove() {
        System.out.println("Menu de delecion de secretaria..");
        borrarSecretaria();
    }

    public void edicionSecretariaAdd() {
        System.out.println("Menu de creacion de secretaria..");
        System.out.println("Ingrese nombre de escuela: ");
        String escuela = leerString();
        System.out.println("Ingrese nombre de encargado de secretaria: ");
        String encargado = leerString();
        Secretaria nueva = new Secretaria(escuela, encargado);
        if(buscarSecretaria(escuela)){
            listaSecretarias.add(nueva);
            graphMark.add(false);
            System.out.println("Secretaria creada existosamente");
        }
        else{
            System.out.println("Datos invalidos.. creacion cancelada.");
            //formatee su pc?XD
        }
    }

    /**
     * mostrarInfoALL(), permide acceder a un menu donde el cual se permite escojer la informacion que se requiere saber
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     * @since 0.1
     */
    public void mostrarInfoALL()
    {
        int i=-1;//variable de inicio del bucle
        String siYeah="no"; //variable auxiliar validadora
        while(i!=0)//inicio del bucle
        {
            System.out.println("[1] Mostrar la lista de Secretarias");
            System.out.println("[2] Mostrar la lista de Salas");
            System.out.println("[0] Salir");
            switch (leerInt(0, 3)){//escojer la opcion correcta al llamar al metodo validador de las opciones
                case 1://opcion1
                if(listaSecretarias.size()>0) //condicion de existencia de secretarias
                {
                    System.out.println("\f"); //refreso de pantalla
                    imprimirListaSecretarias();//se imprimen las secretarias
                    System.out.println("Ingrese el nombre de la Secretaria");//
                    String secreTemp=leerString();     //se inicializa y al mismo tiempo se asigna una entrada llamando al metodo de lectura
                    if(buscarSecretaria(secreTemp)==true)//verificacion de la existencia de la sala buscada
                    {
                        System.out.println("[1] Mostrar las salas asociadas a la Secretaría");
                        System.out.println("[2] Mostrar los proyectores disponibles de la Secretaría");
                        System.out.println("[0] Para salir");
                        siYeah="no";//reasignacion del valor validador
                        switch(leerInt(0,2))//llamado al metodo lector de opciones para una nueva opcion a seguir
                        {   
                            case 1://opcion 1
                            for(Secretaria sec:listaSecretarias)//ciclo que da inicio a la busqueda de la secretaria
                            {
                                if(sec.getEscuela().equals(secreTemp))//momento de econtrar la secretaria
                                { 
                                    sec.getSalasDescription();//imprimir las salas de la secretaria
                                    if(sec.hasSala()==true)//verificacion de la existencia de al menos una sala
                                    {
                                        siYeah="si";//se altera el valor del auxiliar a afirmativo como respuesta a una busqueda exitosa
                                    }
                                }
                            }
                            if(siYeah=="no")//condicion que determina si la secretaria contenia salas
                            {
                                System.out.println("La secretaria no contiene salas");
                            }
                            break;
                            case 2://opcion 2
                            for(Secretaria sec:listaSecretarias)//ciclo de busqueda
                            {
                                if(sec.getEscuela().equals(secreTemp))//comprobacion de la secretaria
                                {
                                    sec.getProyectoresDescription();//momento de imprimir la lista de proyectores de la secretaria
                                    if(sec.hasProyectores()==true)//comprobacion de almenos un proyector
                                    { 
                                        siYeah="si"; //se altera el valor del auxiliar a afirmativo porque se encontro el proyector
                                    }
                                }                                 
                            }
                            if(siYeah=="no")//condicion que determina si la secretaria contenia proyectores
                            {
                                System.out.println("La secretaría no posee proyectores disponibles");
                            }                            
                            break;
                            case 0://se termina el mini-menu
                            break;
                        }
                    }
                    else//condicion en caso de no encontrar una secretaria especifica
                    {
                        System.out.println("No se encontro dicha Secretaria");  
                    }
                }
                else{//condicion en caso de la no existencia de secretarias
                    System.out.println("Aun no existe secretarias creadas");                           
                }
                break;
                case 2:
                if((listaSalas.size()>0))//condicion de existencia de secretarias
                {
                    System.out.println("\f");//refresco de pantalla
                    imprimirListaSalas();//se imprime la lista de salas totales
                    System.out.println("Ingrese el numero de la sala");
                    int number=0;//variable temporal que almacena el numero distintivo de la sala
                    number=leerEntero();//llamado de lectura al metodo que retorna un valor entero
                    if(buscarSala(number)==true)//se comprueba de la existencia de la sala
                    {
                        System.out.println("\f");//refreso de pantalla
                        System.out.println("[1] Mostrar evidencia de un proyector en una sala");
                        System.out.println("[2] Mostrar Secretaria a cargo de la sala");
                        System.out.println("[0] Salir");
                        siYeah="no";//se reinicializa el auxiliar
                        switch(leerInt(0,2))//llamado al metodo de lectura de opciones
                        {
                            case 1://opcion 1                                                     
                            for(Sala sa:listaSalas)//ciclo que da inicio a la busqueda de la sala
                            {
                                if(sa.getNumero()==number)//condicion de buqueda del numero de la sala
                                {
                                    if(sa.hasProyector()==true)//se comprueba de la existencia del proyector
                                    {
                                        System.out.println("La sala posee Un proyector asociado");
                                    }
                                    else//de caso contrario se imprime por pantalla la no existencia del proyector
                                    {
                                        System.out.println("La sala no contiene proyecto alguno");    
                                    }
                                }
                            } 
                            break;
                            case 2:
                            for(Secretaria sec:listaSecretarias)//inicio al ciclo de busqueda de secretarias
                            {
                                if(sec.hasSala(number)==true)//se comprueba la existencia de la sala en la secretaria
                                {
                                    System.out.println("Secretaria a la cual pertenece" + sec.getEscuela());
                                    siYeah="si";//cambio del valor del auxiliar a afirmativo por la  busqueda exitosa de la sala
                                }
                            }                           
                            if(siYeah=="no")//condicion que anuncia que la sala esta solita :(
                            {
                                System.out.println("La sala no esta asociada a ninguna secretaria");                                
                            }
                            break;
                            case 0:
                            break;
                        }
                    }
                    else{//en caso de no existir la sala<-
                        System.out.println("La sala no existe");
                    }
                }
                else{//en caso de que no se hay salas creadas
                    System.out.println("Aun no existen salas");    
                }
                break;
                case 0:
                return;
            }
        }

    }
    /**
     * buscarSala(numeroSala) busca una sala especifica dentro del arraylist de salas existentes
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     */
    public boolean buscarSala(int numeroSala)
    {
        for(Sala sa: listaSalas)
        {
            if(sa.getNumero()==numeroSala==true)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * leerEntero() utiliza el metodo leerString() para poder manipularlo y despues retornar un valor entero
     * 
     * @author Sebastian Valenzuela
     * @version 0.1
     */
    public int leerEntero(){
        int number=0;
        while(true)
        {
            try{
                number=Integer.parseInt(leerString());  
                return number;
            }catch(NumberFormatException e)
            {
                System.err.println("Lo ingresado no fue un numero, profavor intentalo denuevo");
                leerEntero();
            }
        }
    }
}



