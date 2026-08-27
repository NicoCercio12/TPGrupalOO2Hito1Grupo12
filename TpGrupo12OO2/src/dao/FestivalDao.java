package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Festival;

public class FestivalDao {

    private static Session session;
    private Transaction tx;
    private static FestivalDao instancia = null;

    protected FestivalDao() {}

    public static FestivalDao getInstance() {
        if (instancia == null)
            instancia = new FestivalDao();
        return instancia;
    }

    protected void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    protected void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    // Agregar
    public int agregar(Festival objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    // Actualizar
    public void actualizar(Festival objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    // Eliminar
    public void eliminar(Festival objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    // Traer Festival por id
    public Festival traer(long idFestival) {
        Festival objeto = null;
        try {
            iniciaOperacion();
            objeto = (Festival) session.createQuery("from Festival f where f.idFestival=:idFestival")
                    .setParameter("idFestival", idFestival).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    // Traer Festival por nombre
    public Festival traerPorNombre(String nombre) {
        Festival objeto = null;
        try {
            iniciaOperacion();
            objeto = (Festival) session.createQuery("from Festival f where f.nombre=:nombre")
                    .setParameter("nombre", nombre).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    // Traer Festivales por temporada
    public List<Festival> traerPorTemporada(String temporada) {
        List<Festival> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Festival f where f.temporada=:temporada", Festival.class)
                    .setParameter("temporada", temporada).list();
        } finally {
            session.close();
        }
        return lista;
    }

    // Traer todos los Festivales
    public List<Festival> traer() throws HibernateException {
        List<Festival> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Festival", Festival.class).list();
        } finally {
            session.close();
        }
        return lista;
    }
}
