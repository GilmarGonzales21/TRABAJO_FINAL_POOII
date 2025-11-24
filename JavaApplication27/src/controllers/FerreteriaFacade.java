package controllers;

import java.util.List;
import models.Carrito;
import models.Pedido;
import models.Producto;
import models.ReporteSesion;
import models.Boleta;

public class FerreteriaFacade {

    public List<Producto> obtenerCatalogo() {
        return StaticAccess.catalogo().getCatalogo();
    }

    public void agregarProductoAlCarrito(String nombreProducto, int cantidad) {
        Producto producto = buscarProductoPorNombre(nombreProducto);
        if (producto != null) {
            StaticAccess.venta().agregarAlCarrito(producto, cantidad);
        }
    }

    public Carrito obtenerCarritoActual() {
        return StaticAccess.venta().getCarrito();
    }

    public void confirmarPedido(String cliente, String direccionEntrega, boolean requiereEnvio) {
        StaticAccess.pedido().confirmarPedido(cliente, direccionEntrega, requiereEnvio);
    }

    public Pedido obtenerPedidoActual() {
        return StaticAccess.pedido().getPedidoActual();
    }

    public ReporteSesion obtenerReporteSesion() {
        return StaticAccess.venta().getReporteSesion();
    }

    public void generarBoletaDesdePedido(Pedido pedido) {
        StaticAccess.venta().emitirBoletaDesdePedido(pedido);
    }

    private Producto buscarProductoPorNombre(String nombre) {
        List<Producto> productos = StaticAccess.catalogo().getCatalogo();
        for (Producto producto : productos) {
            if (producto.getNombreComercial().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}
