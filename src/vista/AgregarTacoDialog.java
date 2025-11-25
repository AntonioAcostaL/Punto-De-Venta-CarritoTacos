package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.GestorCarrito;
import entidades.Taco;

public class AgregarTacoDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtPrecio;
    private JTextArea txtDescripcion;

    public AgregarTacoDialog(MenuAdmin owner) {
        super(owner, "Agregar taco al menú", true);

        setSize(420, 420);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        add(content, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Nuevo taco");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(lblTitulo);
        content.add(Box.createVerticalStrut(15));

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtNombre = new JTextField();
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Tipo
        JLabel lblTipo = new JLabel("Tipo (ej. Taco, Tosatada, etc.):");
        lblTipo.setForeground(Color.WHITE);
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtTipo = new JTextField();
        txtTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Precio
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPrecio = new JTextField();
        txtPrecio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Descripción
        JLabel lblDesc = new JLabel("Descripción:");
        lblDesc.setForeground(Color.WHITE);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtDescripcion = new JTextArea(4, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);

        // Añadir al panel
        content.add(lblNombre);
        content.add(txtNombre);
        content.add(Box.createVerticalStrut(8));

        content.add(lblTipo);
        content.add(txtTipo);
        content.add(Box.createVerticalStrut(8));

        content.add(lblPrecio);
        content.add(txtPrecio);
        content.add(Box.createVerticalStrut(8));

        content.add(lblDesc);
        content.add(scrollDesc);

        // Botones
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(18, 18, 18));

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarTaco());
        btnCancelar.addActionListener(e -> dispose());

        bottom.add(btnGuardar);
        bottom.add(btnCancelar);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void guardarTaco() {
        String nombre = txtNombre.getText().trim();
        String tipo   = txtTipo.getText().trim();
        String precioTxt = txtPrecio.getText().trim();
        String desc   = txtDescripcion.getText().trim();

        if (nombre.isEmpty() || tipo.isEmpty() || precioTxt.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nombre, tipo y precio son obligatorios.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser un número válido.",
                    "Error en precio",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Taco nuevo = new Taco(tipo, precio, nombre, desc);
        GestorCarrito.agregarTaco(nuevo);

        JOptionPane.showMessageDialog(
                this,
                "Taco agregado al menú.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}
