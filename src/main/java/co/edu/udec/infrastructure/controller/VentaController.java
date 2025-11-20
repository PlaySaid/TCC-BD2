package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Venta;
import co.edu.udec.domain.model.DetalleVenta;
import co.edu.udec.domain.ports.in.GestionVentaService;

import java.util.List;

public class VentaController {

    private final GestionVentaService service;

    public VentaController(GestionVentaService service) {
        this.service = service;
    }

    public int crear(Venta venta, List<DetalleVenta> detalles) {
        return service.crearVenta(venta, detalles);
    }

    public Venta obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Venta> listar() {
        return service.listarVentas();
    }

    public double calcularTotal(int idVenta) {
        return service.calcularTotalVenta(idVenta);
    }
}
