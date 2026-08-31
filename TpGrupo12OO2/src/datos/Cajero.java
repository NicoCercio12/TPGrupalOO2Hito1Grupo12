package datos;

import java.time.LocalDate;

public class Cajero extends Empleado {
	
	private String turno;

	public Cajero() {}
	
	public Cajero(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase, String turno) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.turno = turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	

	@Override
	public double calcularSueldo() {
		
		return getSueldoBase();
	}

	@Override
	public String toString() {
		return "Cajero [turno=" + turno + ", idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso
				+ ", sueldoBase=" + sueldoBase + "]";
	}

	
	

	
	

}
