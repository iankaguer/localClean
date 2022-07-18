package gui.sidebar;

import javax.swing.*;

public class Sidebar extends JPanel {
    public Sidebar() {
setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SidebarItem dashboardItem = new SidebarItem("Dashboard", "src/img/etude.png");
        SidebarItem UtilisateurItem = new SidebarItem("Utilisateurs", "src/img/etude.png");
        SidebarItem interventionItem = new SidebarItem("Intervention", "src/img/projet.png");
        SidebarItem settingItem = new SidebarItem("Réglages", "src/img/etude.png");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(dashboardItem);
        add(interventionItem);
        add(UtilisateurItem);
        add(settingItem);
        setVisible(true);
    }
}

