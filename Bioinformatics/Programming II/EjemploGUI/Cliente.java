
public class Cliente
{
	protected String rut;
	protected String nombre;
	protected int entradas;
	
	public Cliente(String rut, String nombre)
	{
		this.rut = rut;
		this.nombre = nombre;
		this.entradas = 0;
	}
	
	public double calcCostoEntrada(int cantEntradas, double precioEntrada)
	{
	    double precioTotal = cantEntradas * precioEntrada;
	    return precioTotal;
	}
	
	public void agregaEntradas (int cantEntradas)
	{
	    this.entradas = this.entradas + cantEntradas;
	}
	
	public String getNombre()
	{
	    return this.nombre;
	}
}
