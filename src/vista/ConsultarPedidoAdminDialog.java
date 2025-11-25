package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Pedido;
import entidades.GestorCarrito;

public class ConsultarPedidoAdminDialog extends JDialog {

    private JTextField campoCodigo;

    // 👈 ESTE es el constructor que faltaba: recibe MenuAdmin
    public ConsultarPedidoAdminDialog(MenuAdmin owner) {
        super(owner, "Consultar pedido por código", true);

        setSize(420, 230);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // ===== PANEL PRINCIPAL =====
        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        add(content, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Consultar pedido por código");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblInstruccion = new JLabel("Ingresa el código de pago del cliente:");
        lblInstruccion.setForeground(new Color(200, 200, 200));
        lblInstruccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoCodigo = new JTextField();
        campoCodigo.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));

        content.add(lblTitulo);
        content.add(Box.createVerticalStrut(10));
        content.add(lblInstruccion);
        content.add(Box.createVerticalStrut(8));
        content.add(campoCodigo);
        content.add(Box.createVerticalStrut(10));

        // ===== BOTONES =====
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(18, 18, 18));

        JButton btnBuscar = new JButton("Buscar");
        JButton btnCerrar = new JButton("Cerrar");

        btnBuscar.addActionListener(e -> buscarPedido());
        btnCerrar.addActionListener(e -> dispose());

        bottom.add(btnBuscar);
        bottom.add(btnCerrar);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buscarPedido() {
        String codigo = campoCodigo.getText().trim();

        // 1) Validar que no esté vacío
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, ingresa un código.",
                    "Campo vacío",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2) Validar formato: exactamente 6 dígitos
        if (!codigo.matches("^\\d{6}$")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El código debe ser un número de 6 dígitos.",
                    "Código inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 3) Buscar en el mapa de pedidos
        Pedido p = GestorCarrito.pedidosPorCodigo.get(codigo);

        if (p == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró un pedido con ese código.",
                    "Sin resultados",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            // Reutilizamos la ventana de detalle del pedido del cliente.
            new ConsultarPedidoDialog(null, p);
        }
    }

}

