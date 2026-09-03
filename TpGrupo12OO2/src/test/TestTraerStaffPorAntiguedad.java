package test;
/* La Rosa Lucas Rodrigo
 Dni: 41465362
 legajo : UNLA-53592
 */
import java.time.LocalDate;
import java.util.List;

import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;
import negocio.UnidadDeVentaABM;

public class TestTraerStaffPorAntiguedad {

    public static void main(String[] args) {
        try {
            long idUnidad = 1; // Unidad ya cargada en la BD
            LocalDate fechaCorte = LocalDate.of(2026, 12, 31);

            System.out.println("\n--- Verificación ---");

            List<Empleado> staffFiltrado = UnidadDeVentaABM.getInstance()
                    .traerStaffPorIngresoAnterior(idUnidad, fechaCorte);

            if (staffFiltrado == null || staffFiltrado.isEmpty()) {
                System.out.println("No se encontraron empleados en la unidad " + idUnidad +
                                   " con ingreso anterior o igual a " + fechaCorte);
            } else {
                for (Empleado e : staffFiltrado) {
                    String rol;
                    if (e instanceof Cocinero) {
                        rol = "Cocinero";
                    } else if (e instanceof Cajero) {
                        rol = "Cajero";
                    } else {
                        rol = "Empleado";
                    }

                    System.out.println(rol + " | " + e.getNombre() + " " + e.getApellido() +
                                       " | DNI: " + e.getDni() +
                                       " | Fecha Ingreso: " + e.getFechaIngreso());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
