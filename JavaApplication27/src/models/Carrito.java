/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class Carrito {

    private List<ItemCarrito> items = new ArrayList<>();

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void agregarProducto(Producto p, int cantidad) {
        // si ya existe en carrito, solo actualizamos cantidad
        for (ItemCarrito it : items) {
            if (it.getProducto() == p) {
                it.setCantidad(it.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(p, cantidad));
    }

    public void actualizarCantidad(Producto p, int nuevaCantidad) {
        for (ItemCarrito it : items) {
            if (it.getProducto() == p) {
                it.setCantidad(nuevaCantidad);
                return;
            }
        }
    }

    public void eliminarProducto(Producto p) {
        items.removeIf(it -> it.getProducto() == p);
    }

    public double getTotal() {
        double total = 0;
        for (ItemCarrito it : items) {
            total += it.getSubtotal();
        }
        return total;
    }

    public String generarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("COTIZACIÓN:\n");
        for (ItemCarrito it : items) {
            sb.append(" - ").append(it.toString()).append("\n");
        }
        sb.append("TOTAL: S/ ").append(String.format("%.2f", getTotal()));
        return sb.toString();
    }

    @Override
    public String toString() {
        return generarResumen();
    }
}


