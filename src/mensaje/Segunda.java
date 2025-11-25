package mensaje;

import javax.swing.*;
import java.awt.*;

public class Segunda extends JDialog {
    private static final long serialVersionUID = 1L;
    private JLabel etiqueta;

    public Segunda() {
        setTitle("Ventana 2 - Banner Secundario");
        setBounds(100, 100, 450, 200);
        setLayout(new FlowLayout());

        etiqueta = new JLabel("Esperando banner...");
        etiqueta.setPreferredSize(new Dimension(400, 30));
        add(etiqueta);
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }
}