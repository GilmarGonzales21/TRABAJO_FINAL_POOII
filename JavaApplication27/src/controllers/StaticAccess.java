/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author gilma
 */
public class StaticAccess {
    private static CatalogoController catalogoRef;
    private static VentaController ventaRef;
    private static PedidoController pedidoRef;
    private static ReporteController reporteRef;

    public static void init(CatalogoController c1, VentaController c2, PedidoController c3, ReporteController c4) {
        catalogoRef = c1;
        ventaRef = c2;
        pedidoRef = c3;
        reporteRef = c4;
    }

    public static CatalogoController catalogo() {
        return catalogoRef;
    }

    public static VentaController venta() {
        return ventaRef;
    }

    public static PedidoController pedido() {
        return pedidoRef;
    }

    public static ReporteController reporte() {
        return reporteRef;
    }
}
