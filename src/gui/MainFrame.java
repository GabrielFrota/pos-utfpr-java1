package gui;

import motorized.Carga;
import motorized.Passeio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JPanel panel = new JPanel();
    private final JButton pbutton = new JButton();
    private final JButton cbutton = new JButton();
    private final JLabel plabel = new JLabel("Passeio");
    private final JLabel clabel = new JLabel("Carga");

    public MainFrame() {
        super();
        setTitle("Gestão de Veículos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationByPlatform(true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(0, 40, 0, 0));
        add(panel);
        pbutton.setPreferredSize(Constants.SQUARE);
        pbutton.setBackground(Constants.BLUE);
        cbutton.setPreferredSize(Constants.SQUARE);
        cbutton.setBackground(Constants.GREEN);
        var c = new GridBagConstraints();
        c.insets = new Insets(20, 10, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pbutton, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(cbutton, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(plabel, c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(clabel, c);
        pbutton.addActionListener(e -> new OptFrame(Passeio.class));
        cbutton.addActionListener(e -> new OptFrame(Carga.class));
        setVisible(true);
    }
}
