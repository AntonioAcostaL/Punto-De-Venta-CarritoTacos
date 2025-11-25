package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.Pedido;
import mensaje.Banner;

public class MenuCliente extends JDialog {

    private static final long serialVersionUID = 1L;

    private Cliente cliente;
    private Banner banner;
    private JLabel lblBienvenida;
    
    private Pedido pedidoActual;   // pedido en memoria de este cliente

    public void setPedidoActual(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }


    // Panel con imagen de fondo
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Escala la imagen al tamaño del panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
 // Constructor vacío SOLO para WindowBuilder
    public MenuCliente() {
        this(null, null);
    }

    // Constructor principal: banner + cliente
    public MenuCliente(Banner banner, Cliente cliente) {
        this.banner = banner;
        this.cliente = cliente;

        setTitle("Menú del Cliente");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setModal(false); // para que no se sienta “encimada” encima de otras

        // imagen de fondo
        ImageIcon bgIcon = new ImageIcon(
                MenuAdmin.class.getResource("/img/pixel-paradise-beach-stockcake.jpg"));
        Image bgImage = bgIcon.getImage();

        BackgroundPanel contentPanel = new BackgroundPanel(bgImage);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);

        // barra superoor
        JPanel topBanner = new JPanel(new BorderLayout());
        topBanner.setOpaque(false); // deja ver el fondo
        topBanner.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel lblBanner = new JLabel(" ");
        lblBanner.setForeground(Color.WHITE);
        lblBanner.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblBanner.setHorizontalAlignment(SwingConstants.CENTER);

        topBanner.add(lblBanner, BorderLayout.CENTER);
        contentPanel.add(topBanner, BorderLayout.NORTH);

        // Conectar el Banner (SOLO UNA VEZ)
        if (banner != null) {
            banner.cambiaComponente(lblBanner);
        }

        // cnetro
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.add(center, BorderLayout.CENTER);

        // -panel logo y bienvenida
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel labelLogo = new JLabel();
        ImageIcon logo = new ImageIcon(
                MenuAdmin.class.getResource("/img/Captura_de_pantalla_2025-10-20_190309-removebg-preview.png"));
        Image imgLogo = logo.getImage().getScaledInstance(300, 110, Image.SCALE_SMOOTH);
        labelLogo.setIcon(new ImageIcon(imgLogo));
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblBienvenida = new JLabel(
                (cliente != null) ? "Bienvenido, " + cliente.getNombre() : "Bienvenido Cliente"
        );
        lblBienvenida.setForeground(new Color(0, 0, 0));
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(labelLogo);
        leftPanel.add(Box.createVerticalStrut(80));
        leftPanel.add(lblBienvenida);

        center.add(leftPanel, BorderLayout.CENTER);

        // panel de botones
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(10, 40, 10, 40));

        JLabel lblMenu = new JLabel("Menú");
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
        lblMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(lblMenu);
        rightPanel.add(Box.createVerticalStrut(25));

        JButton btnHacerPedido = new JButton("Hacer Pedido");
        estilizarBotonPrincipal(btnHacerPedido);
        btnHacerPedido.addActionListener(e -> {
            // this = MenuCliente (JDialog)
            new HacerPedidoDialog(this, cliente);
        });
        rightPanel.add(btnHacerPedido);
        rightPanel.add(Box.createVerticalStrut(15));

        JButton btnConsultarPedido = new JButton("Consultar Pedido");
        estilizarBotonPrincipal(btnConsultarPedido);
        btnConsultarPedido.addActionListener(e -> {
            if (pedidoActual == null) {
                JOptionPane.showMessageDialog(this,
                    "Aún no has realizado ningún pedido.",
                    "Sin pedido",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                new ConsultarPedidoDialog(this, pedidoActual);
            }
        });
        rightPanel.add(btnConsultarPedido);
        rightPanel.add(Box.createVerticalStrut(15));

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        estilizarBotonSecundario(btnCerrarSesion);
        btnCerrarSesion.addActionListener(e -> {
            // Cerrar SOLO este menú y abrir un solo Login
            dispose();
            new Login();
        });
        rightPanel.add(btnCerrarSesion);

        center.add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    // Si en alguna parte solo se manda banner, que no truene:
    public MenuCliente(Banner banner) {
        this(banner, null);
    }

    // estilos de botones

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

   
}

