package test;

import negocio.PlatoABM;
import datos.Plato;

public class TestAgregarPlato {

    public static void main(String[] args) {
        try {
            // Caso: agregar un Plato
            int idPlato = PlatoABM.getInstance().agregar(
                    "sanguche de bondiola", 
                    4500.0, 
                    2400.0
            );
            System.out.println("Plato agregado con id: " + idPlato);

            // Verificación
            System.out.println("\n--- Verificación ---");
            Plato plato = PlatoABM.getInstance().traer(idPlato);
            System.out.println(plato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
