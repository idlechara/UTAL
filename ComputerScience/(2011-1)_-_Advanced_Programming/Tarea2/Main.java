import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * eso... una clase main creada de forma absurda, ya que todo el trabajo que hice en la micro no sirvio para nada..xD
 * @author Kynku-nekoi
 *
 */
public class Main {

	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	public static int numeroContratos = 0;
	
	public static void main(String[] args) throws Exception {

		//inicializacion

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<PlanBandaAncha> planes = new ArrayList<PlanBandaAncha>();
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();

		//switch de opciones
		String input = null;
		int opcion = 1;
		//entra al ciclo primario (ojo, profe no confunda con lotto.. ¬.¬)
		while((opcion!=-1)&&(opcion!=0)){
			Main.imprimeMenu();
			try{
				opcion = Integer.parseInt(Main.br.readLine());

				int i = 0;
				switch(opcion){

				//añade un cliente
				case 1:
					clientes.add(Main.leeCliente());
					break;

					//borra un cliente
				case 2:
					//enlista los clientes
					for(Cliente cliente: clientes){
						System.out.print("[" + i +"] ");
						System.out.println(cliente.getNombres() + " " +
								cliente.getApellidoPaterno() + " " +
								cliente.getApellidoMaterno());
						i++;
					}

					//borrado
					if(clientes.size()>1){
						i = Integer.parseInt(br.readLine());
						clientes.remove(i);
						System.out.println("Cliente ["+i+"] borrado!");
					}
					if(clientes.size()==1){
						clientes.remove(0);
						System.out.println("Cliente [0] borrado!");
					}
					if(clientes.size()<1){
						System.err.println("Error... no hay clientes registrados.");
					}
					break;
					
					//modifica un cliente
				case 3:
					//enlista los clientes
					for(Cliente cliente: clientes){
						System.out.print("[" + i +"] ");
						System.out.println(cliente.getNombres() + " " +
								cliente.getApellidoPaterno() + " " +
								cliente.getApellidoMaterno());
						i++;
					}

					//edicion
					if(clientes.size()>1){
						i = Integer.parseInt(br.readLine());
						Main.modificaCliente(clientes.get(i));
						System.out.println("Cliente ["+i+"] editado!");
					}
					if(clientes.size()==1){
						Main.modificaCliente(clientes.get(0));
						System.out.println("Cliente [0] editado!");
					}
					if(clientes.size()<1){
						System.err.println("Error... no hay clientes registrados.");
					}
					break;
					
					//crear plan
				case 4:
					planes.add(Main.leePlan());
					break;
					
					//eliminar un plan
				case 5:
					//enlista los planes
					for(PlanBandaAncha plan: planes){
						System.out.print("[" + i +"] ");
						System.out.println(plan.getNombrePlan());
						i++;
					}

					//borrado
					if(planes.size()>1){
						i = Integer.parseInt(br.readLine());
						planes.remove(i);
						System.out.println("Plan ["+i+"] borrado!");
					}
					if(planes.size()==1){
						planes.remove(0);
						System.out.println("Plan [0] borrado!");
					}
					if(planes.size()<1){
						System.err.println("Error... no hay planes registrados");
					}
					break;
					
					//modifica un plan.
				case 6:
					//enlista los planes
					for(PlanBandaAncha plan: planes){
						System.out.print("[" + i +"] ");
						System.out.println(plan.getNombrePlan());
						i++;
					}

					//borrado
					if(planes.size()>1){
						i = Integer.parseInt(br.readLine());
						Main.modificaPlan(planes.get(i));
						System.out.println("Plan ["+i+"] modificado!");
					}
					if(planes.size()==1){
						Main.modificaPlan(planes.get(0));
						System.out.println("Plan [0] modificado!");
					}
					if(planes.size()<1){
						System.err.println("Error... no hay planes registrados");
					}
					break;
					
					//crea un contrato
				case 7:
					contratos.add(Main.leeContrato(clientes, planes));
					break;
					
					//elimina un contrato 
				case 8:
					//enlista los contratos
					for(Contrato contrato: contratos){
						System.out.print("[" + i +"] ");
						System.out.println(contrato.getCorrelativo());
						i++;
					}

					//borrado
					if(contratos.size()>1){
						i = Integer.parseInt(br.readLine());
						contratos.remove(i);
						System.out.println("Contrato ["+i+"] borrado!");
					}
					if(contratos.size()==1){
						contratos.remove(0);
						System.out.println("Contrato [0] borrado!");
					}
					if(contratos.size()<1){
						System.err.println("Error... no hay contratos registrados");
					}
					break;
					
					//guardar en txt..?
					//TODO
					//implementar... sitema de guardado en texto..xD
					//uso:
					/**
					 * primero listar, convertir a xml, luego guardar.
					 * 
					 * como arraylist?
					 */
				case 9:
					
					System.out.println("Ingrese ruta/fichero de salida: ");
					
					String output = br.readLine();
					
					FileOutputStream f_out = new FileOutputStream(output);
					ObjectOutputStream objeto_salida = new ObjectOutputStream (f_out);
					objeto_salida.writeObject(clientes);
					objeto_salida.close();
					f_out.close();
					
					break;
					//y rescatar los archivos de clientes.... ufff..xD
					// OJO: que decia solo clientes..xD
				case 10:

					System.out.println("Ingrese ruta de fichero de entrada: ");
					input = br.readLine();
					FileInputStream f_in = new FileInputStream(input);
					ObjectInputStream obj_in = new ObjectInputStream (f_in);
					Object obj = obj_in.readObject();
					@SuppressWarnings("unchecked")
					ArrayList<Cliente> obj2 = (ArrayList<Cliente>)obj;
					clientes = obj2;
					break;
					//fin de la seleccion de casos de uso!... uff xD
					
				}
				
			}
			catch(NumberFormatException e){
				System.err.println("Error en la opcion ingresada, intente nuevamente...");
			}
			catch(IOException e){
				System.err.println("Error de E/S. Ejecucion Terminada");
			}
		}
	}
	
