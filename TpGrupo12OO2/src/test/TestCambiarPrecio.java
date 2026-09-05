package test;

import datos.Plato;
import negocio.PlatoABM;

public class TestCambiarPrecio {

    public static void main(String[] args) {

        try {
            long idPlato = 1;
            double nuevoPrecio = 5000;

            PlatoABM.getInstance().cambiarPrecio(idPlato, nuevoPrecio);

            Plato plato = PlatoABM.getInstance().traer(idPlato);

            System.out.println("--- Plato actualizado ---");
            System.out.println(plato);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
