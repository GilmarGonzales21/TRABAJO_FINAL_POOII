/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ReporteSesion {

    private double totalVendido = 0.0;
    private List<Pedido> pedidosEmitidos = new ArrayList<>();
    private Map<String, Integer> contadorProductos = new HashMap<>();

    public void registrarVenta(Boleta boleta) {
        totalVendido += boleta.getTotalPagar();
        Pedido p = boleta.getPedido();
        pedidosEmitidos.add(p);

        // contar productos vendidos
        for (ItemCarrito it : p.getItemsPedido()) {
            String nombre = it.getProducto().getNombreComercial();
            int cant = it.getCantidad();
            contadorProductos.put(nombre, contadorProductos.getOrDefault(nombre, 0) + cant);
        }
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public List<Pedido> getPedidosEmitidos() {
        return pedidosEmitidos;
    }

    public Map<String, Integer> getProductosMasVendidos() {
        return contadorProductos;
    }

    public String resumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESUMEN DE SESIÓN ===\n");
        sb.append("Total vendido: S/ ")
                .append(String.format("%.2f", totalVendido))
                .append("\n");
        sb.append("Pedidos emitidos: ").append(pedidosEmitidos.size()).append("\n");
        sb.append("Más vendidos:\n");
        for (String prod : contadorProductos.keySet()) {
            sb.append(" - ").append(prod)
                    .append(": ").append(contadorProductos.get(prod))
                    .append(" und.\n");
        }
        return sb.toString();
    }
}

