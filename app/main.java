package app;

import javax.swing.SwingUtilities;
import view.TelaLogin;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
