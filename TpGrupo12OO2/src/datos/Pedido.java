package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pedido {

	private long idPedido;
	private LocalDate fecha;
	private Festival festival;
	private UnidadDeVenta unidadDeVenta;
	private Set<ItemPedido> items;

	public Pedido() {
		this.items = new HashSet<ItemPedido>();
	}

	public Pedido(LocalDate fecha, Festival festival, UnidadDeVenta unidadDeVenta) {
		super();
		this.fecha = fecha;
		this.festival = festival;
		this.unidadDeVenta = unidadDeVenta;
		this.items = new HashSet<ItemPedido>();
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public UnidadDeVenta getUnidadDeVenta() {
		return unidadDeVenta;
	}

	public void setUnidadDeVenta(UnidadDeVenta unidadDeVenta) {
		this.unidadDeVenta = unidadDeVenta;
	}

	public Set<ItemPedido> getItems() {
		return items;
	}

	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}

	@Override
	public String toString() {
	    return "Pedido [idPedido=" + idPedido +
	           ", fecha=" + fecha +
	           ", idFestival=" + (festival != null ? festival.getIdFestival() : null) +
	           ", idUnidad=" + (unidadDeVenta != null ? unidadDeVenta.getIdUnidad() : null) + "]";
	}
}