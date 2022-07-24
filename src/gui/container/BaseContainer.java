package gui.container;

import gui.sidebar.Sidebar;
import utils.SelectedContainerObservable;
import utils.SelectedContainerObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BaseContainer extends JPanel {
    public BaseContainer() {
        MainContainer mainContainer = new MainContainer();
        SelectedContainerObservable observable = new SelectedContainerObservable("dashboard");
        SelectedContainerObserver nameObs = new SelectedContainerObserver(mainContainer);
        observable.addObserver(nameObs);



        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Sidebar sidebar = new Sidebar(observable);
        sidebar.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
       // sidebar.setBackground(Color.WHITE);
        add(sidebar,  BorderLayout.EAST);
        setBackground(Color.WHITE);
        add(mainContainer, BorderLayout.CENTER);
    }
}


