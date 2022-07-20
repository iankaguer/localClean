package gui.container;


import utils.Basic;
import utils.SelectedContainerObservable;
import utils.SelectedContainerObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainContainer extends JPanel {
    public JPanel dashboard;
    public JPanel setting;
    public JPanel utilisateur;
    public JPanel intervention;
    public MainContainer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(Basic.APP_BACKGROUND_COLOR));
        this.dashboard = new Dashboard();
        this.utilisateur = new Utilisateur();
        this.intervention = new Intervention();
        this.setting = new Setting();

        dashboard.setVisible(true);
        utilisateur.setVisible(false);
        intervention.setVisible(false);
        setting.setVisible(false);



        add(this.dashboard);
        add(this.utilisateur);
        add(this.intervention);
        add(this.setting);

    }

}


