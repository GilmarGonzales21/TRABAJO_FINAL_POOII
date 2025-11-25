/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import controllers.HomeController;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import core.Controller;
/**
 *
 * @author gilma
 */
public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Controller.mainFrame.setIconImage(
                new ImageIcon(Controller.class.getResource("/assets/logo.png")).getImage()
            );
        } catch (Exception e) {
            System.out.println("ERROR cargando icono: " + e.getMessage());
        }
    HomeController app = new HomeController();
        app.run();
    }
}
