package test;



import java.time.LocalDate;

import java.util.List;



import datos.Cajero;

import datos.Cocinero;

import datos.Empleado;

import negocio.UnidadDeVentaABM;



public class TestTraerStaffPorAntiguedad {



public static void main(String[] args) {



try {

long idUnidad = 6L; // Unidad generada en la última carga exitosa

LocalDate fechaCorte = LocalDate.of(2026, 12, 31);



System.out.println("=== Consultando Staff de la Unidad ID: " + idUnidad + " ===");

System.out.println("Criterio: Fecha de ingreso anterior o igual a " + fechaCorte + "\n");



List<Empleado> staffFiltrado = UnidadDeVentaABM.getInstance()

.traerStaffPorIngresoAnterior(idUnidad, fechaCorte);



if (staffFiltrado == null || staffFiltrado.isEmpty()) {

System.out.println("No se registraron empleados en esta unidad que cumplan el criterio.");

} else {

System.out.println("Empleados encontrados (" + staffFiltrado.size() + "):");

for (Empleado e : staffFiltrado) {

String rol = (e instanceof Cocinero) ? "Cocinero" : (e instanceof Cajero) ? "Cajero" : "Empleado";

System.out.println("- [" + rol + "] " + e.getNombre() + " " + e.getApellido()

+ " | DNI: " + e.getDni()

+ " | Fecha Ingreso: " + e.getFechaIngreso());

}

}



} catch (Exception e) {

System.out.println("Error en la consulta: " + e.getMessage());

e.printStackTrace();

}

}

} 

