package test;

import java.util.List;
import negocio.EmpleadoABM;
import negocio.UnidadDeVentaABM;
import datos.UnidadDeVenta;

public class TestTraerUnidadesComoResponsable {

    public static void main(String[] args) {
        try {
            // Supongamos que ya existe un empleado con id 1
            long idEmpleado = 1;

            // Traemos las unidades de venta donde es responsable
            List<UnidadDeVenta> unidades = EmpleadoABM.getInstance().traerUnidadesComoResponsable(idEmpleado);

            // Verificación
            System.out.println("\n--- Verificación ---");
            if (unidades.isEmpty()) {
                System.out.println("El empleado con id " + idEmpleado + " no tiene unidades de venta asignadas como responsable.");
            } else {
                for (UnidadDeVenta u : unidades) {
                    System.out.println(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
