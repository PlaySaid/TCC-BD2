package co.edu.udec.application.service;

import co.edu.udec.domain.model.Proveedor;
import co.edu.udec.domain.ports.in.GestionProveedorService;
import co.edu.udec.domain.ports.out.ProveedorRepository;

import java.util.List;

public class GestionProveedorServiceImpl implements GestionProveedorService {

    private final ProveedorRepository proveedorRepository;

    public GestionProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public boolean crearProveedor(Proveedor proveedor) {
        if (proveedor == null || proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            return false;
        }
        return proveedorRepository.guardar(proveedor);
    }

    @Override
    public boolean actualizarProveedor(Proveedor proveedor) {
        if (proveedor == null || proveedor.getId() <= 0) return false;
        return proveedorRepository.actualizar(proveedor);
    }

    @Override
    public boolean eliminarProveedor(int id) {
        if (id <= 0) return false;
        return proveedorRepository.eliminar(id);
    }

    @Override
    public Proveedor obtenerPorId(int id) {
        if (id <= 0) return null;
        return proveedorRepository.obtenerPorId(id);
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.listar();
    }
}
