package gui.container;

import utils.Basic;

import javax.swing.*;
import java.awt.*;

public class Dashboard  extends JPanel {
    public Dashboard() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Dashboard"));
    }

}
