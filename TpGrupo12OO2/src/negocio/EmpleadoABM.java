package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.EmpleadoDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;

public class EmpleadoABM {

	private static EmpleadoABM instancia = null; // Patrón Singleton

	protected EmpleadoABM() {
	}

	public static EmpleadoABM getInstance() {
		if (instancia == null)
			instancia = new EmpleadoABM();
		return instancia;
	}

	public int agregarCocinero(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plusCategoria)
			throws Exception {

		if (EmpleadoDao.getInstance().traerPorDni(dni) != null) {

			throw new Exception("ERROR: Ya existe un cliente con ese dni");
		}

		Cocinero c = new Cocinero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase,
				especialidadCulinaria, plusCategoria);

		return EmpleadoDao.getInstance().agregar(c);
	}

	public int agregarCajero(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String turno) throws Exception {

		if (EmpleadoDao.getInstance().traerPorDni(dni) != null) {

			throw new Exception("ERROR: Ya existe un empleado con ese dni");
		}

		Cajero c = new Cajero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turno);

		return EmpleadoDao.getInstance().agregar(c);

	}

	public Empleado traer(int idEmpleado) {
		return EmpleadoDao.getInstance().traer(idEmpleado);
	}

	public List<Empleado> traer() {
		return EmpleadoDao.getInstance().traer();
	}

}
