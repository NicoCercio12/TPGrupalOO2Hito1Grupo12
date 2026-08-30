package dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;

public class EmpleadoDao {

	private static Session session;
	private Transaction tx;
	private static EmpleadoDao instancia = null;

	protected EmpleadoDao() {
	}

	public static EmpleadoDao getInstance() {
		if (instancia == null)
			instancia = new EmpleadoDao();
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

	// Agregar (no es necesario realmente)

	public long agregar(Empleado objeto) {

		long id = 0;

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

	public void actualizar(Empleado objeto) {

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

	public void eliminar(Empleado objeto) {

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

	// Trae Empleado por id

	public Empleado traer(long idEmpleado) {

		Empleado objeto = null;

		try {

			iniciaOperacion();
			objeto = (Empleado) session.createQuery("from Empleado e where e.idEmpleado=:idEmpleado")
					.setParameter("idEmpleado", idEmpleado).uniqueResult();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return objeto;
	}

	public Empleado traerPorDni(String dni) {

		Empleado objeto = null;

		try {

			iniciaOperacion();
			objeto = (Empleado) session.createQuery("from Empleado e where e.dni=:dni").setParameter("dni", dni)
					.uniqueResult();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return objeto;
	}

	// Trae la lista de empleados

	public List<Empleado> traer() {

		List<Empleado> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from Empleado", Empleado.class).list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return lista;
	}

	// Trae la lista de cocineros

	public List<Cocinero> traerCocineros() {

		List<Cocinero> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from Cocinero", Cocinero.class).list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();
		}

		return lista;
	}

	// Trae la lista de cajeros

	public List<Cajero> traerCajeros() {

		List<Cajero> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from Cajero", Cajero.class).list();

		} catch (HibernateException he) {

			manejaExcepcion(he);

		} finally {

			session.close();

		}

		return lista;
	}

}
