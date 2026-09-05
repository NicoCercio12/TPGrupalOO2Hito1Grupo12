package negocio;

import java.util.List;
import dao.PlatoDao;
import datos.Plato;

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
    public Plato traerMasVendidoPorUnidad(long idUnidad) throws Exception {
        return PlatoDao.getInstance().traerMasVendidoPorUnidad(idUnidad);
    }
    
    public void cambiarPrecio(long idPlato, double nuevoPrecio) throws Exception {
        Plato plato = PlatoDao.getInstance().traer(idPlato);

        if (plato == null) {
            throw new Exception("No existe un plato con ese ID");
        }

        plato.setPrecioVenta(nuevoPrecio);

        PlatoDao.getInstance().actualizar(plato);
    }
    
    public Plato traerMasBarato() throws Exception {
        return PlatoDao.getInstance().traerMasBarato();
    }
    
}
