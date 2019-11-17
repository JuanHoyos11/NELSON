/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosXML;

import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlElement;
import model.Tour;

/**
 *
 * @author nxlsxn
 */
public class TourEcologicoXML {
    @XmlElement (name = "Listado_de_toures_de_tipo_ecologico")
    
    private HashMap <Integer,Tour> listaTours = new HashMap<>();

    public TourEcologicoXML() {
    }
    public TourEcologicoXML(HashMap<Integer,Tour> tour) {
        this.listaTours = tour;
    }
    
    
    public HashMap<Integer, Tour> getListaReservas() {
        return listaTours;
    }
        
    
}
