import java.util.ArrayList;


public class AreaFormacion {

	private ArrayList<Curso> listaCursos;

	public static final int CIENCIAS_BASICAS = 1;
	public static final int CIENCIAS_INGIENERIA = 2;
	public static final int FORMACION_FUNDAMENTAL = 3;
	public static final int CIENCIAS_COMPUTACION = 4; 
	
	private int area;
	

	public AreaFormacion() {
		this.listaCursos = new ArrayList<Curso>(0);
		System.out.println("Categorias Disponibles:");
		System.out.println("\t[1] Ciencias Basicas");
		System.out.println("\t[2] Ciencias de Ingieneria");
		System.out.println("\t[3] Ciencias de Computacion");
		System.out.println("\t[4] Formacion Fundamental");
		System.out.println("\t\tIngrese categoria... ");
		this.area = Main.leerNumero(1, 4);
	}
	
	public void addCurso(Curso curso){
		this.listaCursos.add(curso);
	}
	
	public void menuEdicion(){
		int retorno = -1;
		boolean loop = true;
		Curso cursoTemp = null;
		while(loop){
			System.out.println("Edicion Area de formacion:");
			System.out.println("\t[1]Agregar curso a Area de formacion");
			System.out.println("\t[2]Eliminar curso a Area de formacion");
			System.out.println("\t[0]Salir.");
			System.out.println("\n\t\tIngrese su opcion... .");
			switch(Main.leerNumero(0, 2)){
			case 1:
				cursoTemp = new Curso();
				if(Main.containsCurso(cursoTemp.getNombre())){
					System.out.println("El curso ya existe.");
				}
				else{
					this.listaCursos.add(cursoTemp);
				}
				break;
			case 2:
				if (this.listaCursos.size()>0) {
					System.out.println("\tIngrese curso a eliminar:");
					this.listarCursos();
					System.out.println("\n\t\tIngrese su opcion:");
					Main.listaCursosOcupados.remove(this.listaCursos.remove(Main.leerNumero(0,
							this.listaCursos.size()-1)));
				}
				else{
					System.out.println("\t\tNo hay cursos a eliminar...");
				}
				break;
			case 0:
				loop = false;
			}
		}
	}

	public boolean isValid(){
		if(this.area == AreaFormacion.CIENCIAS_BASICAS && this.listaCursos.size()<10){
			return false;
		}
		if(this.area == AreaFormacion.CIENCIAS_COMPUTACION && this.listaCursos.size()<15){
			return false;
		}
		if(this.area == AreaFormacion.FORMACION_FUNDAMENTAL && this.listaCursos.size()<5){
			return false;
		}
		if(this.area == AreaFormacion.CIENCIAS_INGIENERIA && this.listaCursos.size()<10){
			return false;
		}
		return true;
	}

	public String toString(){
		switch(this.area){
		case AreaFormacion.CIENCIAS_BASICAS:
			return("Ciencias Basicas");
		case AreaFormacion.CIENCIAS_COMPUTACION:
			return("Ciencias de Computacion");
		case AreaFormacion.CIENCIAS_INGIENERIA:
			return("Ciencias de Ingieneria");
		case AreaFormacion.FORMACION_FUNDAMENTAL:
			return("Formacion Fundamental");
		}
		return null;
	}
	
	public boolean listar(){
		System.out.print("\tArea formacion: ");
		switch(this.area){
		case AreaFormacion.CIENCIAS_BASICAS:
			System.out.println("Ciencias Basicas");
			break;
		case AreaFormacion.CIENCIAS_COMPUTACION:
			System.out.println("Ciencias de Computacion");
			break;
		case AreaFormacion.CIENCIAS_INGIENERIA:
			System.out.println("Ciencias de Ingieneria");
			break;
		case AreaFormacion.FORMACION_FUNDAMENTAL:
			System.out.println("Formacion Fundamental");
			break;
		}
		System.out.println("\tCursos Comprendidos");
		if(this.listaCursos.size()==0){
			System.out.println("\t\tEsta area no comprende ningun curso");
			return false;
		}
		
		return this.listarCursos();
	}
	
	public boolean listarCursos(){
		boolean retorno = false;
		for(Curso curso: this.listaCursos){
			System.out.println("\t\t"+curso.getNombre());
			retorno = true;
		}
		return retorno;
	}
	
	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

}
