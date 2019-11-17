/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosXML;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import model.Reserva;

/**
 *
 * @author nxlsxn
 */
public class ReservasFechaEspecificaXML {
    @XmlElement (name = "Listado_de_reservas_de_un_tour_y_fecha_especifica")
    
    private ArrayList <Reserva> listaReservas = new ArrayList<>();

    public ReservasFechaEspecificaXML() {
    }
    public ReservasFechaEspecificaXML(ArrayList <Reserva> res) {
        this.listaReservas = res;
    }
    
    
    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }
        
    
}
