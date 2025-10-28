package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.CatalogoController;
import core.View;
import core.Model;
import java.util.List;
import java.util.stream.Collectors;
import models.Producto;

@SuppressWarnings("serial")
public class CatalogoView extends JPanel implements View {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboCategoria;

    private CatalogoController controller;

    public CatalogoView(CatalogoController controller) {
        this.controller = controller;
        setLayout(null);

        JLabel lbl = new JLabel("Catálogo de Productos");
        lbl.setBounds(20, 20, 200, 25);
        add(lbl);

        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setBounds(20, 55, 80, 25);
        add(lblCat);

        comboCategoria = new JComboBox<>();
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
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 90, 740, 280);
        add(scroll);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(320, 55, 120, 25);
        add(btnRefrescar);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(560, 390, 200, 30);
        add(btnVolver);

        
        btnRefrescar.addActionListener(e -> cargarProductosFiltrados());
        comboCategoria.addActionListener(e -> cargarProductosFiltrados());
        btnVolver.addActionListener(e -> core.Controller.loadView("HomeView"));

        cargarProductosFiltrados();
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
