import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


@SuppressWarnings("serial")
public class PlanBandaAncha implements Serializable{

	private String nombrePlan;
	private int precioMensual;
	private float tazaSubida;
	private float tazaBajada;
	private boolean wifi;
	
	public String getNombrePlan() {
		return nombrePlan;
	}
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	public PlanBandaAncha(String nombrePlan, int precioMensual,
			float tazaSubida, float tazaBajada, boolean wifi) {
		this.nombrePlan = nombrePlan;
		this.precioMensual = precioMensual;
		this.tazaSubida = tazaSubida;
		this.tazaBajada = tazaBajada;
		this.wifi = wifi;
	}
	public int getPrecioMensual() {
		return precioMensual;
	}
	public void setPrecioMensual(int precioMensual) {
		this.precioMensual = precioMensual;
	}
	public float getTazaSubida() {
		return tazaSubida;
	}
	public void setTazaSubida(float tazaSubida) {
		this.tazaSubida = tazaSubida;
	}
	public float getTazaBajada() {
		return tazaBajada;
	}
	public void setTazaBajada(float tazaBajada) {
		this.tazaBajada = tazaBajada;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	
}

