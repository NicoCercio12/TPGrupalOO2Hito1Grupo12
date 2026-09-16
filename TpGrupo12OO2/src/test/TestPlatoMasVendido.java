package test;

import java.time.LocalDate;

import datos.Plato;
import negocio.PlatoABM;
import datos.UnidadDeVenta;
import negocio.UnidadDeVentaABM;

public class TestPlatoMasVendido {

    public static void main(String[] args) {

        try {

            UnidadDeVenta unidad = UnidadDeVentaABM.getInstance().traer(1);

            LocalDate fechaInicio = LocalDate.parse("2026-09-07");
            LocalDate fechaFin = LocalDate.parse("2026-09-30");

            Plato plato = PlatoABM.getInstance()
                    .traerMasVendidoPorUnidad(
                            unidad,
                            fechaInicio,
                            fechaFin
                    );

            System.out.println("-- Plato más vendido entre fechas --");
            System.out.println("Desde: " + fechaInicio);
            System.out.println("Hasta: " + fechaFin);
            System.out.println(plato);

        } catch (Exception e) {

        	System.out.println(e.getMessage());

        }
    }
}
