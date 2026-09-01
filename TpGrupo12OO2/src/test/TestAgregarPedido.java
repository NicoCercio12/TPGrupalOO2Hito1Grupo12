package test;

import java.time.LocalDate;

import datos.Festival;
import datos.Plato;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.PedidoABM;
import negocio.UnidadDeVentaABM;

public class TestAgregarPedido {

	public static void main(String[] args) {

		try {

			long idFestival = 1;
			long idUnidad = 1;

			Festival festival =
					FestivalABM.getInstance().traer(idFestival);

			UnidadDeVenta unidad =
					UnidadDeVentaABM.getInstance()
							.traerUnidadyPlatos(idUnidad);

			Plato plato =
					unidad.getLstPlatos()
							.iterator()
							.next();

			int idPedido =
					PedidoABM.getInstance().agregar(
							LocalDate.now(),
							festival,
							unidad,
							plato,
							2);

			System.out.println(
					"Pedido agregado correctamente. ID: "
					+ idPedido);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}