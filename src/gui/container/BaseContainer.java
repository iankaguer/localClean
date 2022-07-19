package gui.container;

import gui.sidebar.Sidebar;

import javax.swing.*;
import java.awt.*;

public class BaseContainer extends JPanel {
    public BaseContainer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Sidebar sidebar = new Sidebar();
        sidebar.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
        add(sidebar,  BorderLayout.EAST);
        add(new MainContainer(), BorderLayout.WEST);
    }
}
