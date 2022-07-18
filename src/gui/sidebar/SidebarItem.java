package gui.sidebar;

import javax.swing.*;

public class SidebarItem extends JPanel {
    private String name;
    public SidebarItem(String name, String iconPath) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       JLabel label = new JLabel(name);
         JLabel icon = new JLabel(new ImageIcon(iconPath));
         label.setHorizontalAlignment(JLabel.CENTER);
            icon.setHorizontalAlignment(JLabel.CENTER);
            label.setVisible(true);
            icon.setVisible(true);

        add(icon);
        add(label);
        setVisible(true);
    }

}
