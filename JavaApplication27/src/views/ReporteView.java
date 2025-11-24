package views;

import javax.swing.*;
import controllers.ReporteController;
import controllers.StaticAccess;
import core.View;
import core.Model;

@SuppressWarnings("serial")
public class ReporteView extends JPanel implements View {

    private JTextArea txtReporte;
    private ReporteController reporteController;

    public ReporteView(ReporteController reporteController) {
        this.reporteController = reporteController;
        setLayout(null);

        JLabel lbl = new JLabel("Reporte de Ventas (Sesión Actual)");
        lbl.setBounds(20, 10, 300, 20);
        add(lbl);

        txtReporte = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtReporte);
        scroll.setBounds(20, 40, 600, 360);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar reporte");
        btnActualizar.setBounds(20, 410, 180, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> {
            String resumen = reporteController.generarResumen(StaticAccess.venta());
            txtReporte.setText(resumen);
        });

        JButton btnVolver = new JButton("Volver al menú principal");
        btnVolver.setBounds(430, 410, 180, 30);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            core.Controller.loadView("HomeView");
        });
        cargarReporte();
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
