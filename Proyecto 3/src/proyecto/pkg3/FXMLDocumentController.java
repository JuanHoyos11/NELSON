/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg3;

import control.ControlAgencia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cliente;
import model.Tour;

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
;
    
    @FXML
    private TableColumn<Tour, Integer> columnaID = new TableColumn<Tour, Integer>("CodigoIdentificacion");
;
    
    @FXML
    private TableColumn<Tour, String> columnaPartida = new TableColumn<Tour, String>("LugarPartita");

    @FXML
    private TableColumn<Tour, String> columnaHora = new TableColumn<Tour, String> ("HoraPartida");

    @FXML
    private TableColumn<Tour, Double> columnaPrecio = new TableColumn<Tour, Double> ("Precio");

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }

    @FXML
    private void handleButtonTour(ActionEvent event) {
       
    }

    @FXML
    private void handleButtonCliente(ActionEvent event) {
        
    }    

    @FXML
    private void fillItems() {
        for (Tour tour : controlador.getListaTours().values()) {
            //Llenar la tabla
            tablaTours.getItems().add(tour);
            
            //llenar el choicebox
            //listaClientes.getItems().add(cln.getCedula());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillItems();
        //tableClientes.getColumns().addAll(clientesNombre, clientesCedula, clientesTelefono);
        tablaTours.getColumns().addAll(columnaNombre,columnaID,columnaPartida,columnaPartida,columnaPrecio);
    }  
    void agregarNuevotour(ActionEvent event) throws Exception{
        
        /*Tour agregar = new Tour(123456,"ASD","aswe","asd",12);
        controlador.getGestionTours().insertarTour(controlador.getListaTours(), agregar);
        fillItems();*/
        
      
    }
    
}
