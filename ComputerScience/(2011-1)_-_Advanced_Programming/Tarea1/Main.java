import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {	
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static ArrayList<AreaFormacion> listaAreasDisponibles = new ArrayList<AreaFormacion>(0);
	public static ArrayList<Curso> listaCursosOcupados = new ArrayList<Curso>(0);
	
	public static void main(String[] args){
		
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>(0);
		String lecturas = null;
		int opcion = -1;
		while(opcion != 0){
			opcion = mostrarMenuCarrera();
			int i = 0;
			switch(opcion){
			case 1:
				try {
					System.out.println("Ingrese el nombre de la Carrera");
					listaCarreras.add(new Carrera(br.readLine()));
				} catch (IOException e) {
					System.err.println("ERROR...las capacidades de E/S no estan funcionando adecuadamente...");
					System.err.println("Ingrese el valor nuevamente... ");
				}
				break;

			case 2:
				if (listaCarreras.size()>0) {
					System.out.println("\tSeleccione Carrera a editar: ");
					for (Carrera carreras : listaCarreras) {
						System.out.println("[" + i + "]" + carreras.getNombre());
						i++;
					}
					System.out.println("\n\tIngrese su opcion... .");
					listaCarreras.get(leerNumero(0, listaCarreras.size()-1)).menuEdicion();
				}
				else{
					System.out.println("\t\tNo hay carreras a editar...");
				}
				break;
				
			case 3:
				if (listaCarreras.size()>0) {
					System.out.println("\tSeleccione Carrera a borrar: ");
					for (Carrera carreras : listaCarreras) {
						System.out
								.println("[" + i + "]" + carreras.getNombre());
						i++;
					}
					System.out.println("\n\tIngrese su opcion... .");
					listaCarreras.remove(Main.leerNumero(0, i - 1));
				}
				else{
					System.out.println("\t\tNo hay carreras a eliminar...");
				}
				break;
				
			case 4:
				listaAreasDisponibles.add(new AreaFormacion());
				break;
			case 5:
				if (listaAreasDisponibles.size()>0) {
					System.out.println("Seleccione Area a editar: ");
					listarAreasDisponibles();
					System.out.println("\n\tIngrese su opcion... .");
					listaAreasDisponibles.get(Main.leerNumero(0, listaAreasDisponibles.size()-1))
							.menuEdicion();
				}
				else{
					System.out.println("\t\tNo hay areas a eliminar...");
				}
			}
		}

	}
	/*
		while (opcion == 0) {
			try {
				// se muestra las opciones
				System.out.println("Menu principal ingerse 0...");

				//se elije la opcion:
				lectura = br.readLine();

				// - se lee la opcion
				opcion = Integer.parseInt(lectura);

				// - se verifica (validacion)
				if (opcion == 0) {
					// -se trabaja de acuerdo a la opcion
					System.out.println("ha ingresado un 0");
				} else {
					System.out.println("ha ingresado otro numero.");
				}
			} catch (IOException e) {
				System.err.println("xuxa!... ocurrio un errror de E/S");
			} catch (NumberFormatException e) {
				System.err.println("xuxa!... ocurrio un errror de que no se ingreso un numero");
			}
			finally{
				System.out.println("VAllejo csm!");
			}
			System.out.println("Regenerando menu...");
		}
		// loop
		 * */

	public static int mostrarMenuCarrera(){
		System.out.println("Menu Principal:");
		System.out.println("\t[1]Agregar Carrera");
		System.out.println("\t[2]Editar Carrera");
		System.out.println("\t[3]Eliminar Carrera");
		System.out.println("\t[4]Agregar Area de formacion -sistema-");
		System.out.println("\t[5]Editar Area de formacion -sistema-");
		System.out.println("\t[0]Salir.");
		System.out.println("\n\t\tIngrese su opcion... .");
		return leerNumero(0, 5);
	}
	
	public static int leerNumero(int min, int max){
		int retorno = -1;
		boolean loop = true;
		while(loop){
			try {
				retorno = Integer.parseInt(br.readLine());
				if(retorno < min || retorno > max){
					throw new NumberFormatException();
				}
				loop=false;
			} 
			catch (NumberFormatException e) {
				System.err.print("ERROR... el numero ingresado no es valido... favor ingrese otro valor... ");
			} 
			catch (IOException e) {
				System.err.println("ERROR...las capacidades de E/S no estan funcionando adecuadamente...");
				System.err.println("Ingrese el valor nuevamente... ");
			}
		}
		return retorno;
	}
	
	public static void listarAreasDisponibles(){
		int i=0;
		for(AreaFormacion area: listaAreasDisponibles){
			System.out.println("\t\t["+i+"]"+area.toString());
			i++;
		}
	}

	public static int areasDisponibles(){
		return listaAreasDisponibles.size();
	}
	
	public static String leerCadena(){
		try {
			return br.readLine();
		} catch (IOException e) {
			System.err.println("ERROR...las capacidades de E/S no estan funcionando adecuadamente...");
			System.err.println("Ingrese el valor nuevamente... ");
		}
		return null;
	}
	
	public static boolean containsCurso(String nombre){
		for(Curso curso: listaCursosOcupados){
			if(curso.getNombre().toLowerCase().trim().equals(nombre.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}
