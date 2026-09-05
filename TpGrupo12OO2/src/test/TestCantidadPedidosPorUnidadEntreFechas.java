package test;
import java.time.LocalDate;

import negocio.PedidoABM;
public class TestCantidadPedidosPorUnidadEntreFechas {
	public static void main(String[] args) {
		
		
		PedidoABM pedido = PedidoABM.getInstance();
		
		
		System.out.println("--- Test 1: Cálculo entre fechas correcto ---");
		
		LocalDate fechaInicio= LocalDate.parse("2026-09-20");
		LocalDate fechaFin=LocalDate.parse("2026-09-30");
		long idUnidad = 2;
		try {
		long cantPedidos=pedido.traerCantidadPedidosPorUnidadEntreFechas(idUnidad, fechaInicio, fechaFin);
		System.out.println("La cantidad de pedidos generados entre el" + fechaInicio + "y el" + fechaFin + " son un total de" +cantPedidos+"Pedidos");
	}catch (Exception e) {
		System.out.println( e.getMessage());	
	
	} 
	}
}
