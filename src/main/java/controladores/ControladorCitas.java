package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cita;
import modelo.CitaDAO;
import vistas.VistaCitas;

public class ControladorCitas implements ActionListener {
    
    private VistaCitas vista;
    private CitaDAO citaDAO;

    public ControladorCitas(VistaCitas vista) {
        this.vista = vista;
        this.citaDAO = new CitaDAO();
        
        this.vista.botonNuevaCita.addActionListener(this);
        this.vista.botonCompletar.addActionListener(this);
        this.vista.botonCancelar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        List<Cita> lista = citaDAO.listarCitas();
        for (Cita c : lista) {
            Object[] fila = {
                c.getId(),
                c.getNombreCliente(),
                String.format("%.2f €", c.getPrecioTotal()),
                c.getEstado(),
                c.getFecha()
            };
            vista.modeloTabla.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonRefrescar) {
            cargarTabla();
        }
        else if (e.getSource() == vista.botonNuevaCita) {
            // Flujo simplificado: Pedir IDs por ventana emergente
            // Nota: En el futuro esto debería ser un panel con ComboBoxes que cargan los nombres
            try {
                String idCliStr = JOptionPane.showInputDialog(vista, "Introduce ID del Cliente:");
                if (idCliStr == null) return;
                int idCliente = Integer.parseInt(idCliStr);

                String idServStr = JOptionPane.showInputDialog(vista, "Introduce ID del Servicio:");
                if (idServStr == null) return;
                int idServicio = Integer.parseInt(idServStr);

                String idPeluStr = JOptionPane.showInputDialog(vista, "Introduce ID de la Peluquera:");
                if (idPeluStr == null) return;
                int idPeluquera = Integer.parseInt(idPeluStr);

                if (citaDAO.crearCita(idCliente, idServicio, idPeluquera)) {
                    JOptionPane.showMessageDialog(vista, "¡Cita creada con éxito!");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al crear cita. Verifica que los IDs existan.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Error: Debes introducir números válidos.");
            }
        }
        else if (e.getSource() == vista.botonCompletar || e.getSource() == vista.botonCancelar) {
            int fila = vista.tablaCitas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Selecciona una cita.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            
            String nuevoEstado = (e.getSource() == vista.botonCompletar) ? "Completada" : "Cancelada";
            
            if (citaDAO.actualizarEstado(id, nuevoEstado)) {
                cargarTabla();
            }
        }
    }
}