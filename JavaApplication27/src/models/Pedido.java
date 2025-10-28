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
public class Pedido {

    private static int contador = 1;

    private int idPedido;
    private String cliente;
    private String direccionEntrega;
    private List<ItemCarrito> itemsPedido = new ArrayList<>();
    private String estado; // "pendiente", "emitido"

    public Pedido(String cliente, String direccionEntrega, Carrito carrito) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente obligatorio");
        }
        // si la dirección está vacía y se supone que es envío a domicilio,
        // eso se validaría antes de construir (en el controlador)

        this.idPedido = contador++;
        this.cliente = cliente;
        this.direccionEntrega = direccionEntrega;
        this.estado = "pendiente";

        // copiamos los items del carrito en ese momento
        for (ItemCarrito it : carrito.getItems()) {
            this.itemsPedido.add(it);
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public List<ItemCarrito> getItemsPedido() {
        return itemsPedido;
    }

    public String getEstado() {
        return estado;
    }

    public double getTotalPedido() {
        double total = 0;
        for (ItemCarrito it : itemsPedido) {
            total += it.getSubtotal();
        }
        return total;
    }

    public void marcarEmitido() {
        this.estado = "emitido";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PEDIDO #").append(idPedido).append("\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Dirección: ").append(direccionEntrega).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Detalle:\n");
        for (ItemCarrito it : itemsPedido) {
            sb.append(" * ").append(it.toString()).append("\n");
        }
        sb.append("TOTAL PEDIDO: S/ ")
                .append(String.format("%.2f", getTotalPedido()))
                .append("\n");
        return sb.toString();
    }
}
