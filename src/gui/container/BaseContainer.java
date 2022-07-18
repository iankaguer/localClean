package gui.container;

import gui.sidebar.Sidebar;

import javax.swing.*;
import java.awt.*;

public class BaseContainer extends JPanel {
    public BaseContainer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new Sidebar(),  BorderLayout.EAST);
        add(new MainContainer(), BorderLayout.CENTER);
    }
}
