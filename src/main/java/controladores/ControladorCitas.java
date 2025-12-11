package controladores;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Cita;
import modelo.CitaDAO;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Peluquera;
import modelo.PeluqueraDAO;
import modelo.Servicio;
import modelo.ServicioDAO;
import vistas.VistaCitas;

public class ControladorCitas implements ActionListener {
    
    private VistaCitas vista;
    private CitaDAO citaDAO;
    
    // Necesitamos los otros DAOs para llenar los ComboBox
    private ClienteDAO clienteDAO;
    private ServicioDAO servicioDAO;
    private PeluqueraDAO peluqueraDAO;

    public ControladorCitas(VistaCitas vista) {
        this.vista = vista;
        this.citaDAO = new CitaDAO();
        this.clienteDAO = new ClienteDAO();
        this.servicioDAO = new ServicioDAO();
        this.peluqueraDAO = new PeluqueraDAO();
        
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
            mostrarFormularioNuevaCita();
        }
        else if (e.getSource() == vista.botonCompletar || e.getSource() == vista.botonCancelar) {
            gestionarEstadoCita(e.getSource() == vista.botonCompletar);
        }
    }
    
    private void mostrarFormularioNuevaCita() {
        // 1. Obtener datos de la BD
        List<Cliente> clientes = clienteDAO.listarClientes();
        List<Servicio> servicios = servicioDAO.listarServicios();
        List<Peluquera> peluqueras = peluqueraDAO.listarPeluqueras();
        
        // Validar que haya datos suficientes
        if (clientes.isEmpty() || servicios.isEmpty() || peluqueras.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Faltan datos (Clientes, Servicios o Peluqueras) para crear una cita.");
            return;
        }

        // 2. Crear componentes del formulario
        JComboBox<Cliente> comboClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        JComboBox<Servicio> comboServicios = new JComboBox<>(servicios.toArray(new Servicio[0]));
        JComboBox<Peluquera> comboPeluqueras = new JComboBox<>(peluqueras.toArray(new Peluquera[0]));

        JPanel panelForm = new JPanel(new GridLayout(0, 1));
        panelForm.add(new JLabel("Seleccione Cliente:"));
        panelForm.add(comboClientes);
        panelForm.add(new JLabel("Seleccione Servicio:"));
        panelForm.add(comboServicios);
        panelForm.add(new JLabel("Seleccione Peluquera:"));
        panelForm.add(comboPeluqueras);

        // 3. Mostrar diálogo
        int result = JOptionPane.showConfirmDialog(vista, panelForm, "Agendar Nueva Cita",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Cliente cli = (Cliente) comboClientes.getSelectedItem();
                Servicio ser = (Servicio) comboServicios.getSelectedItem();
                Peluquera pel = (Peluquera) comboPeluqueras.getSelectedItem();
                
                if (citaDAO.crearCita(cli.getId(), ser.getId(), pel.getId())) {
                    JOptionPane.showMessageDialog(vista, "Cita agendada correctamente.");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al guardar en base de datos.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error procesando los datos: " + ex.getMessage());
            }
        }
    }
    
    private void gestionarEstadoCita(boolean esCompletar) {
        int fila = vista.tablaCitas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona una cita de la tabla.");
            return;
        }
        
        int id = (int) vista.modeloTabla.getValueAt(fila, 0);
        String estadoActual = (String) vista.modeloTabla.getValueAt(fila, 3);
        
        // Evitar cambiar estados finales
        if ("Completada".equals(estadoActual) || "Cancelada".equals(estadoActual)) {
            JOptionPane.showMessageDialog(vista, "Esta cita ya está cerrada.");
            return;
        }

        String nuevoEstado = esCompletar ? "Completada" : "Cancelada";
        
        if (citaDAO.actualizarEstado(id, nuevoEstado)) {
            cargarTabla();
            // Si se completa, sugerir facturar (opcional, pero buena UX)
            if (esCompletar) {
                JOptionPane.showMessageDialog(vista, "Cita completada. Recuerda generar la factura en la pestaña Facturas.");
            }
        }
    }
}