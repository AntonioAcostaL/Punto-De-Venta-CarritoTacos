package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Empleado;
import entidades.GestorCarrito;

public class LoginA extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
    private JTextField txtID;
    private JPasswordField txtPassword;
    private JLabel lblMensaje;

    public LoginA() {
        setTitle("Inicio de sesión - Administrador");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(380, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(18, 18, 18));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPanel);

        // cabexera
        JPanel header = new JPanel();
        header.setBackground(new Color(18, 18, 18));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Administrador");
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

        // ID
        JLabel labelID = new JLabel("ID:");
        labelID.setForeground(Color.WHITE);
        labelID.setFont(new Font("SansSerif", Font.PLAIN, 14));

        txtID = new JTextField();
        estilizarCampo(txtID);

        // Contraseña
        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);

        // Mensaje info/error
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(new Color(255, 120, 120));
        lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(labelID);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtID);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(labelPassword);
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
        btnLogin.addActionListener(e -> intentarLoginAdmin());

        JButton btnVolver = new JButton("Volver");
        estilizarBotonSecundario(btnVolver);
        btnVolver.addActionListener(e -> {
            dispose();
            new Usuario().setVisible(true);
        });

        buttonPanel.add(btnLogin);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnVolver);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // estilo
    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(35, 35, 35));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        Dimension d = new Dimension(Integer.MAX_VALUE, 55);
        campo.setMaximumSize(d);
        campo.setPreferredSize(new Dimension(200, 28));
        campo.setMinimumSize(new Dimension(100, 24));
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

    // logica de login admin
    private void intentarLoginAdmin() {
        String id = txtID.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (id.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor, llena todos los campos.");
            return;
        }

        Empleado admin = GestorCarrito.buscarEmpleado(id, password);

        if (admin != null) {
            lblMensaje.setText("Inicio de sesión exitoso.");
            dispose();
            new MenuAdmin().setVisible(true);
        } else {
            lblMensaje.setText("Administrador no válido.");
        }
    }
}
