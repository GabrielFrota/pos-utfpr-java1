package gui;

import database.BDVeiculos;
import database.VeicExistException;
import gui.verifier.FloatVerifier;
import gui.verifier.IntVerifier;
import gui.verifier.StringVerifier;
import motorized.Carga;
import motorized.Passeio;
import motorized.Veiculo;
import motorized.VelocException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class ItemFrame extends JFrame {
    private final List<JTextField> fields = new ArrayList<>();

    public ItemFrame(Class<? extends Veiculo> type, int opt) {
        super();
        setLocationByPlatform(true);
        var veic = type == Passeio.class ? "Passeio" : "Carga";
        var title = opt == Constants.ADD_OPT ? "Cadastro de " + veic : "Consultar / Excluir pela placa";
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(4, 4, 14, 14));
        add(panel);

        var labels = new ArrayList<JLabel>();
        for (var l : Constants.VEIC_LABELS)
            labels.add(new JLabel(l+":"));
        for (var l : type == Passeio.class ? Constants.PASSEIO_LABELS : Constants.CARGA_LABELS)
            labels.add(new JLabel(l+":"));
        for (int i = 0; i < labels.size(); i++) {
            fields.add(new JTextField());
            fields.get(fields.size()-1).setColumns(20);
        }
        int cnt = 0;
        for (; cnt < 4; cnt++)
            fields.get(cnt).setInputVerifier(new StringVerifier());
        fields.get(cnt++).setInputVerifier(new FloatVerifier());
        for (; cnt < fields.size(); cnt++)
            fields.get(cnt).setInputVerifier(new IntVerifier());

        var c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 0, 0);
        for (int i = 0; i < fields.size(); i++) {
            c.gridy = i;
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_END;
            panel.add(labels.get(i), c);
            c.gridx = 1;
            panel.add(fields.get(i), c);
        }
        var buttons = new JButton[3];
        if (opt == Constants.ADD_OPT) {
            buttons[0] = new JButton("Cadastrar");
            buttons[0].addActionListener(e -> {
                try {
                    BDVeiculos.db().existsByPlate(fields.get(0).getText());
                    BDVeiculos.db().save(fieldsToVeiculo(type));
                    JOptionPane.showMessageDialog(this,
                            "Cadastro concluído com sucesso.",
                            "INFO", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (VeicExistException vex) {
                    JOptionPane.showMessageDialog(this,
                            "Placa inserida já está cadastrada no sistema. " +
                                    "Cadastro duplicado não é permitido.",
                            "ERRO", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Campos com valores inválidos. " +
                                    "Preencha o cadastro corretamente e tente a inserção novamente.",
                            "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            });
            buttons[1] = new JButton("Limpar");
            buttons[1].setVerifyInputWhenFocusTarget(false);
            buttons[1].addActionListener(e -> clearFields());
            buttons[2] = new JButton("Sair");
            buttons[2].setVerifyInputWhenFocusTarget(false);
            buttons[2].addActionListener(e -> this.dispose());
        }
        else if (opt == Constants.FIND_OPT) {
            for (int i = 1; i < fields.size(); i++)
                fields.get(i).setEditable(false);
            buttons[0] = new JButton("Consultar");
            buttons[0].setVerifyInputWhenFocusTarget(false);
            buttons[0].addActionListener(e -> {
                var p = fields.get(0).getText();
                var v = BDVeiculos.db().findByPlate(type, p);
                if (v.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Placa inserida não consta no cadastro.",
                            "ERRO", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                    return;
                }
                veiculoToFields(v.get());
            });
            buttons[1] = new JButton("Excluir");
            buttons[1].setVerifyInputWhenFocusTarget(false);
            buttons[1].addActionListener(e -> {
                var p = fields.get(0).getText();
                var v = BDVeiculos.db().findByPlate(type, p);
                if (v.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Placa inserida não consta no cadastro.",
                            "ERRO", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                } else {
                    veiculoToFields(v.get());
                    BDVeiculos.db().delete(v.get());
                    JOptionPane.showMessageDialog(this,
                            "Registro excluído com sucesso.",
                            "INFO", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                }
            });
            buttons[2] = new JButton("Sair");
            buttons[2].setVerifyInputWhenFocusTarget(false);
            buttons[2].addActionListener(e -> this.dispose());
        }
        c.gridy = labels.size();
        c.gridx = 0;
        panel.add(buttons[0], c);
        c.gridx = 1;
        var p = new JPanel();
        p.setLayout(new GridBagLayout());
        var cc = new GridBagConstraints();
        cc.gridx = 0;
        cc.gridy = 0;
        cc.weightx = 1;
        cc.ipadx = 20;
        cc.anchor = GridBagConstraints.LINE_END;
        p.add(buttons[1], cc);
        cc.gridx = 1;
        p.add(buttons[2], cc);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(p, c);
        pack();
        setVisible(true);
    }

    private Veiculo fieldsToVeiculo(Class<? extends Veiculo> type) {
        var v = type == Passeio.class ? new Passeio() : new Carga();
        v.setPlaca(fields.get(0).getText());
        v.setMarca(fields.get(1).getText());
        v.setModelo(fields.get(2).getText());
        v.setCor(fields.get(3).getText());
        try {
            var df = new DecimalFormat();
            var pos = new ParsePosition(0);
            var num = df.parse(fields.get(4).getText().trim(), pos);
            v.setVelocMax(num.floatValue());
        } catch (VelocException e) {
            try {
                v.setVelocMax(v instanceof Passeio ? 100 : 90);
            } catch (VelocException ignored) {}
        }
        v.setQtdRodas(Integer.parseInt(fields.get(5).getText().trim()));
        v.getMotor().setQtdPist(Integer.parseInt(fields.get(6).getText().trim()));
        v.getMotor().setPotencia(Integer.parseInt(fields.get(7).getText().trim()));
        if (v instanceof Passeio p) {
            p.setQtdPassageiros(Integer.parseInt(fields.get(8).getText().trim()));
        } else {
            Carga c = (Carga) v;
            c.setCargaMax(Integer.parseInt(fields.get(8).getText().trim()));
            c.setTara(Integer.parseInt(fields.get(9).getText().trim()));
        }
        return v;
    }

    private void veiculoToFields(Veiculo v) {
        fields.get(1).setText(v.getMarca());
        fields.get(2).setText(v.getModelo());
        fields.get(3).setText(v.getCor());
        fields.get(4).setText(Float.toString(v.getVelocMax()));
        fields.get(5).setText(Integer.toString(v.getQtdRodas()));
        fields.get(6).setText(Integer.toString(v.getMotor().getQtdPist()));
        fields.get(7).setText(Integer.toString(v.getMotor().getPotencia()));
        if (v instanceof Passeio p) {
            fields.get(8).setText(Integer.toString(p.getQtdPassageiros()));
        } else {
            Carga c = (Carga) v;
            fields.get(8).setText(Integer.toString(c.getCargaMax()));
            fields.get(9).setText(Integer.toString(c.getTara()));
        }
    }

    private void clearFields() {
        for (var f : fields) {
            f.setText("");
            f.setBorder(Constants.DEFAULT_BORDER);
        }
    }
}
