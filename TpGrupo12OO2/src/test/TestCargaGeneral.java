package test;

import java.time.LocalDate;

import dao.FestivalDao;
import dao.PedidoDao;

import datos.Empleado;
import datos.Festival;
import datos.Pedido;
import datos.Plato;
import datos.UnidadDeVenta;

import negocio.EmpleadoABM;
import negocio.PedidoABM;
import negocio.PlatoABM;
import negocio.UnidadDeVentaABM;

public class TestCargaGeneral {

	public static void main(String[] args) {

		try {

			System.out.println("==============================================");
			System.out.println("        INICIO CARGA GENERAL DE DATOS");
			System.out.println("==============================================");

			// 1. EMPLEADOS
			// Datos usados por TestAgregarEmpleado

			long idLaura = EmpleadoABM.getInstance().agregarCocinero("Laura", "Gomez", "30111222", LocalDate.of(1990, 5, 10), LocalDate.of(2022, 1, 15), 500000.0, "Parrilla", 80000.0);

			long idMartinDiaz = EmpleadoABM.getInstance().agregarCajero("Martin", "Diaz", "28555666", LocalDate.of(1985, 3, 20), LocalDate.of(2021, 6, 1), 420000.0, "Mañana");

			// Datos utilizados en los tests de Unidad/Staff
			long idMartinPalermo = EmpleadoABM.getInstance().agregarCocinero("Martin", "Palermo", "34324324", LocalDate.of(1990, 5, 10), LocalDate.of(2019, 4, 1), 500000.0, "Carnes y Parrilla", 60000.0);

			long idRomina = EmpleadoABM.getInstance().agregarCajero("Romina", "Gomez", "34324325", LocalDate.of(1997, 11, 23), LocalDate.of(2023, 8, 15), 420000.0, "Tarde");

			// Empleados extra
			long idSofia = EmpleadoABM.getInstance().agregarCocinero("Sofia", "Fernandez", "36777888", LocalDate.of(1992, 8, 12), LocalDate.of(2020, 3, 10), 520000.0, "Pastas", 70000.0);

			long idLucas = EmpleadoABM.getInstance().agregarCajero("Lucas", "Perez", "37888999", LocalDate.of(1995, 4, 20), LocalDate.of(2024, 2, 1), 430000.0, "Noche");

			Empleado laura = EmpleadoABM.getInstance().traer(idLaura);

			Empleado martinPalermo = EmpleadoABM.getInstance().traer(idMartinPalermo);

			Empleado romina = EmpleadoABM.getInstance().traer(idRomina);

			Empleado sofia = EmpleadoABM.getInstance().traer(idSofia);

			Empleado lucas = EmpleadoABM.getInstance().traer(idLucas);

			System.out.println("Empleados cargados correctamente");

			// =====================================================
			// 2. UNIDADES DE VENTA
			// ID 1 = FoodTruck
			// ID 2 = Puesto
			// =====================================================

			long idFoodTruck = UnidadDeVentaABM.getInstance().agregarFoodTruck("FoodTruck Central", laura, 20.0, 280000.0, "FT-9999032", "AF999ZZ", true);

			long idPuesto = UnidadDeVentaABM.getInstance().agregarPuestoDesarmable("Puesto Gourmet Plaza", laura, 35.0, 150000.0, "PD-9999032", 2, 90);

			// Unidad extra
			long idFoodTruck2 = UnidadDeVentaABM.getInstance().agregarFoodTruck("Sabores del Sur", sofia, 25.0, 310000.0, "FT-9999033", "AG123BC", false);

			System.out.println("Unidades cargadas correctamente");
	
			// 3. STAFF		

			UnidadDeVenta foodTruckStaff = UnidadDeVentaABM.getInstance().traerUnidadyStaff(idFoodTruck);

			foodTruckStaff.getLstStaff().add(martinPalermo);
			foodTruckStaff.getLstStaff().add(romina);

			UnidadDeVentaABM.getInstance().modificar(foodTruckStaff);

			UnidadDeVenta puestoStaff = UnidadDeVentaABM.getInstance().traerUnidadyStaff(idPuesto);

			puestoStaff.getLstStaff().add(sofia);
			puestoStaff.getLstStaff().add(lucas);

			UnidadDeVentaABM.getInstance().modificar(puestoStaff);

			System.out.println("Staff cargado");

			// 4. PLATOS

			int idBondiola = PlatoABM.getInstance().agregar("sanguche de bondiola", 4500.0, 2400.0);

			int idHamburguesa = PlatoABM.getInstance().agregar("Hamburguesa Completa", 9000.0, 4300.0);

			int idPizza = PlatoABM.getInstance().agregar("Pizza Napolitana", 11000.0, 5200.0);

			int idPapas = PlatoABM.getInstance().agregar("Papas Fritas", 5000.0, 2100.0);

			int idEmpanadas = PlatoABM.getInstance().agregar("Empanadas", 2500.0, 1100.0);

			int idVegana = PlatoABM.getInstance().agregar("Hamburguesa Vegana", 9500.0, 4600.0);

			Plato bondiola = PlatoABM.getInstance().traer(idBondiola);

			Plato hamburguesa = PlatoABM.getInstance().traer(idHamburguesa);

			Plato pizza = PlatoABM.getInstance().traer(idPizza);

			Plato papas = PlatoABM.getInstance().traer(idPapas);

			Plato empanadas = PlatoABM.getInstance().traer(idEmpanadas);

			Plato vegana = PlatoABM.getInstance().traer(idVegana);

			System.out.println("Platos cargados");

			// 5. ASOCIAR PLATOS CON UNIDADES

			UnidadDeVenta foodTruck = UnidadDeVentaABM.getInstance().traerUnidadyPlatos(idFoodTruck);

			foodTruck.getLstPlatos().add(bondiola);
			foodTruck.getLstPlatos().add(hamburguesa);
			foodTruck.getLstPlatos().add(pizza);
			foodTruck.getLstPlatos().add(papas);

			UnidadDeVentaABM.getInstance().modificar(foodTruck);

			UnidadDeVenta puesto = UnidadDeVentaABM.getInstance().traerUnidadyPlatos(idPuesto);

			puesto.getLstPlatos().add(bondiola);
			puesto.getLstPlatos().add(pizza);
			puesto.getLstPlatos().add(empanadas);
			puesto.getLstPlatos().add(vegana);

			UnidadDeVentaABM.getInstance().modificar(puesto);

			UnidadDeVenta foodTruck2 = UnidadDeVentaABM.getInstance().traerUnidadyPlatos(idFoodTruck2);

			foodTruck2.getLstPlatos().add(hamburguesa);
			foodTruck2.getLstPlatos().add(papas);
			foodTruck2.getLstPlatos().add(vegana);

			UnidadDeVentaABM.getInstance().modificar(foodTruck2);

			System.out.println("Platos asociados a las unidades correctamente");

			// 6. FESTIVALES

			Festival festivalParrillero = new Festival("Festival Parrillero", "Verano", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20));

			int idFestivalParrillero = FestivalDao.getInstance().agregar(festivalParrillero);

			Festival festivalVegano = new Festival("Festival Vegano", "Invierno", LocalDate.of(2026, 7, 5), LocalDate.of(2026, 7, 15));

			int idFestivalVegano = FestivalDao.getInstance().agregar(festivalVegano);

			Festival saboresDelMundo = new Festival("Sabores del Mundo", "Primavera", LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 30));

