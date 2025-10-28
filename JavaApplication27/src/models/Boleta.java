/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Boleta {

    private static int contador = 1000;

    private int numeroBoleta;
    private Pedido pedido;
    private double totalPagar;

    public Boleta(Pedido pedido) {
        this.pedido = pedido;
        this.totalPagar = pedido.getTotalPedido();
        this.numeroBoleta = contador++;
    }

    public int getNumeroBoleta() {
        return numeroBoleta;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*** BOLETA ***\n");
        sb.append("Boleta N°: ").append(numeroBoleta).append("\n");
        sb.append("Asociada al Pedido #").append(pedido.getIdPedido()).append("\n");
        sb.append("Cliente: ").append(pedido.getCliente()).append("\n");
        sb.append("Detalle:\n");
        for (ItemCarrito it : pedido.getItemsPedido()) {
            sb.append("- ").append(it.toString()).append("\n");
        }
        sb.append("TOTAL A PAGAR: S/ ")
                .append(String.format("%.2f", totalPagar))
                .append("\n");
        return sb.toString();
    }
}