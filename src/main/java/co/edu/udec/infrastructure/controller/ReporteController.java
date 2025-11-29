package co.edu.udec.infrastructure.controller;

import co.edu.udec.infrastructure.repository.ReporteRepositoryImpl;

import java.util.List;

public class ReporteController {

    private final ReporteRepositoryImpl repo;

    public ReporteController() {
        this.repo = new ReporteRepositoryImpl();
    }

    public void mostrarProductosBajoStock() {
        List<String> data = repo.productosBajoStock();
        System.out.println("\n=== PRODUCTOS CON BAJO STOCK ===");
        data.forEach(System.out::println);
    }

    public void mostrarValorInventario() {
        double total = repo.valorInventario();
        System.out.println("\n=== VALOR TOTAL DEL INVENTARIO ===");
        System.out.println("$ " + total);
    }

    public void mostrarVentasPorMes() {
        List<String> data = repo.ventasPorMes();
        System.out.println("\n=== VENTAS POR MES ===");
        data.forEach(System.out::println);
    }

    public void mostrarProductosMasVendidos() {
        List<String> data = repo.productosMasVendidos();
        System.out.println("\n=== PRODUCTOS MÁS VENDIDOS ===");
        data.forEach(System.out::println);
    }
}