	public static void imprimeMenu(){
		System.out.println("Menu de inicio: ");
		System.out.println("\t1. Agregar un cliente.\n"+
				"\t2. Eliminar un cliente.\n"+
				"\t3. Editar la informacion de un cliente ya registrado.\n"+
				"\t4. Crear planes de Banda Ancha.\n"+
				"\t5. Eliminar planes de Banda Ancha.\n"+
				"\t6. Editar la informacion de los planes de Banda Ancha.\n"+
				"\t7. Crear un contrato.\n"+
				"\t8. Eliminar un contrato ya creado.\n"+
				"\t9. Guardar los datos de los clientes en un archivo de texto.\n"+
				"\t10. Cargar los datos de los clientes desde un archivo.\n"+
				"\t0. Salir.\n"+
		"\n \t\t Ingrese su opcion: ");
	}
	
	public static Cliente leeCliente() throws IOException{
		String rut, nombres, apellidoPaterno, apellidoMaterno, direccion, ciudad, email;
		System.out.print("Rut: ");
		rut = br.readLine();
		System.out.print("Nombre: ");
		nombres = br.readLine();
		System.out.print("Apellido Paterno: ");
		apellidoPaterno = br.readLine();
		System.out.print("Apellido Materno: ");
		apellidoMaterno = br.readLine();
		System.out.print("Direccion: ");
		direccion = br.readLine();
		System.out.print("Ciudad: ");
		ciudad = br.readLine();
		System.out.print("Correo: ");
		email = br.readLine();
		
		return (new Cliente(rut, nombres, apellidoPaterno, apellidoMaterno, direccion,ciudad, email));
	}
	
	public static void modificaCliente(Cliente cliente) throws IOException{
		String temp;
		//inicio de edicion:
		System.out.println("Editando cliente " + cliente.getNombres()+ "[presione [ENTER] para omitir campo:]");
		
		//edicion de campos::
		System.out.println("Nombre ("+cliente.getNombres()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setNombres(temp);
		}
		
		System.out.println("Apellido Paterno ("+cliente.getApellidoPaterno()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setApellidoPaterno(temp);
		}
		
		System.out.println("Apellido Materno ("+cliente.getApellidoMaterno()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setApellidoMaterno(temp);
		}

		System.out.println("Direccion ("+cliente.getDireccion()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setDireccion(temp);
		}

		System.out.println("Ciudad ("+cliente.getCiudad()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setCiudad(temp);
		}
		

		System.out.println("E-mail ("+cliente.getEmail()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			cliente.setEmail(temp);
		}
		//fin de ediciones
	}

	public static PlanBandaAncha leePlan() throws IOException{
		String nombrePlan = null;
		int precioMensual = 0;
		float tazaSubida = 0, tazaBajada = 0;
		Boolean wifi = null;
		boolean loop = true;


		while(loop){
			System.out.print("Nombre Plan: ");
			
				nombrePlan = br.readLine();
				if((nombrePlan.toLowerCase().equals("plan mega6"))||
						(nombrePlan.toLowerCase().equals("plan mega15"))||
						(nombrePlan.toLowerCase().equals("plan mega30"))){
					loop = false;
				}
			
		}
		
		loop = true;
		while(loop){
			System.out.print("Precio Mensual: ");
			
			try {
				precioMensual = Integer.parseInt(br.readLine());
				if(precioMensual<0){
					throw new NumberFormatException();
				}
				loop = false;
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un valor valido...");
			}
			
		}
		
		loop = true;
		while(loop){
			System.out.print("Taza Subida: ");
			
			try {
				tazaSubida = Float.parseFloat(br.readLine());
				if(tazaSubida<0){
					throw new NumberFormatException();
				}
				loop = false;
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un valor valido...");
			}
			
		}		
		
		loop = true;
		while(loop){
			System.out.print("Taza Bajada: ");
			
			try {
				tazaBajada = Float.parseFloat(br.readLine());
				if(tazaBajada<0){
					throw new NumberFormatException();
				}
				loop = false;
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un valor valido...");
			}
			
		}
		
		loop = true;
		String temp;
		while(loop){
			System.out.print("Tiene wifi?: ");
			temp = (br.readLine());

			if(temp.equals("si")){
				wifi = true;
				loop = false;
			}
			else{
				if(temp.equals("no")){
					wifi = true;
					loop = false;
				}
				else{
					System.out.println("Ingrese una opcion valida (si/no)");
				}
			}

		}

		
		return (new PlanBandaAncha(nombrePlan, precioMensual, tazaSubida, tazaBajada, wifi));
	}

