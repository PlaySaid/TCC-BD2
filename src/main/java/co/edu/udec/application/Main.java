package co.edu.udec.application;

import co.edu.udec.application.service.*;
import co.edu.udec.domain.model.*;
import co.edu.udec.infrastructure.repository.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Usuario usuarioLogueado;
    private static final Scanner scanner = new Scanner(System.in);

    // Services
    private static GestionProductoServiceImpl productoService;
    private static GestionClienteServiceImpl clienteService;
    private static LoginServiceImpl loginService;

    public static void main(String[] args) {

        // Repository -> Service wiring
        productoService = new GestionProductoServiceImpl(new ProductoRepositoryImpl());
        clienteService = new GestionClienteServiceImpl(new ClienteRepositoryImpl());
        loginService = new LoginServiceImpl(new UsuarioRepositoryImpl());

        menuPrincipal();
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n========= EL BUEN VECINO - SISTEMA =========");
            System.out.println("Usuario: " + (usuarioLogueado != null ? usuarioLogueado.getUsuario() : "No logueado"));
            System.out.println("--------------------------------------------");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Clientes");
            System.out.println("4. Reportes");
            System.out.println("0. Salir");
            System.out.println("--------------------------------------------");

            int opcion = leerInt("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> login();
                case 2 -> menuProductos();
                case 3 -> menuClientes();
                case 4 -> menuReportes();
                case 0 -> {
                    System.out.println("👋 Cerrando el sistema...");
                    return;
                }
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    // ---------------- LOGIN ----------------

    private static void login() {
        if (usuarioLogueado != null) {
            System.out.println("⚠ Ya hay sesión activa. ¿Cerrar sesión? (s/n)");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                usuarioLogueado = null;
                System.out.println("Sesión cerrada.");
            }
            return;
        }

        String user = leerTexto("Usuario: ");
        String pass = leerTexto("Contraseña: ");

        Usuario u = loginService.login(user, pass);

        if (u == null) {
            System.out.println("❌ Credenciales incorrectas.");
        } else {
            usuarioLogueado = u;
            System.out.println("✔ Bienvenido, " + usuarioLogueado.getNombre());
        }
    }

    // ---------------- PRODUCTOS ----------------

    private static void menuProductos() {
        if (!validarSesion()) return;

        while (true) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            int opcion = leerInt("Opción: ");

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> agregarProducto();
                case 3 -> editarProducto();
                case 4 -> eliminarProducto();
                case 0 -> { return; }
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        System.out.println("\n📦 LISTA DE PRODUCTOS");
        productos.forEach(p ->
                System.out.printf("[%d] %s | %s | $%.2f | Stock: %d | SKU: %s%n",
                        p.getId(), p.getNombre(), p.getDescripcion(),
                        p.getPrecio_venta(), p.getStock(), p.getNombre())
        );
    }

    private static void agregarProducto() {
        if (!esAdmin()) {
            System.out.println("⚠ No tienes permisos para esta acción.");
            return;
        }

        System.out.println("\n📦 --- AGREGAR PRODUCTO ---");

        String nombre = leerTexto("Nombre: ");
        String descripcion = leerTexto("Descripción: ");
        double precioCompra = leerDouble("Precio compra: ");
        double precioVenta = leerDouble("Precio venta: ");
        int stock = leerInt("Stock inicial: ");

        // ⚠ ESTE ES EL CONSTRUCTOR CORRECTO SEGÚN TU CLASE Producto
        Producto p = new Producto(0, nombre, descripcion, precioCompra, precioVenta, stock);

        boolean ok = productoService.crearProducto(p);

        if (ok) {
            System.out.println("✔ Producto creado correctamente.");
        } else {
            System.out.println("❌ Error al guardar el producto.");
        }
    }

    private static void editarProducto() {
        if (!esAdmin()) return;

        int id = leerInt("ID del producto: ");
        Producto p = productoService.obtenerPorId(id);

        if (p == null) {
            System.out.println("❌ No existe.");
            return;
        }

        String nuevoNombre = leerTexto("Nuevo nombre (" + p.getNombre() + "): ");
        if (!nuevoNombre.isEmpty()) p.setNombre(nuevoNombre);

        String nuevaDescripcion = leerTexto("Nueva descripcion (" + p.getDescripcion() + "): ");
        if (!nuevaDescripcion.isEmpty()) p.setDescripcion(nuevaDescripcion);

        Double nuevoPrecioCompra = leerDouble("Nueva precio de compra (" + p.getPrecio_compra() + "): ");
        if (nuevoPrecioCompra !=null) {
            p.setPrecio_compra(nuevoPrecioCompra);
        }

        Double nuevoPrecioVenta = leerDouble("Nueva precio de venta (" + p.getPrecio_venta() + "): ");
        if (nuevoPrecioVenta !=null) {
            p.setPrecio_venta(nuevoPrecioVenta);
        }

        int nuevoStock = leerInt("Nueva stock (" + p.getStock() + "): ");
        if (nuevoStock != -1) {
            p.setStock(nuevoStock);
        }

        boolean ok = productoService.actualizarProducto(p);
        System.out.println(ok ? "✔ Actualizado" : "❌ Error");
    }

    private static void eliminarProducto() {
        if (!esAdmin()) return;

        int id = leerInt("ID a eliminar: ");
        boolean ok = productoService.eliminarProducto(id);
        System.out.println(ok ? "✔ Eliminado" : "❌ Error");
    }

    // ---------------- CLIENTES ----------------

    private static void menuClientes() {
        if (!validarSesion()) return;

        while (true) {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            int opcion = leerInt("Opción: ");

            switch (opcion) {
                case 1 -> listarClientes();
                case 2 -> agregarCliente();
                case 3 -> editarCliente();
                case 4 -> eliminarCliente();
                case 0 -> { return; }
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        System.out.println("\n🧍 LISTA DE CLIENTES");
        clientes.forEach(c -> System.out.printf("[%d] %s | %s | %s | %s | %s%n", c.getId(), c.getNombre(), c.getTelefono(), c.getEmail(), c.getDireccion(), c.getFecha_registro()));
    }

    private static void agregarCliente() {
        if (!esAdmin()) return;

        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String email = leerTexto("Email: ");
        String direccion = leerTexto("Direccion: ");
        int dia = leerInt("Día: ");
        int mes = leerInt("Mes: ");
        int anio = leerInt("Año: ");

        LocalDate localDate = LocalDate.of(anio, mes, dia);
        Date fechaRegistro = java.sql.Date.valueOf(localDate);

        Cliente c = new Cliente(0, nombre, telefono, email, direccion, fechaRegistro);
        boolean ok = clienteService.crearCliente(c);

        System.out.println(ok ? "✔ Cliente creado" : "❌ Error");
    }

    private static void editarCliente() {
        if (!esAdmin()) return;

        int id = leerInt("ID cliente: ");
        Cliente c = clienteService.obtenerPorId(id);

        if (c == null) {
            System.out.println("❌ No existe.");
            return;
        }

        // -- Nuevo registro de las columnas. --

        String nuevoNombre = leerTexto("Nuevo nombre (" + c.getNombre() + "): ");
        if (!nuevoNombre.isEmpty()) c.setNombre(nuevoNombre);

        String nuevoTelefono = leerTexto("Nuevo telefono (" + c.getTelefono() + "): ");
        if (!nuevoTelefono.isEmpty()) c.setNombre(nuevoTelefono);

        String nuevoEmail = leerTexto("Nuevo email (" + c.getEmail() + "): ");
        if (!nuevoTelefono.isEmpty()) c.setEmail(nuevoEmail);

        String nuevaDireccion = leerTexto("Nueva descripcion (" + c.getDireccion() + "): ");
        if (!nuevaDireccion.isEmpty()) c.setDireccion(nuevaDireccion);

        int nuevoDia = leerInt("Nuevo día (" + c.getFecha_registro() + "): ");
        int nuevoMes = leerInt("Nuevo mes (" + c.getFecha_registro() + "): ");
        int nuevoAnio = leerInt("Nuevo año (" + c.getFecha_registro() +"): ");

        if (nuevoDia != -1 && nuevoMes != -1 && nuevoAnio != -1) {

            LocalDate nuevaFechaLocal = LocalDate.of(nuevoAnio, nuevoMes, nuevoDia);
            Date nuevaFecha = java.sql.Date.valueOf(nuevaFechaLocal);

            c.setFecha_registro(nuevaFecha);
        }

        boolean ok = clienteService.actualizarCliente(c);
        System.out.println(ok ? "✔ Actualizado" : "❌ Error");
    }

    private static void eliminarCliente() {
        if (!esAdmin()) return;

        int id = leerInt("ID a eliminar: ");
        boolean ok = clienteService.eliminarCliente(id);
        System.out.println(ok ? "✔ Eliminado" : "❌ Error");
    }

    // ---------------- REPORTES ----------------

    private static void menuReportes() {
        if (!validarSesion()) return;

        System.out.println("\n📊 REPORTES — CONSULTAS AVANZADAS");
        System.out.println("1. Productos con stock bajo (< 5)");
        System.out.println("2. Cantidad total de productos en inventario");
        System.out.println("0. Volver");

        int op = leerInt("Opción: ");

        switch (op) {
            case 1 -> productosStockBajo();
            case 2 -> totalInventario();
            case 0 -> {}
            default -> System.out.println("❌ Opción inválida");
        }
    }

    private static void productosStockBajo() {
        System.out.println("\n⚠ PRODUCTOS CON STOCK BAJO:");
        productoService.listarProductos().stream()
                .filter(p -> p.getStock() < 5)
                .forEach(p -> System.out.println(p.getNombre() + " → stock: " + p.getStock()));
    }

    private static void totalInventario() {
        double total = productoService.listarProductos()
                .stream().mapToDouble(p -> p.getPrecio_venta() * p.getStock())
                .sum();

        System.out.printf("\n💰 VALOR TOTAL DEL INVENTARIO: $%.2f%n", total);
    }

    // ---------------- UTILIDADES ----------------

    private static int leerInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private static double leerDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }

    private static String leerTexto(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static boolean validarSesion() {
        if (usuarioLogueado == null) {
            System.out.println("⚠ Debes iniciar sesión primero.");
            return false;
        }
        return true;
    }

    private static boolean esAdmin() {
        if (usuarioLogueado.getIdRol() != 1) {
            System.out.println("🚫 No tienes permisos.");
            return false;
        }
        return true;
    }
}


