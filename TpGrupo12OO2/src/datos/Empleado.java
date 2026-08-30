package datos;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Empleado {

	protected long idEmpleado;
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected LocalDate fechaNacimiento;
	protected LocalDate fechaIngreso;
	protected double sueldoBase;

	public Empleado() {
	}

	public Empleado(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase) {
		super();
		setNombre(nombre);
		setApellido(apellido);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
		this.fechaIngreso = fechaIngreso;
		this.sueldoBase = sueldoBase;
	}

	public long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		if(nombre == null || nombre.isBlank()) {
			
			throw new IllegalArgumentException("ERROR: El nombre no puede quedar vacio");
		}
		
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		
		if(apellido == null || apellido.isBlank()) {
			
			throw new IllegalArgumentException("ERROR: el apellido no puede quedar vacio");
		}
		
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {

		if (dni == null || dni.isBlank()) {

			throw new IllegalArgumentException("ERROR: El dni no puede estar vacio");

		}

		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		
		if(fechaNacimiento == null) {
			
			throw new IllegalArgumentException("ERROR: la fecha de nacimiento no puede estar vacia");
		}
		
		if(fechaNacimiento.isAfter(LocalDate.now())) {
			
			throw new IllegalArgumentException("ERROR: la fecha de nacimiento no puede ser futura");
		}
		
		this.fechaNacimiento = fechaNacimiento;
		
		if(!esMayorDeEdad()) {
			
			throw new IllegalArgumentException("ERROR: el empleado no puede ser menor de edad");
		}
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public int calcularAntiguedad() {
		return Period.between(fechaIngreso, LocalDate.now()).getYears();
	}

	public boolean esMayorDeEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
	}
	
	public abstract double calcularSueldo();

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso + ", sueldoBase="
				+ sueldoBase + "]";
	}

}
