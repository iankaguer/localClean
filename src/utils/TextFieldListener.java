package utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.lang.reflect.Field;

public class TextFieldListener {
    private final JTextField textField;
    private final JLabel errorLabel;
    String valueToChange;




    public TextFieldListener(JTextField textField, JLabel errorLabel, String valueToChange) {
        this.textField = textField;
        this.errorLabel = errorLabel;
        this.valueToChange = valueToChange;
    }

    public void addListener() {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                //if textField is empty, set error message
                if (textField.getText().isEmpty()) {
                    errorLabel.setText("This "+ valueToChange +" is empty");
                    errorLabel.setForeground(Color.red);
                }
                //if textField is not empty, set error message to empty
                else {
                    errorLabel.setText("");

                    try {
                        //get variable from Basic class by name JtextFieldValue
                        //set variable value to textField.getText()
                        Field[] fields = Basic.class.getFields();
                        for (Field field : fields) {
                            if (field.getName().equals(valueToChange)) {
                                //set variable value to textField.getText()
                                field.set(this, textField.getText());
                            }
                        }


                        System.out.println("BASE_URL " +Basic.BASE_URL);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }
}
