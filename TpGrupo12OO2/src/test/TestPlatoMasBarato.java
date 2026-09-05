package test;

import datos.Plato;
import negocio.PlatoABM;

public class TestPlatoMasBarato {

    public static void main(String[] args) {

        try {
            Plato plato = PlatoABM.getInstance().traerMasBarato();

            System.out.println("--- Plato más barato ---");
            System.out.println(plato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}