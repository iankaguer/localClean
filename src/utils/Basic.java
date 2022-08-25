package utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Basic {
    public static final String APP_BACKGROUND_COLOR = "#EDEDED";
    public static final String APP_FOREGROUND_COLOR = "#3e3e3e";
    public static String SELECTED_CONTAINER = "dashboard";
    public static String BASE_URL = "https://dummyjson.com";

    public static JButton FlatButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.decode("#E3E3E3"));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

}
