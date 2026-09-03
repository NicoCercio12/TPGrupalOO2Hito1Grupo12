package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.FestivalDao;
import datos.Festival;
import datos.Pedido;

public class FestivalABM {

    private static FestivalABM instancia = null; // Patrón Singleton

    protected FestivalABM() {
    }

    public static FestivalABM getInstance() {
        if (instancia == null)
            instancia = new FestivalABM();
        return instancia;
    }

    // Agregar Festival
    public int agregarFestival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {

        // Validación simple: que no exista otro festival con mismo nombre
        if (FestivalDao.getInstance().traerPorNombre(nombre) != null) {
            throw new Exception("ERROR: Ya existe un festival con ese nombre");
        }

        Festival f = new Festival(nombre, temporada, fechaInicio, fechaFin);
        return FestivalDao.getInstance().agregar(f);
    }

    // Traer Festival por id
    public Festival traer(long idFestival) {
        return FestivalDao.getInstance().traer(idFestival);
    }

    // Traer Festival por nombre
    public Festival traerPorNombre(String nombre) {
        return FestivalDao.getInstance().traerPorNombre(nombre);
    }

    // Traer Festivales por temporada
    public List<Festival> traerPorTemporada(String temporada) {
        return FestivalDao.getInstance().traerPorTemporada(temporada);
    }

    // Listar todos los Festivales
    public List<Festival> traer() {
        return FestivalDao.getInstance().traer();
    }
    
    public List<Pedido> traerPedidosDelFestival(long idFestival) {
    	return FestivalDao.getInstance().traerPedidosDelFestival(idFestival);
    }
    
    // Traer festivales por rango de fechas

    public List<Festival> traerPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return traer().stream()
                      .filter(f -> !f.getFechaInicio().isBefore(inicio)
                                && !f.getFechaFin().isAfter(fin))
                      .toList();
    }


    
 
}
