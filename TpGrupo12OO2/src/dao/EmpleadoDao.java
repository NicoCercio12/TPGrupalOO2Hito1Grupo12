package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public int agregar(Empleado objeto) {

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

	
	//Eliminar
	
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

	public Empleado traer(int idEmpleado) {

		Empleado objeto = null;

		try {

			iniciaOperacion();
			objeto = (Empleado) session.createQuery("from Empleado e where e.idEmpleado=:idEmpleado")
					.setParameter("idEmpleado", idEmpleado).uniqueResult();

		} finally {

			session.close();
		}

		return objeto;
	}

	// Trae la lista de empleados

	public List<Empleado> traer() throws HibernateException {

		List<Empleado> lista = null;

		try {

			iniciaOperacion();
			lista = session.createQuery("from Empleado", Empleado.class).list();

		} finally {

			session.close();
		}

		return lista;
	}

}
