package datos;

public class FoodTruck extends UnidadDeVenta {

	private String patente;
	private boolean usaElectricidad;
	
	public FoodTruck() {}
	
	public FoodTruck(String nombreComercial, Empleado empleado, double superficie, double costo,
			String codUnico, String patente, boolean usaElectricidad) throws Exception {
		super(nombreComercial, empleado, superficie,costo, codUnico);
		this.patente = patente;
		this.usaElectricidad = usaElectricidad;
	}

	// Getters & Setters
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public boolean isUsaElectricidad() {
		return usaElectricidad;
	}

	public void setUsaElectricidad(boolean usaElectricidad) {
		this.usaElectricidad = usaElectricidad;
	}

	@Override
	public String toString() {
		return "FoodTruck [patente=" + patente + ", usaElectricidad=" + usaElectricidad + "]";
	}
	

}
