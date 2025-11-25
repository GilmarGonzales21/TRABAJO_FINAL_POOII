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
    private Image logo;

    public HomeView(HomeController controller) {
        
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }
        
        JLabel titulo = new JLabel("Ferretería Santa Rosa - Menú Principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(AZUL);
        titulo.setBounds(20, 20, 400, 25);
        add(titulo);

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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (logo != null) {
            int logoWidth = 350;
            int logoHeight = 350;
            int x = getWidth() - logoWidth - 40; 
            int y = (getHeight() - logoHeight) / 2;

            g.drawImage(logo.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH),
                    x, y, this);
        }
    }
    
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);

            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
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