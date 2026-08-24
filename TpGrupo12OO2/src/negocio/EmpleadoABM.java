package negocio;

import java.util.List;

import dao.EmpleadoDao;
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
	
	public Empleado traer(int idEmpleado) {
		return EmpleadoDao.getInstance().traer(idEmpleado);
	}
	
	public List<Empleado> traer(){
		return EmpleadoDao.getInstance().traer();
	}
	
	

}
