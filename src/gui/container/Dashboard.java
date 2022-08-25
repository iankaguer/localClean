package gui.container;

import gui.sidebar.CardItem;
import model.Intervention;
import utils.Basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Dashboard  extends JPanel {
    public Dashboard(ArrayList<Intervention> listIntervention) {
       // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        int interventionTraite = 0, interventionEnAttente = 0, userCount = listIntervention.size();
        for (Intervention intervention : listIntervention) {
            if (intervention.getStatus().equals("")) {
                interventionEnAttente++;
            }
            //check if intervention date !== ""
            if (!intervention.getDate().equals("")) {
                interventionTraite++;
            }
        }

        CardItem cardIntervention = new CardItem("intervention(s) effectuée(s)", String.valueOf(interventionTraite), String.valueOf(listIntervention.size()));
        CardItem cardInterventionEnAttente = new CardItem("interventions en attente de validation", String.valueOf(interventionEnAttente), String.valueOf(listIntervention.size()));
        CardItem cardUtilisateur = new CardItem("Utilisateurs", String.valueOf(userCount), String.valueOf(listIntervention.size()));

        cardIntervention.setLayout(new BoxLayout(cardIntervention, BoxLayout.Y_AXIS));
        cardInterventionEnAttente.setLayout(new BoxLayout(cardInterventionEnAttente, BoxLayout.Y_AXIS));
        cardUtilisateur.setLayout(new BoxLayout(cardUtilisateur, BoxLayout.Y_AXIS));



        String[] colNames = new String[]{
                "Nom", "Adresse", "Telephone", "Email", "Latitude", "Longitude"
        };
        String[][] data = new String[listIntervention.size()][colNames.length];
        for (int i = 0; i < listIntervention.size(); i++) {
            data[i][0] = listIntervention.get(i).getName();
            data[i][1] = listIntervention.get(i).getAddress();
            data[i][2] = listIntervention.get(i).getPhone();
            data[i][3] = listIntervention.get(i).getEmail();
            data[i][4] = String.valueOf(listIntervention.get(i).getLatitude());
            data[i][5] = String.valueOf(listIntervention.get(i).getLongitude());

        }


        JTable tableIntervention = new JTable(data,colNames);
        JTable tableUtilisateurs = new JTable(data,colNames);

        final JScrollPane scrollPaneIntervention = new JScrollPane(tableIntervention);
        final JScrollPane scrollPaneUtilisateur = new JScrollPane(tableUtilisateurs);
        scrollPaneIntervention.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneUtilisateur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        final JButton nextIntervention = Basic.FlatButton("Suivant >>");
        final JButton prevIntervention = Basic.FlatButton("<< Précédent");
        final JButton nextUtilisateur = Basic.FlatButton("Suivant >>");
        final JButton prevUtilisateur = Basic.FlatButton("<< Précédent");

        ActionListener alIntervention = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Rectangle rect = scrollPaneIntervention.getVisibleRect();
                JScrollBar  bar = scrollPaneIntervention.getVerticalScrollBar();
                int blockIncr = scrollPaneIntervention.getViewport().getViewRect().height;
                if (e.getSource() == nextIntervention) {
                    bar.setValue(bar.getValue() + blockIncr);
                } else if (e.getSource() == prevIntervention) {
                    bar.setValue(bar.getValue() - blockIncr);
                }
                scrollPaneIntervention.scrollRectToVisible(rect);
            }
        };

        nextIntervention.addActionListener(alIntervention);
        prevIntervention.addActionListener(alIntervention);

        ActionListener alUtilisateur = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Rectangle rect = scrollPaneUtilisateur.getVisibleRect();
                JScrollBar  bar = scrollPaneUtilisateur.getVerticalScrollBar();
                int blockIncr = scrollPaneUtilisateur.getViewport().getViewRect().height;
                if (e.getSource() == nextUtilisateur) {
                    bar.setValue(bar.getValue() + blockIncr);
                } else if (e.getSource() == prevUtilisateur) {
                    bar.setValue(bar.getValue() - blockIncr);
                }
                scrollPaneUtilisateur.scrollRectToVisible(rect);
            }
        };
        nextUtilisateur.addActionListener(alUtilisateur);
        prevUtilisateur.addActionListener(alUtilisateur);

        JPanel panelUtilisateur = new JPanel(new BorderLayout());
        JPanel buttonPanelUtilisateur = new JPanel();
        buttonPanelUtilisateur.add(prevUtilisateur);
        buttonPanelUtilisateur.add(nextUtilisateur);
        panelUtilisateur.add(buttonPanelUtilisateur, BorderLayout.CENTER);
        panelUtilisateur.add(scrollPaneUtilisateur, BorderLayout.NORTH);

        JPanel panelIntervention = new JPanel(new BorderLayout());
        JPanel buttonPanelIntervention = new JPanel();
        buttonPanelIntervention.add(prevIntervention);
        buttonPanelIntervention.add(nextIntervention);

        panelIntervention.add(buttonPanelIntervention, BorderLayout.CENTER);
        panelIntervention.add(scrollPaneIntervention, BorderLayout.NORTH);






        JPanel cardBottom = new JPanel();
        cardBottom.setLayout(new GridLayout(1,2));
        cardBottom.add(panelIntervention);
        cardBottom.add(panelUtilisateur);
        //set margin
        scrollPaneIntervention.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPaneUtilisateur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPaneIntervention.setBackground(Color.WHITE);
        scrollPaneUtilisateur.setBackground(Color.WHITE);

        cardBottom.setVisible(true);

        cardBottom.setSize(new Dimension(getWidth(), 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(cardIntervention, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(cardInterventionEnAttente, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(cardUtilisateur, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 3;
        gbc.weighty = 7;

        add(cardBottom, gbc);


    }

}
