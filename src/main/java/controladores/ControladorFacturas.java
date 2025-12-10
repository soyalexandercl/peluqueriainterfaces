package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Factura;
import modelo.FacturaDAO;
import vistas.VistaFacturas;

public class ControladorFacturas implements ActionListener {
    
    private VistaFacturas vista;
    private FacturaDAO facturaDAO;

    public ControladorFacturas(VistaFacturas vista) {
        this.vista = vista;
        this.facturaDAO = new FacturaDAO();
        
        this.vista.botonGenerar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        List<Factura> lista = facturaDAO.listarFacturas();
        
        // Variables para calcular total (un extra visual)
        double totalIngresos = 0;
        
        for (Factura f : lista) {
            Object[] fila = {
                f.getId(),
                f.getIdCita(),
                f.getNombreCliente(),
                String.format("%.2f €", f.getImporte()),
                f.getMetodoPago(),
                f.getFecha()
            };
            vista.modeloTabla.addRow(fila);
            totalIngresos += f.getImporte();
        }
        // Podríamos poner el total en algún label, pero por ahora está bien así.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonRefrescar) {
            cargarTabla();
        }
        else if (e.getSource() == vista.botonGenerar) {
            try {
                String idCitaStr = JOptionPane.showInputDialog(vista, "Introduce el ID de la Cita a facturar:");
                if (idCitaStr == null) return;
                int idCita = Integer.parseInt(idCitaStr);
                
                String[] opcionesPago = {"Efectivo", "Tarjeta", "Transferencia"};
                int seleccion = JOptionPane.showOptionDialog(vista, "Método de pago:", "Cobrar",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesPago, opcionesPago[0]);
                
                if (seleccion >= 0) {
                    String metodo = opcionesPago[seleccion];
                    if (facturaDAO.generarFactura(idCita, metodo)) {
                        JOptionPane.showMessageDialog(vista, "Factura generada y cita cerrada.");
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error: Verifica que el ID de la cita exista.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El ID debe ser un número.");
            }
        }
    }
}