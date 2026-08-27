package test;

import java.time.LocalDate;

import dao.EmpleadoDao;
import datos.Cajero;
import datos.Cocinero;

public class TestAgregarEmpleado {

	public static void main(String[] args) {

		// Caso 1: agregar un Cocinero
		Cocinero cocinero = new Cocinero("Laura", "Gomez", "30111222", LocalDate.of(1990, 5, 10),
				LocalDate.of(2022, 1, 15), 500000.0, "Parrilla", 80000.0);

		int idCocinero = EmpleadoDao.getInstance().agregar(cocinero);
		System.out.println("Cocinero agregado con id: " + idCocinero);

		// Caso 2: agregar un Cajero
		Cajero cajero = new Cajero("Martin", "Diaz", "28555666", LocalDate.of(1985, 3, 20), LocalDate.of(2021, 6, 1),
				420000.0, "Mañana");

		int idCajero = EmpleadoDao.getInstance().agregar(cajero);
		System.out.println("Cajero agregado con id: " + idCajero);

		// Uso esto para verificar que todo este OK 
		System.out.println("\n--- Verificación ---");
		System.out.println(EmpleadoDao.getInstance().traer(idCocinero));
		System.out.println(EmpleadoDao.getInstance().traer(idCajero));
	}
}