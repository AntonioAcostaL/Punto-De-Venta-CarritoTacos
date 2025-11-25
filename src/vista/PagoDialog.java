package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;

import entidades.GestorCarrito;
import entidades.Pedido;

public class PagoDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagoDialog(JDialog owner, Pedido pedido) {
        super(owner, "Código de pago", true);

        setSize(360, 220);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        add(content, BorderLayout.CENTER);

        JLabel lblInfo1 = new JLabel("Muestra este código al empleado");
        lblInfo1.setForeground(Color.WHITE);
        lblInfo1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblInfo2 = new JLabel("para confirmar tu pago.");
        lblInfo2.setForeground(Color.WHITE);
        lblInfo2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo2.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(lblInfo1);
        content.add(lblInfo2);
        content.add(Box.createVerticalStrut(15));

        String codigo = generarCodigo();

        JLabel lblCodigo = new JLabel(codigo);
        
        pedido.setCodigoPago(codigo);
        GestorCarrito.pedidosPorCodigo.put(codigo, pedido);
        GestorCarrito.guardarPedidos();

        lblCodigo.setForeground(Color.GREEN);
        lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblCodigo.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(lblCodigo);
        content.add(Box.createVerticalStrut(10));

        if (pedido != null) {
            JLabel lblPedido = new JLabel("ID Pedido: " + pedido.getIdPedido());
            lblPedido.setForeground(Color.LIGHT_GRAY);
            lblPedido.setFont(new Font("SansSerif", Font.PLAIN, 12));
            lblPedido.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(lblPedido);
        }

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(18, 18, 18));
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        bottom.add(btnCerrar);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String generarCodigo() {
        Random r = new Random();
        int num = 100000 + r.nextInt(900000); // 6 dígitos
        return String.valueOf(num);
    }
}

