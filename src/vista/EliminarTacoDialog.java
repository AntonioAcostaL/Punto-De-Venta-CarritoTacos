package vista;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidades.GestorCarrito;
import entidades.Taco;

public class EliminarTacoDialog extends JDialog {

    private JList<String> listaNombres;
    private DefaultListModel<String> modeloLista;

    public EliminarTacoDialog(MenuAdmin owner) {
        super(owner, "Eliminar taco del menú", true);

        setSize(380, 350);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(18, 18, 18));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Selecciona un taco para eliminar");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        content.add(lblTitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();

        // cargar nombres desde el menú
        List<Taco> tacos = GestorCarrito.menuTacos;
        for (Taco t : tacos) {
            modeloLista.addElement(t.getNombre() + " - $" + t.getPrecio());
        }

        listaNombres = new JList<>(modeloLista);
        listaNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listaNombres);
        content.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(18, 18, 18));
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnCerrar = new JButton("Cerrar");

        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnCerrar.addActionListener(e -> dispose());

        bottom.add(btnEliminar);
        bottom.add(btnCerrar);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void eliminarSeleccionado() {
        int index = listaNombres.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un taco de la lista.",
                    "Nada seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Taco t = GestorCarrito.menuTacos.get(index);

        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar:\n" + t.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opc == JOptionPane.YES_OPTION) {
            GestorCarrito.eliminarTaco(t);   // quitamos de la lista y guardamos
            modeloLista.remove(index);       // actualizamos la lista visual

            JOptionPane.showMessageDialog(
                    this,
                    "Taco eliminado del menú.",
                    "Eliminado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}

