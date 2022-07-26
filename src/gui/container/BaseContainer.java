package gui.container;

import gui.sidebar.Sidebar;
import model.InterventionAdapter;
import utils.SelectedContainerObservable;
import utils.SelectedContainerObserver;

import javax.swing.*;
import java.awt.*;

public class BaseContainer extends JPanel {
    public BaseContainer() {
        MainContainer mainContainer = new MainContainer();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.X_AXIS));

        mainContainer.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        SelectedContainerObservable observable = new SelectedContainerObservable("dashboard");
        SelectedContainerObserver nameObs = new SelectedContainerObserver(mainContainer);
        observable.addObserver(nameObs);



        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Sidebar sidebar = new Sidebar(observable);
        sidebar.setSize(new Dimension(200, Integer.MAX_VALUE));

        mainContainer.setSize(new Dimension((Integer.MAX_VALUE - 200), Integer.MAX_VALUE));
        //sidebar.setBackground(Color.WHITE);

        add(sidebar);

        add(mainContainer);

        setBackground(Color.WHITE);
    }
}


