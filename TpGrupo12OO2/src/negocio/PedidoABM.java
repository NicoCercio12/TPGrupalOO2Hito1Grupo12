package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.PedidoDao;

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
}