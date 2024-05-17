package gui;

import database.BDVeiculos;
import motorized.Carga;
import motorized.Passeio;
import motorized.Veiculo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;


public class ListFrame extends JFrame {
    public ListFrame(Class<? extends Veiculo> type) {
        super();
        setLocationByPlatform(true);
        var title = "Imprimir / Excluir todos";
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(14, 14, 14, 14));
        add(panel);

        var jtable = new JTable(new MyModel(type));
        var jscroll = new JScrollPane(jtable);
        for (int i = 0; i < jtable.getColumnCount(); i++) {
            jtable.getColumnModel().getColumn(i).setPreferredWidth(130);
        }
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtable.getTableHeader().setResizingAllowed(false);
        jtable.getTableHeader().setReorderingAllowed(false);
        jtable.setPreferredScrollableViewportSize(
                new Dimension(jtable.getPreferredSize().width, jtable.getPreferredSize().height));
        jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        var c = new GridBagConstraints();
        c.gridy = 0;
        panel.add(jscroll, c);

        var jbutton = new JButton("Excluir todos");
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 0, 0,0);
        c.ipadx = 20;
        jbutton.addActionListener(e -> {
            var m = (MyModel)jtable.getModel();
            var l = new LinkedList<Veiculo>(m.getData());
            for (var v : l) {
                BDVeiculos.db().delete(v);
            }
            m.fireTableDataChanged();
        });
        panel.add(jbutton, c);
        pack();
        setVisible(true);
    }

    static class MyModel extends AbstractTableModel {
        final Class<? extends Veiculo> type;
        final List<? extends Veiculo> data;

        MyModel(Class<? extends Veiculo> type) {
            super();
            if (type != Passeio.class && type != Carga.class)
                throw new IllegalArgumentException();
            this.type = type;
            data = BDVeiculos.db().findAll(type);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            int cnt = Constants.VEIC_LABELS.size();
            if (type == Passeio.class)
                return cnt + Constants.PASSEIO_LABELS.size();
            else
                return cnt + Constants.CARGA_LABELS.size();
        }

        @Override
        public String getColumnName(int col) {
            if (col < Constants.VEIC_LABELS.size())
                return Constants.VEIC_LABELS.get(col);
            else if (type == Passeio.class)
                return Constants.PASSEIO_LABELS.get(col - Constants.VEIC_LABELS.size());
            else
                return Constants.CARGA_LABELS.get(col - Constants.VEIC_LABELS.size());
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var v = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> v.getPlaca();
                case 1 -> v.getMarca();
                case 2 -> v.getModelo();
                case 3 -> v.getCor();
                case 4 -> v.getVelocMax();
                case 5 -> v.getQtdRodas();
                case 6 -> v.getMotor().getQtdPist();
                case 7 -> v.getMotor().getPotencia();
                case 8 -> type == Passeio.class
                        ? ((Passeio) v).getQtdPassageiros()
                        : ((Carga) v).getCargaMax();
                case 9 -> ((Carga) v).getTara();
                default -> null;
            };
        }
        
        public List<? extends Veiculo> getData() {
            return data;
        }
    }
}
