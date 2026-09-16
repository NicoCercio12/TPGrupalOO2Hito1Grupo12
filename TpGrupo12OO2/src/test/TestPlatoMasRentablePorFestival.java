package test;

import datos.Festival;
import datos.Plato;
import negocio.FestivalABM;
import negocio.PlatoABM;

public class TestPlatoMasRentablePorFestival {

    public static void main(String[] args) {

        try {

            Festival festival =
                    FestivalABM.getInstance().traer(1);

            Plato plato = PlatoABM.getInstance()
                    .traerMasRentablePorFestival(festival);

            System.out.println("-- Plato más rentable del festival --");
            System.out.println("Festival: " + festival);
            System.out.println("Plato: " + plato);

        } catch (Exception e) {

        	System.out.println(e.getMessage());
        }
    }
}
