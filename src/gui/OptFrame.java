package gui;

import database.BDVeiculos;
import motorized.Passeio;
import motorized.Veiculo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OptFrame extends JFrame {
    public OptFrame(Class<? extends Veiculo> type) {
        super();
        setLocationByPlatform(true);
        setTitle("Veículo de " + (type == Passeio.class ? "Passeio" : "Carga"));
        setSize(400, 360);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(0, 40, 0, 0));
        add(panel);

        JLabel[] labels = {new JLabel("Cadastrar"), new JLabel("Consultar / Excluir pela placa"),
                new JLabel("Imprimir / Excluir todos"), new JLabel("Sair")};
        var buttons = new JButton[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(type == Passeio.class ? Constants.BLUE : Constants.GREEN);
            buttons[i].setPreferredSize(Constants.SQUARE);
        }
        buttons[3].setBackground(Constants.RED);
        var c = new GridBagConstraints();
        c.insets = new Insets(20, 10, 0, 0);
        for (int i = 0; i < labels.length; i++) {
            c.gridy = i;
            c.gridx = 0;
            panel.add(buttons[i], c);
            c.gridx = 1;
            c.anchor = GridBagConstraints.LINE_START;
            panel.add(labels[i], c);
        }
        buttons[0].addActionListener(e -> new ItemFrame(type, Constants.ADD_OPT));
        buttons[1].addActionListener(e -> new ItemFrame(type, Constants.FIND_OPT));
        buttons[2].addActionListener(e -> new ListFrame(type));
        buttons[3].addActionListener(e -> this.dispose());
        setVisible(true);
    }
}
