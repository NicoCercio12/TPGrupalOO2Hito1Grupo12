package datos;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class UnidadDeVenta {
	private long idUnidad;
    private String nombreComercial;
    private String codUnico;
    private Empleado responsable;
    private Double costo;
    private Double superficie;
    protected Set<Plato>lstPlatos;
    protected Set<Empleado>lstStaff;
    protected Set<Pedido>lstPedidos;
public UnidadDeVenta() {}
    
    public UnidadDeVenta(String nombreComercial,Empleado responsable, double superficie,double costo, String codigo ) throws Exception {
        this.nombreComercial = nombreComercial;
        this.superficie = superficie;
        this.setCodigo(codigo);
        this.responsable = responsable;
        this.costo=costo;
    }

	// Getters & Setters
	public long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado Responsable) {
		this.responsable = Responsable;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	
	public double getCosto() {
		return costo;
	}

	public void setCosto(double Costo) {
		this.costo = costo;
	}
	
	
	public String getCodigo() {
		return codUnico;
	}
	
	
	public Set<Plato> getLstPlatos() {
		return lstPlatos;
	}
	
	public Set<Pedido> getLstPedidos() {
		return lstPedidos;
	}
	
	public Set<Empleado> getLstStaff() {
		return lstStaff;
	}

	public boolean equals(UnidadDeVenta unidad) {
		return this.codUnico.equalsIgnoreCase(unidad.getCodigo());
	}
	
	//CONDICION DE CODIGO 10 CARACTERES
	public void setCodigo(String codigo) throws Exception {
		if (codigo == null || codigo.length() != 10) {
			throw new Exception("ERROR: El código debe tener exactamente 10 caracteres");
		}
		this.codUnico = codigo;
	}

	@Override
	public String toString() {
		return "UnidadDeVenta [idUnidad=" + idUnidad + ", nombreComercial=" + nombreComercial + ", codUnico=" + codUnico
				+ ", responsable=" + responsable + ", costo=" + costo + ", superficie=" + superficie +  "]";
	}

	
	

	
	
	
    
}
