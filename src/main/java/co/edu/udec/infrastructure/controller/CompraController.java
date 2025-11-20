package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Compra;
import co.edu.udec.domain.model.DetalleCompra;
import co.edu.udec.domain.ports.in.GestionCompraService;

import java.util.List;

public class CompraController {

    private final GestionCompraService service;

    public CompraController(GestionCompraService service) {
        this.service = service;
    }

    public int crear(Compra compra, List<DetalleCompra> detalles) {
        return service.crearCompra(compra, detalles);
    }

    public Compra obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Compra> listar() {
        return service.listarCompras();
    }

    public double calcularTotal(int idCompra) {
        return service.calcularTotalCompra(idCompra);
    }
}
