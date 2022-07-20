package utils;

import java.util.Observable;

public class SelectedContainerObservable   extends Observable {
    public SelectedContainerObservable(String name) {
        Basic.SELECTED_CONTAINER = name;
    }
    public String getName() {
        return Basic.SELECTED_CONTAINER ;
    }
    public void setName(String name) {
        Basic.SELECTED_CONTAINER  = name;
        setChanged();
        notifyObservers(name);
    }
}