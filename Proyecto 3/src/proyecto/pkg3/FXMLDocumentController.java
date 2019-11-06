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
    private TableColumn<Tour, String> columnaHoraPartida = new TableColumn<>("HoraPartida");
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
        String horaPartida = newHoraPartida.getText();
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
        
        Tour existeTour;
        existeTour = controlador.getGestionTours().getTour(controlador.getListaTours(), id);
        boolean digitosValidados = controlador.validarDigitos(id);
        
        if (existeTour == null && digitosValidados == true && salida != null && regreso != null) {
            if (tipoTour.getSelectedToggle().equals(tour_RadioButton)) {
                
                Tour tour = new Tour(id, nombre, lugarPartida, horaPartida, precio, salida, regreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), tour);
                
            } else if (tipoTour.getSelectedToggle().equals(tourEcologico_RadioButton)) {
                
                Double impuestoLocal = Double.parseDouble(newImpuestoLocal.getText());
                Ecologico nuevoEco = new Ecologico(true, impuestoLocal, 
                        true, id, nombre, lugarPartida, horaPartida, precio, salida, regreso);
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
                Empresarial nuevoEmp = new Empresarial(nombreEmpresa, true, tipo,
                        id, nombre, lugarPartida, horaPartida, precio, salida, regreso);
                controlador.getGestionTours().insertarTour(controlador.getListaTours(), nuevoEmp);
            }
            
            crearAlerta(AlertType.CONFIRMATION, 
                    "Exito!",
                    "Se ha agregado el  nuevo tour",
                    "Numero de tours " + controlador.getListaTours().size());
        } else {
            crearAlerta(AlertType.ERROR, 
                    "ERROR", 
                    "No se ha podido agregar el tour por alguno de estos motivos",
                    " -El tour ya existe\n -El codigo no es valido\n"
                  + " -La fecha ingresada no es valida");
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
                    
                    if (grupoModificar.getSelectedToggle().equals(modificarMedio_RADIOBUTTON)){
                        ((Empresarial) tour).setTipo(TipoEmpresa.MEDIO_COMUNICACION);
                    } else if (grupoModificar.getSelectedToggle().equals(modificarTec_RADIOBUTTON)){
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
