
package model;

import enumeradores.TipoTransporte;

public class Transporte extends ServicioAdicional{
    
    //ATRIBUTOS
    
    double distancia;
    TipoTransporte tipo;
    int numeroPasajeros;
    
    //CONSTRUCTORES

    public Transporte(double distancia, TipoTransporte tipo, int numeroPasajeros, long codigoServicio, String nombreServicio, double precio) {
        super(codigoServicio, nombreServicio, precio);
        this.distancia = distancia;
        this.tipo = tipo;
        this.numeroPasajeros = numeroPasajeros;
    }

    public Transporte(double distancia, TipoTransporte tipo, int numeroPasajeros) {
        this.distancia = distancia;
        this.tipo = tipo;
        this.numeroPasajeros = numeroPasajeros;
    }
    
    //ACCESORS

    //GETTERS
    
    public double getDistancia() {
        return distancia;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    //SETTERS
    
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }
    
    //METODOS
    
    @Override
    public double calcularPrecio()
    {
        
        int numVehiculos;
        
        if(tipo == TipoTransporte.TAXI)
        {
            if(numeroPasajeros <= 4)
            {
                return precio;
            }
            else
            {
                if(numeroPasajeros%4 == 0)
                {
                    numVehiculos = numeroPasajeros/4;
                }
                else
                {
                    numVehiculos = (numeroPasajeros/4)+1;
                }
                
                return (precio * numVehiculos);
                
            }     
        }
        else if(tipo == TipoTransporte.MINIVAN)
        {
            if(numeroPasajeros <= 6)
            {
                return (distancia * (precio + ((50*precio)/100)));
            }
            else
            {
                if(numeroPasajeros%6 == 0)
                {
                    numVehiculos = numeroPasajeros/6;
                }
                else
                {
                    numVehiculos = (numeroPasajeros/6)+1;
                }
                
                return ((distancia * (precio + ((50*precio)/100))) * numVehiculos);
                
            }        
        }
        else if (tipo == TipoTransporte.PARTICULAR)
        {
            if(numeroPasajeros <= 4)
            {
                return (distancia * (precio + ((25*precio)/100)));
            }
            else
            {
                if(numeroPasajeros%4 == 0)
                {
                    numVehiculos = numeroPasajeros/4;
                }
                else
                {
                    numVehiculos = (numeroPasajeros/4)+1;
                }
                
                return ((distancia * (precio + ((25*precio)/100))) * numVehiculos);
                
            }  
        }
        
        return -1;
        
    }
}
