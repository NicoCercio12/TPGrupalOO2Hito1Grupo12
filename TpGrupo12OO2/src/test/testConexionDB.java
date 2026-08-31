package test;
import org.hibernate.Session;
import dao.HibernateUtil;

public class testConexionDB {
	public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println(">>> ¡Conexión exitosa a la base de datos! <<<");
            session.close();
        } catch (Exception e) {
            System.err.println(">>> Error al conectar con la base de datos: <<<");
            e.printStackTrace();
        }
    }
}
