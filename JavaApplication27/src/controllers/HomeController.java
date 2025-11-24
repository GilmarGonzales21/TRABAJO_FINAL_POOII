/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import core.Controller;
import static core.Controller.addView;
import static core.Controller.loadView;
import views.HomeView;
import javax.swing.SwingUtilities;
/**
 *
 * @author gilma
 */
public class HomeController extends Controller{
    private HomeView homeView;
    private final CatalogoController catalogoController = new CatalogoController();
    private final VentaController ventaController = new VentaController();
    private final PedidoController pedidoController = new PedidoController(ventaController);
    private final ReporteController reporteController = new ReporteController();
    private final AlmacenController almacenController = new AlmacenController();
    @Override
    public void run() {
        Runnable init = () -> {
            
            StaticAccess.init(catalogoController, ventaController, pedidoController, reporteController);

            catalogoController.run();
            ventaController.run();
            pedidoController.run();
            reporteController.run();
            almacenController.run();
            
            homeView = new HomeView(this);
            addView("HomeView", homeView);

            showMainFrame();
            loadView("HomeView");
        };

        if (SwingUtilities.isEventDispatchThread()) {
            init.run();
        } else {
            SwingUtilities.invokeLater(init);
        }
    }
    
    public CatalogoController getCatalogoController() {
        return catalogoController;
    }

    public VentaController getVentaController() {
        return ventaController;
    }

    public PedidoController getPedidoController() {
        return pedidoController;
    }

    public ReporteController getReporteController() {
        return reporteController;
    }
}
