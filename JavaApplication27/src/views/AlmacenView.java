/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;
import controllers.AlmacenController;
import core.Model;
import core.View;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import models.Almacen;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author gilma
 */
@SuppressWarnings("serial")
public class AlmacenView extends JPanel implements View{
    private final Color AMARILLO = new Color(247, 212, 0);
    private final Color AZUL = new Color(26, 34, 56);
    private final Color AMARILLO_HOVER = new Color(255, 230, 80);
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private Image logo;

    public AlmacenView(AlmacenController controller) {
        setLayout(null);
        try {
            logo = new ImageIcon(getClass().getResource("/assets/logo.png")).getImage();
        } catch (Exception e) {
            System.out.println("ERROR cargando logo: " + e.getMessage());
        }
        
        JLabel lblTitulo = new JLabel("Listado de Almacenes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(AZUL);
        lblTitulo.setBounds(20, 20, 380, 45);
        add(lblTitulo);
        if (logo != null) {
            Image small = logo.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel logoTitulo = new JLabel(new ImageIcon(small));
            logoTitulo.setBounds(410, 15, 50, 50);
            add(logoTitulo);
        }

        String[] columnas = { "Nombre", "Dirección", "Teléfono" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int f, int c) { 
                return false; 
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 740, 280);
        add(scroll);

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(560, 360, 200, 30);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            core.Controller.loadView("HomeView");
        });

        cargarAlmacenes(controller.getAlmacenes());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(AMARILLO);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void cargarAlmacenes(List<Almacen> almacenes) {
        modeloTabla.setRowCount(0);

        for (Almacen a : almacenes) {
            Object[] fila = new Object[3];
            fila[0] = a.getNombre();
            fila[1] = a.getDireccion();
            fila[2] = a.getTelefono();  
            modeloTabla.addRow(fila);
        }
    }
    @Override
    public void update(Model model, Object data) {
    }  
}
