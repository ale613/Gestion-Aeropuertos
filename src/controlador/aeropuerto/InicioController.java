package controlador.aeropuerto;

import controlador.aerolinea.AeroLineas;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import modelo.aeropuerto.Aeropuerto;
import modelo.aeropuerto.AeropuertoModel;
import vista.aeropuerto.Inicio;

public class InicioController {

    private Inicio vistaInicio;

    public InicioController() {
        vistaInicio = new Inicio();
        vistaInicio.setVisible(true);
        vistaInicio.setLocationRelativeTo(null);

        cargarAeropuertos();
        oyentes();
    }

    protected void cargarAeropuertos() {
        Aeropuerto[] aeros;

        //traigo los aeropuertos de la BD
        aeros = AeropuertoModel.getAeropuertos();

        //creo un modelo para la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //no se puede editar la tabla
                return false;
            }
        };

        modeloTabla.addColumn("Nombre del Aeropuerto");
        modeloTabla.addColumn("Ciudad");
        modeloTabla.addColumn("Pais");
        for (Aeropuerto aero : aeros) {
            modeloTabla.addRow(aero.imprimir());
        }
        vistaInicio.tablaAero.setModel(modeloTabla);

    }

    private void oyentes() {
        /*Menu*/
        vistaInicio.menuAero.addActionListener((ActionEvent ae) ->{
            new AeroLineas(vistaInicio);
        });
        
        vistaInicio.bAgregar.addActionListener((ActionEvent ae) -> {
            new AgregarAeropuerto(vistaInicio);
        });

        vistaInicio.bDatos.addActionListener((ActionEvent ae) -> {
            //guardar el aeropuerto seleccionado
            int fila = vistaInicio.tablaAero.getSelectedRow();
            String nombre = (String) vistaInicio.tablaAero.getModel().getValueAt(fila, 0);
            new VerAeropuerto(vistaInicio, nombre);
        });
        
        vistaInicio.bEditar.addActionListener((ActionEvent ae) ->{
            int fila = vistaInicio.tablaAero.getSelectedRow();
            String nombre = (String) vistaInicio.tablaAero.getModel().getValueAt(fila, 0);
            new EditarAeropuerto(vistaInicio,nombre);
            
        });
        
        vistaInicio.bEliminar.addActionListener((ActionEvent ae) ->{
            int fila = vistaInicio.tablaAero.getSelectedRow();
            String nombre = (String) vistaInicio.tablaAero.getModel().getValueAt(fila, 0);
            new EliminarAeropuerto(nombre);
            
        });
        
    }
}
