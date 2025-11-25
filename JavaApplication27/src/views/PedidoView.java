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
import java.awt.*;

@SuppressWarnings("serial")
public class PedidoView extends JPanel implements View {
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AMARILLO_HOVER = new Color(255, 230, 80);
    private final Color AZUL = new Color(26, 34, 56);
    private JTextField txtCliente;
    private JTextField txtDireccion;
    private JCheckBox chkEnvio;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;
    private Image logo;
    
    public PedidoView(PedidoController pedidoController) {
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }

        JLabel lblTitulo = new JLabel("Confirmar Pedido");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(20, 10, 400, 40);
        add(lblTitulo);
        if (logo != null) {
            Image small = logo.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(small));
            logoTitulo.setBounds(380, 10, 50, 50);
            add(logoTitulo);
        }

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setForeground(AZUL);
        lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCliente.setBounds(20,70,100,25);
        add(lblCliente);
        txtCliente = new JTextField();
        txtCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCliente.setBounds(110, 70, 250, 28);
        add(txtCliente);

        chkEnvio = new JCheckBox("¿Enviar a domicilio?");
        chkEnvio.setBackground(AMARILLO);
        chkEnvio.setForeground(AZUL);
        chkEnvio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        chkEnvio.setBounds(380,70, 200, 25);
        add(chkEnvio);

        JLabel lblDir = new JLabel("Dirección:");
        lblDir.setForeground(AZUL);
        lblDir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDir.setBounds(20,110,100, 25);
        add(lblDir);
        txtDireccion = new JTextField();
        txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDireccion.setBounds(110, 110, 450, 28);
        add(txtDireccion);

        // JRadioButton para seleccionar el método de pago
        JLabel lblMetodoPago = new JLabel("Método de Pago:");
        lblMetodoPago.setForeground(AZUL);
        lblMetodoPago.setBounds(20, 150, 150, 25);
        add(lblMetodoPago);

        JRadioButton rbtnTarjeta = new JRadioButton("Tarjeta");
        rbtnTarjeta.setBackground(AMARILLO);
        rbtnTarjeta.setForeground(AZUL);
        rbtnTarjeta.setBounds(160, 150, 100, 25);
        add(rbtnTarjeta);

        JRadioButton rbtnEfectivo = new JRadioButton("Efectivo");
        rbtnEfectivo.setBackground(AMARILLO);
        rbtnEfectivo.setForeground(AZUL);
        rbtnEfectivo.setBounds(260, 150, 100, 25);
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
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(22);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(20, 190, 760, 230); // Ajusté la posición aquí
        add(scrollTabla);

        lblTotal = new JLabel("TOTAL: S/ 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotal.setForeground(AZUL);
        lblTotal.setBounds(20, 430, 260, 30);
        add(lblTotal);

        JButton btnConfirmar = crearBoton("Confirmar pedido + Emitir boleta");
        btnConfirmar.setBounds(20, 470, 280, 40);
        add(btnConfirmar);

        JButton btnRefrescar = crearBoton("Refrescar carrito");
        btnRefrescar.setBounds(320, 470, 200, 40);
        add(btnRefrescar);

        JButton btnVolver = crearBoton("Volver al menú principal");
        btnVolver.setBounds(540, 470, 240, 40);
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
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());
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
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(AMARILLO_HOVER); }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(AMARILLO); }
        });

        return btn;
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
