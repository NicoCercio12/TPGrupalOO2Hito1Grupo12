package dao;
import java.util.List; 
import java.time.LocalDate;
import datos.UnidadDeVenta;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import datos.Plato;
import datos.Festival;

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
		public Plato traerMasVendidoPorUnidad(UnidadDeVenta unidad, LocalDate fechaInicio, LocalDate fechaFin) {

		    Plato plato = null;

		    try {

		        iniciaOperacion();

		        plato = (Plato) session.createQuery(
		                "select i.plato " +
		                "from Pedido p join p.items i " +
		                "where p.unidadDeVenta = :unidad " +
		                "and p.fecha between :fechaInicio and :fechaFin " +
		                "group by i.plato " +
		                "order by sum(i.cantidad) desc")
		                .setParameter("unidad", unidad)
		                .setParameter("fechaInicio", fechaInicio)
		                .setParameter("fechaFin", fechaFin)
		                .setMaxResults(1)
		                .uniqueResult();

		    } catch (HibernateException he) {

		        manejaExcepcion(he);

		    } finally {

		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }

		    return plato;
		}
		
		
		public Plato traerMasRentablePorFestival(Festival festival) {

		    Plato plato = null;

		    try {

		        iniciaOperacion();

		        plato = (Plato) session.createQuery(
		                "select i.plato " +
		                "from Pedido p join p.items i " +
		                "where p.festival = :festival " +
		                "group by i.plato " +
		                "order by sum((i.plato.precioVenta - i.plato.costoProduccion) * i.cantidad) desc")
		                .setParameter("festival", festival)
		                .setMaxResults(1)
		                .uniqueResult();

		    } catch (HibernateException he) {

		        manejaExcepcion(he);

		    } finally {

		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }

		    return plato;
		}
		
}
