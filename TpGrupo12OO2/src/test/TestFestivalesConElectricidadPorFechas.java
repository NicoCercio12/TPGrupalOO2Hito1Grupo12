package test;

import java.time.LocalDate;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import datos.Festival;
import datos.UnidadDeVenta;
import datos.FoodTruck;
import negocio.FestivalABM;

public class TestFestivalesConElectricidadPorFechas {

    public static void main(String[] args) {
        FestivalABM festivalABM = FestivalABM.getInstance();

        LocalDate inicio = LocalDate.of(2026, 1, 1);
        LocalDate fin = LocalDate.of(2026, 12, 31);

        try {
            List<Festival> festivales = festivalABM.traerPorRangoFechas(inicio, fin);

            // Usar un Set para evitar imprimir festivales repetidos
            Set<String> festivalesImpresos = new HashSet<>();

            System.out.println("============================================================");
            System.out.println(" FESTIVALES CON USO DE ELECTRICIDAD EN EL RANGO " + inicio + " - " + fin);
            System.out.println("============================================================");

            double totalPlusElectricidad = 0;
            int totalUnidadesElectricas = 0;

            for (Festival f : festivales) {
                // Solo imprimir el festival si no lo mostramos antes
                if (!festivalesImpresos.contains(f.getNombre())) {
                    System.out.println("\nFestival: " + f.getNombre());
                    festivalesImpresos.add(f.getNombre());
                }

                // Recorrer las unidades del festival
                for (UnidadDeVenta u : f.getLstUnidades()) {
                    if (u instanceof FoodTruck) {
                        FoodTruck ft = (FoodTruck) u;

                        // Mostrar cada food truck
                        System.out.println("   FoodTruck: " + ft.getNombreComercial() +
                                           " | usaElectricidad=" + ft.isUsaElectricidad());

                        if (ft.isUsaElectricidad()) {
                            totalUnidadesElectricas++;
                            totalPlusElectricidad += 500; // valor fijo del plus
                        }
                    }
                }
            }

            System.out.println("\n------------------------------------------------------------");
            System.out.println("Total de unidades con electricidad: " + totalUnidadesElectricas);
            System.out.println("Total plus de electricidad aplicado: $" + totalPlusElectricidad);
            System.out.println("------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("ERROR EN CONSULTA: " + e.getMessage());
        }
    }
}
