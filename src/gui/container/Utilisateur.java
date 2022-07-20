package gui.container;

import javax.swing.*;

public class Utilisateur extends JPanel {
    public Utilisateur() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Utilisateur"));
    }


}
