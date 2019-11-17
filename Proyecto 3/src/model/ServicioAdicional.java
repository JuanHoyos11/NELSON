package model;

public abstract class ServicioAdicional {

	//ATRIBUTOS
	protected long codigoServicio;
	protected String nombreServicio;
	protected double precio;

	//SETTERS AND GETTERS
	public long getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	//CONSTRUCTOR
	public ServicioAdicional(long codigoServicio, String nombreServicio, double precio) {
		this.codigoServicio = codigoServicio;
		this.nombreServicio = nombreServicio;
		this.precio = precio;
	}
	public ServicioAdicional() {
	}
        
        //METODOS
        
        public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "\n ServicioAdicional " 
                + "\n codigoServicio=" + codigoServicio 
                + "\n nombreServicio=" + nombreServicio 
                + "\n precio=" + precio + "\n";
    }
        

}
