
package model;

import enumeradores.TipoEmpresa;
import java.util.Date;

public class Empresarial extends Tour{
    
    //ATRIBUTOS
    
    String nombreEmpresa;
    boolean viajeroFrecuente;
    TipoEmpresa tipo;
    
    //CONSTRUCTORES

    public Empresarial(String nombreEmpresa, boolean viajeroFrecuente, TipoEmpresa tipo) {
        this.nombreEmpresa = nombreEmpresa;
        this.viajeroFrecuente = viajeroFrecuente;
        this.tipo = tipo;
    }

    public Empresarial(String nombreEmpresa, boolean viajeroFrecuente, TipoEmpresa tipo, long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida, double precio) {
        super(codigoIdentificacion, nombreComercial, lugarPartita, horaPartida, precio);
        this.nombreEmpresa = nombreEmpresa;
        this.viajeroFrecuente = viajeroFrecuente;
        this.tipo = tipo;
    }

    public Empresarial(String nombreEmpresa, boolean viajeroFrecuente, TipoEmpresa tipo, long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida, double precio, Date fechaSalida, Date fechaRegreso) {
        super(codigoIdentificacion, nombreComercial, lugarPartita, horaPartida, precio, fechaSalida, fechaRegreso);
        this.nombreEmpresa = nombreEmpresa;
        this.viajeroFrecuente = viajeroFrecuente;
        this.tipo = tipo;
    }
    
    public Empresarial(){
        
    }
    
    //ACCESORS

    //GETTERS
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public boolean isViajeroFrecuente() {
        return viajeroFrecuente;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }
    
    //SETTERS

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void setViajeroFrecuente(boolean viajeroFrecuente) {
        this.viajeroFrecuente = viajeroFrecuente;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }
    
    //METODOS
    
    @Override
    public int calcularEstadia()
    {
        return super.calcularEstadia();
    }
    
    @Override
    public double calcularPrecio()
    {
        if(tipo == TipoEmpresa.TURISMO || tipo == TipoEmpresa.MEDIO_COMUNICACION) //DESCUENTO 10%
        {
            return (precio - ((10*precio)/100));
        }
        else
        {
            return precio;
        }
    }
}
