/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */
public class PedidoBuilder {

    private String cliente;
    private String direccionEntrega;
    private Carrito carrito;

    public PedidoBuilder conCliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder conDireccion(String direccion) {
        this.direccionEntrega = direccion;
        return this;
    }

    public PedidoBuilder conCarrito(Carrito carrito) {
        this.carrito = carrito;
        return this;
    }

    public Pedido build() {
        return new Pedido(cliente, direccionEntrega, carrito);
    }
}