			int idSabores = FestivalDao.getInstance().agregar(saboresDelMundo);

			Festival festivalPrimavera = new Festival("Festival Primavera", "Primavera", LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 15));

			int idPrimavera = FestivalDao.getInstance().agregar(festivalPrimavera);

			System.out.println("Festivales cargados");

			festivalParrillero = FestivalDao.getInstance().traer(idFestivalParrillero);

			festivalVegano = FestivalDao.getInstance().traer(idFestivalVegano);

			saboresDelMundo = FestivalDao.getInstance().traer(idSabores);

			// 7. PEDIDOS
			
			// PEDIDO 1

			int idPedido1 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 1, 12), festivalParrillero, foodTruck, bondiola, 3);

			// PEDIDO 2

			Pedido pedidoSinItems = new Pedido(LocalDate.of(2026, 9, 10), saboresDelMundo, puesto);

			int idPedido2 = PedidoDao.getInstance().agregar(pedidoSinItems);

			int idPedido3 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 1, 15), festivalParrillero, foodTruck, pizza, 2);

			int idPedido4 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 1, 18), festivalParrillero, foodTruck, bondiola, 4);


			// Pedidos de UNIDAD 2 durante septiembre.


			int idPedido5 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 9, 12), saboresDelMundo, puesto, pizza,2);

			int idPedido6 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 9, 20), saboresDelMundo, puesto, empanadas, 6);

			int idPedido7 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 9, 25), saboresDelMundo, puesto, vegana, 3);

			// Festival Vegano

			int idPedido8 = PedidoABM.getInstance().agregar(LocalDate.of(2026, 7, 8), festivalVegano, puesto, vegana,4);

			System.out.println();
			System.out.println("==============================================");
			System.out.println("        CARGA FINALIZADA CORRECTAMENTE");
			System.out.println("==============================================");

		} catch (Exception e) {

			System.err.println("ERROR DURANTE LA CARGA!!!");

			System.err.println(e.getMessage());

			e.printStackTrace();
		}
	}
}