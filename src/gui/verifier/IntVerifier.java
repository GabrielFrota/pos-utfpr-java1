package gui.verifier;

import gui.Constants;

import javax.swing.*;

public class IntVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText().trim();
        try {
            var i = Integer.parseInt(text);
            input.setBorder(Constants.DEFAULT_BORDER);
            return true;
        } catch (NumberFormatException e) {
            input.setBorder(Constants.ERROR_BORDER);
            return false;
        }
    }
}
