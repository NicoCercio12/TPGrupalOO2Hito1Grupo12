package test;

import java.time.LocalDate;

import dao.FestivalDao;
import datos.Festival;

public class TestAgregarFestival {

    public static void main(String[] args) {

        // Caso 1: agregar un Festival
        Festival festival = new Festival("Festival Parrillero", "Verano",
                LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20));

        int idFestival = FestivalDao.getInstance().agregar(festival);
        System.out.println("Festival agregado con id: " + idFestival);

        // Verificación: traer por id
        System.out.println("\n--- Verificación ---");
        System.out.println(FestivalDao.getInstance().traer(idFestival));

        // Caso 2: agregar otro Festival
        Festival festival2 = new Festival("Festival Vegano", "Invierno",
                LocalDate.of(2026, 7, 5), LocalDate.of(2026, 7, 15));

        int idFestival2 = FestivalDao.getInstance().agregar(festival2);
        System.out.println("Festival agregado con id: " + idFestival2);

        // Verificación: traer todos
        System.out.println("\n--- Lista de Festivales ---");
        FestivalDao.getInstance().traer().forEach(System.out::println);
    }
}
