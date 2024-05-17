package gui.verifier;

import gui.Constants;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class FloatVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText().trim();
        var df = new DecimalFormat();
        var pos = new ParsePosition(0);
        df.parse(text, pos);
        if (pos.getErrorIndex() != -1 || pos.getIndex() < text.length()) {
            input.setBorder(Constants.ERROR_BORDER);
            return false;
        }
        input.setBorder(Constants.DEFAULT_BORDER);
        return true;
    }
}
