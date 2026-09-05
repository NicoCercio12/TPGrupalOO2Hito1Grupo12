package dao;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import datos.UnidadDeVenta;
import datos.Empleado;
import datos.Pedido;
public class UnidadDeVentaDao {

	private static Session session;
	private Transaction tx;
	private static UnidadDeVentaDao instancia = null;

	protected UnidadDeVentaDao() {}
	
	//----------------------------------------Patrón Singleton---------------------------------------------
	
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
	//------------------------------------------------------------------------------------------------------
	
	
	
	


	//---------------------------------------------------------METODOS DEL DAO -----------------------------
	
	
	// 1)--------------------------------------------------Agregar un Objeto
	
	public long agregar(UnidadDeVenta objeto) {

		long id = 0;

		try {

			iniciaOperacion();
			id = Long.parseLong(session.save(objeto).toString());
			tx.commit();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally  {
			
			//Evaluo si la sesion no quedo nula y si en algun otro flujo ya se cerro
			
			if (session != null && session.isOpen()) {
            session.close();
        	}
		}

		return id;
	}

	// 2)--------------------------------------------------Actualizar un Objeto
	
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
	
	// 3)--------------------------------------------------Eliminar un Objeto
	
	public void eliminar(UnidadDeVenta objeto) {

		try {

			iniciaOperacion();
			session.delete(objeto);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);

		} finally {
			if (session != null && session.isOpen()) {
            session.close();
			}
		}
	}

	// 4)--------------------------------------------------Traer un Objeto por ID	

	public UnidadDeVenta traer(long idUnidad) throws HibernateException{

		UnidadDeVenta objeto = null;

		try {

			iniciaOperacion();
			objeto = (UnidadDeVenta) session.createQuery("from UnidadDeVenta u where u.idUnidad=:idUnidad").setParameter("idUnidad", idUnidad).uniqueResult();

		} catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {

			if (session != null && session.isOpen()) {
	            session.close();
				}
		}

		return objeto;
	}
	
	
	// 5)--------------------------------------------------Traer un Objeto CodUnico
	
	public UnidadDeVenta traerPorCodigoUnico(String codUnico) throws HibernateException{
		
		UnidadDeVenta objeto = null;
		
		try {
			
			iniciaOperacion();
			objeto = (UnidadDeVenta) session.createQuery("from UnidadDeVenta u where u.codUnico=:codUnico").setParameter("codUnico", codUnico).uniqueResult();
			
		} catch (HibernateException he) {
			
			manejaExcepcion(he);
			
		} finally {
			
			if (session != null && session.isOpen()) {
	            session.close();
				}
		}
		
		return objeto;
	}

	// 6)--------------------------------------------------Traer una Lista con todas las UDV 

	public List<UnidadDeVenta> traer() throws HibernateException {

		List<UnidadDeVenta> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from UnidadDeVenta", UnidadDeVenta.class).list();
			
		}catch (HibernateException he) {
		        manejaExcepcion(he);
		    } finally {

			if (session != null && session.isOpen()) {
	            session.close();
				}
		}

		return lista;
	}
	
	// 7)--------------------------------------------------Traer una UDV con todos sus Pedidos
	
	public UnidadDeVenta traerUnidadYPedidos(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		
		
		if (objeto != null) {
            Hibernate.initialize(objeto.getLstPedidos());
        }
		}catch (HibernateException he) {
	        manejaExcepcion(he);
	    }
		finally {
			if (session != null && session.isOpen()) {
	            session.close();
	        }
		}
		return objeto;
	}
	
	
	// 8)--------------------------------------------------Traer una UDV con todos su Staff
	
	public UnidadDeVenta traerUnidadYstaff(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		if (objeto != null) {
            Hibernate.initialize(objeto.getLstStaff());
        }
		
		
		}catch (HibernateException he) {
	        manejaExcepcion(he);
	    }
		finally {
			if (session != null && session.isOpen()) {
	            session.close();
	        }
		}
		return objeto;
	}
	
	// 7)--------------------------------------------------Traer una UDV con todos sus Platos
	
	public UnidadDeVenta traerUnidadYplatos(long idUnidad) throws HibernateException {
		UnidadDeVenta objeto = null;
		try {
		iniciaOperacion();
		String hql = "from UnidadDeVenta u where u.idUnidad=:idUnidad";
		objeto=(UnidadDeVenta) session.createQuery(hql).setParameter("idUnidad", idUnidad).uniqueResult();
		if (objeto != null) {
            Hibernate.initialize(objeto.getLstPlatos());
           }
		}catch (HibernateException he) {
	        manejaExcepcion(he);
	    }
		finally {
			if (session != null && session.isOpen()) {
	            session.close();
	            }
			}
		return objeto;
	}
		
	
	/*CORRECCION DEL METODO ALUMNO LA ROSA LUCAS, SE AJUSTO LA CONSULTA A LA LOGICA DE NEGOCIO
	public long traerCantidadDePedidosEntreUnRango(long idUnidad, LocalDate fechaInicio,LocalDate fechaFin) throws HibernateException {
	    long cantidad =0;
	    try {
	        iniciaOperacion();
	        String hql = "select count(p)  from UnidadDeVenta u join u.lstPedidos"+ "where u.idUnidad = :idUnidad and p.fecha between :fechaInicio and :fechaFin";
	        Long resultado = (Long) session.createQuery(hql)
	                .setParameter("idUnidad", idUnidad)
	                .setParameter("fechaInicio", fechaInicio)
	                .setParameter("fechaFin", fechaFin)
	                .uniqueResult();
	        
	        if (resultado != null) {
	            cantidad = resultado;
	        }       
	    }catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    return cantidad;
	}*/
	
	
	
	
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