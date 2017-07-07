import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Contrato implements Serializable{

	private String ejecutivoResponsable;
	private String fechaInicio;
	private ArrayList<PlanBandaAncha> serviciosContratados;
	private Cliente cliente;
	private int correlativo;
	
	public Contrato(String ejecutivoResponsable, String fechaInicio
			, Cliente cliente, int correlativo) {
		super();
		this.ejecutivoResponsable = ejecutivoResponsable;
		this.fechaInicio = fechaInicio;
		this.cliente = cliente;
		this.correlativo = correlativo;
		this.serviciosContratados = new ArrayList<PlanBandaAncha>();
	}
	
	public void addServicio(PlanBandaAncha plan){
		this.serviciosContratados.add(plan);
	}
	
	public String getEjecutivoResponsable() {
		return ejecutivoResponsable;
	}

	public void setEjecutivoResponsable(String ejecutivoResponsable) {
		this.ejecutivoResponsable = ejecutivoResponsable;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public ArrayList<PlanBandaAncha> getServiciosContratados() {
		return serviciosContratados;
	}

	public void setServiciosContratados(ArrayList<PlanBandaAncha> serviciosContratados) {
		this.serviciosContratados = serviciosContratados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
}
