package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Peluquera;
import modelo.PeluqueraDAO;
import vistas.VistaPeluqueras;

public class ControladorPeluqueras implements ActionListener {
    
    private VistaPeluqueras vista;
    private PeluqueraDAO modeloDAO;

    public ControladorPeluqueras(VistaPeluqueras vista) {
        this.vista = vista;
        this.modeloDAO = new PeluqueraDAO();
        
        // Listeners
        this.vista.botonAgregar.addActionListener(this);
        this.vista.botonEliminar.addActionListener(this);
        this.vista.botonRefrescar.addActionListener(this);
        
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0); // Limpiar
        List<Peluquera> lista = modeloDAO.listarPeluqueras();
        
        for (Peluquera p : lista) {
            Object[] fila = {
                p.getId(),
                p.getNombre(),
                p.getApellidos(),
                p.getTelefono(),
                p.getAniosExperiencia() + " años",
                p.getEspecialidad(),
                p.getEstado()
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
            // Simulación de datos (luego haremos el formulario real)
            Peluquera nueva = new Peluquera(0, "Ana", "García", "611223344", 5, "Corte", "Disponible");
            if (modeloDAO.insertarPeluquera(nueva)) {
                JOptionPane.showMessageDialog(vista, "Peluquera contratada correctamente");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al contratar");
            }
        }
        else if (e.getSource() == vista.botonEliminar) {
            int fila = vista.tablaPeluqueras.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Selecciona una peluquera para despedir.");
                return;
            }
            
            // Obtenemos el ID de la columna 0 (que es el ID)
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            
            // Confirmación
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Seguro que deseas eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (modeloDAO.eliminarPeluquera(id)) {
                    JOptionPane.showMessageDialog(vista, "Peluquera eliminada.");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar. Puede que tenga citas asignadas.");
                }
            }
        }
    }
}