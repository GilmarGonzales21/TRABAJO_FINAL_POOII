package controllers;

import core.Controller;
import java.util.List;
import javax.swing.SwingUtilities;
import models.Boleta;
import models.Carrito;
import models.Pedido;
import models.Producto;
import models.ReporteSesion;
import views.HomeView;

public class HomeController extends Controller {

    private HomeView homeView;

    // Instanciamos los controladores
    private final CatalogoController catalogoController = new CatalogoController();
    private final VentaController ventaController = new VentaController();
    private final PedidoController pedidoController = new PedidoController(ventaController);
    private final ReporteController reporteController = new ReporteController();
    private final AlmacenController almacenController = new AlmacenController();

    // Instanciamos el Facade
    private final FerreteriaFacade ferreteriaFacade = new FerreteriaFacade(); 

    @Override
    public void run() {
        Runnable init = () -> {
            // Inicializamos los controladores
            StaticAccess.init(catalogoController, ventaController, pedidoController, reporteController);

            // Corremos los controladores
            catalogoController.run();
            ventaController.run();
            pedidoController.run();
            reporteController.run();
            almacenController.run();

            // Vista principal
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

    // Métodos que delegan las operaciones al Facade
    public List<Producto> obtenerCatalogo() {
        return ferreteriaFacade.obtenerCatalogo();
    }

    public void agregarProductoAlCarrito(String nombreProducto, int cantidad) {
        ferreteriaFacade.agregarProductoAlCarrito(nombreProducto, cantidad);
    }

    public Carrito obtenerCarritoActual() {
        return ferreteriaFacade.obtenerCarritoActual();
    }

    public void confirmarPedido(String cliente, String direccionEntrega, boolean requiereEnvio) {
        ferreteriaFacade.confirmarPedido(cliente, direccionEntrega, requiereEnvio);
    }

    public Pedido obtenerPedidoActual() {
        return ferreteriaFacade.obtenerPedidoActual();
    }

    public ReporteSesion obtenerReporteSesion() {
        return ferreteriaFacade.obtenerReporteSesion();
    }

    public void generarBoletaDesdePedido(Pedido pedido) {
        ferreteriaFacade.generarBoletaDesdePedido(pedido);
    }
}
