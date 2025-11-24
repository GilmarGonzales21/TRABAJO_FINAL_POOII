package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.PedidoController;
import controllers.StaticAccess;
import controllers.VentaController;
import core.View;
import core.Model;
import models.ItemCarrito;
import models.PagoEfectivo;
import models.PagoTarjetaCredito;
import models.Pedido;

@SuppressWarnings("serial")
public class PedidoView extends JPanel implements View {

    private JTextField txtCliente;
    private JTextField txtDireccion;
    private JCheckBox chkEnvio;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;

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

        // JRadioButton para seleccionar el método de pago
        JLabel lblMetodoPago = new JLabel("Método de Pago:");
        lblMetodoPago.setBounds(20, 110, 120, 25);
        add(lblMetodoPago);

        JRadioButton rbtnTarjeta = new JRadioButton("Tarjeta");
        rbtnTarjeta.setBounds(140, 110, 100, 25);
        add(rbtnTarjeta);

        JRadioButton rbtnEfectivo = new JRadioButton("Efectivo");
        rbtnEfectivo.setBounds(240, 110, 100, 25);
        add(rbtnEfectivo);

        // Agrupar los botones de opción
        ButtonGroup grupoPago = new ButtonGroup();
        grupoPago.add(rbtnTarjeta);
        grupoPago.add(rbtnEfectivo);

        // Tablita
        String[] columnas = {"Producto", "Cant.", "Precio (S/.)", "Subtotal (S/.)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(20, 140, 700, 180); // Ajusté la posición aquí
        add(scrollTabla);

        lblTotal = new JLabel("TOTAL: S/ 0.00");
        lblTotal.setBounds(20, 330, 200, 25);
        add(lblTotal);

        JButton btnConfirmar = new JButton("Confirmar pedido + Emitir boleta");
        btnConfirmar.setBounds(20, 360, 260, 30);
        add(btnConfirmar);

        JButton btnRefrescar = new JButton("Refrescar carrito");
        btnRefrescar.setBounds(300, 360, 160, 30);
        add(btnRefrescar);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(570, 360, 180, 30);
        add(btnVolver);

        VentaController ventaController = StaticAccess.venta();

        cargarDesdeCarrito();

        btnConfirmar.addActionListener(e -> {
            // Verifica si se seleccionó algún método de pago
            if (rbtnTarjeta.isSelected()) {
                pedidoController.setMetodoPago(new PagoTarjetaCredito()); // Asigna el tipo de pago
            } else if (rbtnEfectivo.isSelected()) {
                pedidoController.setMetodoPago(new PagoEfectivo()); // Asigna el tipo de pago
            } else {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un método de pago.");
                return; // Si no se ha seleccionado un método de pago, no procede
            }

            // Confirmar el pedido, pasando los datos correspondientes
            pedidoController.confirmarPedido(
                    txtCliente.getText(),
                    txtDireccion.getText(),
                    chkEnvio.isSelected()
            );

            Pedido pedidoHecho = pedidoController.getPedidoActual();
            if (pedidoHecho != null) {
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
            Object[] fila = new Object[]{
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
            Object[] fila = new Object[]{
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
    public void update(Model model, Object data) {
    }
}
