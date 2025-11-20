package co.edu.udec.domain.model;

import java.sql.Timestamp;

public class Venta {
    private int id;
    private Timestamp fecha;
    private int idUsuario;
    private Integer idCliente;
    private double total;

    public Venta() {}

    public Venta(int id, Timestamp fecha, int idUsuario, Integer idCliente, double total) {
        this.id = id;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
