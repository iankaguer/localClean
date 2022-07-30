import gui.container.BaseContainer;

import javax.swing.*;
import java.awt.*;

public class App {


    public static void main(String [] args){
        SwingUtilities.invokeLater(() -> {
            BaseContainer bc = new BaseContainer();
            //fenetre.setVisible(true);
            JFrame fenetre = new JFrame("LocalClean");
            bc.setLayout(new BoxLayout(bc, BoxLayout.X_AXIS));
            fenetre.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
            fenetre.setBackground(Color.WHITE);
            fenetre.setVisible(true);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setContentPane(bc);

        });
    }


}
