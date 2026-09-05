package negocio;
import org.hibernate.HibernateException;
import java.time.LocalDate;
import java.util.List;

import dao.PedidoDao;
import dao.UnidadDeVentaDao;

import datos.Festival;
import datos.ItemPedido;
import datos.Pedido;
import datos.Plato;
import datos.UnidadDeVenta;

public class PedidoABM {

	private static PedidoABM instancia = null; // Patrón Singleton

	protected PedidoABM() {
	}

	public static PedidoABM getInstance() {

		if (instancia == null)
			instancia = new PedidoABM();

		return instancia;
	}

	// Agregar Pedido
	public int agregar(
			LocalDate fecha,
			Festival festival,
			UnidadDeVenta unidadDeVenta,
			Plato plato,
			int cantidad) throws Exception {

		if (fecha == null) {
			throw new Exception(
					"ERROR: La fecha no puede ser nula");
		}

		if (festival == null) {
			throw new Exception(
					"ERROR: El festival no puede ser nulo");
		}

		if (unidadDeVenta == null) {
			throw new Exception(
					"ERROR: La unidad de venta no puede ser nula");
		}

		if (plato == null) {
			throw new Exception(
					"ERROR: El plato no puede ser nulo");
		}

		if (cantidad <= 0) {
			throw new Exception(
					"ERROR: La cantidad debe ser mayor a cero");
		}

		Pedido pedido =
				new Pedido(fecha, festival, unidadDeVenta);

		ItemPedido item =
				new ItemPedido(plato, cantidad);

		pedido.getItems().add(item);

		return PedidoDao.getInstance().agregar(pedido);
	}

	// Agregar Item a un Pedido existente
	public void agregarItem(
			int idPedido,
			Plato plato,
			int cantidad) throws Exception {

		Pedido pedido =
				PedidoDao.getInstance().traer(idPedido);

		if (pedido == null) {
			throw new Exception(
					"ERROR: El pedido no existe");
		}

		if (plato == null) {
			throw new Exception(
					"ERROR: El plato no puede ser nulo");
		}

		if (cantidad <= 0) {
			throw new Exception(
					"ERROR: La cantidad debe ser mayor a cero");
		}

		ItemPedido item =
				new ItemPedido(plato, cantidad);

		pedido.getItems().add(item);

		PedidoDao.getInstance().actualizar(pedido);
	}

	// Modificar Pedido
	public void modificar(Pedido pedido) throws Exception {

		Pedido pedidoModificar =
				PedidoDao.getInstance()
						.traer(pedido.getIdPedido());

		if (pedidoModificar == null) {
			throw new Exception(
					"ERROR: El pedido no existe");
		}

		if (pedido.getItems() == null ||
				pedido.getItems().isEmpty()) {

			throw new Exception(
					"ERROR: El pedido debe tener al menos un item");
		}

		PedidoDao.getInstance().actualizar(pedido);
	}

	// Eliminar Pedido
	public void eliminar(Pedido pedido) throws Exception {

		Pedido pedidoEliminar =
				PedidoDao.getInstance()
						.traer(pedido.getIdPedido());

		if (pedidoEliminar == null) {
			throw new Exception(
					"ERROR: El pedido no existe");
		}

		PedidoDao.getInstance()
				.eliminar(pedidoEliminar);
	}

	// Traer Pedido por id
	public Pedido traer(long idPedido) {

		return PedidoDao.getInstance().traer(idPedido);
	}

	// Traer todos los pedidos
	public List<Pedido> traer() {

		return PedidoDao.getInstance()
				.traer();
	}

	// Traer pedidos de un Festival
	public List<Pedido> traerPorFestival(long idFestival) {

		return PedidoDao.getInstance()
				.traerPorFestival(idFestival);
	}

	// Traer pedidos de una Unidad de Venta
	public List<Pedido> traerPorUnidad(long idUnidad) {

		return PedidoDao.getInstance()
				.traerPorUnidad(idUnidad);
	}
	
	
	//************CASO DE USO : PEDIDOS DE UNA UNIDAD ENTRE UN LAPSO DE TIEMPO***************
	
	//ALUMNO LUCAS LA ROSA
	
	public long traerCantidadPedidosPorUnidadEntreFechas(long idUnidad, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
      
		// Validaciones previas de la capa de negocio
		
		UnidadDeVenta udv = UnidadDeVentaDao.getInstance().traer(idUnidad);
		if (udv == null) {
	        throw new Exception("La unidad con ID " + idUnidad + " no existe.");
	    }

        if (fechaInicio == null || fechaFin == null) {
            throw new Exception("ERROR en PedidoABM: Las fechas de búsqueda no pueden ser nulas.");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new Exception("ERROR en PedidoABM: La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        
        try {
            return PedidoDao.getInstance().traerCantidadPedidosPorUnidadEntreFechas(idUnidad, fechaInicio, fechaFin);
        } catch (HibernateException he) {
            throw new Exception("ERROR en PedidoABM -> " + he.getMessage());
        }
    }
	
	//************CASO DE USO : VALOR TOTAL DE UN PEDIDO********************************
	
	//ALUMNO LUCAS LA ROSA
	
	
	public double calcularTotalPedido(int idPedido) throws Exception {
	    // 1. Valida que el pedido exista en la base de datos
	    Pedido pedido = PedidoDao.getInstance().traer(idPedido);
	    if (pedido == null) {
	        throw new Exception("El pedido con ID " + idPedido + " no existe.");
	    }

	  
	    Double total = PedidoDao.getInstance().calcularTotalPedidoPorHql(idPedido);

	    // 2. Dispara la excepción si la colección está vacía
	    if (total == null) {
	        throw new Exception("El calculo del pedido esta vacio (no posee items cargados).");
	    }

	    return total;
	}
	
	
}