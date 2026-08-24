package datos;

import java.time.LocalDate;

public class Cocinero extends Empleado {

	private String especialidadCulinaria;
	private double plusCategoria;
	
	public Cocinero() {}
	
	public Cocinero(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase, String especialidadCulinaria, double plusCategoria) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.especialidadCulinaria = especialidadCulinaria;
		this.plusCategoria = plusCategoria;
	}

	public String getEspecialidadCulinaria() {
		return especialidadCulinaria;
	}

	public void setEspecialidadCulinaria(String especialidadCulinaria) {
		this.especialidadCulinaria = especialidadCulinaria;
	}

	public double getPlusCategoria() {
		return plusCategoria;
	}

	public void setPlusCategoria(double plusCategoria) {
		this.plusCategoria = plusCategoria;
	}

	@Override
	public String toString() {
		return "Cocinero [especialidadCulinaria=" + especialidadCulinaria + ", plusCategoria=" + plusCategoria
				+ ", idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso + ", sueldoBase="
				+ sueldoBase + "]";
	}

	
	
}
