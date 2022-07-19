package gui.sidebar;

import utils.Basic;

import javax.swing.*;
import java.awt.*;

public class SidebarItem extends JPanel {
    private String name;
    public SidebarItem(String name, String iconPath) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       JLabel label = new JLabel(name);

        ImageIcon imageIcon = new ImageIcon(iconPath); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

         JLabel icon = new JLabel(imageIcon);
         label.setForeground(Color.decode(Basic.APP_FOREGROUND_COLOR));


         label.setHorizontalAlignment(JLabel.CENTER);
         icon.setHorizontalAlignment(JLabel.CENTER);
        label.setVisible(true);
        icon.setVisible(true);

        //set padding
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        add(icon);
        add(label);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        //set width wrap_content
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        //set border line
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);

    }

}
