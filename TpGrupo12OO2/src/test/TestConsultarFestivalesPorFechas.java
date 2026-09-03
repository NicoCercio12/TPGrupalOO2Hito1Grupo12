package test;

import java.time.LocalDate;
import java.util.List;

import datos.Festival;
import negocio.FestivalABM;

public class TestConsultarFestivalesPorFechas {

    public static void main(String[] args) {

        FestivalABM festivalABM = FestivalABM.getInstance();

        // Definir rango de fechas
        LocalDate inicio = LocalDate.of(2026, 1, 1);
        LocalDate fin = LocalDate.of(2026, 6, 30);

        try {
            // Traer festivales en el rango
        	List<Festival> festivales = festivalABM.traerPorRangoFechas(inicio, fin);

            System.out.println();
            System.out.println("============================================================");
            System.out.println("       CONSULTA DE FESTIVALES POR FECHAS");
            System.out.println("============================================================");
            System.out.println("Rango: " + inicio + " hasta " + fin);
            System.out.println();

            if (festivales.isEmpty()) {
                System.out.println("No existen festivales en ese rango de fechas.");
            } else {
                int posicion = 1;
                for (Festival f : festivales) {
                    System.out.println(
                        posicion + ". " + f.getNombre() +
                        " | Temporada: " + f.getTemporada() +
                        " | Inicio: " + f.getFechaInicio() +
                        " | Fin: " + f.getFechaFin()
                    );
                    posicion++;
                }
            }

            System.out.println();
            System.out.println("============================================================");
            System.out.println("       CONSULTA FINALIZADA CORRECTAMENTE");
            System.out.println("============================================================");

        } catch (Exception e) {
            System.err.println("ERROR AL CONSULTAR FESTIVALES POR FECHAS:");
            e.printStackTrace();
        }
    }
}