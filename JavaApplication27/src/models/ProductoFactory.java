/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class ProductoFactory {

    public static List<Producto> crearCatalogoInicial() {
        List<Producto> catalogo = new ArrayList<>();

        // ===== Cemento =====
        catalogo.add(new Producto(
            "Cemento Sol", "Sol", "Cemento",
            "Bolsa 42.5kg uso estructural", 30.00, 120
        ));
        catalogo.add(new Producto(
            "Cemento Andino", "Andino", "Cemento",
            "Bolsa 42.5kg obra general", 29.50, 90
        ));
        catalogo.add(new Producto(
            "Cemento APU", "APU", "Cemento",
            "Bolsa 42.5kg alta resistencia", 27.50, 60
        ));
        catalogo.add(new Producto(
            "Cemento Holcim", "Holcim", "Cemento",
            "Bolsa 42.5kg construcción estándar", 28.00, 70
        ));

        // ===== Tuberías =====
        catalogo.add(new Producto(
            "Tubo PVC 4\"", "Nicoll", "Tuberías",
            "Tubería PVC 4 pulgadas desagüe", 45.00, 40
        ));
        catalogo.add(new Producto(
            "Tubo PVC 3\"", "Nicoll", "Tuberías",
            "Tubería PVC 3 pulgadas desagüe", 32.00, 55
        ));
        catalogo.add(new Producto(
            "Tubo PVC 2\"", "Nicoll", "Tuberías",
            "Tubería PVC 2 pulgadas agua", 18.00, 80
        ));
        catalogo.add(new Producto(
            "Tubo PVC 1\"", "Nicoll", "Tuberías",
            "Tubería PVC 1 pulgada agua", 9.50, 100
        ));
        catalogo.add(new Producto(
            "Tubo PVC 3/4\"", "Nicoll", "Tuberías",
            "Tubería PVC 3/4 pulgada agua", 6.90, 150
        ));

        // ===== Conexiones =====
        catalogo.add(new Producto(
            "Codo PVC 1/2\"", "Nicoll", "Conexiones",
            "Codo de 90° PVC 1/2 pulgada", 1.20, 300
        ));
        catalogo.add(new Producto(
            "Codo PVC 2\"", "Nicoll", "Conexiones",
            "Codo de 90° PVC 2 pulgadas", 4.50, 120
        ));
        catalogo.add(new Producto(
            "Codo PVC 4\"", "Nicoll", "Conexiones",
            "Codo de 90° PVC 4 pulgadas", 8.90, 60
        ));

        catalogo.add(new Producto(
            "Tee PVC 1/2\"", "Nicoll", "Conexiones",
            "Tee PVC 1/2 pulgada", 1.50, 250
        ));
        catalogo.add(new Producto(
            "Tee PVC 2\"", "Nicoll", "Conexiones",
            "Tee PVC 2 pulgadas", 4.90, 130
        ));
        catalogo.add(new Producto(
            "Tee PVC 4\"", "Nicoll", "Conexiones",
            "Tee PVC 4 pulgadas", 9.50, 70
        ));

        catalogo.add(new Producto(
            "Yee PVC 1/2\"", "Nicoll", "Conexiones",
            "Yee PVC 1/2 pulgada", 1.80, 200
        ));
        catalogo.add(new Producto(
            "Yee PVC 2\"", "Nicoll", "Conexiones",
            "Yee PVC 2 pulgadas", 5.20, 110
        ));
        catalogo.add(new Producto(
            "Yee PVC 4\"", "Nicoll", "Conexiones",
            "Yee PVC 4 pulgadas", 10.90, 65
        ));

        // ===== Pegamentos =====
        catalogo.add(new Producto(
            "Pegamento PVC Sika", "Sika", "Pegamentos",
            "Soldadura química para PVC presión", 16.00, 75
        ));
        catalogo.add(new Producto(
            "Pegamento PVC Oatey", "Oatey", "Pegamentos",
            "Cemento solvente para PVC sanitario", 18.50, 50
        ));

        return catalogo;
    }
}

