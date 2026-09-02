package test;

import datos.Empleado;
import negocio.EmpleadoABM;

public class TestCalcularSueldo {

    public static void main(String[] args) {

        long idCocinero = 1; // ajustá a los ids reales que quedaron en tu base
        long idCajero = 2;

        Empleado e1 = EmpleadoABM.getInstance().traer(idCocinero);
        Empleado e2 = EmpleadoABM.getInstance().traer(idCajero);

        if (e1 != null) {
            System.out.println(e1.getClass().getSimpleName() + ": " + e1.getNombre() + " " + e1.getApellido()
                    + " | Sueldo: $" + e1.calcularSueldo());
        } else {
            System.out.println("Empleado 1 no encontrado.");
        }

        if (e2 != null) {
            System.out.println(e2.getClass().getSimpleName() + ": " + e2.getNombre() + " " + e2.getApellido()
                    + " | Sueldo: $" + e2.calcularSueldo());
        } else {
            System.out.println("Empleado 2 no encontrado.");
        }
    }
}