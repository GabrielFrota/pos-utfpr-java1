package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;


public class Constants {
    public static final Border DEFAULT_BORDER = new JTextField().getBorder();
    public static final Border ERROR_BORDER = new LineBorder(Color.red, 2);
    public static final Dimension SQUARE = new Dimension(50, 50);
    public static final Color BLUE = new Color(3, 152, 252);
    public static final Color GREEN = new Color(25, 196, 2);
    public static final Color RED = new Color(252, 48, 48);
    public static final int ADD_OPT = 1;
    public static final int FIND_OPT = 2;
    public static final List<String> VEIC_LABELS = List.of("Placa", "Marca", "Modelo", "Cor",
            "Velocidade Máx", "Qtd. Rodas", "Qtd. Pistões", "Potência");
    public static final List<String> PASSEIO_LABELS = List.of("Qtd. Passageiros");
    public static final List<String> CARGA_LABELS = List.of("Carga Máx", "Tara");
}