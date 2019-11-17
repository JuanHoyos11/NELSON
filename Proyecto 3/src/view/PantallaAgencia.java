
package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import control.ControlAgencia;
import java.text.ParseException;

import model.Tour;

import model.Cliente;

import model.Reserva;

import model.ServicioAdicional;

import model.Concierto;
import model.Ecologico;
import model.Empresarial;

import enumeradores.TipoTransporte;

import model.Transporte;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import enumeradores.TipoEmpresa;

public class PantallaAgencia {

	private static ControlAgencia agencia;

	public static void main(String [] args)
	{
		int opc;
		Scanner scanner = new Scanner(System.in);
		agencia = new ControlAgencia(); 

		do {
			mostrarMenu();
			opc = scanner.nextInt();

			switch(opc) {
			case 1: 
				listarTours();
				break;
			case 2:
				insertarTour();
				break;
			case 3:
				modificarTour();
				break;
			case 4:
				eliminarTour();
				break;
			case 5:
				listarClientes();
				break;
			case 6:
				insertarCliente();
				break;
			case 7:
				modificarCliente();
				break;
			case 8:
				eliminarCliente();
				break;
			case 9:
				reservarTour();
				break;
			case 10:
				modificarReserva();
				break;
			case 11:
				eliminarReserva();
				break;
			case 12:
				listarReservas();
				break;
			case 13:
				listarReservasUnTour();
				break;
			case 14:
                                buscarCliente();
                            break;
                        case 15:
                                buscarTour();
                            break;
                        case 16:
                            	System.out.println("Fin del progrma");
				break;
			default:
				System.out.println("opcion invalida");
				break;
			}
                        
			systemPause();

		}while(opc != 16);
	}
	public static void mostrarMenu() {
		System.out.println("");
		System.out.println(" 1. Ver listado de Tours disponibles"); 
		System.out.println(" 2. Insertar Tour");
		System.out.println(" 3. Modificar Tour");
		System.out.println(" 4. Eliminar Tour");
		System.out.println(" 5. Ver listado de Clientes registrados en el sistema");
		System.out.println(" 6. Insertar Cliente");
		System.out.println(" 7. Modificar datos de Cliente");
		System.out.println(" 8. Eliminar un Cliente");
		System.out.println(" 9. Reservar Tour");
		System.out.println("10. Modificar reserva de Tour");
		System.out.println("11. Eliminar reserva de Tour");
		System.out.println("12. Ver listado de Reservas existentes");
		System.out.println("13. Ver listado de Reservas para un Tour y una fecha de especifica");
                System.out.println("14. Buscar cliente");
                System.out.println("15. Buscar tour");
		System.out.println("16. Salir");
		System.out.print(" Digita tu opcion-> ");
	}

	public static void systemPause() {
		System.out.println("Press Any Key To Continue...");
		new java.util.Scanner(System.in).nextLine();
	}

	public static void listarTours() {
		agencia.getGestionTours().listarTours(agencia.getListaTours());
	}

