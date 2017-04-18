
import java.util.*;

public class Vendedor
{
    private ArrayList espectaculos = new ArrayList();
    private ArrayList clientes = new ArrayList();
    
    public Vendedor()
    {
        simInicio();
    }
    
    /*Llena las listas para que no esten vacias al principio*/
    private void simInicio()
    {
        Espectaculo espec = new Espectaculo("Secico", 15000);
        espectaculos.add(espec);
        espec = new Espectaculo ("Temuco en llamas", 20000);
        espectaculos.add(espec);
        Cliente clie = new Cliente("19", "Tito Fernandez");
        clientes.add(clie);
        clie = new Cliente("27", "Guido Meza");
        clientes.add(clie);
    }
    
    public ArrayList listadoClientes()
    {
        return clientes;
    }
    
    public ArrayList listadoEspectaculos()
    {
        return espectaculos;
    }
    
    public void agregarEspectaculo(Espectaculo newEspec)
    {
        espectaculos.add(newEspec);
    }
    public void agregarCliente(Cliente newClie)
    {
        clientes.add(newClie);
    }
}
