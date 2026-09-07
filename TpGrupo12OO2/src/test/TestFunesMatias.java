package test;

import java.time.LocalDate;
import datos.Pedido;
import negocio.PedidoABM;
import java.util.*;

public class TestFunesMatias {

	public static void main(String[] args) {
		long idFestival = 1;

        LocalDate fechaInicio =
                LocalDate.of(2026, 9, 1);

        LocalDate fechaFin =
                LocalDate.of(2026, 9, 30);

        try {

            List<Pedido> pedidos =
                    PedidoABM.getInstance()
                            .traerPorFestivalEntreFechas(
                                    idFestival,
                                    fechaInicio,
                                    fechaFin);

            System.out.println();
            System.out.println(
                    "====================================================");

            System.out.println(
                    "     PEDIDOS DE FESTIVAL ENTRE FECHAS");

            System.out.println(
                    "====================================================");

            System.out.println(
                    "Festival ID: " + idFestival);

            System.out.println(
                    "Desde: " + fechaInicio);

            System.out.println(
                    "Hasta: " + fechaFin);

            System.out.println();

            if (pedidos.isEmpty()) {

                System.out.println(
                        "No existen pedidos para el festival "
                        + "en el rango indicado.");

            } else {

                int posicion = 1;

                for (Pedido pedido : pedidos) {

                    System.out.println(
                            posicion
                            + ". Pedido ID: "
                            + pedido.getIdPedido()
                            + " | Fecha: "
                            + pedido.getFecha());

                    posicion++;
                }
            }

            System.out.println();

            System.out.println(
                    "Cantidad de pedidos encontrados: "
                    + pedidos.size());

            System.out.println(
                    "====================================================");

        } catch (Exception e) {

            System.err.println(
                    "ERROR AL CONSULTAR LOS PEDIDOS:");

            System.err.println(e.getMessage());
        }

	}

}
