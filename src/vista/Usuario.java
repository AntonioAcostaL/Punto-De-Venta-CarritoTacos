package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.GestorCarrito;

public class Usuario extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Primero intentamos cargar clientes desde archivo
                	GestorCarrito.cargarClientes();  // carga clientes desde archivo (si hay)
                	GestorCarrito.datosPrueba();     // completa clientes de prueba (si está vacío) y SIEMPRE crea admin
                	
                	GestorCarrito.cargarTacos();
                	GestorCarrito.datosPruebaTacos(); // solo crea si está vacío


                    // Si no hay clientes cargados, metemos los de prueba
                    if (GestorCarrito.clientes.isEmpty()) {
                        GestorCarrito.datosPrueba();
                        
                        GestorCarrito.guardarClientes();
                    }

                    Usuario frame = new Usuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Usuario() {
        setTitle("Taquería El Carrito - Bienvenido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Panel principal con color de fondo
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(18, 18, 18));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        //cabezera
        JPanel header = new JPanel();
        header.setBackground(new Color(18, 18, 18));
        header.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("El Carrito");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblSub = new JLabel("Sistema de pedidos");
        lblSub.setForeground(new Color(180, 180, 180));
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setBackground(new Color(18, 18, 18));
        titleBox.add(lblTitulo);
        titleBox.add(Box.createVerticalStrut(5));
        titleBox.add(lblSub);

        header.add(titleBox, BorderLayout.WEST);

        contentPane.add(header, BorderLayout.NORTH);

        // centro
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(new Color(30, 30, 30));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                new EmptyBorder(30, 40, 30, 40)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // Logo 
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(getClass().getResource("/img/Tiburon.PNG")); 
            Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            // si no encuentra la imagen se pone defaault
        }

        JLabel lblBienvenido = new JLabel("Bienvenido a la Taquería El Carrito");
        lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenido.setForeground(Color.WHITE);
        lblBienvenido.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel lblTexto = new JLabel("Selecciona el tipo de usuario para continuar");
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTexto.setForeground(new Color(190, 190, 190));
        lblTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));

        card.add(lblLogo);
        card.add(Box.createVerticalStrut(15));
        card.add(lblBienvenido);
        card.add(Box.createVerticalStrut(10));
        card.add(lblTexto);
        card.add(Box.createVerticalStrut(30));

        // Botones
        JButton btnCliente = new JButton("Entrar como Cliente");
        estilizarBotonPrincipal(btnCliente);
        btnCliente.addActionListener(e -> LoginCliente());

        JButton btnAdmin = new JButton("Entrar como Administrador");
        estilizarBotonSecundario(btnAdmin);
        btnAdmin.addActionListener(e -> {
            dispose();      // cierra la ventana Usuario
            new LoginA();   // abre el login de administrador
        });


        card.add(btnCliente);
        card.add(Box.createVerticalStrut(10));
        card.add(btnAdmin);
        
        centerPanel.add(card);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        
        JPanel footer = new JPanel();
        footer.setBackground(new Color(18, 18, 18));
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel lblCopy = new JLabel("© 2025 Carrito - Proyecto Java");
        lblCopy.setForeground(new Color(120, 120, 120));
        lblCopy.setFont(new Font("SansSerif", Font.PLAIN, 11));

        footer.add(lblCopy);
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private void estilizarBotonPrincipal(JButton boton) {
        boton.setBackground(new Color(255, 111, 0));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void estilizarBotonSecundario(JButton boton) {
        boton.setBackground(new Color(50, 50, 50));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    //estos metodos siguen llamando a las mismas clases
    public void LoginAdmin() {
        new LoginA(); // Login para administrador
        dispose();
    }

    public void LoginCliente() {
        new Login();  // Login para cliente
        dispose();
    }
}

