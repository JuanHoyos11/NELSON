package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Tour {

	protected long codigoIdentificacion;
	protected String nombreComercial;
	protected String lugarPartita;
	protected String horaPartida;
	protected double precio;
        protected Date fechaSalida;
        protected Date fechaRegreso;
        
	//GETTERS AND SETTERS
	public long getCodigoIdentificacion() {
		return codigoIdentificacion;
	}
	public void setCodigoIdentificacion(long codigoIdentificacion) {
		this.codigoIdentificacion = codigoIdentificacion;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getLugarPartita() {
		return lugarPartita;
	}
	public void setLugarPartita(String lugarPartita) {
		this.lugarPartita = lugarPartita;
	}
	public String getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }
        
	//CONSTRUCTORS
	public Tour() {
	}
	public Tour(long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida,double precio) {
		this.codigoIdentificacion = codigoIdentificacion;
		this.nombreComercial = nombreComercial;
		this.lugarPartita = lugarPartita;
		this.horaPartida = horaPartida;
		this.precio = precio;
	}

    public Tour(long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida, double precio, Date fechaSalida, Date fechaRegreso) {
        this.codigoIdentificacion = codigoIdentificacion;
        this.nombreComercial = nombreComercial;
        this.lugarPartita = lugarPartita;
        this.horaPartida = horaPartida;
        this.precio = precio;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
    }
    
    //TO STRING

    @Override
    public String toString() {
        return "Tour{" + "fechaSalida=" + fechaSalida + ", fechaRegreso=" + fechaRegreso + '}';
    }
    
    //METODOS
     
    public int calcularEstadia()
    {
        long difEnMillis = Math.abs(fechaRegreso.getTime() - fechaSalida.getTime());
        long diferencia = TimeUnit.DAYS.convert(difEnMillis, TimeUnit.MILLISECONDS);
        int diasEstadia = (int) diferencia;
        
        return diasEstadia;
    }
    
    public double calcularPrecio()
    {   
        return (precio*calcularEstadia());
    }
}
