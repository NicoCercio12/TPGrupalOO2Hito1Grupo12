package datos;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class UnidadDeVenta {
	private long idUnidad;
    private String nombreComercial;
    private String codUnico;
    private Empleado responsable;
    private double costo;
    private double superficie;
    protected Set<Plato>lstPlatos;
    protected Set<Empleado>lstStaff;
    protected Set<Pedido>lstPedidos;
    protected Set<Festival>lstFestivales;
    
public UnidadDeVenta() {
	//Prevención de NullPointerException
	   this.lstPlatos=new HashSet<>();
	   this.lstStaff=new HashSet<>();
	   this.lstPedidos=new HashSet<>();
	   this.lstFestivales=new HashSet<>();
}
    
    public UnidadDeVenta(String nombreComercial,Empleado responsable, double superficie,double costo, String codUnico ) throws Exception {
        this.nombreComercial = nombreComercial;
        this.superficie = superficie;
        this.setCodigo(codUnico);
        this.responsable = responsable;
        this.costo=costo;
    }

	// Getters & Setters
	public long getIdUnidad() {
		return idUnidad;
	}

	protected void setIdUnidad(long idUnidad) {
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

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
	public String getCodUnico() {
		return codUnico;
	}
	
	public void setCodUnico(String codUnico) throws Exception {
	    this.setCodigo(codUnico);
	}
	

	public Set<Plato> getLstPlatos() {
        return lstPlatos;
    }

    public void setLstPlatos(Set<Plato> lstPlatos) {
        this.lstPlatos = lstPlatos;
    }

    public Set<Pedido> getLstPedidos() {
        return lstPedidos;
    }

    public void setlstFestivales(Set<Festival> lstFestivales) {
        this.lstFestivales = lstFestivales;
    }

    public Set<Festival> getlstFestivales() {
        return lstFestivales;
    }
    
    public void setLstPedidos(Set<Pedido> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }

    public Set<Empleado> getLstStaff() {
        return lstStaff;
    }

    public void setLstStaff(Set<Empleado> lstStaff) {
        this.lstStaff = lstStaff;
    }
    
    @Override
	public boolean equals(Object obj) {
    	
    	//si ambos punteros contienen la misma dirección de memoria física en el Heap de la JVM.
    	if(this==obj) return true;
    	
    	//Compara que el objeto que recibo no sea nulo y que sea del mismo tipo que la clase que evaluo
    	if(obj==null|| getClass() !=obj.getClass()) return false;
    	
    	UnidadDeVenta udv =(UnidadDeVenta) obj;

    	//Evaluo que el codigo unico sea igual y que no sea nulo ( no importa la capitalizacion)	
		return codUnico != null && codUnico.equalsIgnoreCase(udv.codUnico);
	}
    
    @Override
    public int hashCode() {
        return Objects.hash(codUnico != null ? codUnico.toLowerCase() : null);
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
