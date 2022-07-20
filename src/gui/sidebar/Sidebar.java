package gui.sidebar;

import utils.Basic;
import utils.BorderRadius;
import utils.SelectedContainerObservable;
import utils.SelectedContainerObserver;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sidebar extends JPanel {
    static SelectedContainerObservable observable;
    public Sidebar(SelectedContainerObservable observablex) {
        SidebarItem dashboardItem = new SidebarItem("Dashboard", "src/img/etude.png");
        SidebarItem UtilisateurItem = new SidebarItem("Utilisateurs", "src/img/etude.png");
        SidebarItem interventionItem = new SidebarItem("Intervention", "src/img/etude.png");
        SidebarItem settingItem = new SidebarItem("Réglages", "src/img/etude.png");


        setBackground(Color.decode(Basic.APP_BACKGROUND_COLOR));

        //set padding
        dashboardItem.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        UtilisateurItem.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        interventionItem.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        settingItem.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setBorder(new BorderRadius(30));

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //set height 100% of window
        setMaximumSize(new Dimension(100, Integer.MAX_VALUE));

        //add on click listener to each item
        dashboardItem.addMouseListener(new SidebarItemListener("dashboard"));
        UtilisateurItem.addMouseListener(new SidebarItemListener("utilisateur"));
        interventionItem.addMouseListener(new SidebarItemListener("intervention"));
        settingItem.addMouseListener(new SidebarItemListener("setting"));

        observable = observablex;



        add(dashboardItem);
        add(interventionItem);
        add(UtilisateurItem);
        add(settingItem);
        setVisible(true);
    }

    public static class SidebarItemListener extends MouseAdapter {
        private final String sidebarItem;
        public SidebarItemListener(String selectedItem) {
            this.sidebarItem = selectedItem;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //SelectedContainerObservable s = new SelectedContainerObservable(sidebarItem);
            observable.setName(sidebarItem);
        }
    }
}

