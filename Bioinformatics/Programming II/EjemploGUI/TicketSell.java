
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TicketSell implements ActionListener
{
    JFrame ventana;
    JComboBox cboClientes;
    JComboBox cboEspectaculos;
    JPanel pnlNuevoCliente;
    JLabel lblNomCliente;
    JLabel lblRutCliente;
    JTextField nomCliente;
    JTextField rutCliente;
    JButton cmdAgregarCliente;
    Vendedor vendedor;
    
    public TicketSell()
    {
        vendedor = new Vendedor();
        ventana = new JFrame("TicketSell");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cboClientes = new JComboBox();
        cboClientes.addItem("Seleccione cliente");
        cboEspectaculos = new JComboBox();
        cboEspectaculos.addItem("Seleccione espectaculo");
        this.llenarClientes();
        this.llenarEspectaculos();
        
        lblNomCliente = new JLabel("Nombre");
        lblRutCliente = new JLabel("RUT");
        nomCliente = new JTextField();
        rutCliente = new JTextField();
        cmdAgregarCliente = new JButton("Agregar cliente");
        pnlNuevoCliente = new JPanel(new GridLayout(3,2));
        pnlNuevoCliente.add(lblNomCliente);
        pnlNuevoCliente.add(nomCliente);
        pnlNuevoCliente.add(lblRutCliente);
        pnlNuevoCliente.add(rutCliente);
        pnlNuevoCliente.add(cmdAgregarCliente);
        
        ventana.getContentPane().setLayout(new BorderLayout());
        ventana.getContentPane().add(cboClientes, BorderLayout.WEST);
        ventana.getContentPane().add(cboEspectaculos, BorderLayout.CENTER);
        ventana.getContentPane().add(pnlNuevoCliente, BorderLayout.EAST);
        
        cmdAgregarCliente.addActionListener(this);
        
        ventana.pack();
        ventana.setVisible(true);
    }
    
    public void generarCliente()
    {
        String nombreNewCliente;
        String rutNewCliente;
        nombreNewCliente = nomCliente.getText();
        rutNewCliente = rutCliente.getText();
        Cliente newCliente = new Cliente(rutNewCliente, nombreNewCliente);
        vendedor.agregarCliente(newCliente);
    }
    
    public void agregarCliente(Cliente cliente)
    {
        vendedor.agregarCliente(cliente);
    }
    /* llenarClientes:
     * Pide la lista de clientes de Vendedor y la utiliza para llenar
     * el ComboBox con los clientes que hay en el arreglo.
     */
    public void llenarClientes()
    {
        ArrayList listaClientes = vendedor.listadoClientes();
        String nomCliente;
        for (int i = 0; i < listaClientes.size(); i++)
        {
            nomCliente = ((Cliente)listaClientes.get(i)).getNombre();
            cboClientes.addItem(nomCliente);
        }
    }
    
    public void vaciarClientes()
    {
        int cantidadElementos = cboClientes.getItemCount();
        for (int i = 0; i < cantidadElementos; i++)
        {
            cboClientes.removeItemAt(0);
        }
    }
    
    /* llenarEspectaculos:
     * Pide la lista de espectaculos de Vendedor y la utiliza para llenar
     * el ComboBox con los espectaculos que hay en el arreglo.
     */
    public void llenarEspectaculos()
    {
        ArrayList listaEspectaculos = vendedor.listadoEspectaculos();
        String nomEspectaculo;
        for (int i = 0; i < listaEspectaculos.size(); i++)
        {
            nomEspectaculo = ((Espectaculo)listaEspectaculos.get(i)).getNombre();
            cboEspectaculos.addItem(nomEspectaculo);
        }
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        generarCliente();
        vaciarClientes();
        cboClientes.addItem("Seleccione cliente");
        llenarClientes();
        nomCliente.setText("");
        rutCliente.setText("");
    }
}