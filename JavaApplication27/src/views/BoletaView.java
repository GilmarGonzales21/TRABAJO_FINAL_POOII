/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.*;
import controllers.VentaController;
import controllers.StaticAccess;
import core.View;
import core.Model;

/**
 *
 * @author Gilmar Gonzales
 */
public class BoletaView extends JPanel implements View {

    private JTextArea txtBoleta;

    public BoletaView(VentaController ventaController) {
        setLayout(null);

        JLabel lbl = new JLabel("Boleta Emitida");
        lbl.setBounds(20, 10, 200, 20);
        add(lbl);

        txtBoleta = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtBoleta);
        scroll.setBounds(20, 40, 600, 360);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar Boleta");
        btnActualizar.setBounds(20, 410, 160, 30);
        add(btnActualizar);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(450, 410, 180, 30);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            core.Controller.loadView("HomeView");
        });

        btnActualizar.addActionListener(e -> {
            VentaController vc = StaticAccess.venta();
            if (vc.getBoletaActual() != null) {
                txtBoleta.setText(vc.getBoletaActual().toString());
            } else {
                txtBoleta.setText("No hay boleta emitida aún.");
            }
        });
    }

    @Override
    public void update(Model model, Object data) {
    }
}
