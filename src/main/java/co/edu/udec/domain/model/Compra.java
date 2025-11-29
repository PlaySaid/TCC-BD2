package co.edu.udec.domain.model;

import java.sql.Timestamp;

public class Compra {
    private int id;
    private Timestamp fecha;
    private int idProveedor;
    private int idUsuario;
    private double total;

    public Compra() {}

    public Compra(int id, Timestamp fecha, int idProveedor, int idUsuario, double total) {
        this.id = id;
        this.fecha = fecha;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
