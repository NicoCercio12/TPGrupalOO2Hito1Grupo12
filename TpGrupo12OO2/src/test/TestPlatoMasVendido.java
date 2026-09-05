package test;

import datos.Plato;
import negocio.PlatoABM;

public class TestPlatoMasVendido {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {

            // ID de la Unidad de Venta que queremos consultar
            long idUnidad = 1;

            Plato plato = PlatoABM.getInstance()
                    .traerMasVendidoPorUnidad(idUnidad);

            System.out.println("--- Plato más vendido ---");
            System.out.println(plato);

        } catch (Exception e) {

            e.printStackTrace();
        }
	}
}
