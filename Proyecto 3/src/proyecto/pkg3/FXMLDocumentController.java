/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg3;

import control.ControlAgencia;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Cliente;
import model.Ecologico;
import model.Empresarial;
import model.ServicioAdicional;
import model.TipoEmpresa;
import model.Tour;
import view.PantallaAgencia;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {

    private ControlAgencia controlador = new ControlAgencia();

    @FXML
    private TableView<Tour> tablaTours;

    @FXML
    private TableColumn<Tour, String> columnaNombre = new TableColumn<Tour, String>("NombreComercial");

    @FXML
    private TableColumn<Tour, Integer> columnaID = new TableColumn<Tour, Integer>("CodigoIdentificacion");

    @FXML
    private TableColumn<Tour, String> columnaPartida = new TableColumn<Tour, String>("LugarPartita");

    @FXML
    private TableColumn<Tour, Date> columnaFechaPartida = new TableColumn<>("FechaSalida");

    @FXML
    private TableColumn<Tour, Date> columnaFechaRegreso = new TableColumn<>("FechaRegreso");

    @FXML
    private TableColumn<Tour, Double> columnaPrecio = new TableColumn<Tour, Double>("Precio");
    @FXML
    private TextField newNombreTour;

    @FXML
    private TextField newIdTour;

    @FXML
    private TextField newLugarPartidaTour;

    @FXML
    private TextField newFechaDeSalidaTour;

    @FXML
    private TextField newFechaDeRegresoTour;

    @FXML
    private TextField newPrecioTour;

    @FXML
    private RadioButton tour_RadioButton;

    @FXML
    private ToggleGroup tipoTour;

    @FXML
    private RadioButton tourEcologico_RadioButton;

    @FXML
    private RadioButton tourEmpresarial_RadioButton;

    @FXML
    private Button continuarTour_BUTTON;

    @FXML
    private TextField newImpuestoLocal;

    @FXML
    private TextField newNombreEmpresa;

    @FXML
    private RadioButton newTourTecnologia;

    @FXML
    private ToggleGroup tipoTourEmpresarial;

    @FXML
    private RadioButton newTourTurismo;

    @FXML
    private RadioButton newTourMedioAmbiente;

    @FXML
    private Button agregarTour_BUTTON;
    @FXML
    private ChoiceBox<Long> eliminar_ChoiceBox;
    @FXML
    private Button eliminar_BUTTON;
    @FXML
    private CheckBox eliminar_CHECKBOX;
    @FXML
    private RadioButton nombreMod_RADIOBUTTON;

    @FXML
    private ToggleGroup grupoModificar;

    @FXML
    private RadioButton idMod_RADIOBUTTON;

    @FXML
    private RadioButton lugarMod_RADIOBUTTON;

    @FXML
    private RadioButton precioMod_RADIOBUTTON;

    @FXML
    private RadioButton fechaPMod_RADIOBUTTON;

    @FXML
    private RadioButton fechaRMod_RADIOBUTTON;

    @FXML
    private RadioButton horaMod_RADIOBUTTON;

    @FXML
    private RadioButton impuestoMod_RADIOBUTTON;

    @FXML
    private RadioButton nombreEmpresaMod_RADIOBUTTON;

    @FXML
    private TextField modificar_TEXTFIELD;

    @FXML
    private Button modificar_BUTTON;

    @FXML
    private ChoiceBox<Long> modificarTour_CHOICEBOX;

    @FXML
    private void fillItemsTour() {
        for (Tour tour : controlador.getListaTours().values()) {
            //Llenar la tabla
            tablaTours.getItems().add(tour);

            //llenar el choicebox
            eliminar_ChoiceBox.getItems().add(tour.getCodigoIdentificacion());
            modificarTour_CHOICEBOX.getItems().add(tour.getCodigoIdentificacion());
        }
    }

    @FXML
    private void clearItemsTour() {
        //Clear tabla
        tablaTours.getItems().clear();
        //Clear choice box
        eliminar_ChoiceBox.getItems().clear();
        modificarTour_CHOICEBOX.getItems().clear();
    }

    @FXML
    public void agregarNuevoTour(ActionEvent event) throws Exception {

        String nombre = newNombreTour.getText();
        Integer id = new Integer(newIdTour.getText());
        String lugarPartida = newLugarPartidaTour.getText();
        Double precio = Double.parseDouble(newPrecioTour.getText());
        String salidaCadena = newFechaDeSalidaTour.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date salida = null;
        try {
            salida = sdf.parse(salidaCadena);
        } catch (ParseException ex) {
            Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date regreso = null;
        String regresoCadena = newFechaDeRegresoTour.getText();
        try {
            regreso = sdf.parse(regresoCadena);
        } catch (ParseException ex) {
            Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tour existeTour = controlador.getGestionTours().getTour(controlador.getListaTours(), id);
        boolean digitosValidados = controlador.validarDigitos(id);

        if (existeTour == null && digitosValidados == true && salida != null && regreso != null) {
            if (tipoTour.getSelectedToggle().equals(tour_RadioButton)) {

                Tour tour = new Tour(id, nombre, lugarPartida, "20:00", precio, salida, regreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), tour);

            } else if (tipoTour.getSelectedToggle().equals(tourEcologico_RadioButton)) {

                Double impuestoLocal = Double.parseDouble(newImpuestoLocal.getText());
                Ecologico nuevoEco = new Ecologico(true, impuestoLocal, true, id, nombre, lugarPartida, "00:00", precio, salida, regreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), nuevoEco);

            } else if (tipoTour.getSelectedToggle().equals(tourEmpresarial_RadioButton)) {

                String nombreEmpresa = newNombreEmpresa.getText();
                TipoEmpresa tipo = null;
                if (tipoTourEmpresarial.getSelectedToggle().equals(newTourTecnologia)) {
                    tipo = TipoEmpresa.TECNOLOGIA;
                } else if (tipoTourEmpresarial.getSelectedToggle().equals(newTourMedioAmbiente)) {
                    tipo = TipoEmpresa.MEDIO_COMUNICACION;
                } else if (tipoTourEmpresarial.getSelectedToggle().equals(newTourTurismo)) {
                    tipo = TipoEmpresa.MEDIO_COMUNICACION;
                }
                Empresarial nuevoEmp = new Empresarial(nombreEmpresa, true, tipo, id, nombre, lugarPartida, "00:00", precio, salida, regreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), nuevoEmp);
            }

            crearAlerta(AlertType.CONFIRMATION, "Exito!", "Se ha agregado el  nuevo tour",
                    "Numero de tours " + controlador.getListaTours().size());
        } else {
            crearAlerta(AlertType.ERROR, "ERROR", "No se ha podido agregar el tour por alguno de estos motivos",
                    " -El tour ya existe\n -El codigo no es valido\n -La fecha ingresada no es valida");
        }
        clearItemsTour();
        fillItemsTour();

    }

    /*PREGUNTAR AL PROFESOR SI SE TIENE QUE PREGUNTA SI ESTA SEGURO DE ELIMINAR EL TOUR*/
    public void eliminiarTour(ActionEvent event) throws Exception {

        long codigo = eliminar_ChoiceBox.getValue();
        Tour tour = controlador.getGestionTours().getTour(controlador.getListaTours(), codigo);

        if (!(controlador.tourTieneReserva(codigo))) {
            if (eliminar_CHECKBOX.isSelected()) {
                controlador.getGestionTours().eliminarTour(controlador.getListaTours(), tour);
            }
            else{
                crearAlerta(AlertType.ERROR, "ERROR", "No se ha podido eliminar el tour", "Debe de confirmar la eliminacion");
            }
        } else {
            crearAlerta(AlertType.ERROR, "ERROR", "No se ha podidio eliminar el tour", "El tour tiene reservas");
        }

        clearItemsTour();
        fillItemsTour();
    }

    public void modificarTour(ActionEvent event) throws Exception {

        Tour tour = controlador.getGestionTours().getTour(controlador.getListaTours(), modificarTour_CHOICEBOX.getValue());

    }

    private void crearAlerta(AlertType tipo, String titulo, String tituloInterno, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillItemsTour();
        tablaTours.getColumns().addAll(columnaNombre, columnaID, columnaPartida, columnaPartida, columnaPrecio);
    }
}
