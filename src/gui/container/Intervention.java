package gui.container;

import javax.swing.*;

public class Intervention extends JPanel {
    public Intervention() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Intervention"));
    }

}
