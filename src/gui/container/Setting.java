package gui.container;

import utils.Basic;
import utils.TextFieldListener;

import javax.swing.*;
import java.awt.*;

public class Setting extends JPanel {
    public Setting() {
        setLayout(new GridLayout(32, 3));
        //add base url input
        JPanel baseUrlPanel = new JPanel(new BorderLayout());
        JTextField baseUrlTextField = new JTextField();



        baseUrlPanel.add(new JLabel("Base URL"), BorderLayout.WEST);
        baseUrlPanel.add(baseUrlTextField, BorderLayout.CENTER);
        JLabel baseUrlTextFieldError = new JLabel("");

        TextFieldListener baseUrlTextFieldListener = new TextFieldListener(baseUrlTextField, baseUrlTextFieldError, "BASE_URL");
        baseUrlTextFieldListener.addListener();
        baseUrlPanel.add(baseUrlTextFieldError, BorderLayout.EAST);

        add(baseUrlPanel);


    }

}
