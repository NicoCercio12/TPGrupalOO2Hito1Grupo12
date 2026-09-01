package test;

import datos.Pedido;
import negocio.PedidoABM;

public class TestTraerPedido {

	public static void main(String[] args) {

		try {

			int idPedido = 1;

			Pedido pedido =
					PedidoABM.getInstance()
							.traer(idPedido);

			if (pedido != null) {

				System.out.println(
						"Pedido encontrado:");

				System.out.println(pedido);

			} else {

				System.out.println(
						"No existe un pedido con ID: "
						+ idPedido);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}