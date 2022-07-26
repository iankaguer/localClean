package gui.container;

import model.InterventionAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Intervention extends JPanel {
    public Intervention() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Intervention"));

        InterventionAdapter adapter = new InterventionAdapter();
        ArrayList<model.Intervention> list = adapter.listInterventions();


        String[] colNames = new String[]{
                "Nom", "Adresse", "Telephone", "Email", "Latitude", "Longitude"
        };
        String[][] data = new String[list.size()][colNames.length];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getAddress();
            data[i][2] = list.get(i).getPhone();
            data[i][3] = list.get(i).getEmail();
            data[i][4] = String.valueOf(list.get(i).getLatitude());
            data[i][5] = String.valueOf(list.get(i).getLongitude());

        }

        JTable table = new JTable(data,colNames);

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        final JButton next = new JButton("next");
        final JButton prev = new JButton("prev");

        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Rectangle rect = scrollPane.getVisibleRect();
                JScrollBar  bar = scrollPane.getVerticalScrollBar();
                int blockIncr = scrollPane.getViewport().getViewRect().height;
                if (e.getSource() == next) {
                    bar.setValue(bar.getValue() + blockIncr);
                } else if (e.getSource() == prev) {
                    bar.setValue(bar.getValue() - blockIncr);
                }
                scrollPane.scrollRectToVisible(rect);
            }
        };

        next.addActionListener(al);
        prev.addActionListener(al);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prev);
        buttonPanel.add(next);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.NORTH);
        add(panel);

    }



}
