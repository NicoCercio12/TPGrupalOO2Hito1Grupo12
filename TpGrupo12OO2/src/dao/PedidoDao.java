package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pedido;

public class PedidoDao {

	private static Session session;
	private Transaction tx;

	private static PedidoDao instancia = null; // Patrón Singleton

	protected PedidoDao() {
	}

	public static PedidoDao getInstance() {

		if (instancia == null)
			instancia = new PedidoDao();

		return instancia;
	}

	private void iniciaOperacion() throws HibernateException {

		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {

		tx.rollback();

		throw new HibernateException(
				"ERROR en la capa de acceso a datos", he);
	}

	// Agregar
	public int agregar(Pedido objeto) {

		int id = 0;

		try {

			iniciaOperacion();

			id = Integer.parseInt(
					session.save(objeto).toString());

			tx.commit();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return id;
	}

	// Actualizar
	public void actualizar(Pedido objeto) {

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
	public void eliminar(Pedido objeto) {

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

	// Traer Pedido por id
	public Pedido traer(int idPedido) {

		Pedido objeto = null;

		try {

			iniciaOperacion();

			objeto = (Pedido) session
					.createQuery(
							"from Pedido p where p.idPedido=:idPedido")
					.setParameter("idPedido", idPedido)
					.uniqueResult();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return objeto;
	}

	// Traer todos los pedidos
	public List<Pedido> traer() {

		List<Pedido> lista = null;

		try {

			iniciaOperacion();

			lista = session
					.createQuery("from Pedido", Pedido.class)
					.list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return lista;
	}

	// Traer pedidos por Festival
	public List<Pedido> traerPorFestival(long idFestival) {

		List<Pedido> lista = null;

		try {

			iniciaOperacion();

			lista = session.createQuery(
					"from Pedido p where p.festival.idFestival=:idFestival",
					Pedido.class)
					.setParameter("idFestival", idFestival)
					.list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return lista;
	}

	// Traer pedidos por Unidad de Venta
	public List<Pedido> traerPorUnidad(long idUnidad) {

		List<Pedido> lista = null;

		try {

			iniciaOperacion();

			lista = session.createQuery(
					"from Pedido p where p.unidadDeVenta.idUnidad=:idUnidad",
					Pedido.class)
					.setParameter("idUnidad", idUnidad)
					.list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return lista;
	}
}