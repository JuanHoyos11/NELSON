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
import javafx.scene.control.TextField;
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
    private TextField newNombre;

    @FXML
    private TextField newCedula;

    @FXML
    private TextField newTelefono;

    @FXML
    private Button agregarBtn;
    
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
  void agregarNuevoCliente(ActionEvent event) throws Exception{
      long num = Long.valueOf(newCedula.getText());
      Cliente cln = new Cliente(num, newNombre.getText(),newTelefono.getText());
      if( controlador.getGestionCliente().getCliente(num, controlador.getListaClientes()) == null){
          controlador.getGestionCliente().insertarCliente(cln, controlador.getListaClientes());
          //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          //alert.setTitle("Exito");
          //alert.setHeaderText("El cliente ha sido agregado correctamente");
          //alert.setContentText("En el sistema hay "+controlador.getClientes().size()+" clientes.");
          //alert.showAndWait();
          clearItems();
          fillItemsClientes();
      } else {
          //Alert alert = new Alert(Alert.AlertType.ERROR);
          //alert.setTitle("Error");
          //alert.setHeaderText("Error de al agregar un nuevo Cliente:");
          //alert.setContentText("El cliente ya existe.");
          //alert.showAndWait();
      }
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
    
     private void clearItems() {
        
        tabla.getItems().clear();
    }
    
    }
  
