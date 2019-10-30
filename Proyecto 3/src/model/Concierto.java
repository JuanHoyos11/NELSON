
package model;

public class Concierto extends ServicioAdicional{
    
    //ATRIBUTOS
    
    String artista;
    String lugar;
    String horaIngreso;
    
    //CONSTRUCTORES

    public Concierto(String artista, String lugar, String horaIngreso, long codigoServicio, String nombreServicio, double precio) {
        super(codigoServicio, nombreServicio, precio);
        this.artista = artista;
        this.lugar = lugar;
        this.horaIngreso = horaIngreso;
    }

    public Concierto(String artista, String lugar, String horaIngreso) {
        this.artista = artista;
        this.lugar = lugar;
        this.horaIngreso = horaIngreso;
    }
    
    //ACCESORS

    //GETTERS
    
    public String getArtista() {
        return artista;
    }

    public String getLugar() {
        return lugar;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }
    
    //SETTERS

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
    
    @Override
    public double calcularPrecio()
    {
        if(horaIngreso.endsWith("am") == true || horaIngreso.endsWith("pm") == true)
        {
            if(horaIngreso.endsWith("am") && (horaIngreso.startsWith("1") || horaIngreso.startsWith("2") || horaIngreso.startsWith("3") || horaIngreso.startsWith("4") || horaIngreso.startsWith("5") || horaIngreso.startsWith("6")))
            {
                return (precio + ((10*precio)/100));
            }
            else if(horaIngreso.endsWith("pm") && (horaIngreso.startsWith("6") || horaIngreso.startsWith("7") || horaIngreso.startsWith("8") || horaIngreso.startsWith("9") || horaIngreso.startsWith("10") || horaIngreso.startsWith("11") || horaIngreso.startsWith("12")))
            {
                return (precio + ((10*precio)/100));
            }
            
            return precio;
            
        }
        else
        {
            System.out.println("INGRESE BIEN EL FORMATO DE HORA! (ejemplo: 6am o 6pm)");
            return -1;
        }
    }
    
    
}
