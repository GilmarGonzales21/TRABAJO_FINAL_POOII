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
    import java.util.List;
    import java.util.stream.Collectors;

    @SuppressWarnings("serial")
    public class CarritoView extends JPanel implements View {

        private JTable tabla;
        private DefaultTableModel modeloTabla;
        private JComboBox<String> comboCategoria;
        private JComboBox<String> comboProducto;
        private JTextField txtCantidad;
        private JLabel lblTotal;

        public CarritoView(VentaController ventaController) {
            setLayout(null);

            JLabel lblTitulo = new JLabel("Carrito / Cotización");
            lblTitulo.setBounds(20, 10, 200, 20);
            add(lblTitulo);
    //selecciona la categoria
            JLabel lblCat = new JLabel("Categoría:");
            lblCat.setBounds(20, 40, 80, 25);
            add(lblCat);

            comboCategoria = new JComboBox<>();
            comboCategoria.setBounds(100, 40, 150, 25);
            add(comboCategoria);

            comboCategoria.addItem("Cemento");
            comboCategoria.addItem("Tuberías");
            comboCategoria.addItem("Conexiones");
            comboCategoria.addItem("Pegamentos");

    //seleciona el producto
            JLabel lblProd = new JLabel("Producto:");
            lblProd.setBounds(270, 40, 80, 25);
            add(lblProd);

            comboProducto = new JComboBox<>();
            comboProducto.setBounds(340, 40, 200, 25);
            add(comboProducto);

    //la cantidad
            JLabel lblCant = new JLabel("Cantidad:");
            lblCant.setBounds(560, 40, 70, 25);
            add(lblCant);

            txtCantidad = new JTextField("1");
            txtCantidad.setBounds(630, 40, 50, 25);
            add(txtCantidad);

            JButton btnAgregar = new JButton("Agregar");
            btnAgregar.setBounds(690, 40, 90, 25);
            add(btnAgregar);

    //tablita
            String[] columnas = { "Producto", "Cant.", "Precio (S/.)", "Subtotal (S/.)" };
            modeloTabla = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int col) { return false; }
            };
            tabla = new JTable(modeloTabla);

            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 90, 760, 230);
            add(scroll);

            lblTotal = new JLabel("TOTAL: S/ 0.00");
            lblTotal.setBounds(20, 330, 200, 25);
            add(lblTotal);


            JButton btnVolver = new JButton("Volver al menú principal");
            btnVolver.setBounds(600, 360, 180, 30);
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

        @Override
        public void update(Model model, Object data) {}
    }
