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
import java.awt.*;
/**
 *
 * @author Gilmar Gonzales
 */
@SuppressWarnings("serial")
public class BoletaView extends JPanel implements View {
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AZUL = new Color(26, 34, 56);
    private JTextArea txtBoleta;
    private Image logo;
    
    public BoletaView(VentaController ventaController) {
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }
        JLabel lbl = new JLabel("Boleta Emitida");
         lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setForeground(AZUL);
        lbl.setBounds(20, 10, 200, 20);
        add(lbl);
         if (logo != null) {
            Image small = logo.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(small));
            logoTitulo.setBounds(250, 8, 40, 40);
            add(logoTitulo);
        }

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
            if (ventaController.getBoletaActual() != null) {
                txtBoleta.setText(ventaController.getBoletaActual().toString());
            } else {
                txtBoleta.setText("No hay boleta emitida aún.");
            }
        });
        JButton btnExportar = new JButton("Exportar .TXT");
        btnExportar.setBounds(200, 410, 180, 30);
        add(btnExportar);
        btnExportar.addActionListener(e -> {
        StaticAccess.venta().exportarBoletaTXT();
        JOptionPane.showMessageDialog(null, "Boleta exportada correctamente.");
    });
         cargarBoleta();
    }
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
        private void cargarBoleta() {
            VentaController vc = StaticAccess.venta();
            if (vc.getBoletaActual() != null) {
                txtBoleta.setText(vc.getBoletaActual().toString());
            } else {
                txtBoleta.setText("No hay boleta emitida aún.");
            }
        }
    @Override
    public void update(Model model, Object data) {
         cargarBoleta();
    }   
}
