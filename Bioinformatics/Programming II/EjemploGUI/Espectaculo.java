
public class Espectaculo
{
    private String nombre;
    private double precioEntrada;
    
    public Espectaculo(String nombre, double precioEntrada)
    {
        this.nombre = nombre;
        this.precioEntrada = precioEntrada;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
}
