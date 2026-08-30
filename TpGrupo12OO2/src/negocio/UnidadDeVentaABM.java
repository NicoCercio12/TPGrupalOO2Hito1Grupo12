package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.UnidadDeVentaDao;
import datos.Empleado;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadDeVenta;

public class UnidadDeVentaABM {

	private static UnidadDeVentaABM instancia = null; // Patrón Singleton

	protected UnidadDeVentaABM() {
	}

	public static UnidadDeVentaABM getInstance() {
		if (instancia == null)
			instancia = new UnidadDeVentaABM();
		return instancia;
	}

	public int agregarFoodTruck(String nombreComercial, Empleado responsable,  double superficie, double costo,String codigo,String patente,boolean requiereElecetrcidad)
			throws Exception {

		if (UnidadDeVentaDao.getInstance().traerPorCodigoUnico(codigo) != null) {

			throw new Exception("ERROR: Ya existe una unidad con ese codigo");
		}

		FoodTruck f = new FoodTruck( nombreComercial,responsable, superficie,costo,codigo, patente,requiereElecetrcidad);

		return UnidadDeVentaDao.getInstance().agregar(f);
	}

	public int agregarPuestoDesarmable(String nombreComercial, Empleado responsable,  double superficie, double costo,String codigo,int cantidadCarpas, int tiempoMontajeMinutos) throws Exception {

		if (UnidadDeVentaDao.getInstance().traerPorCodigoUnico(codigo) != null) {

			throw new Exception("ERROR: Ya existe una unidad con ese codigo");
		}

		PuestoDesarmable pd = new PuestoDesarmable(nombreComercial,responsable, superficie,costo,codigo, cantidadCarpas, tiempoMontajeMinutos);

		return UnidadDeVentaDao.getInstance().agregar(pd);

	}

	public UnidadDeVenta traerPorCodigoUnico(int codUnico) {
		return UnidadDeVentaDao.getInstance().traer(codUnico);
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
	
	public UnidadDeVenta traerUnidadyPedidos(long idUnidad) {
		return UnidadDeVentaDao.getInstance().traerUnidadYPedidos(idUnidad);
	}
}
