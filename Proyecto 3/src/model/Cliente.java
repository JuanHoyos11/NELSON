package model;

public class Cliente {

	//ATRIBUTOS
	private long numeroIdentificacion;
	private String nombreCompleto;
	private String telefonoContacto;

	//SETTERS AND GETTER
	public long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	//CONSTRUCTOR
	public Cliente () {
	}
	public Cliente(long numeroIdentificacion, String nombreCompleto, String telefonoContacto) {
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombreCompleto = nombreCompleto;
		this.telefonoContacto = telefonoContacto;
	}



}
