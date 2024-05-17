package gui.verifier;

import gui.Constants;

import javax.swing.*;

public class StringVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        if (text.length() > 0) {
            input.setBorder(Constants.DEFAULT_BORDER);
            return true;
        } else {
            input.setBorder(Constants.ERROR_BORDER);
            return false;
        }
    }
}
