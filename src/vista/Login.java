package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.GestorCarrito;
import mensaje.Banner;   

public class Login extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JLabel lblMensaje;

    // Banner e hilo para el banner
    private Banner banner;
    private Thread hiloBanner;

    public Login() {
        setTitle("Inicio de sesión - Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(18, 18, 18));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPanel);

        // cabezera
        JPanel header = new JPanel();
        header.setBackground(new Color(18, 18, 18));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Cliente");
        lblSub.setForeground(new Color(200, 200, 200));
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(lblTitulo);
        header.add(Box.createVerticalStrut(5));
        header.add(lblSub);
        header.add(Box.createVerticalStrut(20));

        contentPanel.add(header, BorderLayout.NORTH);

        // centro
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));

        txtUsuario = new JTextField();
        estilizarCampo(txtUsuario);

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);

        // Mensaje de error/info
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(255, 120, 120));
        lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(lblUsuario);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtUsuario);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblPassword);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblMensaje);
        formPanel.add(Box.createVerticalStrut(20));

        contentPanel.add(formPanel, BorderLayout.CENTER);

        // botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(18, 18, 18));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnLogin = new JButton("Entrar");
        estilizarBotonPrincipal(btnLogin);
        btnLogin.addActionListener(e -> intentarLogin());

        JButton btnRegistrar = new JButton("Registrarse");
        estilizarBotonSecundario(btnRegistrar);
        btnRegistrar.addActionListener(e -> RegistrarCliente());

        JButton btnVolver = new JButton("Volver");
        estilizarBotonSecundario(btnVolver);
        btnVolver.addActionListener(e -> {
            Usuario u = new Usuario();
            u.setVisible(true);
            dispose();
        });

        buttonPanel.add(btnLogin);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnRegistrar);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnVolver);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // CREAR BANNER (aunque aquí no se muestre) 
        
        JLabel dummy = new JLabel();
        banner = new Banner(dummy);
        hiloBanner = new Thread(banner);
        hiloBanner.start();

        // Mostrar ventana
        setVisible(true);
    }

    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(35, 35, 35));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
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
        boton.setBackground(new Color(40, 40, 40));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // logica del login
    private void intentarLogin() {

        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor, llena todos los campos.");
            return;
        }

        // prueba
        System.out.println("Clientes registrados: " + GestorCarrito.clientes.size());

        // Buscar al cliente según el gestor
        Cliente cliente = GestorCarrito.buscarCliente(usuario, password);

        if (cliente != null) {

            lblMensaje.setText("Inicio de sesión exitoso.");

            // Cerrar la ventana de login ANTES de abrir el menu
            dispose();

            // Abrir el menú del cliente
            new MenuCliente(banner, cliente);

        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
        }

    }

    public void RegistrarCliente() {
       
        dispose();

        new Registrarse(banner);
    }

}

