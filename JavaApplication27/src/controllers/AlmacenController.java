/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.util.List;
import core.Controller;
import models.Almacen;
import models.AlmacenFactory;
import views.AlmacenView;
/**
 *
 * @author gilma
 */
public class AlmacenController extends Controller{
    private AlmacenView almacenView;
    private List<Almacen> almacenes;
    
    
    @Override
    public void run() {
        almacenes = AlmacenFactory.crearAlmacenesDemo();
        almacenView = new AlmacenView(this);
        addView("AlmacenView", almacenView);    
    }
    public List<Almacen> getAlmacenes() {
        return almacenes;
    }
}
