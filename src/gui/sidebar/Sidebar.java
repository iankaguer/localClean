package gui.sidebar;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    public Sidebar() {
setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SidebarItem dashboardItem = new SidebarItem("Dashboard", "src/img/etude.png");
        SidebarItem UtilisateurItem = new SidebarItem("Utilisateurs", "src/img/etude.png");
        SidebarItem interventionItem = new SidebarItem("Intervention", "src/img/projet.png");
        SidebarItem settingItem = new SidebarItem("Réglages", "src/img/etude.png");

        //set wwidth 60px
        dashboardItem.setMaximumSize(new Dimension(60, 60));
        UtilisateurItem.setMaximumSize(new Dimension(60, 60));
        interventionItem.setMaximumSize(new Dimension(60, 60));
        settingItem.setMaximumSize(new Dimension(60, 60));
        setBackground(Color.WHITE);

        //set padding
        dashboardItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        UtilisateurItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        interventionItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        settingItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(dashboardItem);
        add(interventionItem);
        add(UtilisateurItem);
        add(settingItem);
        setVisible(true);
    }
}

