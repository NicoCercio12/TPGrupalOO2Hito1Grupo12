package negocio;

import java.util.List;

import java.time.LocalDate;
import datos.UnidadDeVenta;
import dao.PlatoDao;
import datos.Plato;
import datos.Festival;

public class PlatoABM {

    private static PlatoABM instancia = null;

    protected PlatoABM() {}

    public static PlatoABM getInstance() {
        if (instancia == null) {
            instancia = new PlatoABM();
        }
        return instancia;
    }

    // Agregar un plato
    public int agregar(String nombre, double precioVenta, double costoProduccion) throws Exception {
        Plato plato = new Plato(nombre, precioVenta, costoProduccion);
        return PlatoDao.getInstance().agregar(plato);
    }

    // Actualizar un plato
    public void actualizar(Plato plato) throws Exception {
        PlatoDao.getInstance().actualizar(plato);
    }

    // Eliminar un plato
    public void eliminar(Plato plato) throws Exception {
        PlatoDao.getInstance().eliminar(plato);
    }

    // Traer plato por id
    public Plato traer(long idPlato) throws Exception {
        return PlatoDao.getInstance().traer(idPlato);
    }

    // Traer plato por nombre
    public Plato traerPorNombre(String nombre) throws Exception {
        return PlatoDao.getInstance().traerPorNombre(nombre);
    }

    // Traer todos los platos
    public List<Plato> traer() throws Exception {
        return PlatoDao.getInstance().traer();
    }
    
 // Traer el plato más vendido de una Unidad de Venta
    public Plato traerMasVendidoPorUnidad(UnidadDeVenta unidad, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
    	
    	if (unidad == null) {
    	    throw new Exception("La unidad no puede ser nula");
    	}
    	
    	if (fechaInicio == null || fechaFin == null) {
    	    throw new Exception("Las fechas no pueden ser nulas");
    	}

    	if (fechaInicio.isAfter(fechaFin)) {
    	    throw new Exception("La fecha de inicio no puede ser posterior a la fecha de fin");
    	}
        return PlatoDao.getInstance().traerMasVendidoPorUnidad(unidad, fechaInicio, fechaFin );
    }
    
    public Plato traerMasRentablePorFestival(Festival festival) throws Exception{
    	
    	if (festival == null) {
    	    throw new Exception("El festival no puede ser nulo");
    	}

        return PlatoDao.getInstance().traerMasRentablePorFestival(festival);
    }
}
