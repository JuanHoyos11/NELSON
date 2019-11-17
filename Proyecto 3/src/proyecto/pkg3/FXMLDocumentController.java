/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg3;

import control.ControlAgencia;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import model.Cliente;
import model.Ecologico;
import model.Empresarial;
import model.Reserva;
import model.ServicioAdicional;
import enumeradores.TipoEmpresa;
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
    private TableColumn<Tour, String> columnaHoraPartida = new TableColumn<>("HoraPartida");
    @FXML
    private TextField newNombreTour;

    @FXML
    private TextField newIdTour;

    @FXML
    private TextField newLugarPartidaTour;

    @FXML
    private DatePicker newFechaPartida_DP;

    @FXML
    private DatePicker newFechaRegreso_DP;

    @FXML
    private TextField newPrecioTour;

    @FXML
    private TextField newHoraPartida;

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
    private TextField modificar_TEXTFIELD;

    @FXML
    private Button modificar_BUTTON;

    @FXML
    private ChoiceBox<String> modificarValor_CHECKBOX;

    @FXML
    private ChoiceBox<Long> modificarTour_CHOICEBOX;

    @FXML
    private RadioButton modificarTec_RADIOBUTTON;

    @FXML
    private ToggleGroup grupoModificar;

    @FXML
    private RadioButton modificarTur_RADIOBUTTON;

    @FXML
    private RadioButton modificarMedio_RADIOBUTTON;
    @FXML
    private TextArea reporte_TextArea;

    @FXML
    private RadioButton reservaEspecifico_RB;

    @FXML
    private ToggleGroup grupoArchivos;

    @FXML
    private RadioButton touresEcologicos_RB_XML;

    @FXML
    private RadioButton tourEmpresarial_RB_XML;

    @FXML
    private RadioButton precioTourEcologico_RB;

    @FXML
    private RadioButton carroParticular_RB;

    @FXML
    private RadioButton reservasExistentes_RB;

    @FXML
    private Button generarReporte_BUTTON;

    @FXML
    private TextField codigoTourXML_B;

    @FXML
    private DatePicker fechaInicial_DP_XML;

    @FXML
    private DatePicker fechaFinal_DP_XML;

    @FXML
    private DatePicker fechaReserva_DP_XML;

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
    private void fillItemsReporteReservasExistentes() {
        int i = 1;
        StringBuilder stringCompuesto = new StringBuilder("");
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte listado de Reservas existentes");
        stringCompuesto.append("\n============================================================================\n");
        for (Reserva res : controlador.getReservas()) {
            stringCompuesto.append(res.toString() + "\n");
        }
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte listado de Reservas para un tour y una fecha específica");
        stringCompuesto.append("\n============================================================================\n");
        for (Reserva res : controlador.getReservasFechaEspecifica()) {
            stringCompuesto.append(res.toString() + "\n");
        }
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte de todos los tours de tipo ecológico");
        stringCompuesto.append("\n============================================================================\n");
        for (Tour tour : controlador.getListaToursEcologicosXML().values()) {
            stringCompuesto.append(tour.toString() + "\n");
        }
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte  Reservas asociadas a tour empresarial");
        stringCompuesto.append("\n============================================================================\n");
        for (Reserva res : controlador.getReservasTourEmpresarial()) {
            stringCompuesto.append(res.toString() + "\n");
        }
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte precio total de reservas para tour ecológico para un rango de fechas");
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append(controlador.getPrecioXML() + "\n");
        stringCompuesto.append("\n============================================================================\n");
        stringCompuesto.append("Reporte Servicios de Transporte de tipo carro particular");
        stringCompuesto.append("\n============================================================================\n");
        for (ServicioAdicional ser : controlador.getCarrosParticularesXML()) {
            stringCompuesto.append(ser.toString());
        }
        reporte_TextArea.setText(stringCompuesto.toString());

    }

    @FXML
    private void clearTextAreaReporte() {
        reporte_TextArea.clear();
    }

    @FXML
    private void clearItemsTour() {
        //Clear tabla
        tablaTours.getItems().clear();
        //Clear choice box
        eliminar_ChoiceBox.getItems().clear();
        modificarTour_CHOICEBOX.getItems().clear();
    }

    /*--Ventana Reportes-----------------*/
    @FXML
    void generarReporte(ActionEvent event) throws IOException {

        boolean error = false;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.setInitialDirectory(new File("res"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        fileChooser.setInitialFileName("reporte.xml");
        File selectedFile = fileChooser.showSaveDialog(this.generarReporte_BUTTON.getScene().getWindow());

        System.out.println("File to save:" + selectedFile.getAbsolutePath());
        if (grupoArchivos.getSelectedToggle().equals(reservasExistentes_RB)) {
            error = controlador.crearListaReservaXML(selectedFile);

        } else if (grupoArchivos.getSelectedToggle().equals(reservaEspecifico_RB)) {

            Long codigo;
            LocalDateTime fecha;

            try {
                codigo = Long.parseLong(codigoTourXML_B.getText());
                fecha = fechaReserva_DP_XML.getValue().atStartOfDay();

                System.out.println(fecha);
                error = controlador.crearFechaEspecificaXML(selectedFile, codigo, fecha);

            } catch (Exception e) {
                crearAlerta(AlertType.ERROR,
                        "ERROR!",
                        "Se genero una exepcion",
                        e.getMessage());
            }

        } else if (grupoArchivos.getSelectedToggle().equals(touresEcologicos_RB_XML)) {
            error = controlador.crearTourEcologicoXML(selectedFile);
        } else if (grupoArchivos.getSelectedToggle().equals(tourEmpresarial_RB_XML)) {
            error = controlador.crearTourEmpresarialXML(selectedFile);
        } else if (grupoArchivos.getSelectedToggle().equals(precioTourEcologico_RB)) {
            Date fechaInicial;
            Date fechaFinal;
            fechaInicial = Date.from(fechaInicial_DP_XML.getValue().
                    atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            fechaFinal = Date.from(fechaFinal_DP_XML.getValue().
                    atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            error = controlador.crearTourPrecioXML(selectedFile, fechaInicial, fechaFinal);
        } else if (grupoArchivos.getSelectedToggle().equals(carroParticular_RB)) {
            error = controlador.crearTransporteCarroParticularXML(selectedFile);
        }
        if (error) {
            crearAlerta(Alert.AlertType.CONFIRMATION,
                    "Exito!",
                    "Se ha creado el reporte",
                    ":)");
        } else {
            crearAlerta(Alert.AlertType.CONFIRMATION,
                    "Error!",
                    "No se ha creado el reporte",
                    ":(");
        }
        clearTextAreaReporte();
        fillItemsReporteReservasExistentes();

    }


    /*----Ventana toures------*/
    @FXML
    public void agregarNuevoTour(ActionEvent event) throws Exception {

        String nombre = null;
        Integer id = null;
        String lugarPartida = null;
        Double precio = null;
        String horaPartida = null;
        Date fechaPartida = null;
        Date fechaRegreso = null;
        Tour existeTour = null;
        boolean digitosValidados = false;
        boolean error = false;
        try {
            nombre = newNombreTour.getText();
            id = new Integer(newIdTour.getText());
            lugarPartida = newLugarPartidaTour.getText();
            precio = Double.parseDouble(newPrecioTour.getText());
            horaPartida = newHoraPartida.getText();
            fechaPartida = Date.from(newFechaPartida_DP.getValue().
                    atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            fechaRegreso = Date.from(newFechaRegreso_DP.getValue().
                    atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            existeTour = controlador.getGestionTours().getTour(controlador.getListaTours(), id);
            digitosValidados = controlador.validarDigitos(id);
        } catch (Exception e) {
            error = true;
        }
        if (existeTour == null && digitosValidados == true && !error) {
            if (tipoTour.getSelectedToggle().equals(tour_RadioButton)) {

                Tour tour = new Tour(id, nombre, lugarPartida, horaPartida, precio,
                        fechaPartida, fechaRegreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), tour);

            } else if (tipoTour.getSelectedToggle().equals(tourEcologico_RadioButton)) {

                Double impuestoLocal = null;
                try {
                    impuestoLocal = Double.parseDouble(newImpuestoLocal.getText());
                } catch (Exception e) {
                    error = true;
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "Se ha presentado un error",
                            e.getLocalizedMessage());
                }
                Ecologico nuevoEco = new Ecologico(true, impuestoLocal,
                        true, id, nombre, lugarPartida, horaPartida, precio, fechaPartida, fechaRegreso);
                if (!error) {
                    controlador.getGestionTours().insertarTour(controlador.getListaTours(), nuevoEco);
                }

            } else if (tipoTour.getSelectedToggle().equals(tourEmpresarial_RadioButton)) {

                String nombreEmpresa = null;

                try {
                    nombreEmpresa = newNombreEmpresa.getText();
                } catch (Exception e) {
                    error = true;
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "Se ha presentado un error",
                            e.getLocalizedMessage());
                }
                TipoEmpresa tipo = null;
                if (tipoTourEmpresarial.getSelectedToggle().equals(newTourTecnologia)) {
                    tipo = TipoEmpresa.TECNOLOGIA;
                } else if (tipoTourEmpresarial.getSelectedToggle().equals(newTourMedioAmbiente)) {
                    tipo = TipoEmpresa.MEDIO_COMUNICACION;
                } else if (tipoTourEmpresarial.getSelectedToggle().equals(newTourTurismo)) {
                    tipo = TipoEmpresa.MEDIO_COMUNICACION;
                }
                Empresarial nuevoEmp = new Empresarial(nombreEmpresa, true, tipo,
                        id, nombre, lugarPartida, horaPartida, precio, fechaPartida, fechaRegreso);
                if (!error) {
                    controlador.getGestionTours().insertarTour(controlador.getListaTours(), nuevoEmp);
                }
            }

            if (!error) {
                crearAlerta(AlertType.CONFIRMATION,
                        "Exito!",
                        "Se ha agregado el  nuevo tour",
                        "Numero de tours " + controlador.getListaTours().size());
            }
        } else {
            crearAlerta(AlertType.ERROR,
                    "ERROR",
                    "No se ha podido agregar el tour por alguno de estos motivos",
                    " -El tour ya existe\n -El codigo no es valido\n"
                    + " -La fecha ingresada no es valida \n"
                    + " -Faltan datos por llenar");
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
                crearAlerta(AlertType.CONFIRMATION,
                        "EXITO",
                        "Se ha eliminado el tour",
                        ":)");
            } else {
                crearAlerta(AlertType.ERROR,
                        "ERROR",
                        "No se ha podido eliminar el tour",
                        "Debe de confirmar la eliminacion");
            }
        } else {
            crearAlerta(AlertType.ERROR,
                    "ERROR",
                    "No se ha podidio eliminar el tour",
                    "El tour tiene reservas");
        }

        clearItemsTour();
        fillItemsTour();
    }

    public void modificarTour(ActionEvent event) throws Exception {

        Tour tour = controlador.getGestionTours().getTour(controlador.getListaTours(), modificarTour_CHOICEBOX.getValue());
        boolean seAgrego = false;
        boolean mensajeError = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long key = tour.getCodigoIdentificacion();

        switch (modificarValor_CHECKBOX.getValue()) {

            case "Nombre":

                String nuevoNombre = modificar_TEXTFIELD.getText();
                tour.setNombreComercial(nuevoNombre);
                seAgrego = true;
                break;

            case "ID":

                Integer nuevoID;
                nuevoID = Integer.parseInt(modificar_TEXTFIELD.getText());
                boolean ValidacionNumeros;
                ValidacionNumeros = controlador.validarDigitos(nuevoID.longValue());

                // validar que el ID no exista y sean 7 digitos
                if (controlador.getGestionTours().getTour(controlador.getListaTours(), nuevoID) != null) {
                    crearAlerta(AlertType.ERROR,
                            "ERROR!",
                            "No se ha podido cambiar el nombre por:",
                            "-El ID ya existe");
                    mensajeError = false;
                } else if (controlador.validarDigitos(nuevoID) == false) {
                    crearAlerta(AlertType.ERROR,
                            "ERROR!",
                            "No se ha podido cambiar el nombre por:",
                            "-El ID no tiene 7 digitos");
                    mensajeError = false;

                } else {
                    tour.setCodigoIdentificacion(nuevoID.longValue());
                    seAgrego = true;
                }

                break;

            case "Lugar de partida":

                String nuevoLugarDePartida = modificar_TEXTFIELD.getText();
                tour.setLugarPartita(nuevoLugarDePartida);
                seAgrego = true;
                break;

            case "Fecha de partida":

                String salidaCadena = modificar_TEXTFIELD.getText();
                Date salida = null;
                try {
                    salida = sdf.parse(salidaCadena);
                } catch (ParseException ex) {
                    Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (salidaCadena != null) {
                    tour.setFechaSalida(salida);
                    seAgrego = true;
                } else {
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "No se ha podido modificar la fecha",
                            "La fecha no es valida!");
                    mensajeError = false;
                }
                break;

            case "Fecha de regreso":

                Date regreso = null;
                String regresoCadena = modificar_TEXTFIELD.getText();
                try {
                    regreso = sdf.parse(regresoCadena);
                } catch (ParseException ex) {
                    Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (regresoCadena == null) {
                    tour.setFechaRegreso(regreso);
                    seAgrego = true;
                } else {
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "No se ha podido modificar la fecha",
                            "La fecha no es valida!");
                    mensajeError = false;
                }
                break;

            case "Precio":

                Double nuevoPrecio;
                nuevoPrecio = Double.parseDouble(modificar_TEXTFIELD.getText());
                tour.setPrecio(nuevoPrecio);
                seAgrego = true;
                break;

            case "Impuesto Local":

                if (tour instanceof Ecologico) {
                    Double nuevoImpuesto;
                    nuevoImpuesto = Double.parseDouble(modificar_TEXTFIELD.getText());
                    ((Ecologico) tour).setImpuestoLocal(nuevoImpuesto);
                    seAgrego = true;
                } else {
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "No se ha podido modificar",
                            "El tour no es de tipo ecologico");
                    mensajeError = false;
                }
                break;

            case "Nombre de la empresa":

                if (tour instanceof Empresarial) {
                    String nuevoNombreEmpresa = modificar_TEXTFIELD.getText();
                    ((Empresarial) tour).setNombreEmpresa(nuevoNombreEmpresa);
                    seAgrego = true;
                } else {
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "No se ha podido modificar",
                            "El tour no es de tipo empresarial");
                    mensajeError = false;
                }
                break;

            case "Tipo de tour":
                if (tour instanceof Empresarial) {

                    if (grupoModificar.getSelectedToggle().equals(modificarMedio_RADIOBUTTON)) {
                        ((Empresarial) tour).setTipo(TipoEmpresa.MEDIO_COMUNICACION);
                    } else if (grupoModificar.getSelectedToggle().equals(modificarTec_RADIOBUTTON)) {
                        ((Empresarial) tour).setTipo(TipoEmpresa.TECNOLOGIA);
                    } else {
                        ((Empresarial) tour).setTipo(TipoEmpresa.TURISMO);
                    }
                    seAgrego = true;
                } else {
                    crearAlerta(AlertType.ERROR,
                            "ERROR",
                            "No se ha podido modificar",
                            "El tour no es de tipo empresarial");
                    mensajeError = false;
                }

                break;
            case "Hora de partida":

                String horaDePartida = modificar_TEXTFIELD.getText();
                tour.setHoraPartida(horaDePartida);
                seAgrego = true;
                break;

        }
        if (seAgrego) {
            crearAlerta(AlertType.CONFIRMATION,
                    "EXITO",
                    "Se ha modificaso el tour con exito",
                    ":)");
            controlador.getGestionTours().modificarTour(controlador.getListaTours(), tour, key);
        } else if (mensajeError) {
            crearAlerta(AlertType.ERROR,
                    "ERROR",
                    "No se ha podido modificar el tour",
                    ":(");
        }
        clearItemsTour();
        fillItemsTour();

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
        modificarValor_CHECKBOX.getItems().addAll("Nombre", "ID",
                "Lugar de partida", "Fecha de partida", "Fecha de regreso",
                "Precio", "Impuesto Local", "Nombre de la empresa",
                "Tipo de tour", "Hora de partida");

        tablaTours.getColumns().addAll(columnaNombre, columnaID, columnaPartida, columnaPartida, columnaPrecio);
    }
}
