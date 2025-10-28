/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import core.Controller;
import models.Producto;
import models.ProductoFactory;
import views.CatalogoView;

/**
 *
 * @author gilma
 */
public class CatalogoController extends Controller {

    private CatalogoView view;
    private List<Producto> catalogo;

    @Override
    public void run() {
        catalogo = ProductoFactory.crearCatalogoInicial();
        view = new CatalogoView(this);
        addView("CatalogoView", view);
    }

    public List<Producto> getCatalogo() {
        return catalogo;
    }
}
