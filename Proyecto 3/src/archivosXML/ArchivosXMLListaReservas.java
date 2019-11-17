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
public class ArchivosXMLListaReservas {
    @XmlElement (name = "Listado_de_reservas_existentes")
    
    private ArrayList <Reserva> listaReservas = new ArrayList<>();
    
    public ArchivosXMLListaReservas(){
    }
    public ArchivosXMLListaReservas(ArrayList <Reserva> listaReservas){
        this.listaReservas = listaReservas;
    }
    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }
        
    
}
