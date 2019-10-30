package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

/*import Entidad.Cliente;
import Entidad.Tour;*/

public class Reserva {

	//ATRIBUTOS
	private ArrayList 	<ServicioAdicional> serviciosAdicionales;
	private long 		numeroReserva;
	private LocalDateTime 	fecha;
	private boolean 	pagado;
	private int 		cantidadPersonas;
	private Cliente 	clienteReserva;
	private Tour 		tourReservado;

	//SETTERS AND GETTERS
	public ArrayList<ServicioAdicional> getServiciosAdicionales() {
		return serviciosAdicionales;
	}
	public void setServiciosAdicionales(ArrayList<ServicioAdicional> serviciosAdicionales) {
		this.serviciosAdicionales = serviciosAdicionales;
	}
	public long getNumeroReserva() {
		return numeroReserva;
	}
	public void setNumeroReserva(long numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public boolean getPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public Cliente getClienteReserva() {
		return clienteReserva;
	}
	public void setClienteReserva(Cliente clienteReserva) {
		this.clienteReserva = clienteReserva;
	}
	public Tour getTourReservado() {
		return tourReservado;
	}
	public void setTourReservado(Tour tourReservado) {
		this.tourReservado = tourReservado;
	}

	//CONSTRUCTOR
	public Reserva() {
		this.numeroReserva = 1;
		this.fecha = null;
		this.pagado = false; // false = No ha sido pagado
		this.cantidadPersonas = 1;
		this.tourReservado = null;
		this.clienteReserva = null;
		this.serviciosAdicionales = new ArrayList<>();
	} 
	public Reserva(Reserva res) {
		this.numeroReserva = res.getNumeroReserva();
		this.fecha = res.getFecha();
		this.pagado = res.getPagado(); // false = No ha sido pagado
		this.cantidadPersonas = res.getCantidadPersonas();
		this.tourReservado = res.getTourReservado();
		this.clienteReserva = res.clienteReserva;
		this.serviciosAdicionales = new ArrayList<ServicioAdicional>();
		for(ServicioAdicional servi : res.serviciosAdicionales) {
			serviciosAdicionales.add(servi);
		}
	}
	public Reserva(ArrayList<ServicioAdicional> serviciosAdicionales, long numeroReserva, LocalDateTime fecha, boolean pagado, int cantidadPersonas, Cliente clienteReserva, Tour tourReservado) 
        {
		this.serviciosAdicionales = serviciosAdicionales;
		this.numeroReserva = numeroReserva;
		this.fecha = fecha;
		this.pagado = pagado;
		this.cantidadPersonas = cantidadPersonas;
		this.clienteReserva = clienteReserva;
		this.tourReservado = tourReservado;
	}
	public Reserva(long numeroReserva, LocalDateTime fecha, boolean pagado, int cantidadPersonas) {
		this.numeroReserva = numeroReserva;
		this.fecha = fecha;
		this.pagado = pagado;
		this.cantidadPersonas = cantidadPersonas;
	}
	public Reserva(long numeroReserva, LocalDateTime fecha, boolean pagado, int cantidadPersonas, Tour tourReservado, Cliente clienteReserva) {
		this.numeroReserva = numeroReserva;
		this.fecha = fecha;
		this.pagado = pagado;
		this.cantidadPersonas = cantidadPersonas;
		this.tourReservado = tourReservado;
		this.clienteReserva = clienteReserva;
		this.serviciosAdicionales = new ArrayList<>();
	}
        
        public ArrayList<ServicioAdicional> retornarServiciosAdicionalesTransporteParticular()
        {
            
            ArrayList<ServicioAdicional> particulares = new ArrayList<>();
            
            for(int i = 0; i < serviciosAdicionales.size(); i++)
            {
                if(serviciosAdicionales.get(i) instanceof Transporte)
                {
                    if( ((Transporte) serviciosAdicionales.get(i)).getTipo() == TipoTransporte.PARTICULAR)
                    {
                        particulares.add(serviciosAdicionales.get(i));
                    }
                }
            }
            return particulares;
        }
        
}
