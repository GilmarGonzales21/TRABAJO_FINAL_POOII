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
/**
 *
 * @author gilma
 */
public class AlmacenView extends JPanel implements View{
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public AlmacenView(AlmacenController controller) {
        setLayout(null);
        
        JLabel lblTitulo = new JLabel("Listado de Almacenes");
        lblTitulo.setBounds(20, 20, 200, 25);
        add(lblTitulo);

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