	private static void modificaPlan(PlanBandaAncha plan) throws IOException {
		String temp;
		//inicio de edicion:
		System.out.println("Editando plan " + plan.getNombrePlan()+ "[presione [ENTER] para omitir campo:]");
		
		//edicion de campos::
		System.out.println("Nombre plan ("+plan.getNombrePlan()+"): ");
		temp = br.readLine();
		if(!(temp.equals("\n")||temp==null)){
			plan.setNombrePlan(temp);
		}
		
		boolean loop = true;
		int temp1;

		while(loop){
			System.out.println("Precio Mensual ("+plan.getPrecioMensual()+"): ");
			temp = br.readLine();
			if(!(temp.equals("\n")||temp==null)){
				try{
					temp1 = Integer.parseInt(temp);
					plan.setPrecioMensual(temp1);
					loop = false;
				}
				catch(NumberFormatException e){
					System.err.println("Error... Ingrese un valor valido...");
				}
			}
		}

		loop=true;
		float temp3;
		while(loop){
			System.out.println("Taza bajada ("+plan.getTazaBajada()+"): ");
			temp = br.readLine();
			if(!(temp.equals("\n")||temp==null)){
				try{
					temp3 = Float.parseFloat(temp);
					plan.setTazaBajada(temp3);
					loop = false;
				}
				catch(NumberFormatException e){
					System.err.println("Error... Ingrese un valor valido...");
				}
			}
		}

		loop=true;
		while(loop){
			System.out.println("Taza subida ("+plan.getTazaSubida()+"): ");
			temp = br.readLine();
			if(!(temp.equals("\n")||temp==null)){
				try{
					temp3 = Float.parseFloat(temp);
					plan.setTazaSubida(temp3);
					loop = false;
				}
				catch(NumberFormatException e){
					System.err.println("Error... Ingrese un valor valido...");
				}
			}
		}

		loop=true;
		while(loop){
			System.out.println("Tiene wifi? ("+plan.getTazaSubida()+"): ");
			temp = br.readLine();
			if(!(temp.equals("\n")||temp==null)){
				if(temp.equals("si")){
					plan.setWifi(true);
					loop = false;
				}
				else{
					if(temp.equals("no")){
						plan.setWifi(false);
						loop = false;
					}
					else{
						System.err.println("Error, ingrese un valor valido (si/no)");
					}
				}
			}
		}

	}

	//TODO el contrato.
	private static Contrato leeContrato(ArrayList<Cliente> clientes, ArrayList<PlanBandaAncha> planes) throws Exception {
		
		if((clientes.size()<1)||(planes.size()<1)){
			throw new Exception("Ingreso invalido...");
		}
		
		String ejecutivoResponsable, fechaInicio;
		Cliente cliente_asosicado;
		int correlativo;
		
		//lectura de datos primaria
		System.out.println("Ingrese Ejecutivo responsable: ");
		ejecutivoResponsable = br.readLine();
		int i = 0;
		System.out.println("Ingrese cliente a asociar al contrato:");
		
		
		for(Cliente cliente: clientes){
			System.out.print("[" + i +"] ");
			System.out.println(cliente.getNombres() + " " +
					cliente.getApellidoPaterno() + " " +
					cliente.getApellidoMaterno());
			i++;
		}

		i = Integer.parseInt(br.readLine());
		cliente_asosicado = clientes.get(i);
		
		fechaInicio = Calendar.getInstance().DAY_OF_MONTH + "/" +
		Calendar.getInstance().MONTH + "/" + 
		Calendar.getInstance().YEAR;
		
		correlativo = numeroContratos++;
		
		Contrato keioku = new Contrato(ejecutivoResponsable, fechaInicio, cliente_asosicado, correlativo);
		
		
		
		boolean loop = true;
		int temp1;
		String temp;

		while(loop){
			System.out.println("Ingrese contrato a añadir <ingrese [fin], para terminar>");
			i=0;
			for(PlanBandaAncha plan: planes){
				System.out.print("[" + i +"] ");
				System.out.println(plan.getNombrePlan());
				i++;
			}
			
			temp = br.readLine();
			if(!temp.equals("")){
				try{
					temp1 = Integer.parseInt(temp);
					if((temp1<planes.size())&&(temp1>=0)){
						PlanBandaAncha planTemp = planes.get(temp1);
						System.out.println(planTemp);
						keioku.addServicio(planTemp);
					}
					else{
						System.out.println("temp1: "+ temp1);
						System.out.println("temp: "+ temp);
						throw new NumberFormatException();
					}
				}
				catch(NumberFormatException e){
					System.out.println("temp: "+ temp);
					System.err.println("Error... Ingrese un valor valido...");
				}
			}
			else{
				loop = false;
			}
		}
		return (keioku);
	}

}