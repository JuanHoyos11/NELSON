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
import javafx.scene.control.TableView;
import model.Cliente;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
    private ControlAgencia controlador = new ControlAgencia();
    //hhhhhhhh
    @FXML
    private Label label;
      @FXML
    private TableView<Cliente> nombre;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Cliente x = new Cliente (53637,"Juan", "48933");
        System.out.println("You clicked me!");
        label.setText(x.getNombreCompleto());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
