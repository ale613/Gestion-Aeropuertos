package controlador.aeropuerto;
import static gestor_aeropuertos.Gestor_Aeropuertos.controlInicio;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.aerolinea.AeroLinea;
import modelo.aerolinea.AeroLineaModel;
import modelo.aeropuerto.Aeropuerto;
import modelo.aeropuerto.AeropuertoModel;
import vista.aeropuerto.Inicio;

public class AgregarAeropuerto {

    private vista.aeropuerto.AgregarAeropuerto vista;
    
    public AgregarAeropuerto(Inicio vistaInicio) {
        vista = new vista.aeropuerto.AgregarAeropuerto(vistaInicio, true);
        cargarAeroLineas();
        oyentes();
        vista.setVisible(true);

    }

    private void oyentes() {
        vista.bCancelar.addActionListener((ActionEvent ae) -> {
            vista.dispose();
        });
        
        vista.bAgregar.addActionListener((ActionEvent ae)->{
            Aeropuerto aeropuerto = new Aeropuerto();
            
            //cargo los datos del nuevo aeropuerto segun los datos de la vista
            aeropuerto.setNombre(vista.cajaNombre.getText());
            aeropuerto.setCiudad(vista.cajaCiudad.getText());
            aeropuerto.setPais(vista.cajaPais.getText());
            AeroLinea lineas[] = new AeroLinea[vista.lista.getSelectedValuesList().size()];
            lineas = vista.lista.getSelectedValuesList().toArray(lineas);
            aeropuerto.setLineas(lineas);
            
            //hago la insercion en la tabla
            JOptionPane.showMessageDialog(null, (AeropuertoModel.create(aeropuerto)) ? 
                    "Aeropuerto agregado con exito":"Error al ingresar el aeropuerto");
            
            vista.dispose();
            
            //recargo los aeropuertos para que aparezca en la tabla la nueva insercion
            controlInicio.cargarAeropuertos();
        });

    }
    private void cargarAeroLineas() {
        AeroLinea lineas[];

        lineas = AeroLineaModel.getAeroLineas();

        DefaultListModel modeloLista = new DefaultListModel();
        for (AeroLinea linea : lineas) {
            modeloLista.addElement(linea);
        }
        
        

        vista.lista.setModel(modeloLista);
    }
}
    



