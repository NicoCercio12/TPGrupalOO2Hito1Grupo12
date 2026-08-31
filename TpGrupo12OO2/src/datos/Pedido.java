package datos;

import java.time.LocalDate;

public class Pedido {
	private int idPedido;
    private LocalDate fecha;
    private Festival festival;
    //private UnidadDeVenta uniVenta;
    private ItemPedido itemPedido;

    public Pedido() {}

    public Pedido(LocalDate fecha, Festival festival, /*UnidadDeVenta uniVenta,*/ ItemPedido itemPedido) {
        super();
        this.fecha = fecha;
        this.festival = festival;
        //this.uniVenta = uniVenta;
        this.itemPedido = itemPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
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

   /* public UnidadDeVenta getUniVenta() {
        return uniVenta;
    }

    public void setUniVenta(UnidadDeVenta uniVenta) {
        this.uniVenta = uniVenta;
    }*/

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", festival=" + festival
                + /*", uniVenta=" + uniVenta +*/ ", itemPedido=" + itemPedido + "]";
    }
}
