/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gilma
 */
public class AlmacenFactory {

    public static List<Almacen> crearAlmacenesDemo() {
        List<Almacen> lista = new ArrayList<Almacen>();

        lista.add(new Almacen("Almacén Principal", "Mz U Lt 12 Parque Industrial Santa Rosa de Collanac, Manchay", "975540900"));
        lista.add(new Almacen("Depósito San Antonio", "Jr. Las Flores 450, San Antonio", "985498899"));
        lista.add(new Almacen("Almacén Obra Pesada", "Carretera Central km 35, Santa Rosa de Sigues", "9755409232"));

        return lista;
    }
}
