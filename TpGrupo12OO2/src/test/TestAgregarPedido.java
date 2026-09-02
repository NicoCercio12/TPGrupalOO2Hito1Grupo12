package test;

import java.time.LocalDate;

import datos.Festival;
import datos.Pedido;
import datos.Plato;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.PedidoABM;
import negocio.UnidadDeVentaABM;

public class TestAgregarPedido {

    public static void main(String[] args) {
        try {
            long idFestival = 1; // Festival ya cargado en BD
            long idUnidad = 1;   // Unidad ya cargada en BD

            // Traemos el festival y la unidad con sus platos
            Festival festival = FestivalABM.getInstance().traer(idFestival);
            UnidadDeVenta unidad = UnidadDeVentaABM.getInstance().traerUnidadyPlatos(idUnidad);

            // Validamos que la unidad tenga platos asociados
            if (unidad.getLstPlatos() == null || unidad.getLstPlatos().isEmpty()) {
                System.out.println("⚠ La unidad " + idUnidad + " no tiene platos asociados. Inserte al menos uno en la tabla platosxunidad.");
                return;
            }

            // Tomamos el primer plato disponible
            Plato plato = unidad.getLstPlatos().iterator().next();

            // Agregamos el pedido
            long idPedido = PedidoABM.getInstance().agregar(
                    LocalDate.now(),
                    festival,
                    unidad,
                    plato,
                    2 // cantidad
            );
            System.out.println("Pedido agregado correctamente. ID: " + idPedido);

            // Verificación
            System.out.println("\n--- Verificación ---");
            Pedido pedido = PedidoABM.getInstance().traer(idPedido);
            System.out.println(pedido);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
