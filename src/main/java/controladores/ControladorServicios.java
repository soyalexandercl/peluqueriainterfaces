package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import modelo.Servicio;
import modelo.ServicioDAO;
import vistas.VistaServicios;

public class ControladorServicios implements ActionListener {
    
    private VistaServicios vista;
    private ServicioDAO servicioDAO;
    private ProductoDAO productoDAO;

    public ControladorServicios(VistaServicios vista) {
        this.vista = vista;
        this.servicioDAO = new ServicioDAO();
        this.productoDAO = new ProductoDAO();
        
        this.vista.botonAgregar.addActionListener(this);
        this.vista.botonEliminar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        List<Servicio> lista = servicioDAO.listarServicios();
        for (Servicio s : lista) {
            Object[] fila = {
                s.getId(),
                s.getNombre(),
                String.format("%.2f €", s.getPrecio()),
                s.getDuracion(), // Muestra HH:MM:SS
                s.getNombreProducto()
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
            // Buscamos un producto existente para asignarlo
            int idProd = productoDAO.obtenerPrimerProducto();
            
            if (idProd != -1) {
                // Crear un servicio de prueba: Corte de Pelo, 15 euros, 30 minutos
                Servicio nuevo = new Servicio(0, "Corte Estándar", 15.00, Time.valueOf("00:30:00"), idProd);
                
                if (servicioDAO.insertarServicio(nuevo)) {
                    JOptionPane.showMessageDialog(vista, "Servicio creado correctamente.");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al crear servicio.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Error: No hay productos registrados. Crea un producto primero.");
            }
        }
        else if (e.getSource() == vista.botonEliminar) {
            int fila = vista.tablaServicios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Selecciona un servicio.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (servicioDAO.eliminarServicio(id)) {
                cargarTabla();
            }
        }
    }
}