	public static void insertarTour() {

		Scanner scanner = new Scanner(System.in);
                String nom, lugar, hora, salidaCadena, regresoCadena;
                Date salida = new Date();
                Date regreso = new Date();
                double precio;
                long codId;
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
		System.out.println("Digite el codigo del tour: ");
                codId = scanner.nextLong();
  
		//VALIDAR LOS 7 DIGITOS Y QUE EL TOUR NO EXISTA
                
		while(!(agencia.validarDigitos(codId)) || agencia.getGestionTours().getTour(agencia.getListaTours(), codId) != null)
                {
			System.out.println("Codigo invalido, vuelva a intentar: ");
			codId = scanner.nextLong();
		}
                /*
		scanner.reset();
		String dummy = scanner.nextLine();
*/
		System.out.println("Digite el nombre del comercial: ");
		nom = scanner.next();
                
		System.out.println("Digite el lugar de partida: ");
		lugar = scanner.next();
                
		System.out.println("Digite la hora de partida: ");
		hora = scanner.next();
                
		System.out.println("Digite el precio: ");
		precio = scanner.nextDouble();
                
                System.out.println("Ingrese la fecha de salida (yyyy-MM-dd): ");
                salidaCadena = scanner.next();
            try {
                salida = sdf.parse(salidaCadena);
            } catch (ParseException ex) {
                Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                System.out.println("Ingrese la fecha de regreso (yyyy-MM-dd): ");
                regresoCadena = scanner.next();
            try {
                regreso = sdf.parse(regresoCadena);
            } catch (ParseException ex) {
                Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                int naturalezaTour;
                
                System.out.println("Quiere ingresar un nuevo tour General, tour Ecologico, o tour Empresarial? (General = 1/ Ecologico = 2/ Empresarial = 3): ");
                naturalezaTour = scanner.nextInt();
                
                if(naturalezaTour == 1)
                {
                    Tour nuevoTour = new Tour(codId, nom, lugar, hora, precio, salida, regreso);
                    
                    agencia.getGestionTours().insertarTour(agencia.getListaTours(), nuevoTour);
                    System.out.println("Tour agregado!");
                }
                else if(naturalezaTour == 2)
                {
                    double impLocal;
                    
                    System.out.println("Ingrese el impuesto local: ");
                    impLocal = scanner.nextDouble();
                    
                    Ecologico nuevoEco = new Ecologico(true, impLocal, true, codId, nom, lugar, hora, precio, salida, regreso);
                    
                    agencia.getGestionTours().insertarTour(agencia.getListaTours(), nuevoEco);
                    System.out.println("Tour Ecologico agregado!");
                }
                else if(naturalezaTour == 3)
                {
                    String nomEmp, tipoCadena;
                    TipoEmpresa tipo;
                    
                    System.out.println("Ingrese el nombre de la empresa: ");
                    nomEmp = scanner.next();
                    
                    System.out.println("Ingrese el tipo de empresa (TECNOLOGIA/TURISMO/MEDIO_COMUNICACION): ");
                    tipoCadena = scanner.next();
                    
                    tipo = TipoEmpresa.TURISMO;
                    if(tipoCadena.compareTo("TECNOLOGIA") == 0)
                    {
                        tipo = TipoEmpresa.TECNOLOGIA;
                    }
                    if(tipoCadena.compareTo("TURISMO") == 0)
                    {
                        tipo = TipoEmpresa.TURISMO;
                    }
                    if(tipoCadena.compareTo("MEDIO_COMUNICACION") == 0)
                    {
                        tipo = TipoEmpresa.MEDIO_COMUNICACION;
                    }
                    
                    Empresarial nuevoEmp = new Empresarial(nomEmp, true, tipo, codId, nom, lugar, hora, precio, salida, regreso);
                    
                    agencia.getGestionTours().insertarTour(agencia.getListaTours(), nuevoEmp);
                    System.out.println("Tour Empresarial agregado!");
                }
	}


            public static void modificarTour() {

		Tour tourMod = new Tour();
		Scanner sc = new Scanner(System.in);
		long codigo;
		int  opc;
		long key;
		System.out.println("Digite el codigo del Tour que desea modificar: ");
		codigo = sc.nextLong();
		tourMod = agencia.getGestionTours().getTour(agencia.getListaTours(), codigo);
		key = tourMod.getCodigoIdentificacion();
		
		if(tourMod != null) {

			do {
				System.out.println("Modificar los datos del tour");
				System.out.println("1. Codigo del tour: "  + tourMod.getCodigoIdentificacion());
				System.out.println("2. Nombre: "           + tourMod.getNombreComercial());
				System.out.println("3. Lugar de partida: " + tourMod.getLugarPartita());
				System.out.println("4. Hora de partida: "  + tourMod.getHoraPartida());
				System.out.println("5. Precio: "           + tourMod.getPrecio());
                                System.out.println("6. Fecha salida: " + tourMod.getFechaSalida().toString());
                                System.out.println("7. Fecha de regreso: " + tourMod.getFechaRegreso().toString());
                                if(tourMod instanceof Ecologico)
                                {
                                    System.out.println("8. Impuesto local: " + ((Ecologico)tourMod).getImpuestoLocal());
                                }
                                else if(tourMod instanceof Empresarial)
                                {
                                    System.out.println("9. Nombre de la empresa: " + ((Empresarial)tourMod).getNombreEmpresa());
                                    System.out.println("10. Tipo de la empresa: " + ((Empresarial)tourMod).getTipo().toString());
                                }
				System.out.println("0. Regresar al menu");
				System.out.print  ("   Digita tu opcion -> ");

				opc = sc.nextInt();
				sc.reset();
				String dummy = sc.nextLine();

				switch (opc) {
				case 1 : {
					System.out.print("Digita el nuevo codigo-> ");
					long codigoTemporal = sc.nextLong();
					if (agencia.getGestionTours().getTour(agencia.getListaTours(), codigoTemporal) != null) {
						System.out.println("Este codigo ya esta asignado!");
					}
					else if(agencia.validarDigitos(codigoTemporal) == false) {
						System.out.println("Error, codigo no valido");
					}
					else {
						tourMod.setCodigoIdentificacion(codigoTemporal);
					}
				} break;
				case 2 : {
					System.out.print("Digita el nuevo nombre-> ");
					tourMod.setNombreComercial(sc.nextLine());
				} break;
				case 3 : {
					System.out.print("Digita el nuevo lugar de partida-> ");
					tourMod.setLugarPartita(sc.nextLine());
				} break;
				case 4 : {
					System.out.print("Digita la nueva hora de partida-> ");
					tourMod.setHoraPartida(sc.nextLine());
				} break;
				case 5 : {
					System.out.print("Digita el nuevo precio-> ");
					tourMod.setPrecio(sc.nextDouble());
				} break;
                                case 6:
                                    String salidaCad;
                                    Date salida;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    
                                        System.out.println("Digita la nueva fecha de salida (yyyy-MM-dd): ");
                                        salidaCad = sc.next();  
                                
                                    try {
                                        salida = sdf.parse(salidaCad);
                                        tourMod.setFechaSalida(salida);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 7:
                                    String regresoCad;
                                    Date regreso;
                                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                    
                                        System.out.println("Digita la nueva fecha de regreso (yyyy-MM-dd): ");
                                        regresoCad = sc.next(); 
                                        
                                    try {
                                        regreso = sdf2.parse(regresoCad);
                                        tourMod.setFechaSalida(regreso);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(PantallaAgencia.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 8: 
                                    	System.out.print("Digita el nuevo impuesto local-> ");
					((Ecologico)tourMod).setImpuestoLocal(sc.nextDouble());
                                    break;  
                                case 9: 
                                        System.out.print("Ingrese el nuevo nombre de la empresa-> ");
					((Empresarial)tourMod).setNombreEmpresa(sc.next());
                                    break; 
                                case 10:
                                    String tipoCad;
                                    TipoEmpresa tipo;
                                    
                                        System.out.print("Ingrese el nuevo tipo de la empresa-> ");
					tipoCad = sc.next();
                                        
                                        tipo = TipoEmpresa.TURISMO;
                                           if(tipoCad.compareTo("TECNOLOGIA") == 0)
                                           {
                                               tipo = TipoEmpresa.TECNOLOGIA;
                                           }
                                           if(tipoCad.compareTo("TURISMO") == 0)
                                           {
                                               tipo = TipoEmpresa.TURISMO;
                                           }
                                           if(tipoCad.compareTo("MEDIO_COMUNICACION") == 0)
                                           {
                                               tipo = TipoEmpresa.MEDIO_COMUNICACION;
                                           }
                                           
                                           ((Empresarial)tourMod).setTipo(tipo);
                                        
                                    break;
				}
			} while (opc != 0);

			agencia.getGestionTours().modificarTour(agencia.getListaTours(),tourMod,key);
			System.out.println("El tour ha sido modificado!");
		}
		else {
			System.out.println("Error, el codigo no fue encontrado!");
		}
	}

	public static void eliminarTour() {
		Scanner sc = new Scanner(System.in);
		long codigo;
		int opc;
		Tour tourMod = new Tour();

		System.out.println("Digite el codigo del Tour que desea eliminar: ");
		codigo = sc.nextLong();
		tourMod = agencia.getGestionTours().getTour(agencia.getListaTours(), codigo);

		if(tourMod != null) {
			if(!(agencia.tourTieneReserva(tourMod.getCodigoIdentificacion()))) {
				System.out.println("Seguro que desea eliminar el Tour? (SI = 1/NO = 0)");
				opc = sc.nextInt();
				if(opc == 1) {
					agencia.getGestionTours().eliminarTour(agencia.getListaTours(), tourMod);
					System.out.println("Fin del proceso, el tour se ha eliminado");
				}
				else {
					System.out.println("Fin del proceso, no se elimino el tour");
				}
			}
			else {
				System.out.println("Fin del proceso, no se elimino el tour");
			}
		}
		else
			System.out.println("El tour no existe");

	}



	public static void listarClientes() {
		agencia.ListaClientes();
	}

	public static void insertarCliente() {
		Scanner sc = new Scanner(System.in);
		Cliente nuevoCliente = new Cliente();

		System.out.println("Digite el numero de identificacion: ");
		nuevoCliente.setNumeroIdentificacion(sc.nextLong());
		while(agencia.getGestionCliente().getCliente(nuevoCliente.getNumeroIdentificacion(), agencia.getListaClientes()) != null) {
			System.out.println("Error, el Id ya existe, intente de nuevo: ");
			nuevoCliente.setNumeroIdentificacion(sc.nextLong());
		}

		sc.reset();
		String dummy = sc.nextLine();

		System.out.println("Digite el nombre completo: ");
		nuevoCliente.setNombreCompleto(sc.nextLine());
		System.out.println("Digite el telefono de contacto: ");
		nuevoCliente.setTelefonoContacto(sc.nextLine());

		agencia.getGestionCliente().insertarCliente(nuevoCliente, agencia.getListaClientes());
		System.out.println("Cliente agregado!");


	}
	public static void modificarCliente() {
		Scanner sc = new Scanner (System.in);

		System.out.print("Digite el numero de identificacion -> ");
		long codigo = sc.nextLong();

		Cliente cliente = agencia.getGestionCliente().getCliente(codigo, agencia.getListaClientes());
		long key = cliente.getNumeroIdentificacion();
		if (cliente != null) {

			int opcion;
			do {
				System.out.println("Modificar los datos del cliente");
				System.out.println("1. Numero de identificacion: " + cliente.getNumeroIdentificacion());
				System.out.println("2. Nombre del cliente:       " + cliente.getNombreCompleto());
				System.out.println("3. Telefono de contacto:     " + cliente.getTelefonoContacto());
				System.out.println("0. Regresar al menu");
				System.out.print  ("   Digita tu opcion -> ");

				opcion = sc.nextInt();

				sc.reset();
				String dummy = sc.nextLine();

				switch (opcion) {
				case 1 : {
					System.out.print("Digita el nuevo numero de identificacion-> ");
					long codigoTemporal = sc.nextLong();

					Cliente clienteTemporal = agencia.getGestionCliente().getCliente(codigoTemporal, agencia.getListaClientes());
					if (clienteTemporal != null) {
						System.out.println("Este numero de identificacion ya fue asignado!");
					}
					else {
						cliente.setNumeroIdentificacion(codigoTemporal);
					}
				} break;
				case 2 : {
					System.out.print("Digita el nuevo nombre del cliente-> ");
					cliente.setNombreCompleto(sc.nextLine());
				} break;
				case 3 : {
					System.out.print("Digita el nuevo telefono-> ");
					cliente.setTelefonoContacto(sc.nextLine());
				} break;
				}
			} while (opcion != 0);

			agencia.getGestionCliente().modificarCliente(agencia.getListaClientes(),cliente,key);
			System.out.println("El cliente ha sido modificado!");
		}
		else {
			System.out.println("Error, el codigo no fue encontrado!");
		}
	}

	public static void eliminarCliente() {

		Scanner sc = new Scanner(System.in);
		long ID;
		int  opc;
		Cliente cliente = new Cliente();

		System.out.print("Digite el numero de la identificacion del cliente que desea eliminar: ");
		ID = sc.nextLong();

		cliente = agencia.getGestionCliente().getCliente(ID, agencia.getListaClientes());
		if(cliente != null) {
			if(!(agencia.clienteTieneReserva(ID))) {

				System.out.println("Seguro que desea eliminar al cliente? (SI = 1/ NO = 0)");
				opc = sc.nextInt();
				if(opc == 1) {
					agencia.getGestionCliente().eliminarCliente(agencia.getListaClientes(), cliente);
					System.out.println("El cliente ha sido eliminado!");
				}
				else
					System.out.println("Error, fin del proceso");

			}
			else
				System.out.println("Error, fin del proceso");

		}
		else
			System.out.println("El usuario no existe");

	}
	public static void reservarTour() 
        {

		Scanner sc = new Scanner(System.in);
		long codigoTour, ID, numeroReserva;
		int cantiPersonas,opc;
		boolean pagado = false;
		LocalDateTime fechaReserva;
		Tour tour = new Tour();
		Cliente cliente = new Cliente();

		System.out.print("Digite el codigo del tour: ");
		codigoTour = sc.nextLong();
		tour = agencia.getGestionTours().getTour(agencia.getListaTours(), codigoTour);

		System.out.print("Digite el ID del cliente: ");
		ID = sc.nextLong();
		cliente = agencia.getGestionCliente().getCliente(ID, agencia.getListaClientes());

		if(cliente != null && tour != null) //VERIFICAR QUE EXISTA EL CLIENTE Y EL TOUR
                {  
			fechaReserva = agencia.solicitarFecha();
			//validar que no exista una reserva y que la fecha sea despues de pasado ma�ana
			while(agencia.existeReserva(fechaReserva, cliente.getNumeroIdentificacion(), tour.getCodigoIdentificacion())
                                || (agencia.reservaMenorADosDias(fechaReserva))) 
                        {
				System.out.println("ERROR! La fecha ya esta reservada y/o No se puede reservar un tour con menos de dos dias de antelacion a la fecha de la reserva");
				fechaReserva = agencia.solicitarFecha();
			}

			System.out.println("Cuantas personas van a asistir al tour? ");
			cantiPersonas = sc.nextInt();
			numeroReserva = agencia.generarNumeroReserva();

			//agencia.agregarReserva(numeroReserva, fechaReserva, pagado, cantiPersonas, tour, cliente);
			System.out.println("Desea adquirir algun servicio adicional? (SI = 1/ NO = 0)");
			opc = sc.nextInt();

			while(opc == 1) 
                        {
				
                                    int opci;

                                    System.out.println("Quiere agregar un Concierto o un Transporte? (Concierto = 1/ Transporte = 2): ");
                                    opci = sc.nextInt();

                                    long codServ;
                                    String nombreServ, artista, lugar, horaIngreso, tipoCadena;
                                    double precio, distancia;
                                    int numPasajeros;
                                    TipoTransporte tipo;

                                    System.out.println("Digite el codigo del servicio: ");
                                    codServ = sc.nextLong();

                                    System.out.println("Ingrese el nombre del servicio: ");
                                    nombreServ = sc.next();

                                    System.out.println("Ingrese el precio del servicio: ");
                                    precio = sc.nextDouble();

                                    if(opci == 1)
                                    {
                                        System.out.println("Ingrese el nombre del artista: ");
                                        artista = sc.next();

                                        System.out.println("Ingrese el nombre del lugar: ");
                                        lugar = sc.next();

                                        System.out.println("Digite la hora de ingreso: ");
                                        horaIngreso = sc.next();

                                        Concierto elConcierto = new Concierto(artista, lugar, horaIngreso, codServ, nombreServ, precio);
                                        agencia.getReservas().get(agencia.getReservas().size()-1).getServiciosAdicionales().add(elConcierto);
                                    }
                                    if(opci == 2)
                                    {

                                        System.out.println("Ingrese la distancia: ");
                                        distancia = sc.nextDouble();

                                        System.out.println("Ingrese el tipo de transporte (TAXI, MINIVAN, PARTICULAR): ");
                                        tipoCadena = sc.next();

                                        tipo = TipoTransporte.TAXI; //PARA QUE ESTE INICIALIZADO EN TODOS LOS CASOS Y COMPILE EL CODIGO

                                        if(tipoCadena.compareTo("TAXI") == 0)
                                        {
                                            tipo = TipoTransporte.TAXI;
                                        }
                                        if(tipoCadena.compareTo("MINIVAN") == 0)
                                        {
                                            tipo = TipoTransporte.MINIVAN;
                                        }
                                        if(tipoCadena.compareTo("PARTICULAR") == 0)
                                        {
                                            tipo = TipoTransporte.PARTICULAR;
                                        }

                                        System.out.println("Digite el numero de pasajeros: ");
                                        numPasajeros = sc.nextInt();

                                        Transporte elTransporte = new Transporte(distancia, tipo, numPasajeros, codServ, nombreServ, precio);
                                        agencia.getReservas().get(agencia.getReservas().size()-1).getServiciosAdicionales().add(elTransporte);

                                    }
                                    
                            System.out.println("El precio sin adicionales de su reserva: " + agencia.costoReservaSinAdicionales(numeroReserva));
                            System.out.println("El precio de sus servicios adicionales: " + agencia.costoServiciosAdicionales(numeroReserva));
                            System.out.println("El precio Total de su reserva: " + agencia.costoReservaConAdicionales(numeroReserva));

                            System.out.println("Desea realizar el pago? (SI = 1/ NO = 0)");
                            opc = sc.nextInt();
                            if(opc == 1) {
                                    agencia.pagado(numeroReserva);
                            }
                            
                            System.out.println("Desea adquirir algun otro servicio adicional? (SI = 1/ NO = 0)");
                            opc = sc.nextInt();
                            
                        }
		

                }
                else
			System.out.println("Error, no existe el cliente o el tour");
        }        

	public static void modificarReserva() {
		Scanner sc = new Scanner(System.in);
		long numReserva;
		int index, opc;

		System.out.println("Digite el numero de la reserva: ");
		numReserva = sc.nextLong();
		index = agencia.existeReserva(numReserva);
		if(index >= 0) {
			Reserva res = agencia.getReserva(numReserva);
			Reserva original = new Reserva(res);
			do {
				agencia.mostrarMenuModificarReserva(res);
				opc = sc.nextInt();
				switch (opc) {

				case 1:
					LocalDateTime fecha = agencia.solicitarFecha();
					if (agencia.fechaUnicaReserva(fecha)) {
						if(agencia.reservaMenorADosDias(fecha)) {
							System.out.println("Lo siento!, la reserva debe hacerse con al menos dos dias de antelacion.");
						}
						else
							res.setFecha(fecha);
					}
					else {
						System.out.println("Lo siento!, ya existe una reserva agendada para esa fecha.");
					}
					break;
				case 2:
					System.out.print("Digite la nueva cantidad de personas: ");
					int cantiTemporal = sc.nextInt();
					while(cantiTemporal <= 0) {
						System.out.println("ERROR, debe de ir al menos una persona");
						cantiTemporal = sc.nextInt();
					}
					res.setCantidadPersonas(cantiTemporal);
					break;

				case 3:
					long opci;
					if(res.getServiciosAdicionales() == null) 
                                        {
						System.out.println("No tiene servicios adicionales, desea agregar alguno? (SI = 1/NO = 0): ");
						opci = sc.nextInt();
						if(opci == 1) 
                                                {
                                                    System.out.println("Quiere agregar un Concierto o un Transporte? (Concierto = 1/ Transporte = 2): ");
                                                    opci = sc.nextInt();
                                                    
                                                    long codServ;
                                                    String nombreServ, artista, lugar, horaIngreso, tipoCadena;
                                                    double precio, distancia;
                                                    int numPasajeros;
                                                    TipoTransporte tipo;

                                                    System.out.println("Digite el codigo del servicio: ");
                                                    codServ = sc.nextLong();

                                                    System.out.println("Ingrese el nombre del servicio: ");
                                                    nombreServ = sc.next();

                                                    System.out.println("Ingrese el precio del servicio: ");
                                                    precio = sc.nextDouble();
                                                    
                                                    if(opci == 1)
                                                    {
                                                        System.out.println("Ingrese el nombre del artista: ");
                                                        artista = sc.next();
                                                        
                                                        System.out.println("Ingrese el nombre del lugar: ");
                                                        lugar = sc.next();
                                                        
                                                        System.out.println("Digite la hora de ingreso: ");
                                                        horaIngreso = sc.next();
                                                        
                                                        Concierto elConcierto = new Concierto(artista, lugar, horaIngreso, codServ, nombreServ, precio);
                                                        res.getServiciosAdicionales().add(elConcierto);
                                                    }
                                                    if(opci == 2)
                                                    {
                                                        
                                                        System.out.println("Ingrese la distancia: ");
                                                        distancia = sc.nextDouble();
                                                        
                                                        System.out.println("Ingrese el tipo de transporte (TAXI, MINIVAN, PARTICULAR): ");
                                                        tipoCadena = sc.next();
                                                        
                                                        tipo = TipoTransporte.TAXI; //PARA QUE ESTE INICIALIZADO EN TODOS LOS CASOS Y COMPILE EL CODIGO
                                                        
                                                        if(tipoCadena.compareTo("TAXI") == 0)
                                                        {
                                                            tipo = TipoTransporte.TAXI;
                                                        }
                                                        if(tipoCadena.compareTo("MINIVAN") == 0)
                                                        {
                                                            tipo = TipoTransporte.MINIVAN;
                                                        }
                                                        if(tipoCadena.compareTo("PARTICULAR") == 0)
                                                        {
                                                            tipo = TipoTransporte.PARTICULAR;
                                                        }
                                                        
                                                        System.out.println("Digite el numero de pasajeros: ");
                                                        numPasajeros = sc.nextInt();
                                                        
                                                        Transporte elTransporte = new Transporte(distancia, tipo, numPasajeros, codServ, nombreServ, precio);
                                                        res.getServiciosAdicionales().add(elTransporte);
                                                    }
                                                }	
						else
                                                {
                                                    System.out.println("No se agrego ningun paquete adicional");
                                                }		
					}
					else 
                                        {
                                            System.out.println("Desea agregar o eliminar algun paquete adicional (Agregar = 1/ Eliminar = 0): ");
                                            opci = sc.nextLong();
                                            if(opci == 1) 
                                            {
                                                System.out.println("Quiere agregar un Concierto o un Transporte? (Concierto = 1/ Transporte = 2): ");
                                                    opci = sc.nextInt();
                                                    
                                                    long codServ;
                                                    String nombreServ, artista, lugar, horaIngreso, tipoCadena;
                                                    double precio, distancia;
                                                    int numPasajeros;
                                                    TipoTransporte tipo;

                                                    System.out.println("Digite el codigo del servicio: ");
                                                    codServ = sc.nextLong();

                                                    System.out.println("Ingrese el nombre del servicio: ");
                                                    nombreServ = sc.next();

                                                    System.out.println("Ingrese el precio del servicio: ");
                                                    precio = sc.nextDouble();
                                                    
                                                    if(opci == 1)
                                                    {
                                                        System.out.println("Ingrese el nombre del artista: ");
                                                        artista = sc.next();
                                                        
                                                        System.out.println("Ingrese el nombre del lugar: ");
                                                        lugar = sc.next();
                                                        
                                                        System.out.println("Digite la hora de ingreso: ");
                                                        horaIngreso = sc.next();
                                                        
                                                        Concierto elConcierto = new Concierto(artista, lugar, horaIngreso, codServ, nombreServ, precio);
                                                        res.getServiciosAdicionales().add(elConcierto);
                                                    }
                                                    if(opci == 2)
                                                    {
                                                        
                                                        System.out.println("Ingrese la distancia: ");
                                                        distancia = sc.nextDouble();
                                                        
                                                        System.out.println("Ingrese el tipo de transporte (TAXI, MINIVAN, PARTICULAR): ");
                                                        tipoCadena = sc.next();
                                                        
                                                        tipo = TipoTransporte.TAXI; //PARA QUE ESTE INICIALIZADO EN TODOS LOS CASOS Y COMPILE EL CODIGO
                                                        
                                                        if(tipoCadena.compareTo("TAXI") == 0)
                                                        {
                                                            tipo = TipoTransporte.TAXI;
                                                        }
                                                        if(tipoCadena.compareTo("MINIVAN") == 0)
                                                        {
                                                            tipo = TipoTransporte.MINIVAN;
                                                        }
                                                        if(tipoCadena.compareTo("PARTICULAR") == 0)
                                                        {
                                                            tipo = TipoTransporte.PARTICULAR;
                                                        }
                                                        
                                                        System.out.println("Digite el numero de pasajeros: ");
                                                        numPasajeros = sc.nextInt();
                                                        
                                                        Transporte elTransporte = new Transporte(distancia, tipo, numPasajeros, codServ, nombreServ, precio);
                                                        res.getServiciosAdicionales().add(elTransporte);
                                                    }
                                            }
                                            else {
                                                    long codServ;
                                                
                                                    System.out.println("Digite el codigo del servicio que quiere eliminar: ");
                                                    codServ = sc.nextLong();
                                                    
                                                    agencia.eliminarServicioAdicional(codServ, res);
                                            }
					}

					break;
				default:
					break;	
				}


			}while(opc != 0);

			System.out.println("El nuevo precio sin adicionales de su reserva: " + agencia.costoReservaSinAdicionales(numReserva));
			System.out.println("El nuevo precio de sus servicios adicionales: " + agencia.costoServiciosAdicionales(numReserva));
			System.out.println("El nuevo precio Total de su reserva: " + agencia.costoReservaConAdicionales(numReserva));
			System.out.println("Desea modificar su reserva? (SI = 1/ NO = 0)");
			opc = sc.nextInt();
			if(opc == 1) {
				agencia.modificarReserva(numReserva,res);
				System.out.println("Se ha modificado su reserva");
			}
			else
				agencia.modificarReserva(numReserva,original);
			System.out.println("No se ha modificado su reserva");
		}
		else
			System.out.println("La reserva no existe");

	}



	public static void eliminarReserva() {
		Scanner sc = new Scanner(System.in);
		long numReserva;
		int index, opc;

		System.out.println("Digite el numero de la reserva a eliminar: ");
		numReserva = sc.nextLong();
		index = agencia.existeReserva(numReserva);
		if(index >= 0) {
			System.out.println("Seguro que desea eliminar la reserva? (SI = 1/NO = 0)");
			opc = sc.nextInt();
			if(opc == 1) {
				agencia.eliminarReserva(numReserva);
				System.out.println("Se ha eliminado la reserva");
			}
			else
				System.out.println("No se ha eliminado la reserva");
		}
		else
			System.out.println("La reserva no existe");
	}
	public static void listarReservas() {
		agencia.listarReservas();
	}
	public static void listarReservasUnTour() {
		Scanner sc = new Scanner(System.in);
		long codigoTour;
		LocalDateTime fecha;
		int sumador;
		System.out.println("Digite el codigo del tour");
		codigoTour = sc.nextLong();
		fecha = agencia.solicitarFecha();
		sumador = agencia.listarReservasUnTour(codigoTour, fecha);
		if(sumador > 0) {
			System.out.println("===================================================================");
			System.out.println("Cantidad total de personas : " + sumador);
		}

	}
        
        public static void buscarCliente() //mio
        {
            Scanner leer = new Scanner(System.in);
            long numId;
            
            System.out.println("Digite el numero de identificacion del cliente: ");
            numId = leer.nextLong();
            
            Cliente elCliente = agencia.getListaClientes().get((int)numId);
            
            System.out.println("NUMERO DE ID: " + elCliente.getNumeroIdentificacion());
            System.out.println("NOMBRE: " + elCliente.getNombreCompleto());
            System.out.println("CONTACTO: " + elCliente.getTelefonoContacto());
            
        }
        
        public static void buscarTour() //mio
        {
            Scanner leer = new Scanner(System.in);
            long codId;
            
            System.out.println("Digite el codigo de identificacion del tour: ");
            codId = leer.nextLong();
            
            Tour elTour = agencia.getListaTours().get((int)codId);
            
            System.out.println("NOMBRE COMERCIAL: " + elTour.getNombreComercial());
            System.out.println("CODIGO DE ID: " + elTour.getCodigoIdentificacion());
            System.out.println("PRECIO: " + elTour.getPrecio());
            System.out.println("LUGAR DE PARTIDA: " + elTour.getLugarPartita());
            System.out.println("FECHA DE REGRESO: " + elTour.getFechaSalida().toString());
            System.out.println("FECHA DE SALIDA: " + elTour.getFechaRegreso().toString());
            if(elTour instanceof Ecologico)
            {
                System.out.println("***MOSTRANDO INFO ADICIONAL***:");
                System.out.println("La vacunacion es requerida?: " + ((Ecologico)elTour).isVacunacionRequerida());
                System.out.println("Impuesto local: " + ((Ecologico)elTour).getImpuestoLocal());
                System.out.println("El lugar es de dificil acceso?: " + ((Ecologico)elTour).isDificilAcceso());
            }
            else if(elTour instanceof Empresarial)
            {
                System.out.println("***MOSTRANDO INFO ADICIONAL***:");
                System.out.println("Nombre de la empresa: " + ((Empresarial)elTour).getNombreEmpresa());
                System.out.println("Es viejaero frecuente?: : " + ((Empresarial)elTour).isViajeroFrecuente());
                System.out.println("Tipo de la empresa: " + ((Empresarial)elTour).getTipo().toString());
            }
            
        }
}
