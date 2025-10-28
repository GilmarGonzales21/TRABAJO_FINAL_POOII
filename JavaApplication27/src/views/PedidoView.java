package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.PedidoController;
import controllers.StaticAccess;
import controllers.VentaController;
import core.View;
import core.Model;
import models.ItemCarrito;
import models.Pedido;

@SuppressWarnings("serial")
public class PedidoView extends JPanel implements View {

    private JTextField txtCliente;
    private JTextField txtDireccion;
    private JCheckBox chkEnvio;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;
    private JTextArea txtInfoPedido;

    public PedidoView(PedidoController pedidoController) {
        setLayout(null);

        JLabel lblTitulo = new JLabel("Confirmar Pedido");
        lblTitulo.setBounds(20, 10, 200, 20);
        add(lblTitulo);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 40, 80, 25);
        add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(100, 40, 200, 25);
        add(txtCliente);

        chkEnvio = new JCheckBox("¿Enviar a domicilio?");
        chkEnvio.setBounds(320, 40, 200, 25);
        add(chkEnvio);

        JLabel lblDir = new JLabel("Dirección:");
        lblDir.setBounds(20, 75, 80, 25);
        add(lblDir);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 75, 300, 25);
        add(txtDireccion);

        String[] columnas = { "Producto", "Cant.", "Precio (S/.)", "Subtotal (S/.)" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(20, 120, 700, 180);
        add(scrollTabla);

        lblTotal = new JLabel("TOTAL: S/ 0.00");
        lblTotal.setBounds(20, 305, 200, 25);
        add(lblTotal);

        JButton btnConfirmar = new JButton("Confirmar pedido + Emitir boleta");
        btnConfirmar.setBounds(20, 340, 260, 30);
        add(btnConfirmar);

        JButton btnRefrescar = new JButton("Refrescar carrito");
        btnRefrescar.setBounds(300, 340, 160, 30);
        add(btnRefrescar);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(570, 340, 180, 30);
        add(btnVolver);

        // Área informativa (pedido final emitido)
        txtInfoPedido = new JTextArea();
        JScrollPane scrollInfo = new JScrollPane(txtInfoPedido);
        scrollInfo.setBounds(20, 380, 700, 80);
        add(scrollInfo);

        VentaController ventaController = StaticAccess.venta();

        cargarDesdeCarrito();

        btnConfirmar.addActionListener(e -> {
            pedidoController.confirmarPedido(
                txtCliente.getText(),
                txtDireccion.getText(),
                chkEnvio.isSelected()
            );

            Pedido pedidoHecho = pedidoController.getPedidoActual();
            if (pedidoHecho != null) {
                txtInfoPedido.setText(pedidoHecho.toString());
                cargarDesdePedido(pedidoHecho);
            }
        });

        btnRefrescar.addActionListener(e -> cargarDesdeCarrito());

        btnVolver.addActionListener(e -> {
            core.Controller.loadView("HomeView");
        });
    }

    private void cargarDesdeCarrito() {
        VentaController ventaController = StaticAccess.venta();
        modeloTabla.setRowCount(0);

        double total = 0.0;
        for (ItemCarrito item : ventaController.getCarrito().getItems()) {
            Object[] fila = new Object[] {
                item.getProducto().getNombreComercial(),
                item.getCantidad(),
                String.format("%.2f", item.getProducto().getPrecioUnitario()),
                String.format("%.2f", item.getSubtotal())
            };
            modeloTabla.addRow(fila);
            total += item.getSubtotal();
        }
        lblTotal.setText("TOTAL: S/ " + String.format("%.2f", total));
    }

    private void cargarDesdePedido(Pedido pedido) {
        modeloTabla.setRowCount(0);
        double total = 0.0;

        for (ItemCarrito item : pedido.getItemsPedido()) {
            Object[] fila = new Object[] {
                item.getProducto().getNombreComercial(),
                item.getCantidad(),
                String.format("%.2f", item.getProducto().getPrecioUnitario()),
                String.format("%.2f", item.getSubtotal())
            };
            modeloTabla.addRow(fila);
            total += item.getSubtotal();
        }

        lblTotal.setText("TOTAL: S/ " + String.format("%.2f", total));
    }

    @Override
    public void update(Model model, Object data) {}
}
