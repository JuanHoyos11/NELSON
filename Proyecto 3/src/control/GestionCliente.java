package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Cliente;

public class GestionCliente {

	//METODO PARA LLENAR LA LISTA DE CLIENTES INCIALES
	public HashMap<Integer, Cliente> crearCliente(){

		HashMap<Integer, Cliente> listaClientes = new HashMap<Integer, Cliente>();

                Cliente cliente1 = new Cliente(10001, "Armando Bronca", "3165317");
                Cliente cliente2 = new Cliente(20002, "Elver Galarga", "3165318");
                Cliente cliente3 = new Cliente(30003, "Jacko Miste", "3165319");
                Cliente cliente4 = new Cliente(40004, "Aide Mora", "3165301");
                Cliente cliente5 = new Cliente(50005, "Elsa Badillo", "3165302");
		
		listaClientes.put((int) cliente1.getNumeroIdentificacion(), cliente1);
		listaClientes.put((int) cliente2.getNumeroIdentificacion(), cliente2);
		listaClientes.put((int) cliente3.getNumeroIdentificacion(), cliente3);
		listaClientes.put((int) cliente4.getNumeroIdentificacion(), cliente4);
		listaClientes.put((int) cliente5.getNumeroIdentificacion(), cliente5);

		return listaClientes;
	}


	public void listarClientes(Cliente cliente) {
		System.out.println("========================");
		System.out.println("Nombre: " + cliente.getNombreCompleto());
		System.out.println("Id: " + cliente.getNumeroIdentificacion());
		System.out.println("Telefono: " + cliente.getTelefonoContacto());
	}



	public void insertarCliente(Cliente cliente, HashMap <Integer, Cliente> listaClientes) {
		listaClientes.put((int) cliente.getNumeroIdentificacion(), cliente);
	}

	public void modificarCliente (HashMap <Integer,Cliente> listaClientes, Cliente cliente, long key) {
		listaClientes.replace((int) cliente.getNumeroIdentificacion(), cliente);
		listaClientes.remove((int) key);
		listaClientes.put((int) cliente.getNumeroIdentificacion(), cliente);
	}

	public void eliminarCliente(HashMap <Integer,Cliente> listaClientes, Cliente cliente) {
		listaClientes.remove((int) cliente.getNumeroIdentificacion());
	}

	public Cliente getCliente (long codigo, HashMap<Integer, Cliente> listaClientes) {
		return listaClientes.get((int) codigo);
	}

}
