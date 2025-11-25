package mensaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Banner implements Runnable {
    private JLabel component;
    private static final int DELAY = 100; // velocidad del movimiento
    private String mensaje = 
    	    " Bienvenido a El Carrito, tacos de mariscos. Disfruta nuestras especialidades: camarón al ajillo, pulpo a la parrilla y camarón capeado. "
    	  + " Contamos con combos especiales, estilo Baja original y mariscos frescos todos los días. "
    	  + " Pregunta por las promociones del día y nuestro menú de temporada. Calidad, sabor y frescura en cada taco. ";

    private int offset = 0; // posición actual del desplazamiento
    private boolean running = true;

    public Banner(JLabel aComponent) {
        this.component = aComponent;
        this.component.setText(mensaje);
    }

    public void cambiaComponente(JLabel aComponent) {
        this.component = aComponent;
        this.component.setText(mensaje.substring(offset) + mensaje.substring(0, offset));
    }

    public void detener() {
        running = false;
    }

    public void run() {
        try {
            while (running) {
                // rotamos el mensaje
                offset = (offset + 1) % mensaje.length();
                SwingUtilities.invokeLater(() -> {
                    component.setText(mensaje.substring(offset) + mensaje.substring(0, offset));
                });
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}