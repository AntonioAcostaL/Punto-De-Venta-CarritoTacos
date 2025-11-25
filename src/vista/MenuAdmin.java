package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.GestorCarrito;

public class MenuAdmin extends JDialog {

    private static final long serialVersionUID = 1L;

    // Panel de fondo con imagen
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public MenuAdmin() {

        setTitle("Panel Administrativo");
        setSize(900, 520);
        setLocationRelativeTo(null);
        setModal(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Fondo con imagen (carga)
        ImageIcon bgIcon = new ImageIcon(
                MenuAdmin.class.getResource("/img/pixel-paradise-beach-stockcake.jpg"));
        Image bgImage = bgIcon.getImage();

        BackgroundPanel contentPanel = new BackgroundPanel(bgImage);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);

        // BANNER
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Panel Administrativo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Gestión de El Carrito");
        lblSub.setForeground(Color.WHITE);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(lblTitulo);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(lblSub);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // CENTRO
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.add(center, BorderLayout.CENTER);

        //PANEL IZQUIERDO (Logo)
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel labelLogo = new JLabel();
        ImageIcon logo = new ImageIcon(
                MenuAdmin.class.getResource("/img/Captura_de_pantalla_2025-10-20_190309-removebg-preview.png"));
        Image imgLogo = logo.getImage().getScaledInstance(300, 110, Image.SCALE_SMOOTH);
        labelLogo.setIcon(new ImageIcon(imgLogo));
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(labelLogo);

        center.add(leftPanel, BorderLayout.CENTER);

        //PANEL DERECHO (Botones)
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(10, 40, 10, 40));

        JLabel lblMenu = new JLabel("Opciones");
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
        lblMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(lblMenu);
        rightPanel.add(Box.createVerticalStrut(25));

        // Botón Agregar Taco 
        JButton btnAgregar = new JButton("Agregar Taco");
        estilizarBotonPrincipal(btnAgregar);
        btnAgregar.addActionListener(e -> new AgregarTacoDialog(this));
        rightPanel.add(btnAgregar);
        rightPanel.add(Box.createVerticalStrut(15));

        // Botón Eliminar Taco
        JButton btnEliminar = new JButton("Eliminar Taco");
        estilizarBotonPrincipal(btnEliminar);
        btnEliminar.addActionListener(e -> {
            GestorCarrito.cargarTacos();   // ← IMPORTANTE
            new EliminarTacoDialog(this);
        });

        rightPanel.add(btnEliminar);
        rightPanel.add(Box.createVerticalStrut(15));

        // Botón Consultar Pedido 
        JButton btnConsultar = new JButton("Consultar Pedido");
        estilizarBotonPrincipal(btnConsultar);
        btnConsultar.addActionListener(e -> new ConsultarPedidoAdminDialog(this));
        rightPanel.add(btnConsultar);
        rightPanel.add(Box.createVerticalStrut(20));

        //  Botón Cerrar Sesión 
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        estilizarBotonSecundario(btnCerrarSesion);
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new LoginA();
        });
        rightPanel.add(btnCerrarSesion);

        center.add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    //Estilos de botones

    private void estilizarBotonPrincipal(JButton boton) {
        boton.setBackground(new Color(255, 111, 0));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(12, 20, 12, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void estilizarBotonSecundario(JButton boton) {
        boton.setBackground(new Color(40, 40, 40));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(10, 20, 10, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    //  Funciones

    private void agregarTaco() {
        JOptionPane.showMessageDialog(this, "Aquí va la lógica para agregar tacos.");
    }

    private void eliminarTaco() {
        JOptionPane.showMessageDialog(this, "Aquí va la lógica para eliminar tacos.");
    }

    private void consultarPedido() {
        JOptionPane.showMessageDialog(this, "Aquí va la lógica para consultar pedidos.");
    }
}

