import gui.container.BaseContainer;
import utils.Basic;

import javax.swing.*;
import java.awt.*;

public class App {


    public static void main(String [] args){
        SwingUtilities.invokeLater(() -> {
            BaseContainer bc = new BaseContainer();
            //fenetre.setVisible(true);
            JFrame fenetre = new JFrame("LocalClean");
            fenetre.setSize(1280, 1000);
            fenetre.setBackground(Color.WHITE);
            fenetre.setVisible(true);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setContentPane(bc);

        });
    }


}
