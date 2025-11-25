package mensaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblBanner;
    private Segunda ventanaSecundaria;
    private Banner banner;
    private Thread hilo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().setVisible(true));
    }

    public Principal() {
        setTitle("Ventana 1 - Banner Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        setLayout(null);

        lblBanner = new JLabel("Cargando...", SwingConstants.CENTER);
        lblBanner.setBounds(50, 50, 400, 30);
        add(lblBanner);

        JButton btnCambiar = new JButton("Cambiar Banner");
        btnCambiar.setBounds(170, 200, 150, 30);
        add(btnCambiar);

        // Crear hilo de banner
        banner = new Banner(lblBanner);
        hilo = new Thread(banner);
        hilo.start();

        btnCambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaSecundaria = new Segunda();
                banner.cambiaComponente(ventanaSecundaria.getEtiqueta());
                ventanaSecundaria.setVisible(true);
            }
        });
    }
}