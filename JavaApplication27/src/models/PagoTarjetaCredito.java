/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author gilma
 */
public class PagoTarjetaCredito implements MetodoPago{

    @Override
    public boolean realizarPago(double monto) {
        System.out.println("Pago realizado con tarjeta de credito");
        return true;
    }
    
}
