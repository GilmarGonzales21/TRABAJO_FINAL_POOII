/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */
public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor a 0");
        }
        if (cantidad > producto.getStockDisponible()) {
            throw new IllegalArgumentException("Cantidad excede stock disponible");
        }

        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor a 0");
        }
        if (nuevaCantidad > producto.getStockDisponible()) {
            throw new IllegalArgumentException("Cantidad excede stock disponible");
        }
        this.cantidad = nuevaCantidad;
    }

    public double getSubtotal() {
        return producto.getPrecioUnitario() * cantidad;
    }

    @Override
    public String toString() {
        return producto.getNombreComercial() + " x" + cantidad
                + " = S/ " + String.format("%.2f", getSubtotal());
    }
}

