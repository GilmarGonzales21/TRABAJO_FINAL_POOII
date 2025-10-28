/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import core.Controller;
import models.*;
import views.CarritoView;
import views.BoletaView;
import javax.swing.JOptionPane;

/**
 *
 * @author gilma
 */
public class VentaController extends Controller {

    private CarritoView carritoView;
    private BoletaView boletaView;

    private Carrito carritoActual = new Carrito();
    private Boleta boletaActual; // última emitida
    private ReporteSesion reporteSesion = new ReporteSesion();

    @Override
    public void run() {
        carritoView = new CarritoView(this);
        boletaView = new BoletaView(this);

        addView("CarritoView", carritoView);
        addView("BoletaView", boletaView);
    }

    public Carrito getCarrito() {
        return carritoActual;
    }

    public Boleta getBoletaActual() {
        return boletaActual;
    }

    public ReporteSesion getReporteSesion() {
        return reporteSesion;
    }

    public void agregarAlCarrito(Producto p, int cantidad) {
        try {
            carritoActual.agregarProducto(p, cantidad);
            JOptionPane.showMessageDialog(null, "Producto agregado al carrito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void actualizarCantidad(Producto p, int nuevaCantidad) {
        try {
            carritoActual.actualizarCantidad(p, nuevaCantidad);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void eliminarDelCarrito(Producto p) {
        carritoActual.eliminarProducto(p);
    }

    public void emitirBoletaDesdePedido(Pedido pedido) {
        boletaActual = new Boleta(pedido);
        reporteSesion.registrarVenta(boletaActual);
        loadView("BoletaView");
    }
}
