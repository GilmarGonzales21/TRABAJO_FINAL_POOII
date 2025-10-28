/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.*;
import controllers.HomeController;
import core.View;
import core.Model;
import core.Controller;

/**
 *
 * @author Gilmar Gonzales
 */
@SuppressWarnings("serial")
public class HomeView extends JPanel implements View {

    public HomeView(HomeController controller) {
        setLayout(null);

        JLabel titulo = new JLabel("Ferretería Santa Rosa - Menú Principal");
        titulo.setBounds(20, 20, 400, 25);
        add(titulo);

        JButton btnCatalogo = new JButton("Ver Catálogo");
        btnCatalogo.setBounds(20, 60, 160, 30);
        add(btnCatalogo);

        JButton btnCarrito = new JButton("Ver Carrito / Cotización");
        btnCarrito.setBounds(20, 100, 200, 30);
        add(btnCarrito);

        JButton btnPedido = new JButton("Confirmar Pedido");
        btnPedido.setBounds(20, 140, 200, 30);
        add(btnPedido);

        JButton btnBoleta = new JButton("Ver Boleta Emitida");
        btnBoleta.setBounds(20, 180, 200, 30);
        add(btnBoleta);

        JButton btnReporte = new JButton("Ver Reporte Sesión");
        btnReporte.setBounds(20, 220, 200, 30);
        add(btnReporte);

        btnCatalogo.addActionListener(e
                -> Controller.loadView("CatalogoView")
        );

        btnCarrito.addActionListener(e
                -> Controller.loadView("CarritoView")
        );

        btnPedido.addActionListener(e
                -> Controller.loadView("PedidoView")
        );

        btnBoleta.addActionListener(e
                -> Controller.loadView("BoletaView")
        );

        btnReporte.addActionListener(e
                -> Controller.loadView("ReporteView")
        );
    }

    @Override
    public void update(Model model, Object data) {
    }
}
