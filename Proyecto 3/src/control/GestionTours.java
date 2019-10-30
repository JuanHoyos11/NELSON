package control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ecologico;
import model.Empresarial;
import model.TipoEmpresa;

import model.Tour;

public class GestionTours {

	//METODO PARA CREACION DE TOURS Y LENNARLOS AUTOMATICAMENTE
	public HashMap <Integer,Tour> crearTour(){
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                Date fechaSalida1 = sdf.parse("2019-01-01");
                Date fechaLlegada1 = sdf.parse("2019-01-05");
                Date fechaSalida2 = sdf.parse("2019-01-06");
                Date fechaLlegada2 = sdf.parse("2019-01-15");
                Date fechaSalida3 = sdf.parse("2019-02-01");
                Date fechaLlegada3 = sdf.parse("2019-02-05");
                Date fechaSalida4 = sdf.parse("2019-03-01");
                Date fechaLlegada4 = sdf.parse("2019-03-05");
                Date fechaSalida5 = sdf.parse("2019-04-01");
                Date fechaLlegada5 = sdf.parse("2019-04-05");
                Date fechaSalida6 = sdf.parse("2019-05-01");
                Date fechaLlegada6 = sdf.parse("2019-05-11");
                
                HashMap <Integer,Tour> listaTours = new HashMap<Integer, Tour>();
                
                
                Tour tour1 = new Tour(1001, "Paraiso Travel", "Medellin", "5:00", 100.0, fechaSalida1, fechaLlegada1);
                Tour tour2 = new Tour(1002, "Adventure Time", "Bogota", "22:00", 300.0, fechaSalida2, fechaLlegada2);
                Ecologico tourEco1 = new Ecologico(true ,20.0 ,true ,2001, "Trip verde", "Ibague", "5:00", 400.0, fechaSalida3, fechaLlegada3);
                Ecologico tourEco2 = new Ecologico(true, 20.0, false,2002, "Fumaratto", "Honda", "0:00", 100.0, fechaSalida4, fechaLlegada4);
                Empresarial tourEmp1 = new Empresarial("Tiobe", true, TipoEmpresa.TECNOLOGIA, 3001, "Wall Street Paisa", "Cartagena", "23:00", 500.0, fechaSalida5, fechaLlegada5);
                Empresarial tourEmp2 = new Empresarial("Aviatur", true, TipoEmpresa.TURISMO, 3002, "Cancun Full", "Bogota", "23:00", 1000.0, fechaSalida6, fechaLlegada6);
                /*
                Tour tourSanAndres = new Tour(1000001,"San Andres","Bogota","21:00",4000000, fechaSalida1, fechaLlegada1);
                Tour tourVilla = new Tour(1000002,"Villa de Leyva","Bogota","17:00",800000, fechaSalida2, fechaLlegada2);
                Tour tourCartagena = new Tour(1000003,"Cartagena","Medellin","80:00",5000000, fechaSalida3, fechaLlegada3);
                Tour tourCali = new Tour(1000004,"Cali","Bogota","04:00",2000000, fechaSalida4, fechaLlegada4);
                Tour tourAmazonas = new Tour(1000005,"Amazonas","Bogota","12:00",2500000, fechaSalida5, fechaLlegada5);
                
                listaTours.put((int) tourSanAndres.getCodigoIdentificacion(), tourSanAndres);
                listaTours.put((int) tourVilla.getCodigoIdentificacion(), tourVilla);
                listaTours.put((int) tourCartagena.getCodigoIdentificacion(), tourCartagena) ;
                listaTours.put((int) tourCali.getCodigoIdentificacion(), tourCali);
                listaTours.put((int) tourAmazonas.getCodigoIdentificacion(), tourAmazonas);
                */
                listaTours.put((int) tour1.getCodigoIdentificacion(), tour1);
                listaTours.put((int) tour2.getCodigoIdentificacion(), tour2);
                listaTours.put((int) tourEco1.getCodigoIdentificacion(), tourEco1);
                listaTours.put((int) tourEco2.getCodigoIdentificacion(), tourEco2);
                listaTours.put((int) tourEmp1.getCodigoIdentificacion(), tourEmp1);
                listaTours.put((int) tourEmp2.getCodigoIdentificacion(), tourEmp2);
                
                return listaTours;
                
            } catch (ParseException ex) {
                Logger.getLogger(GestionTours.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
	}

	public void listarTours(HashMap<Integer, Tour> listaTours)
	{
		for(Tour tour : listaTours.values()) {
			System.out.println("==========================================");
			System.out.println("Codigo: " + tour.getCodigoIdentificacion());
			System.out.println("Nombre: " + tour.getNombreComercial());
			System.out.println("Lugar de partida: " + tour.getLugarPartita());
			System.out.println("Hora de partida: " + tour.getHoraPartida());
			System.out.println("precio: " + tour.getPrecio());
                        System.out.println("fecha de salida: " + tour.getFechaSalida().toString());
                        System.out.println("fecha de regreso: " + tour.getFechaRegreso().toString());
                        if(tour instanceof Ecologico)
                        {
                            System.out.println("***MOSTRANDO INFO ADICIONAL***:");
                            System.out.println("La vacunacion es requerida?: " + ((Ecologico)tour).isVacunacionRequerida());
                            System.out.println("Impuesto local: " + ((Ecologico)tour).getImpuestoLocal());
                            System.out.println("El lugar es de dificil acceso?: " + ((Ecologico)tour).isDificilAcceso());
                        }
                        else if(tour instanceof Empresarial)
                        {
                            System.out.println("***MOSTRANDO INFO ADICIONAL***:");
                            System.out.println("Nombre de la empresa: " + ((Empresarial)tour).getNombreEmpresa());
                            System.out.println("Es viajero frecuente?: : " + ((Empresarial)tour).isViajeroFrecuente());
                            System.out.println("Tipo de la empresa: " + ((Empresarial)tour).getTipo().toString());
                        }
		}	
	}

	public void insertarTour(HashMap<Integer, Tour> listaTours, Tour tour) {

		listaTours.put((int)tour.getCodigoIdentificacion(), tour);

	}
	public void eliminarTour(HashMap<Integer, Tour> listaTours, Tour tour) {
		listaTours.remove((int) tour.getCodigoIdentificacion(), tour);
	}
	
	public void modificarTour(HashMap <Integer,Tour> listaTours, Tour tour,long key) {

		listaTours.remove((int) key);
		listaTours.put((int) tour.getCodigoIdentificacion(), tour);

	}

	//GETTER
	public Tour getTour (HashMap <Integer, Tour> listaTours, long codigoIdentificacion)
	{
		return listaTours.get((int)codigoIdentificacion);
	}


}
