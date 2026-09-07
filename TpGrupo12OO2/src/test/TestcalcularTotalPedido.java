package test;
import negocio.PedidoABM;

public class TestcalcularTotalPedido {
	public static void main(String[] args) {

        PedidoABM pedido = PedidoABM.getInstance();

        // Caso 1: Cálculo exitoso sobre un pedido con ítems asociados
        System.out.println("--- Test 1: Cálculo de total correcto ---");
        try {
            int idPedidoValido = 1;
            double total = pedido.calcularTotalPedido(idPedidoValido);
            System.out.println("Total calculado para Pedido ID " + idPedidoValido + ": $" + total);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }

        // Caso 2: Validación de pedido sin ítems cargados
        System.out.println("\n--- Test 2: Pedido sin ítems ---");
        try {
            int idSinItems = 2; // ID de un pedido en BD que no tenga registros en itempedido
            double total = pedido.calcularTotalPedido(idSinItems);
            System.out.println("Total: $" + total);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        // Caso 3: Validación de pedido inexistente
        System.out.println("\n--- Test 3: Pedido inexistente ---");
        try {
            int idInexistente = 9999;
            double total = pedido.calcularTotalPedido(idInexistente);
            System.out.println("Total: $" + total);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
