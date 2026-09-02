package dao;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import datos.UnidadDeVenta;
import datos.Empleado;

public class UnidadDeVentaDao {

	private static Session session;
	private Transaction tx;
	private static UnidadDeVentaDao instancia = null;

	protected UnidadDeVentaDao() {
	}

	public static UnidadDeVentaDao getInstance() {
		if (instancia == null)
			instancia = new UnidadDeVentaDao();
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

	//Agregar

	public long agregar(UnidadDeVenta objeto) {

		long id = 0;

		try {

			iniciaOperacion();
			id = Long.parseLong(session.save(objeto).toString());
			tx.commit();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return id;

	}

	/// Actualizar
	public void actualizar(UnidadDeVenta objeto) {
	    try {
	        iniciaOperacion();
	        session.merge(objeto);
	        tx.commit();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
	
	//Eliminar
	
	public void eliminar(UnidadDeVenta objeto) {

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

	// Trae unidad por id

	public UnidadDeVenta traer(long idUnidad) {

		UnidadDeVenta objeto = null;

		try {

			iniciaOperacion();
			objeto = (UnidadDeVenta) session.createQuery("from UnidadDeVenta u where u.idUnidad=:idUnidad")
					.setParameter("idUnidad", idUnidad).uniqueResult();

		} finally {

			session.close();
		}

		return objeto;
	}
	
	public UnidadDeVenta traerPorCodigoUnico(String codUnico) {
		
		UnidadDeVenta objeto = null;
		
		try {
			
			iniciaOperacion();
			objeto = (UnidadDeVenta) session.createQuery("from UnidadDeVenta u where u.codUnico=:codUnico").setParameter("codUnico", codUnico).uniqueResult();
			
		} catch (HibernateException he) {
			
			manejaExcepcion(he);
			
		} finally {
			
			session.close();
		}
		
		return objeto;
	}

	// Trae las unidades de venta

	public List<UnidadDeVenta> traer() throws HibernateException {

		List<UnidadDeVenta> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from UnidadDeVenta", UnidadDeVenta.class).list();

		} finally {

			session.close();
		}

		return lista;
	}
	
	//TRAER UNIDAD Y SUS PEDIDOS
	
	public UnidadDeVenta traerUnidadYPedidos(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		Hibernate.initialize(objeto.getLstPedidos());
		}
		finally {
		session.close();
		}
		return objeto;
	}
	
	
	//TRAER UNIDAD Y SU STAFF
	
	public UnidadDeVenta traerUnidadYstaff(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		Hibernate.initialize(objeto.getLstStaff());
		}
		finally {
		session.close();
		}
		return objeto;
	}
	
	//TRAER UNIDAD Y SUS PLATOS
	
	public UnidadDeVenta traerUnidadYplatos(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		Hibernate.initialize(objeto.getLstPlatos());
		}
		finally {
		session.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Empleado> traerStaffPorIngresoAnterior(long idUnidad, LocalDate fecha) throws HibernateException {
	    List<Empleado> lista = null;
	    try {
	        iniciaOperacion();
	        String hql = "select e from UnidadDeVenta u join u.lstStaff e where u.idUnidad = :idUnidad and e.fechaIngreso <= :fecha order by e.fechaIngreso asc";
	        lista = session.createQuery(hql)
	                       .setParameter("idUnidad", idUnidad)
	                       .setParameter("fecha", fecha)
	                       .list();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    return lista;
	}
	
	//Correccion de Nicolás Cerciosimo: Trae la lista de unidades de venta por empleado responsable
	
		public List<UnidadDeVenta> traerUnidadesComoResponsable(Empleado empleado) {

			List<UnidadDeVenta> lista = null;

			try {

				iniciaOperacion();
				lista = session.createQuery("from UnidadDeVenta u where u.responsable = :empleado",
						UnidadDeVenta.class).setParameter("empleado", empleado).list();

			} catch (HibernateException he) {

				manejaExcepcion(he);

			} finally {

				session.close();
			}

			return lista;
		}

}