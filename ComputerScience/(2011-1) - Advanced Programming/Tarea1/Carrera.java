import java.util.ArrayList;


public class Carrera {
	private ArrayList<AreaFormacion> listaAreas;
	private String nombre;

	public Carrera(String nombre) {
		this.listaAreas = new ArrayList<AreaFormacion>();
		this.nombre = nombre;
	}
	public void addArea(AreaFormacion area){
		this.listaAreas.add(area);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void listar(){
		int i=0;
		for(AreaFormacion area: listaAreas){
			System.out.println("["+i+"]"+area.toString());
			i++;
		}
	}

	public void menuEdicion(){
		int retorno = -1;
		boolean loop = true;
		while(loop){
			System.out.println("Menu Carrera:");
			System.out.println("\t[1]Agregar Area de formacion -carrera-");
			System.out.println("\t[2]Eliminar Area de formacion -carrera-");
			System.out.println("\t[0]Salir.");
			System.out.println("\n\t\tIngrese su opcion... .");
			retorno = Main.leerNumero(0, 2);
			switch(retorno){
			case 1:
				if (Main.listaAreasDisponibles.size()>0) {
					System.out.println("Seleccione Area a agregar: ");
					Main.listarAreasDisponibles();
					AreaFormacion temp = Main.listaAreasDisponibles.get(Main
							.leerNumero(0, Main.listaAreasDisponibles.size() - 1));
					if(temp.isValid()){
						this.listaAreas.add(temp);
					}
					else{
						System.out.println("El area de formacion es invalida...");
					}
				}
				else{
					System.out.println("\t\tNo hay areas disponibles...");
				}
				break;

			case 2:
				if (this.listaAreas.size()>0) {
				System.out.println("Seleccione Area a eliminar: ");
				int i=0;
				for(AreaFormacion area: listaAreas){
					System.out.println("["+i+"]"+area.toString());
					i++;
				}
				this.listaAreas.remove(Main.leerNumero(0, i-1));
				}
				else{
					System.out.println("\t\tNo hay areas disponibles...");
				}
				break;
			case 0:
				loop = false;
			}
		}
	}
}
