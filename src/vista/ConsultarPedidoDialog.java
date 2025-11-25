package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Pedido;
import entidades.TacoPedido;
import entidades.Taco;

public class ConsultarPedidoDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultarPedidoDialog(MenuCliente owner, Pedido pedido) {
        super(owner, "Consultar pedido", true);

        setSize(460, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Panel principal 
        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Resumen de tu pedido");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(lblTitulo, BorderLayout.NORTH);

        // area de texto
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(new Color(30, 30, 30));
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));

        StringBuilder sb = new StringBuilder();

        // Datos generales del pedido
        sb.append("ID del pedido: ").append(pedido.getIdPedido()).append("\n");
        if (pedido.getCliente() != null) {
            sb.append("Cliente: ").append(pedido.getCliente().getNombre()).append("\n");
        }
        sb.append("\nTACOS:\n");

        // Listado de tacos
        for (TacoPedido tp : pedido.getTacos()) {
            Taco t = tp.getTaco();
            sb.append("- ")
              .append(t.getNombre())              // nombre del taco en el menú
              .append(" x ")
              .append(tp.getCantidad())           // cantidad pedida
              .append(" = $")
              .append(tp.getSubtotal())           // subtotal de ese tipo de taco
              .append("\n");
        }

        sb.append("\nSubtotal tacos: $").append(pedido.getTotal()).append("\n");

        // Bebida (si hay)
        if (pedido.getBebida() != null && !pedido.getBebida().equalsIgnoreCase("Sin bebida")) {
            sb.append("Bebida: ").append(pedido.getBebida()).append("\n");
            sb.append("Extra bebida: $").append(pedido.getExtraBebida()).append("\n");
        } else {
            sb.append("Bebida: Sin bebida\n");
        }

        sb.append("\nTOTAL A PAGAR: $").append(pedido.getTotalFinal()).append("\n");

        area.setText(sb.toString());

        JScrollPane scroll = new JScrollPane(area);
        content.add(scroll, BorderLayout.CENTER);

        // botones inferiores
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(18, 18, 18));

        JButton btnPagar = new JButton("Continuar con pago");
        JButton btnCerrar = new JButton("Cerrar");

        btnPagar.addActionListener(e -> new PagoDialog(this, pedido));
        btnCerrar.addActionListener(e -> dispose());

        bottom.add(btnPagar);
        bottom.add(btnCerrar);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
