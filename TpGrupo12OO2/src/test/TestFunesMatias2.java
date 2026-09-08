package test;

import datos.Festival;
import datos.Plato;
import negocio.FestivalABM;
import negocio.PedidoABM;
import negocio.PlatoABM;

public class TestFunesMatias2 {

    public static void main(String[] args) {

        long idFestival = 1;
        long idPlato = 2;

        try {

            Festival festival =
                    FestivalABM.getInstance().traer(idFestival);

            Plato plato =
                    PlatoABM.getInstance().traer(idPlato);

            long cantidad =
                    PedidoABM.getInstance()
                            .traerCantidadVendidaDePlatoEnFestival(
                                    idFestival,
                                    idPlato);

            System.out.println();
            System.out.println(
                    "==============================================");

            System.out.println(
                    "   CANTIDAD VENDIDA DE PLATO EN FESTIVAL");

            System.out.println(
                    "==============================================");

            System.out.println(
                    "Festival: " + festival.getNombre());

            System.out.println(
                    "Plato: " + plato.getNombre());

            System.out.println();

            System.out.println(
                    "Cantidad vendida: " + cantidad);

            System.out.println(
                    "==============================================");

        } catch (Exception e) {

            System.err.println(
                    "ERROR AL REALIZAR LA CONSULTA:");

            System.err.println(e.getMessage());
        }
    }
}