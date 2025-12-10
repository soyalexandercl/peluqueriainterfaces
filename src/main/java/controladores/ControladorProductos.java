package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.ProveedorDAO;
import vistas.VistaProductos;

public class ControladorProductos implements ActionListener {
    
    private VistaProductos vista;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;

    public ControladorProductos(VistaProductos vista) {
        this.vista = vista;
        this.productoDAO = new ProductoDAO();
        this.proveedorDAO = new ProveedorDAO();
        
        this.vista.botonAgregar.addActionListener(this);
        this.vista.botonEliminar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        List<Producto> lista = productoDAO.listarProductos();
        for (Producto p : lista) {
            Object[] fila = {
                p.getId(),
                p.getNombre(),
                p.getNombreProveedor(), // ¡Muestra el nombre del proveedor!
                p.getStock() + " / " + p.getStockMaximo(),
                String.format("%.2f €", p.getPrecio())
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
            // Lógica inteligente: Obtenemos un ID de proveedor válido automáticamente
            int idProv = proveedorDAO.obtenerOInsertarProveedorDefecto();
            
            if (idProv != -1) {
                // Simulamos la entrada de datos
                Producto nuevo = new Producto(0, idProv, "Champú Premium", 50, 100, 12.50);
                if (productoDAO.insertarProducto(nuevo)) {
                    JOptionPane.showMessageDialog(vista, "Producto añadido (Vinculado a proveedor por defecto)");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al insertar producto.");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Error crítico: No se pudo gestionar el proveedor.");
            }
        }
        else if (e.getSource() == vista.botonEliminar) {
            int fila = vista.tablaProductos.getSelectedRow();
            if (fila == -1) return;
            
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if(productoDAO.eliminarProducto(id)) {
                cargarTabla();
            }
        }
    }
}