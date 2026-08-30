package test;

import java.time.LocalDate;

import negocio.EmpleadoABM;

public class TestAgregarEmpleado {

	public static void main(String[] args) {

		try {
			// Caso 1: agregar un Cocinero
			long idCocinero = EmpleadoABM.getInstance().agregarCocinero("Laura", "Gomez", "30111222",
					LocalDate.of(1990, 5, 10), LocalDate.of(2022, 1, 15), 500000.0, "Parrilla", 80000.0);
			System.out.println("Cocinero agregado con id: " + idCocinero);

			// Caso 2: agregar un Cajero
			long idCajero = EmpleadoABM.getInstance().agregarCajero("Martin", "Diaz", "28555666",
					LocalDate.of(1985, 3, 20), LocalDate.of(2021, 6, 1), 420000.0, "Mañana");
			System.out.println("Cajero agregado con id: " + idCajero);

			// Verificación
			System.out.println("\n--- Verificación ---");
			System.out.println(EmpleadoABM.getInstance().traer(idCocinero));
			System.out.println(EmpleadoABM.getInstance().traer(idCajero));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}