/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */
public class Producto {

    private String nombreComercial;
    private String marca;
    private String categoria;
    private String descripcionTecnica;
    private double precioUnitario;
    private int stockDisponible;

    public Producto(String nombreComercial, String marca, String categoria,
            String descripcionTecnica, double precioUnitario, int stockDisponible) {
        this.nombreComercial = nombreComercial;
        this.marca = marca;
        this.categoria = categoria;
        this.descripcionTecnica = descripcionTecnica;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcionTecnica() {
        return descripcionTecnica;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void reducirStock(int cantidad) {
        // control lógico en memoria
        this.stockDisponible -= cantidad;
        if (stockDisponible < 0) {
            stockDisponible = 0;
        }
    }

    @Override
    public String toString() {
        return nombreComercial + " (" + marca + ") - "
                + "S/ " + precioUnitario + " - Stock: " + stockDisponible;
    }
}
