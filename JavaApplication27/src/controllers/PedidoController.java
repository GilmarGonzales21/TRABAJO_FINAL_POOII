/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import javax.swing.JOptionPane;
import core.Controller;
import models.*;
import views.PedidoView;
/**
 *
 * @author gilma
 */
public class PedidoController extends Controller {
    private PedidoView pedidoView;
    private VentaController ventaController; 
    private Pedido pedidoActual;             

    private MetodoPago metodoPago; // Interfaz

    public PedidoController(VentaController ventaController) {
        this.ventaController = ventaController;
    }

    @Override
    public void run() {
        pedidoView = new PedidoView(this);
        addView("PedidoView", pedidoView);
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    // Método para establecer el tipo de pago
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void confirmarPedido(String cliente, String direccionEntrega, boolean requiereEnvio) {
        try {
            if (requiereEnvio && (direccionEntrega == null || direccionEntrega.trim().isEmpty())) {
                throw new IllegalArgumentException("Se requiere dirección para envío a domicilio.");
            }

            Carrito carrito = ventaController.getCarrito();

            PedidoBuilder builder = new PedidoBuilder()
                .conCliente(cliente)
                .conDireccion(direccionEntrega)
                .conCarrito(carrito);

            pedidoActual = builder.build();

            pedidoActual.marcarEmitido();

            // Reducir stock de cada producto vendido
            for (ItemCarrito it : pedidoActual.getItemsPedido()) {
                it.getProducto().reducirStock(it.getCantidad());
            }

            if (metodoPago != null) {
                // Realizamos el pago a través de la interfaz
                metodoPago.realizarPago(pedidoActual.getTotalPedido());
            } else {
                throw new IllegalAccessException("No se ha seleccionado un metodo de pago");
            }

            JOptionPane.showMessageDialog(null,
                "Pedido confirmado. ID Pedido: " + pedidoActual.getIdPedido());

            // Emitir boleta
            ventaController.emitirBoletaDesdePedido(pedidoActual);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al confirmar pedido: " + e.getMessage());
        }
    }
}
