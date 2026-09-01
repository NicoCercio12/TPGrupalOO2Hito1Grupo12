package test;

import java.time.LocalDate;

import datos.Pedido;
import negocio.PedidoABM;

public class TestModificarPedido {

	public static void main(String[] args) {

		try {

			int idPedido = 1;

			Pedido pedido =
					PedidoABM.getInstance()
							.traer(idPedido);

			if (pedido != null) {

				pedido.setFecha(
						LocalDate.of(2026, 9, 10));

				PedidoABM.getInstance()
						.modificar(pedido);

				System.out.println(
						"Pedido modificado correctamente:");

				System.out.println(pedido);

			} else {

				System.out.println(
						"No existe el pedido con ID: "
						+ idPedido);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}