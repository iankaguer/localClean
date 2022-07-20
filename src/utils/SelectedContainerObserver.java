package utils;

import gui.container.MainContainer;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class SelectedContainerObserver implements Observer {
    MainContainer mContainer;
    public SelectedContainerObserver(MainContainer mainContainer) {
        this.mContainer = mainContainer;
    }
    public void update(Observable obj, Object arg) {
        if (arg instanceof String) {
            Basic.SELECTED_CONTAINER = (String) arg;
            System.out.println("NameObserver: Some other change to subject! " + Basic.SELECTED_CONTAINER);
        } else {
            System.out.println("NameObserver: Some other change to subject!");
        }

        if (Objects.equals(Basic.SELECTED_CONTAINER, "dashboard")) {
            mContainer.dashboard.setVisible(true);
            mContainer.utilisateur.setVisible(false);
            mContainer.intervention.setVisible(false);
            mContainer.setting.setVisible(false);
        } else if (Objects.equals(Basic.SELECTED_CONTAINER, "utilisateur")) {
            mContainer.dashboard.setVisible(false);
            mContainer.utilisateur.setVisible(true);
            mContainer.intervention.setVisible(false);
            mContainer.setting.setVisible(false);
        } else if (Objects.equals(Basic.SELECTED_CONTAINER, "intervention")) {
            mContainer.dashboard.setVisible(false);
            mContainer.utilisateur.setVisible(false);
            mContainer.intervention.setVisible(true);
            mContainer.setting.setVisible(false);
        } else if (Objects.equals(Basic.SELECTED_CONTAINER, "setting")) {
            mContainer.dashboard.setVisible(false);
            mContainer.utilisateur.setVisible(false);
            mContainer.intervention.setVisible(false);
            mContainer.setting.setVisible(true);
        }
    }
}
