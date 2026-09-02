package test;

import java.util.List;
import negocio.EmpleadoABM;
import negocio.UnidadDeVentaABM;
import datos.Empleado;
import datos.UnidadDeVenta;

public class TestNicolasCerciosimo {

    public static void main(String[] args) {
        try {
            // Supongamos que ya existe un empleado con id 1
            long idEmpleado = 1;

            // Traemos el objeto Empleado completo desde la base
            Empleado empleado = EmpleadoABM.getInstance().traer(idEmpleado);

            if (empleado == null) {
                System.out.println("No existe un empleado con id " + idEmpleado);
                return;
            }

            // Traemos las unidades de venta donde ese empleado es responsable
            List<UnidadDeVenta> unidades = UnidadDeVentaABM.getInstance().traerUnidadesComoResponsable(empleado);
            
            System.out.println();
            
            System.out.println("========================================");
            System.out.println(" Empleado consultado");
            System.out.println("========================================");
            System.out.println(empleado);
            System.out.println();

            System.out.println("========================================");
            System.out.println(" Unidades de venta como responsable");
            System.out.println("========================================");

            if (unidades.isEmpty()) {
                System.out.println("El empleado " + empleado + " no tiene unidades de venta asignadas como responsable.");
            } else {
                for (UnidadDeVenta u : unidades) {
                    System.out.println(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("========================================");
    }
    
}