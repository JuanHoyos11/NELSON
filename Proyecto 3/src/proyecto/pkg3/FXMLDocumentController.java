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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cliente;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
    private ControlAgencia controlador = new ControlAgencia();
    
     @FXML
    private TableView<Cliente> tabla;

    @FXML
    private TableColumn<Cliente, String> nombreColumna = new TableColumn<Cliente, String>("NombreCompleto");

    @FXML
    private TableColumn<Cliente, Integer> cedulaColumna  = new TableColumn<Cliente, Integer>("NumeroIdentificacion");

    @FXML
    private TableColumn<Cliente, String> columnaTelefono  = new TableColumn<Cliente, String>("TelefonoContacto");
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    @FXML
    private void handleButtonTour(ActionEvent event) {
        
    }
      @FXML
    private void handleButtonCliente(ActionEvent event) {
        
    }   
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        this.controlador = new ControlAgencia();
        
       fillItemsClientes();
        tabla.getColumns().addAll();
    }

    private void fillItemsClientes() {
        for(Cliente cln: controlador.getListaClientes().values()){
            
            //Llenar la tabla
            tabla.getItems().add(cln);
            
            
        }
    }
    
    }
  
