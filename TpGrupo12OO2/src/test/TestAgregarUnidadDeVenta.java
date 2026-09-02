package test;

import java.time.LocalDate;

import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;
import datos.UnidadDeVenta;
import negocio.EmpleadoABM;
import negocio.UnidadDeVentaABM;

public class TestAgregarUnidadDeVenta {

	public static void main(String[] args) {

		try {
			// 1. Responsable existente
			Empleado responsable = EmpleadoABM.getInstance().traer(1L);
			if (responsable == null) {
				System.out.println("No se encontró el empleado con ID 1. Ejecutá primero TestAgregarEmpleado.");
				return;
			}

			// 2. Crear unidades de venta (códigos de 10 caracteres)
			long idFoodTruck = UnidadDeVentaABM.getInstance().agregarFoodTruck(
					"FoodTruck Central",
					responsable,
					20.0,
					280000.0,
					"FT-9999032",
					"AF999ZZ",
					true
			);
			System.out.println("FoodTruck creado con ID: " + idFoodTruck);

			long idPuesto = UnidadDeVentaABM.getInstance().agregarPuestoDesarmable(
					"Puesto Gourmet Plaza",
					responsable,
					35.0,
					150000.0,
					"PD-9999032",
					2,
					90
			);
			System.out.println("Puesto Desarmable creado con ID: " + idPuesto);

			// 3. Crear 2 empleados para el staff con fechas de ingreso distintas
			long idCocinero = EmpleadoABM.getInstance().agregarCocinero(
					"Martin",
					"Palermo",
					"34324324",
					LocalDate.of(1990, 5, 10),
					LocalDate.of(2019, 4, 1), // Antiguo (2019)
					500000.0,
					"Carnes y Parrilla",
					60000.0
			);

			long idCajero = EmpleadoABM.getInstance().agregarCajero(
					"Romina",
					"Gomez",
					"34324325", // DNI de 8 dígitos único
					LocalDate.of(1997, 11, 23),
					LocalDate.of(2023, 8, 15), // Reciente (2023)
					420000.0,
					"Tarde"
			);

			Empleado cocinero = EmpleadoABM.getInstance().traer(idCocinero);
			Empleado cajero = EmpleadoABM.getInstance().traer(idCajero);

			// 4. Asignar al staff de la unidad y persistir
			UnidadDeVenta foodTruck = UnidadDeVentaABM.getInstance().traerUnidadyStaff(idFoodTruck);
			foodTruck.getLstStaff().add(cocinero);
			foodTruck.getLstStaff().add(cajero);

			UnidadDeVentaABM.getInstance().modificar(foodTruck);
			System.out.println("Staff asociado exitosamente a la Unidad ID: " + idFoodTruck);

		} catch (Exception e) {
			System.out.println("Error en la carga: " + e.getMessage());
			e.printStackTrace();
		}
	}
}