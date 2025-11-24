/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AMARILLO_HOVER = new Color(255, 230, 80);
    private final Color AZUL = new Color(26, 34, 56);
    private final Color FONDO = new Color(242, 242, 242);

    public HomeView(HomeController controller) {
        setLayout(null);
        setBackground(FONDO);

        JLabel titulo = new JLabel("Ferretería Santa Rosa - Menú Principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(AZUL);
        titulo.setBounds(20, 20, 400, 25);
        add(titulo);
        
        ImageIcon logoImg = new ImageIcon(getClass().getResource("/assets/logo.png"));
        Image scaled = logoImg.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setBounds(350, 80, 300, 300);
        add(logo);

        JButton btnCatalogo = crearBoton("Ver Catálogo");
        btnCatalogo.setBounds(20, 60, 160, 30);
        add(btnCatalogo);

        JButton btnCarrito = crearBoton("Ver Carrito / Cotización");
        btnCarrito.setBounds(20, 100, 200, 30);
        add(btnCarrito);

        JButton btnPedido = crearBoton("Confirmar Pedido");
        btnPedido.setBounds(20, 140, 200, 30);
        add(btnPedido);

        JButton btnBoleta = crearBoton("Ver Boleta Emitida");
        btnBoleta.setBounds(20, 180, 200, 30);
        add(btnBoleta);
        
        
        JButton btnAlmacen = crearBoton("Ver almacenes");
        btnAlmacen.setBounds(20, 220, 200, 30);
        add(btnAlmacen);
        
        
        JButton btnReporte = crearBoton("Ver Reporte Sesión");
        btnReporte.setBounds(20, 260, 200, 30);
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
        
        btnAlmacen.addActionListener(e 
                -> Controller.loadView("AlmacenView"));
    }
    
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);

            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setBackground(AMARILLO);
            btn.setForeground(AZUL);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(AZUL, 2));
            btn.addMouseListener(new MouseAdapter() {
                 
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(AMARILLO_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(AMARILLO);
            }
        });
        return btn;
    }                   
    @Override
    public void update(Model model, Object data) {
    }
}