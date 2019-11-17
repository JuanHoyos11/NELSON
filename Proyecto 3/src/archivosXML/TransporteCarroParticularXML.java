/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosXML;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import model.Reserva;
import model.ServicioAdicional;

/**
 *
 * @author nxlsxn
 */
public class TransporteCarroParticularXML {
     @XmlElement (name = "Listado_de_carros_Particulares")
    
    private ArrayList <ServicioAdicional> listaParticulares = new ArrayList<>();

    public TransporteCarroParticularXML() {
    }
    public TransporteCarroParticularXML(ArrayList <ServicioAdicional> servicioAdicional) {
        this.listaParticulares = servicioAdicional;
    }
    
    
    public ArrayList<ServicioAdicional> getListaReservas() {
        return listaParticulares;
    }
   
}
