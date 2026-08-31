package datos;

public class Plato {
	private int idPlato;
	private String nombre;
    private double precioVenta;
    private double costoProduccion;

    public Plato() {}

    public Plato(String nombre, double precioVenta, double costoProduccion) {
        super();
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.costoProduccion = costoProduccion;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getCostoProduccion() {
        return costoProduccion;
    }

    public void setCostoProduccion(double costoProduccion) {
        this.costoProduccion = costoProduccion;
    }

    @Override
    public String toString() {
        return "Plato [idPlato=" + idPlato + ", nombre=" + nombre + ", precioVenta=" + precioVenta
                + ", costoProduccion=" + costoProduccion + "]";
    }
}
