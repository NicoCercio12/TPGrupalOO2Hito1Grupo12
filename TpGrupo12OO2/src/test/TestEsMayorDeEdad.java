package test;

import datos.Empleado;
import negocio.EmpleadoABM;

public class TestEsMayorDeEdad {

	public static void main(String[] args) {

		//Traigo el empleado existente
		
		long idExistente = 2; 

		Empleado empleado = EmpleadoABM.getInstance().traer(idExistente);

		if (empleado == null) {
			System.out.println("No se encontró el empleado con id " + idExistente);
		} else {
			System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido());
			System.out.println("¿Es mayor de edad?: " + empleado.esMayorDeEdad());
		}

		System.out.println();

		//Creamos un empleado menor de edad
		
		try {
			EmpleadoABM.getInstance().agregarCajero("Juan", "Perez", "45222333",
					java.time.LocalDate.now().minusYears(15), java.time.LocalDate.of(2023, 1, 1), 350000.0, "Noche");

			System.out.println("ERROR: Si podes leer este mensaje, algo esta fallando");

		} catch (Exception e) {
			System.out.println("Funciona correctamente: " + e.getMessage());
		}
	}
}