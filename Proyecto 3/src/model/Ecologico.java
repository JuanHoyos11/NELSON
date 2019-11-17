
package model;

import java.util.Date;


public class Ecologico extends Tour{
    
    //ATRIBUTOS
    
    boolean vacunacionRequerida;
    double impuestoLocal;
    boolean dificilAcceso;
    
    //CONSTRUCTORES

    public Ecologico(boolean vacunacionRequerida, double impuestoLocal, boolean dificilAcceso) {
        this.vacunacionRequerida = vacunacionRequerida;
        this.impuestoLocal = impuestoLocal;
        this.dificilAcceso = dificilAcceso;
    }

    public Ecologico(boolean vacunacionRequerida, double impuestoLocal, boolean dificilAcceso,long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida, double precio) {
        super(codigoIdentificacion, nombreComercial, lugarPartita, horaPartida, precio);
        this.vacunacionRequerida = vacunacionRequerida;
        this.impuestoLocal = impuestoLocal;
        this.dificilAcceso = dificilAcceso;
    }

    public Ecologico(boolean vacunacionRequerida, double impuestoLocal, boolean dificilAcceso, long codigoIdentificacion, String nombreComercial, String lugarPartita, String horaPartida, double precio, Date fechaSalida, Date fechaRegreso) {
        super(codigoIdentificacion, nombreComercial, lugarPartita, horaPartida, precio, fechaSalida, fechaRegreso);
        this.vacunacionRequerida = vacunacionRequerida;
        this.impuestoLocal = impuestoLocal;
        this.dificilAcceso = dificilAcceso;
    }
    
    public Ecologico(){
        
    }
    
    //ACCESORS

    //GETTERS
    
    public boolean isVacunacionRequerida() {
        return vacunacionRequerida;
    }

    public double getImpuestoLocal() {
        return impuestoLocal;
    }

    public boolean isDificilAcceso() {
        return dificilAcceso;
    }

    //SETTERS
    
    public void setVacunacionRequerida(boolean vacunacionRequerida) {
        this.vacunacionRequerida = vacunacionRequerida;
    }

    public void setImpuestoLocal(double impuestoLocal) {
        this.impuestoLocal = impuestoLocal;
    }

    public void setDificilAcceso(boolean dificilAcceso) {
        this.dificilAcceso = dificilAcceso;
    }
    
    //METODOS
    
    @Override
    public String toString()
    {
        return super.toString() + "Ecologico :" + "\n vacunacionRequerida=" + vacunacionRequerida + 
                "\n impuestoLocal=" + impuestoLocal + 
                "\n dificilAcceso=" + dificilAcceso +
                "\n";
    }

    @Override
    public int calcularEstadia() {
        if(dificilAcceso == true)
        {
            return (super.calcularEstadia()+2);
        }
        else
        {
            return super.calcularEstadia();
        }
    }
    
    @Override
    public double calcularPrecio()
    {
        return (super.calcularPrecio()+impuestoLocal);
    }
}
