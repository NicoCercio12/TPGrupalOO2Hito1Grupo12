package test;

import negocio.EmpleadoABM;
import negocio.UnidadDeVentaABM;
import datos.Empleado;
import datos.UnidadDeVenta;

public class TestNicolasCerciosimo2 {

	public static void main(String[] args) {

		try {

			UnidadDeVentaABM unidadABM = UnidadDeVentaABM.getInstance();
			EmpleadoABM empleadoABM = EmpleadoABM.getInstance();

			// 1. Traemos la unidad
			UnidadDeVenta unidad = unidadABM.traer(1L);

			if (unidad == null) {
				throw new Exception("ERROR: No se encontro la Unidad de Venta con ID 1.");
			}

			System.out.println("Unidad de Venta recuperada: " + unidad.getNombreComercial());

			// 2. Ejecutamos la liquidacion total
			// (Este metodo ejecuta el HQL con 'left join fetch' en su propia sesion)
			double totalHaberes = unidadABM.liquidarHaberes(unidad);

			System.out.println("-------------------------------------------------------");
			System.out.println("TOTAL HABERES A PAGAR EN LA UNIDAD: $" + totalHaberes);
			System.out.println("-------------------------------------------------------");

			// 3. Desglose individual: consultamos a los empleados desde el ABM
			// para no depender del proxy lazy de la unidad fuera de sesion
			System.out.println("\n--- DESGLOSE POR EMPLEADO ---");
			
			String[] dnis = {"30111222", "28555666", "34324324", "34324325"};
			
			for (String dni : dnis) {
				Empleado emp = empleadoABM.traerPorDni(dni);
				if (emp != null) {
					System.out.println("Empleado: " + emp.getApellido() + ", " + emp.getNombre() 
							+ " | Haberes: $" + emp.liquidarHaberes());
				}
			}

		} catch (Exception e) {

			System.err.println("ERROR EN TEST: " + e.getMessage());
			e.printStackTrace();

		}
	}
}