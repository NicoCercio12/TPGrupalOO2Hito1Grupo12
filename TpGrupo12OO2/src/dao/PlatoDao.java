package dao;
import java.util.List; 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import datos.Plato;

public class PlatoDao {

		private static Session session;
		private Transaction tx;
		private static PlatoDao instancia = null;

		protected PlatoDao() {
		}

		public static PlatoDao getInstance() {
			if (instancia == null)
				instancia = new PlatoDao();
			return instancia;
		}
		//comentario

		protected void iniciaOperacion() throws HibernateException {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
		}

		protected void manejaExcepcion(HibernateException he) throws HibernateException {
			tx.rollback();
			throw new HibernateException("ERROR en la capa de acceso a datos", he);
		}

		// Agregar

		public int agregar(Plato objeto) {

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

		public void actualizar(Plato objeto) {

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

		public void eliminar(Plato objeto) {

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

		// Trae plato por id

		public Plato traer(long idPlato) {

			Plato objeto = null;

			try {

				iniciaOperacion();
				objeto = (Plato) session.createQuery(
						"from Plato p where p.idPlato=:idPlato")
						.setParameter("idPlato", idPlato)
						.uniqueResult();

			} catch (HibernateException he) {

				manejaExcepcion(he);

			} finally {

				session.close();
			}

			return objeto;
		}

		// Trae plato por nombre

		public Plato traerPorNombre(String nombre) {

			Plato objeto = null;

			try {

				iniciaOperacion();
				objeto = (Plato) session.createQuery(
						"from Plato p where p.nombre=:nombre")
						.setParameter("nombre", nombre)
						.uniqueResult();

			} catch (HibernateException he) {

				manejaExcepcion(he);

			} finally {

				session.close();
			}

			return objeto;
		}

		// Trae la lista de platos

		public List<Plato> traer() {

			List<Plato> lista = null;

			try {

				iniciaOperacion();
				lista = session.createQuery("from Plato", Plato.class).list();

			} catch (HibernateException he) {

				manejaExcepcion(he);

			} finally {

				session.close();
			}

			return lista;
		}
		
		// Traer el plato más vendido de una Unidad de Venta
		public Plato traerMasVendidoPorUnidad(long idUnidad) {

		    Plato plato = null;

		    try {

		        iniciaOperacion();

		        plato = (Plato) session.createQuery(
		                "select i.plato " +
		                "from Pedido p join p.items i " +
		                "where p.unidadDeVenta.idUnidad = :idUnidad " +
		                "group by i.plato " +
		                "order by sum(i.cantidad) desc")
		                .setParameter("idUnidad", idUnidad)
		                .setMaxResults(1)
		                .uniqueResult();

		    } catch (HibernateException he) {

		        manejaExcepcion(he);

		    } finally {

		        session.close();
		    }

		    return plato;
		}
		
		public Plato traerMasBarato() {
		    Plato plato = null;

		    try {
		        iniciaOperacion();

		        plato = (Plato) session.createQuery(
		                "from Plato p order by p.precioVenta asc")
		                .setMaxResults(1)
		                .uniqueResult();

		    } catch (HibernateException he) {
		        manejaExcepcion(he);
		    } finally {
		        session.close();
		    }

		    return plato;
		}
		

}
