/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import core.Controller;
import models.ReporteSesion;
import views.ReporteView;
/**
 *
 * @author gilma
 */
public class ReporteController extends Controller{
     private ReporteView reporteView;
      @Override
    public void run() {
        reporteView = new ReporteView(this);
        addView("ReporteView", reporteView);
    }

    public String generarResumen(VentaController ventaController) {
        ReporteSesion rep = ventaController.getReporteSesion();
        return rep.resumen();
    }
}
