package control;

import model.Cliente;
import model.Reserva;
import model.ServicioAdicional;
import model.Tour;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Concierto;
import model.Transporte;
import model.Ecologico;
import model.Empresarial;
import java.util.Date;

public class ControlAgencia {

	//ATRIBUTOS
	private GestionTours gestionTours;
	private GestionCliente gestionCliente;
	private HashMap <Integer, Tour> listaTours = new HashMap <Integer, Tour>();
	private ArrayList <Reserva> reservas;
	private HashMap <Integer,Cliente> listaClientes = new HashMap <Integer,Cliente>();

	//GETTERS AND SETTERS
	public GestionTours getGestionTours() {
		return gestionTours;
	}
	public void setGestionTours(GestionTours gestionTours) {
		this.gestionTours = gestionTours;
	}
	public GestionCliente getGestionCliente() {
		return gestionCliente;
	}
	public void setGestionCliente(GestionCliente gestionCliente) {
		this.gestionCliente = gestionCliente;
	}
	public HashMap<Integer, Tour> getListaTours() {
		return listaTours;
	}
	public void setListaTours(HashMap<Integer,Tour> listaTours) {
		this.listaTours = listaTours;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	public HashMap <Integer, Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(HashMap <Integer,Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	//CONSTRUCTOR
	public ControlAgencia() {	
		this.gestionTours = new GestionTours();
		this.listaTours = gestionTours.crearTour();

		this.gestionCliente = new GestionCliente();
		this.listaClientes = gestionCliente.crearCliente();

		this.reservas = new ArrayList<Reserva>();

	}

	//--------------METODOS DE TOUR-----------------------------------------------------------
	public boolean tourTieneReserva(long codigo) {
		for(Reserva res : reservas) {
			if(listaTours.get((int)codigo).equals(res.getTourReservado()))
				return true;
		}
		return false;
	}

	//--------------METODOS DE CLIENTES--------------------------------------------------------

	public boolean clienteTieneReserva(long codigo) {
		for(Reserva res : reservas) {
			if(listaClientes.get((int) codigo).equals(res.getClienteReserva()))
				return true;
		}
		return false;
	}

	//Listar clientes
	public void ListaClientes() {

		for(Cliente cli : listaClientes.values()) {
			gestionCliente.listarClientes(cli);
			for(Reserva res : reservas) {
				if(res.getClienteReserva().equals(cli)) {
					System.out.println("Cantidad de acompannantes del tour "
							+ res.getTourReservado().getCodigoIdentificacion() + " : " + res.getCantidadPersonas());
				}
			}
		}

	}

	//------------------------METODOS PARA LAS RESERVAS-----------------------------------------
	public boolean existeReserva(LocalDateTime fecha, long codigoCliente, long codigoTour) {
		for(Reserva res : reservas) {
			if(	res.getClienteReserva().equals(listaClientes.get((int) codigoCliente)) && 
					res.getTourReservado().equals(listaTours.get((int) codigoTour)) && 
					res.getFecha().equals(fecha)) 
				return true;

		}

		return false;
	}
	public int existeReserva(long reserva) {
		for(Reserva res : reservas) {
			if(res.getNumeroReserva() == reserva)
				return reservas.indexOf(res);
		}
		return -1;
	}
	public boolean reservaMenorADosDias(LocalDateTime fecha) {
		LocalDateTime pasadoManiana = LocalDateTime.now().plusDays(2);
		if (fecha.isBefore(pasadoManiana)) {
			return true;
		}
		return false;
	}

	public void agregarReserva(long numeroReserva, LocalDateTime fechaReserva, boolean pagado ,int cantidadPersonas, Tour tourReservado, Cliente clienteReservado){
		Reserva res = new Reserva(numeroReserva,fechaReserva,pagado,cantidadPersonas, tourReservado, clienteReservado);
		this.reservas.add(res);

	}


	public Reserva getReserva(long reserva) {
		for(Reserva res : reservas) {
			if(res.getNumeroReserva() == reserva)
				return res;
		}
		return null;

	}
        
	public double costoReservaSinAdicionales(long reserva) {
		Reserva res = getReserva(reserva);
		double costo = -1;
		if(res != null) {
			costo = res.getTourReservado().calcularPrecio();
		}
		return costo;
	}
        
	public double costoReservaConAdicionales(long reserva) {
		Reserva res = getReserva(reserva);
		double costo = costoReservaSinAdicionales(reserva);
		double costoTotalServicios = costoServiciosAdicionales(reserva);
		costo += costoTotalServicios;
		return costo;
	}
        
	public void modificarReserva(long reserva, Reserva res) {
		int index = existeReserva(reserva);
		if(index >= 0) {
			this.reservas.set(index, res);
		}

	}
        
	public void eliminarReserva(long reserva) {
		int index = existeReserva(reserva);
		if(reserva >= 0) {
			this.reservas.remove(index);
		}

	}
        
	public void listarReservas() {
		for(Reserva res : reservas) {
			System.out.println("Numero de reserva:  " + res.getNumeroReserva());
			System.out.println(" Fecha:  " + 
					res.getFecha().getDayOfMonth() + "/" +
					res.getFecha().getMonthValue() + "/" +
					res.getFecha().getYear());
			System.out.println("Pagado:  " + res.getPagado());
			System.out.println("Cantidad de personas:  " + res.getCantidadPersonas());
		}

	}
        
	public int listarReservasUnTour(long codigoTour, LocalDateTime fecha) {
		int sumador = 0;
		for(Reserva res : reservas) {
			if(codigoTour == res.getTourReservado().getCodigoIdentificacion() && res.getFecha().isEqual(fecha)){
				System.out.println("===================================================================");
				System.out.println("Nombre del cliente: " + res.getClienteReserva().getNombreCompleto());
				System.out.println("Numero de acompaniantes:  " + res.getCantidadPersonas());
				sumador += res.getCantidadPersonas(); 
			}
		}
		return sumador;

	}

	//-----------------------METODOS PARA SERVICIO ADICIONAL----------------------------------------

	public void agregarServicioAdicional(long reserva, ServicioAdicional adicional) { //OK E2

		int indice = existeReserva(reserva);
		if(indice >= 0) {
			reservas.get(indice).getServiciosAdicionales().add(adicional);
		}

	}
           
	public double costoServiciosAdicionales(long reserva) { //OK E2
		Reserva res = getReserva(reserva);
		double costoTotalServicios = 0;
		if(res.getServiciosAdicionales() != null) {
			for(ServicioAdicional adi : res.getServiciosAdicionales()) {
				costoTotalServicios += adi.calcularPrecio();
			}
		}
		//costoTotalServicios *= res.getCantidadPersonas();
		return costoTotalServicios;
	}
        
	public void eliminarServicioAdicional(long numReserva,long codigoServicio) { //OK E2
		Reserva res = getReserva(numReserva);
		if(res != null) {
			for(ServicioAdicional adi : res.getServiciosAdicionales()) {
				if(codigoServicio == adi.getCodigoServicio()) {
					res.getServiciosAdicionales().remove(adi);
				}
			}
		}
	}
        
	public boolean eliminarServicioAdicional(long codigo, Reserva res) {
		for(ServicioAdicional serv : res.getServiciosAdicionales()) {
			if(serv.getCodigoServicio() == codigo) {
				res.getServiciosAdicionales().remove(res.getServiciosAdicionales().indexOf(serv));
				return true;
			}
		}
		return false;
	}

	//---------------------------------EXTRAS-------------------------------------------------------------------------
	public  boolean validarDigitos(long codigo) {
		if(codigo < 1000000 || codigo > 9999999)
			return false;
		return true;
	}
	public long generarNumeroReserva() {
		return reservas.size() + 100;
	}

	public LocalDateTime solicitarFecha() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Fecha de la reserva:");
		System.out.print("Dia-> ");
		int dia = sc.nextInt();
		System.out.print("Mes-> ");
		int mes = sc.nextInt();
		System.out.print("Año-> ");
		int year = sc.nextInt();
		LocalDateTime fecha;
		return LocalDateTime.of(year, mes, dia, 8, 0);

	}

	public void mostrarMenuModificarReserva(Reserva reserva) {
		System.out.println("Datos de la reserva " + reserva.getNumeroReserva());
		System.out.println("1. Modificar fecha: " + reserva.getFecha().getDayOfMonth() + "/" +reserva.getFecha().getMonthValue() + "/" +reserva.getFecha().getYear());
		System.out.println("2. Modificar cantidad de personas: " + reserva.getCantidadPersonas());
		System.out.println("3. Agregar o eliminar un servicio adicional");

		for (ServicioAdicional servicio : reserva.getServiciosAdicionales()) 
                {
                    if(servicio instanceof Concierto)
                    {
                        System.out.println("CONCIERTO DE CODIGO: " + servicio.getCodigoServicio());
                    }
                    if(servicio instanceof Transporte)
                    {
                        System.out.println("TRANSPORTE DE CODIGO: " + servicio.getCodigoServicio());
                    }             
		}

		System.out.println("0. Volver");
		System.out.print  ("   Digita tu opcion (1, 2 0 3)-> ");
	}

	public  boolean fechaUnicaReserva(LocalDateTime fecha) {
		for (Reserva reserva : reservas) {
			if (reserva.getFecha().isEqual(fecha)) {
				return false;
			}
		}
		return true;
	}
	public void pagado(long reserva) {
		int index = existeReserva(reserva);
		if(index >= 0) {
			reservas.get(index).setPagado(true);
		}
	}
        
        //METODOS DE LA SEGUNDA ENTREGA (interaccion de objetos)
        
        public HashMap<Integer, Tour> retornarEcologicos()
        {
            
            HashMap<Integer, Tour> mapa = new HashMap<>();
            
            for(Tour elTour: listaTours.values())
            {
                if(elTour instanceof Ecologico)
                {
                    mapa.put((int) elTour.getCodigoIdentificacion(), elTour);
                }
            }
            
            return mapa;
        }
        
        public ArrayList<Reserva> filtrarAsociadasEmpresarial()
        {
            
            ArrayList<Reserva> asociadas = new ArrayList<>();
            
            for(int i = 0; i < reservas.size(); i++)
            {
                if(reservas.get(i).getTourReservado() instanceof Empresarial)
                {
                    asociadas.add(reservas.get(i));
                }
            }
            
            return asociadas;
            
        }
        
        public double precioReservasToursEcologicosPorFecha(Date fechaInicial, Date fechaFinal)
        {
            
            double total = 0;
            
            for(int i = 0; i < reservas.size(); i++)
            {
                if(reservas.get(i).getTourReservado() instanceof Ecologico) //SI ESTA RELACIONADA CON UN TOUR ECOLOGICO
                {
                    if(reservas.get(i).getTourReservado().getFechaSalida().after(fechaInicial) && reservas.get(i).getTourReservado().getFechaRegreso().before(fechaFinal)) //SI LAS FECHAS DE LA RESERVA ESTAN DENTRO DEL RANGO DE FECHAS
                    {
                        total += costoReservaConAdicionales(reservas.get(i).getNumeroReserva());
                    }
                }
            }
            
            return total;
            
        }
}
