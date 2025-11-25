    package views;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import controllers.VentaController;
    import controllers.StaticAccess;
    import core.View;
    import core.Model;
    import models.Carrito;
    import models.ItemCarrito;
    import models.Producto;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.List;
    import java.util.stream.Collectors;

    @SuppressWarnings("serial")
    public class CarritoView extends JPanel implements View {
        
        private final Color AMARILLO = new Color(247, 212, 0);
        private final Color AMARILLO_HOVER = new Color(255, 230, 80);
        private final Color AZUL = new Color(26, 34, 56);

        private JTable tabla;
        private DefaultTableModel modeloTabla;
        private JComboBox<String> comboCategoria;
        private JComboBox<String> comboProducto;
        private JTextField txtCantidad;
        private JLabel lblTotal;
        private Image logo;

        public CarritoView(VentaController ventaController) {
            setLayout(null);
            
            try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }

            JLabel titulo = new JLabel("Carrito / Cotización");
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
            titulo.setForeground(AZUL);
            titulo.setBounds(20, 10, 400, 40);
            add(titulo);
            if (logo != null) {
            Image iconSmall = logo.getScaledInstance(38, 38, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(iconSmall));
            logoTitulo.setBounds(350, 8, 40, 40);
            add(logoTitulo);
            }
    //selecciona la categoria
            JLabel lblCat = new JLabel("Categoría:");
            lblCat.setForeground(AZUL);
            lblCat.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblCat.setBounds(20, 60, 80, 25);
            add(lblCat);

            comboCategoria = crearCombo();
            comboCategoria.setBounds(100, 60, 150, 25);
            add(comboCategoria);

            comboCategoria.addItem("Cemento");
            comboCategoria.addItem("Tuberías");
            comboCategoria.addItem("Conexiones");
            comboCategoria.addItem("Pegamentos");

    //seleciona el producto
            JLabel lblProd = new JLabel("Producto:");
            lblProd.setForeground(AZUL);
            lblProd.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblProd.setBounds(270, 60, 80, 25);
            add(lblProd);

            comboProducto = crearCombo();
            comboProducto.setBounds(340, 60, 200, 25);
            add(comboProducto);

    //la cantidad
            JLabel lblCant = new JLabel("Cantidad:");
            lblCant.setForeground(AZUL);
            lblCant.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblCant.setBounds(560, 60, 90, 25);
            add(lblCant);

            txtCantidad = new JTextField("1");
            txtCantidad.setBounds(650, 60, 50, 28);
            add(txtCantidad);

            JButton btnAgregar = crearBoton("Agregar");
            btnAgregar.setBounds(720, 60, 140, 30);
            add(btnAgregar);

    //tablita
            String[] columnas = { "Producto", "Cant.", "Precio (S/.)", "Subtotal (S/.)" };
            modeloTabla = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int col) { return false; }
            };
            tabla = new JTable(modeloTabla);
            tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 110, 800, 250);
            add(scroll);

            lblTotal = new JLabel("TOTAL: S/ 0.00");
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTotal.setForeground(AZUL);
            lblTotal.setBounds(20, 370, 300, 30);
            add(lblTotal);


            JButton btnVolver = crearBoton("Volver al menú principal");
            btnVolver.setBounds(650, 380, 230, 35);
            add(btnVolver);


    // 1. Cuando cambia categoría, cargamos productos de esa categoría.
            comboCategoria.addActionListener(e -> cargarProductosPorCategoria());

    // 2. Botón para agregar al carrito con cantidad
            btnAgregar.addActionListener(e -> {
                try {
                    Producto seleccionado = productoActualSeleccionado();
                    int cant = Integer.parseInt(txtCantidad.getText());
                    ventaController.agregarAlCarrito(seleccionado, cant);
                    actualizarTablaCarrito(ventaController.getCarrito());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            });

            btnVolver.addActionListener(e -> {
                core.Controller.loadView("HomeView");
            });

            cargarProductosPorCategoria();
            actualizarTablaCarrito(ventaController.getCarrito());
        }
        
          @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (logo != null) {
            int W = 260, H = 260;
            int x = getWidth() - W - 40;
            int y = (getHeight() - H) / 2;
            g.drawImage(logo.getScaledInstance(W, H, Image.SCALE_SMOOTH), x, y, this);
        }
    }

        private void cargarProductosPorCategoria() {
            comboProducto.removeAllItems();

            String categoriaSel = (String) comboCategoria.getSelectedItem();
            if (categoriaSel == null) return;

            List<Producto> productosCatalogo = StaticAccess.catalogo().getCatalogo();
            List<Producto> filtrados = productosCatalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaSel))
                .collect(Collectors.toList());

            for (Producto p : filtrados) {
                comboProducto.addItem(p.getNombreComercial());
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
        private Producto productoActualSeleccionado() {
            String categoriaSel = (String) comboCategoria.getSelectedItem();
            String nombreProdSel = (String) comboProducto.getSelectedItem();
            if (categoriaSel == null || nombreProdSel == null) return null;

            List<Producto> productosCatalogo = StaticAccess.catalogo().getCatalogo();
            for (Producto p : productosCatalogo) {
                if (p.getCategoria().equalsIgnoreCase(categoriaSel)
                 && p.getNombreComercial().equalsIgnoreCase(nombreProdSel)) {
                    return p;
                }
            }
            return null;
        }

        private void actualizarTablaCarrito(Carrito carrito) {
            modeloTabla.setRowCount(0);

            for (ItemCarrito item : carrito.getItems()) {
                Object[] fila = new Object[] {
                    item.getProducto().getNombreComercial(),
                    item.getCantidad(),
                    String.format("%.2f", item.getProducto().getPrecioUnitario()),
                    String.format("%.2f", item.getSubtotal())
                };
                modeloTabla.addRow(fila);
            }

            lblTotal.setText("TOTAL: S/ " + String.format("%.2f", carrito.getTotal()));
        }
        
        private JButton crearBoton(String text) {
            JButton btn = new JButton(text);
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
        public void update(Model model, Object data) {}
    }
