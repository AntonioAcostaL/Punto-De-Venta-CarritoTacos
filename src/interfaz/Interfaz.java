package interfaz;

import javax.swing.JOptionPane;
import vista.Login;
import vista.LoginA;
import entidades.GestorCarrito;

public class Interfaz {

    public static void main(String[] args) {
        Interfaz i = new Interfaz();
        GestorCarrito.datosPrueba(); // Carga datos de prueba
        i.menu(); // Muestra el menú principal
    }

    private void menu() {
        int opcion = -1;

        do {
            String menu = "🍽️ TACOS EL KENE 🍽️\n\n" +
                          "Selecciona una opción:\n" +
                          "1. Cliente\n" +
                          "2. Administrador\n" +
                          "0. Salir\n";

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                opcion = 0; // Si se cierra el diálogo
            } else {
                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    opcion = -1;
                }
            }

            switch (opcion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.");
            }

        } while (opcion != 0);
    }

    private void menuCliente() {
        new Login(); 
    }

    private void menuAdministrador() {
        new LoginA(); // 
}
}