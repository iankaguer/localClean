package gui.container;


import model.InterventionAdapter;
import utils.Basic;
import utils.SelectedContainerObservable;
import utils.SelectedContainerObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainContainer extends JPanel {
    public JPanel dashboard;
    public JPanel setting;
    public JPanel utilisateur;
    public JPanel intervention;
    public MainContainer() {
        InterventionAdapter interventionAdapter = new InterventionAdapter();
        ArrayList<model.Intervention> listIntervention = interventionAdapter.listInterventions();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(Basic.APP_BACKGROUND_COLOR));
        this.dashboard = new Dashboard(listIntervention);
        this.utilisateur = new Utilisateur(listIntervention);

        this.intervention = new Intervention(listIntervention);
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


