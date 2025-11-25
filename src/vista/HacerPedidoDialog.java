package vista;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.Cliente;
import entidades.GestorCarrito;
import entidades.Pedido;
import entidades.Taco;
import entidades.TacoPedido;

public class HacerPedidoDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Ahora son listas dinámicas, no chkCamaron/chkPulpo/etc.
    private java.util.List<JCheckBox> checksTacos;
    private java.util.List<JSpinner> spinnersTacos;
    private java.util.List<Taco> tacosMenu;   // referencia a los tacos del Gestor

    private JRadioButton rdbMaiz;
    private JRadioButton rdbHarina;

    private JComboBox<String> cmbBebida;

    private MenuCliente menuCliente;
    private Cliente cliente;

    public HacerPedidoDialog(MenuCliente owner, Cliente cliente) {
        super(owner, "Hacer pedido", true);
        this.menuCliente = owner;
        this.cliente = cliente;

        // Cargar tacos desde el archivo / memoria
        GestorCarrito.cargarTacos();
        tacosMenu = GestorCarrito.menuTacos;

        setSize(480, 480);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        add(content, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Arma tu pedido");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(lblTitulo);
        content.add(Box.createVerticalStrut(15));

        // ===== PANEL DE TACOS DINÁMICO =====
        if (tacosMenu == null || tacosMenu.isEmpty()) {
            JLabel lblSinTacos = new JLabel("No hay tacos configurados en el menú.");
            lblSinTacos.setForeground(Color.WHITE);
            lblSinTacos.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(lblSinTacos);
        } else {

            JPanel tacosPanel = new JPanel();
            tacosPanel.setOpaque(false);
            tacosPanel.setLayout(new GridLayout(tacosMenu.size(), 3, 5, 5));

            checksTacos = new ArrayList<>();
            spinnersTacos = new ArrayList<>();

            for (Taco t : tacosMenu) {
                String texto = t.getNombre() + " ($" + t.getPrecio() + ")";
                tacosPanel.add(crearLabel(texto));

                JCheckBox chk = crearCheck();
                tacosPanel.add(chk);
                checksTacos.add(chk);

                JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
                tacosPanel.add(spin);
                spinnersTacos.add(spin);
            }

            content.add(tacosPanel);
        }

        content.add(Box.createVerticalStrut(15));

        // ===== Tipo de tortilla =====
        JLabel lblTortilla = new JLabel("Tipo de tortilla:");
        lblTortilla.setForeground(Color.WHITE);
        lblTortilla.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lblTortilla);
        content.add(Box.createVerticalStrut(5));

        JPanel tortillaPanel = new JPanel();
        tortillaPanel.setOpaque(false);
        rdbMaiz = new JRadioButton("Maíz");
        rdbHarina = new JRadioButton("Harina");
        configRadio(rdbMaiz);
        configRadio(rdbHarina);

        ButtonGroup grupoTortilla = new ButtonGroup();
        grupoTortilla.add(rdbMaiz);
        grupoTortilla.add(rdbHarina);
        rdbMaiz.setSelected(true);

        tortillaPanel.add(rdbMaiz);
        tortillaPanel.add(rdbHarina);
        content.add(tortillaPanel);
        content.add(Box.createVerticalStrut(15));

        // ===== Bebida =====
        JLabel lblBebida = new JLabel("Bebida:");
        lblBebida.setForeground(Color.WHITE);
        lblBebida.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lblBebida);
        content.add(Box.createVerticalStrut(5));

        cmbBebida = new JComboBox<>(new String[] {
                "Sin bebida",
                "Agua de horchata (+$20)",
                "Agua de jamaica (+$18)",
                "Refresco (+$22)"
        });
        cmbBebida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        content.add(cmbBebida);

        content.add(Box.createVerticalStrut(15));

        //  Botones 
        JPanel botones = new JPanel();
        botones.setBackground(new Color(18, 18, 18));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> confirmarPedido());
        btnCancelar.addActionListener(e -> dispose());

        botones.add(btnAceptar);
        botones.add(btnCancelar);

        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JCheckBox crearCheck() {
        JCheckBox chk = new JCheckBox();
        chk.setOpaque(false);
        chk.setForeground(Color.WHITE);
        return chk;
    }

    private void configRadio(JRadioButton r) {
        r.setOpaque(false);
        r.setForeground(Color.WHITE);
    }

    private void confirmarPedido() {

        if (tacosMenu == null || tacosMenu.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay tacos disponibles para hacer un pedido.",
                    "Sin tacos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        ArrayList<TacoPedido> listaTacos = new ArrayList<>();
        boolean alMenosUno = false;

        // Recorremos todos los tacos del menú y verificamos cuáles se marcaron
        for (int i = 0; i < tacosMenu.size(); i++) {
            JCheckBox chk = checksTacos.get(i);
            if (chk.isSelected()) {
                alMenosUno = true;
                int cant = (int) spinnersTacos.get(i).getValue();
                Taco t = tacosMenu.get(i);
                listaTacos.add(new TacoPedido(t, cant));
            }
        }

        if (!alMenosUno) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona al menos un tipo de taco.",
                    "Pedido vacío",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Precio extra de bebida
        String bebida = (String) cmbBebida.getSelectedItem();
        double extraBebida = 0;
        if (bebida != null && !bebida.startsWith("Sin bebida")) {
            if (bebida.contains("20")) extraBebida = 20;
            else if (bebida.contains("18")) extraBebida = 18;
            else if (bebida.contains("22")) extraBebida = 22;
        } else {
            bebida = "Sin bebida";
        }

        // ID del pedido
        String idPedido = generarIdPedido();

        // Crear Pedido con bebida y extra incluidos
        Pedido pedido = new Pedido(idPedido, cliente, listaTacos, bebida, extraBebida);

        JOptionPane.showMessageDialog(
                this,
                "Pedido registrado.\nID: " + idPedido +
                "\nTotal: $" + pedido.getTotalFinal(),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Guardar en MenuCliente y en GestorCarrito
        menuCliente.setPedidoActual(pedido);
        GestorCarrito.pedidos.add(pedido);
        GestorCarrito.guardarPedidos();

        dispose();
    }

    private String generarIdPedido() {
        int num = new Random().nextInt(900000) + 100000;
        return "PED-" + num;
    }
}

