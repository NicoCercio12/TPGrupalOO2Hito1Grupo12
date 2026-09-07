package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.EmpleadoDao;
import dao.UnidadDeVentaDao;
import datos.Empleado;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadDeVenta;

public class UnidadDeVentaABM {
	
	
	// ----------------------------Patrón Singleton-------------------------------------------------------
	private static UnidadDeVentaABM instancia = null; 

	protected UnidadDeVentaABM() {
	}

	public static UnidadDeVentaABM getInstance() {
		if (instancia == null)
			instancia = new UnidadDeVentaABM();
		return instancia;
	}
	//------------------------------------------------------------------------------------------------------
	
	
	//-------------------------------------------------METODOS DE LA CLASE DE NEGOCIO ----------------------
	
	
	public long agregarFoodTruck(String nombreComercial, Empleado responsable, double superficie, double costo,
			String codigo, String patente, boolean requiereElecetrcidad) throws Exception {

		if (UnidadDeVentaDao.getInstance().traerPorCodigoUnico(codigo) != null) {
			throw new Exception("ERROR: Ya existe una unidad con ese codigo");
		}

		FoodTruck f = new FoodTruck(nombreComercial, responsable, superficie, costo, codigo, patente,
				requiereElecetrcidad);

		return UnidadDeVentaDao.getInstance().agregar(f);
	}

	public long agregarPuestoDesarmable(String nombreComercial, Empleado responsable, double superficie, double costo,
			String codigo, int cantidadCarpas, int tiempoMontajeMinutos) throws Exception {

		if (UnidadDeVentaDao.getInstance().traerPorCodigoUnico(codigo) != null) {
			throw new Exception("ERROR: Ya existe una unidad con ese codigo");
		}

		PuestoDesarmable pd = new PuestoDesarmable(nombreComercial, responsable, superficie, costo, codigo,
				cantidadCarpas, tiempoMontajeMinutos);

		return UnidadDeVentaDao.getInstance().agregar(pd);
	}

	public void modificar(UnidadDeVenta unidadDeVenta) throws Exception {
		if (unidadDeVenta == null) {
			throw new Exception("ERROR: La unidad de venta no puede ser nula");
		}
		if (UnidadDeVentaDao.getInstance().traer(unidadDeVenta.getIdUnidad()) == null) {
			throw new Exception("ERROR: La unidad de venta no existe");
		}
		UnidadDeVentaDao.getInstance().actualizar(unidadDeVenta);
	}

	public UnidadDeVenta traer(long idUnidad) {
		return UnidadDeVentaDao.getInstance().traer(idUnidad);
	}

	public UnidadDeVenta traerPorCodigoUnico(String codigo) {
		return UnidadDeVentaDao.getInstance().traerPorCodigoUnico(codigo);
	}

	public List<UnidadDeVenta> traer() {
		return UnidadDeVentaDao.getInstance().traer();
	}

	public UnidadDeVenta traerUnidadyPlatos(long idUnidad) {
		return UnidadDeVentaDao.getInstance().traerUnidadYplatos(idUnidad);
	}

	public UnidadDeVenta traerUnidadyStaff(long idUnidad) {
		return UnidadDeVentaDao.getInstance().traerUnidadYstaff(idUnidad);
	}


	
	//Correccion Nicolás Cerciosimo
	public List<UnidadDeVenta> traerUnidadesComoResponsable(Empleado empleado) {
		return UnidadDeVentaDao.getInstance().traerUnidadesComoResponsable(empleado);
		
	}
	
	public double liquidarHaberes(UnidadDeVenta unidad) throws Exception {
		
		if(unidad == null) {
			
			throw new Exception("ERROR: No existe la unidad de venta");
		}
		
		return UnidadDeVentaDao.getInstance().liquidarHaberes(unidad);
	}

}