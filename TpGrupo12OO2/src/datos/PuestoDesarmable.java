package datos;

public class PuestoDesarmable extends UnidadDeVenta {
	
	private int cantidadCarpas;
	private int tiempoMontajeMinutos;
	
	
	public PuestoDesarmable(){}
	
	public PuestoDesarmable(String nombreComercial, Empleado empleado, double superficie,double costo,
			String codUnico, int cantidadCarpas, int tiempoMontajeMinutos) throws Exception {
		super(nombreComercial, empleado, superficie,costo, codUnico);
		this.cantidadCarpas = cantidadCarpas;
		this.tiempoMontajeMinutos = tiempoMontajeMinutos;
	}

	public int getCantidadCarpas() {
		return cantidadCarpas;
	}

	public void setCantidadCarpas(int cantidadCarpas) {
		this.cantidadCarpas = cantidadCarpas;
	}

	public int getTiempoMontajeMinutos() {
		return tiempoMontajeMinutos;
	}

	public void setTiempoMontajeMinutos(int tiempoMontajeMinutos) {
		this.tiempoMontajeMinutos = tiempoMontajeMinutos;
	}

	@Override
	public String toString() {
		return "PuestoDesarmable [cantidadCarpas=" + cantidadCarpas + ", tiempoMontajeMinutos=" + tiempoMontajeMinutos
				+ "]";
	}

}
