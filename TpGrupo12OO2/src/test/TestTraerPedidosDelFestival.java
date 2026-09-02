package test;

import java.util.List;

import datos.Pedido;
import negocio.FestivalABM;
import dao.FestivalDao;

public class TestTraerPedidosDelFestival {

    public static void main(String[] args) {
        try {
            long idFestival = 1; // Festival ya cargado en BD

            System.out.println("\n--- Verificación ---");
            System.out.println("Pedidos asociados al Festival ID: " + idFestival);

            List<Pedido> pedidos = FestivalDao.getInstance().traerPedidosDelFestival(idFestival);

            if (pedidos == null || pedidos.isEmpty()) {
                System.out.println("No se encontraron pedidos para este festival.");
            } else {
                for (Pedido p : pedidos) {
                    System.out.println(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
