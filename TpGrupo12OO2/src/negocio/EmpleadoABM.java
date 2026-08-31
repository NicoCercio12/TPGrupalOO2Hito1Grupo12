package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.EmpleadoDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;
import datos.UnidadDeVenta;

public class EmpleadoABM {

	private static EmpleadoABM instancia = null; // Patrón Singleton

	protected EmpleadoABM() {
	}

	public static EmpleadoABM getInstance() {
		if (instancia == null)
			instancia = new EmpleadoABM();
		return instancia;
	}

	public long agregarCocinero(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plusCategoria)
			throws Exception {

		if (EmpleadoDao.getInstance().traerPorDni(dni) != null) {

			throw new Exception("ERROR: Ya existe un empleado con ese dni");
		}

		Cocinero c = new Cocinero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase,
				especialidadCulinaria, plusCategoria);
		

		return EmpleadoDao.getInstance().agregar(c);
	}

	public long agregarCajero(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String turno) throws Exception {

		if (EmpleadoDao.getInstance().traerPorDni(dni) != null) {

			throw new Exception("ERROR: Ya existe un empleado con ese dni");
		}

		Cajero c = new Cajero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turno);
		
		
		return EmpleadoDao.getInstance().agregar(c);

	}
	
	
	public void modificar(Empleado empleado) throws Exception{
		
		Empleado empleModificar = EmpleadoDao.getInstance().traer(empleado.getIdEmpleado());
		
		if(empleModificar == null) {
			
			throw new Exception("ERROR: El empleado no existe");
		}
		
		empleModificar = EmpleadoDao.getInstance().traerPorDni(empleado.getDni());
		
		if(empleModificar != null &&  empleModificar.getIdEmpleado() != empleado.getIdEmpleado()) {
			
			throw new Exception("ERROR: Ya existe otro empleado con ese dni");
		}
		
		
		EmpleadoDao.getInstance().actualizar(empleado);
		
	}
	
	public void eliminar(Empleado empleado) throws Exception {
		
		Empleado empleEliminar = EmpleadoDao.getInstance().traer(empleado.getIdEmpleado());
		
		if(empleEliminar == null) {
			
			throw new Exception("ERROR: El empleado no existe");
		}
		
		EmpleadoDao.getInstance().eliminar(empleEliminar);
	}

	public Empleado traer(long idEmpleado) {
		return EmpleadoDao.getInstance().traer(idEmpleado);
	}

	public List<Empleado> traer() {
		return EmpleadoDao.getInstance().traer();
	}
	
	public List<UnidadDeVenta> traerUnidadesComoResponsable(long idEmpleado) {
		return EmpleadoDao.getInstance().traerUnidadesComoResponsable(idEmpleado);
		
	}

}
