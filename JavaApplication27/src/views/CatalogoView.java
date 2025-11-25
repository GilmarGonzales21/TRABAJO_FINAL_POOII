package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.CatalogoController;
import core.View;
import core.Model;
import java.util.List;
import java.util.stream.Collectors;
import models.Producto;
import java.awt.*;

@SuppressWarnings("serial")
public class CatalogoView extends JPanel implements View {
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AMARILLO_HOVER = new Color(255, 230, 80);
    private final Color AZUL = new Color(26, 34, 56);
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboCategoria;
    private Image logo;

    private CatalogoController controller;

    public CatalogoView(CatalogoController controller) {
        this.controller = controller;
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }

        JLabel lbl = new JLabel("Catálogo de Productos");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbl.setForeground(AZUL);
        lbl.setBounds(20, 20, 200, 25);
        add(lbl);
        if (logo != null) {
            Image small = logo.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(small));
            logoTitulo.setBounds(380, 8, 40, 40);
            add(logoTitulo);
        }

        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setForeground(AZUL);
        lblCat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCat.setBounds(20, 55, 80, 25);
        add(lblCat);

        comboCategoria = crearCombo();
        comboCategoria.setBounds(100, 55, 200, 25);
        add(comboCategoria);

        comboCategoria.addItem("Cemento");
        comboCategoria.addItem("Tuberías");
        comboCategoria.addItem("Conexiones");
        comboCategoria.addItem("Pegamentos");

        // Tablita
        String[] columnas = { "Nombre", "Marca", "Descripción", "Precio (S/.)", "Stock" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(22);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setForeground(AZUL);
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 90, 740, 280);
        add(scroll);

        JButton btnRefrescar = crearBoton("Refrescar");
        btnRefrescar.setBounds(320, 55, 120, 25);
        add(btnRefrescar);

        JButton btnVolver = crearBoton("Volver al menú principal");
        btnVolver.setBounds(560, 390, 200, 30);
        add(btnVolver);

        
        btnRefrescar.addActionListener(e -> cargarProductosFiltrados());
        comboCategoria.addActionListener(e -> cargarProductosFiltrados());
        btnVolver.addActionListener(e -> core.Controller.loadView("HomeView"));

        cargarProductosFiltrados();
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Logo grande transparente
        if (logo != null) {
            int W = 260, H = 260;
            int x = getWidth() - W - 40;
            int y = (getHeight() - H) / 2 + 40;

            g.drawImage(
                logo.getScaledInstance(W, H, Image.SCALE_SMOOTH),
                x, y, this
            );
        }
    }

    private JComboBox<String> crearCombo() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setForeground(AZUL);
        combo.setBorder(BorderFactory.createLineBorder(AZUL, 2));
        return combo;
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

    private void cargarProductosFiltrados() {
        String categoriaSeleccionada = (String) comboCategoria.getSelectedItem();
        if (categoriaSeleccionada == null) return;

        List<Producto> todos = controller.getCatalogo();
        List<Producto> filtrados = todos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaSeleccionada))
            .collect(Collectors.toList());

        modeloTabla.setRowCount(0);
        for (Producto p : filtrados) {
            Object[] fila = new Object[] {
                p.getNombreComercial(),
                p.getMarca(),
                p.getDescripcionTecnica(),
                String.format("%.2f", p.getPrecioUnitario()),
                p.getStockDisponible()
            };
            modeloTabla.addRow(fila);
        }
    }

    @Override
    public void update(Model model, Object data) {}
}
