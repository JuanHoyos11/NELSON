/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosXML;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author nxlsxn
 */
public class PrecioTourEcologicoXML {
     @XmlElement (name = "Precio_tour_ecologico")
    
    private double precio;

    public PrecioTourEcologicoXML() {
    }
    public PrecioTourEcologicoXML(double precio) {
        this.precio = precio;
    }
    
    
    public double getPrecio() {
        return precio;
    }
}
