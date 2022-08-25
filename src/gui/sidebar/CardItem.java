package gui.sidebar;

import javax.swing.*;
import java.awt.*;

public class CardItem extends JPanel {

    public CardItem(String name, String value, String total) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(new Dimension(350, 200));
        add(new JLabel(name));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 50));


        JLabel totalLabel = new JLabel("/"+total);
       JPanel valuePanel = new JPanel();
       //set background color to transparent
        valuePanel.setOpaque(false);

        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.LINE_AXIS));
        valuePanel.add(valueLabel);
        valuePanel.add(totalLabel);
        add(valuePanel);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
    }


}
