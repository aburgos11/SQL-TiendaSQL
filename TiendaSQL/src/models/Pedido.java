package models;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Producto producto;
    private int cantidad;
    private LocalDate fecha;

    public Pedido(Cliente cliente, Producto producto, int cantidad, LocalDate fecha) {
        this.id = 0;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido [id= " + id + ", cliente= " + cliente + ", producto= " + producto + ", cantidad= " + cantidad
                + ", fecha= " + fecha + "]";
    }
}
