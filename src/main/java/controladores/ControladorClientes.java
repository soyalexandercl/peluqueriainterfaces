package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import vistas.VistaClientes;

public class ControladorClientes implements ActionListener {
    
    private VistaClientes vista;
    private ClienteDAO modeloDAO;

    public ControladorClientes(VistaClientes vista) {
        this.vista = vista;
        this.modeloDAO = new ClienteDAO();
        
        // Escuchar los botones de la vista
        this.vista.botonAgregar.addActionListener(this);
        this.vista.botonEliminar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        // Cargar datos iniciales
        cargarTabla();
    }

    // Método para rellenar la tabla con datos de la BD
    private void cargarTabla() {
        // Limpiar tabla actual
        vista.modeloTabla.setRowCount(0);
        
        // Obtener lista de clientes de la BD
        List<Cliente> lista = modeloDAO.listarClientes();
        
        // Añadir filas
        for (Cliente c : lista) {
            Object[] fila = {
                c.getId(),
                c.getNombre(),
                c.getApellidos(),
                c.getTelefono(),
                c.isEsVip() ? "Sí" : "No"
            };
            vista.modeloTabla.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonRefrescar) {
            cargarTabla();
        }
        else if (e.getSource() == vista.botonAgregar) {
            // Aquí idealmente abrirías un JDialog (ventana modal) para pedir datos.
            // Por simplicidad ahora, simulo una inserción:
            Cliente nuevo = new Cliente(0, "Nuevo", "Cliente", "600000000", false);
            if(modeloDAO.insertarCliente(nuevo)) {
                JOptionPane.showMessageDialog(vista, "Cliente agregado correctamente");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar cliente");
            }
        }
        else if (e.getSource() == vista.botonEliminar) {
            int filaSeleccionada = vista.tablaClientes.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un cliente primero.");
                return;
            }
            // Lógica de eliminar llamando al DAO...
            JOptionPane.showMessageDialog(vista, "Funcionalidad de eliminar pendiente de implementar en DAO");
        }
    }
}