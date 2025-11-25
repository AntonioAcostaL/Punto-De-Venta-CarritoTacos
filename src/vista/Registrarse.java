package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.GestorCarrito;
import mensaje.Banner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrarse extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoContraseña;
    private JLabel lblMensaje;

    private Banner banner;      // hilo animado que viene de Login/MenuCliente
    //private Thread hiloBanner;  // por si lo necesitas más adelante

    public Registrarse(Banner banner) {
        this.banner = banner;

        setTitle("Registro de cliente");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 380, 540);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(18, 18, 18));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        // ------------ BARRA SUPERIOR (BANNER) ------------
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBackground(new Color(30, 30, 30));
        bannerPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JLabel lblBanner = new JLabel(" ");
        lblBanner.setForeground(Color.WHITE);
        lblBanner.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblBanner.setHorizontalAlignment(SwingConstants.CENTER);

        bannerPanel.add(lblBanner, BorderLayout.CENTER);
        contentPanel.add(bannerPanel, BorderLayout.NORTH);

        // Conectar el Banner si existe
        if (this.banner != null) {
            this.banner.cambiaComponente(lblBanner);
        }

        // ------------ CENTRO: TARJETA DE FORMULARIO ------------
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel lblTitulo = new JLabel("Crear cuenta");
        
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Ingresa tus datos para registrarte");
        lblSub.setForeground(new Color(190, 190, 190));
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(lblTitulo);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(lblSub);
        centerPanel.add(Box.createVerticalStrut(25));

        // Nombre
        JLabel labelNombre = new JLabel("Nombre de usuario");
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        centerPanel.add(labelNombre);
        centerPanel.add(Box.createVerticalStrut(5));

        campoNombre = new JTextField();
        estilizarCampo(campoNombre);
        centerPanel.add(campoNombre);
        centerPanel.add(Box.createVerticalStrut(15));

        // "Correo" (en tu modelo es el segundo campo de Cliente, lo usas como teléfono/correo)
        JLabel labelCorreo = new JLabel("Correo (o teléfono)");
        labelCorreo.setForeground(Color.WHITE);
        labelCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        centerPanel.add(labelCorreo);
        centerPanel.add(Box.createVerticalStrut(5));

        campoCorreo = new JTextField();
        estilizarCampo(campoCorreo);
        centerPanel.add(campoCorreo);
        centerPanel.add(Box.createVerticalStrut(15));

        // Contraseña
        JLabel labelContraseña = new JLabel("Contraseña");
        labelContraseña.setForeground(Color.WHITE);
        labelContraseña.setFont(new Font("SansSerif", Font.PLAIN, 14));
        centerPanel.add(labelContraseña);
        centerPanel.add(Box.createVerticalStrut(5));

        campoContraseña = new JTextField();
        estilizarCampo(campoContraseña);
        centerPanel.add(campoContraseña);
        centerPanel.add(Box.createVerticalStrut(15));

        // Mensaje de error
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(255, 120, 120));
        lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblMensaje);
        centerPanel.add(Box.createVerticalStrut(10));

        contentPanel.add(centerPanel, BorderLayout.CENTER);

        // botones inferiores
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(18, 18, 18));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton botonRegistrar = new JButton("Registrarse");
        estilizarBotonPrincipal(botonRegistrar);
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        JButton btnVolver = new JButton("Volver al inicio de sesión");
        estilizarBotonSecundario(btnVolver);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();       // cerrar Registrarse
                new Login();     // abrir login UNA sola vez
            }
        });
        
        buttonPanel.add(botonRegistrar);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnVolver);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // estilos

    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(35, 35, 35));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void estilizarBotonPrincipal(JButton boton) {
        boton.setBackground(new Color(76, 175, 80));
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

    // logica de registro

    private void registrarCliente() {
        String nombre = campoNombre.getText().trim();
        String contacto = campoCorreo.getText().trim();
        String contraseña = campoContraseña.getText().trim();

        // Validación de campos vacíos
        if (nombre.isEmpty() || contacto.isEmpty() || contraseña.isEmpty()) {
            lblMensaje.setText("Por favor, llena todos los campos.");
            return;
        }

        // Validación de correo o teléfono
        if (!esContactoValido(contacto)) {
            lblMensaje.setText("Ingresa un correo o teléfono válido.");
            return;
        }

        // Evitar duplicados
        Cliente existente = GestorCarrito.buscarCliente(nombre, contraseña);
        if (existente != null) {
            lblMensaje.setText("Ya existe un cliente con ese usuario y contraseña.");
            return;
        }

        // Crear y guardar nuevo cliente
        Cliente nuevo = new Cliente(nombre, contacto, contraseña);
        GestorCarrito.registrarCliente(nuevo);

        JOptionPane.showMessageDialog(
                this,
                "Registro exitoso. Ahora puedes iniciar sesión.",
                "Registro completado",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
        new Login();
    }
 // Validaciones

    private boolean esContactoValido(String texto) {
        if (texto.contains("@")) {
            return esCorreoValido(texto);
        } else {
            return esTelefonoValido(texto);
        }
    }

    private boolean esCorreoValido(String correo) {
        return correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean esTelefonoValido(String tel) {
        return tel.matches("^\\d{8,15}$");  // solo números, longitud razonable
    }

}
