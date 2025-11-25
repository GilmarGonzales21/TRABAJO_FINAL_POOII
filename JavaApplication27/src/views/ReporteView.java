package views;

import javax.swing.*;
import controllers.ReporteController;
import controllers.StaticAccess;
import core.View;
import core.Model;
import java.awt.*;

@SuppressWarnings("serial")
public class ReporteView extends JPanel implements View {
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AMARILLO_HOVER = new Color(255, 230, 80);
    private final Color AZUL = new Color(26, 34, 56);
    private JTextArea txtReporte;
    private ReporteController reporteController;
    private Image logo;

    public ReporteView(ReporteController reporteController) {
        this.reporteController = reporteController;
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }

        JLabel lbl = new JLabel("Reporte de Ventas (Sesión Actual)");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(AZUL);
        lbl.setBounds(20, 10, 500, 40);
        add(lbl);
        if (logo != null) {
            Image small = logo.getScaledInstance(38, 38, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(small));
            logoTitulo.setBounds(420, 8, 50, 50);
            add(logoTitulo);
        }

        txtReporte = new JTextArea();
        txtReporte.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtReporte.setDisabledTextColor(Color.BLACK);
        txtReporte.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scroll = new JScrollPane(txtReporte);
        scroll.setBounds(20, 60, 750, 300);
        add(scroll);

        JButton btnActualizar = crearBoton("Actualizar reporte");
        btnActualizar.setBounds(20, 380, 200, 40);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> {
            String resumen = reporteController.generarResumen(StaticAccess.venta());
            txtReporte.setText(resumen);
        });

        JButton btnVolver = crearBoton("Volver al menú principal");
        btnVolver.setBounds(560, 380, 210, 40);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            core.Controller.loadView("HomeView");
        });
        cargarReporte();
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (logo != null) {
            int W = 260, H = 260;
            int x = getWidth() - W - 40;
            int y = (getHeight() - H) / 2 + 40;
            g.drawImage(logo.getScaledInstance(W, H, Image.SCALE_SMOOTH), x, y, this);
        }
    }
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);

        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(AMARILLO);
        btn.setForeground(AZUL);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(AZUL, 2));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(AMARILLO_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(AMARILLO);
            }
        });
        return btn;
    }
    
    private void cargarReporte() {
        String resumen = reporteController.generarResumen(StaticAccess.venta());
        txtReporte.setText(resumen);
    }

    @Override
    public void update(Model model, Object data) {
        cargarReporte();
    }
}
