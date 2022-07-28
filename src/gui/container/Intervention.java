package gui.container;

import model.InterventionAdapter;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class Intervention extends JPanel {
    model.Intervention selectedIntervention;
    JLabel labelNom, labelEmail, labelTelephone, labelAdresse, imageLabel;
    JXMapKit jXMapKit;
    GeoPosition geoPosition;
    JToolTip tooltip ;
    public Intervention() {
        labelNom = new JLabel("Nom : ");
        labelEmail = new JLabel("Email : ");
        labelTelephone = new JLabel("Telephone : ");
        labelAdresse = new JLabel("Adresse : ");
        imageLabel = new JLabel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InterventionAdapter adapter = new InterventionAdapter();
        ArrayList<model.Intervention> list = adapter.listInterventions();

        selectedIntervention = list.get(0);


        String[] colNames = new String[]{
                "Identifiant","Nom", "Adresse", "Telephone", "Email", "Latitude", "Longitude"
        };
        String[][] data = new String[list.size()][colNames.length];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = String.valueOf(list.get(i).getId());
            data[i][1] = list.get(i).getName();
            data[i][2] = list.get(i).getAddress();
            data[i][3] = list.get(i).getPhone();
            data[i][4] = list.get(i).getEmail();
            data[i][5] = String.valueOf(list.get(i).getLatitude());
            data[i][6] = String.valueOf(list.get(i).getLongitude());

        }

        JTable table = new JTable(data,colNames);

        final JScrollPane scrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                //System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                for (model.Intervention intervention : list) {
                    if (intervention.getId() == Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())) {
                        selectedIntervention = intervention;
                        renderFormComponent();
                    }
                }
            }
        });

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


        JPanel panelNom = new JPanel(new BorderLayout());
        JPanel panelAdresse = new JPanel(new BorderLayout());
        JPanel panelTelephone = new JPanel(new BorderLayout());
        JPanel panelEmail = new JPanel(new BorderLayout());
        JPanel panelMap = new JPanel(new BorderLayout());
        JPanel panelImage = new JPanel(new BorderLayout());

        jXMapKit = new JXMapKit();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapKit.setTileFactory(tileFactory);
        tooltip = new JToolTip();
        setMapData();
        jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                // ignore
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                JXMapViewer map = jXMapKit.getMainMap();
                // convert to world bitmap
                Point2D worldPos = map.getTileFactory().geoToPixel(geoPosition, map.getZoom());
                // convert to screen
                Rectangle rect = map.getViewportBounds();
                int sx = (int) worldPos.getX() - rect.x;
                int sy = (int) worldPos.getY() - rect.y;
                Point screenPos = new Point(sx, sy);
                // check if near the mouse
                if (screenPos.distance(e.getPoint()) < 20) {
                    screenPos.x -= tooltip.getWidth() / 2;
                    tooltip.setLocation(screenPos);
                    tooltip.setVisible(true);
                } else {
                    tooltip.setVisible(false);
                }
            }
        });

        panelNom.add(labelNom, BorderLayout.WEST);
        panelAdresse.add(labelAdresse, BorderLayout.WEST);
        panelTelephone.add(labelTelephone, BorderLayout.WEST);
        panelEmail.add(labelEmail, BorderLayout.WEST);
        panelMap.add(jXMapKit, BorderLayout.CENTER);
        panelImage.add(imageLabel, BorderLayout.CENTER);


        JPanel selectedPanel = new JPanel();
        selectedPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        selectedPanel.add(panelNom, c);
        c.gridx = 1;
        selectedPanel.add(panelAdresse, c);
        c.gridx = 0;
        c.gridy = 1;
        selectedPanel.add(panelTelephone, c);
        c.gridy = 1;
        selectedPanel.add(panelEmail, c);
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 100;
        selectedPanel.add(panelMap, c);
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 1;
        selectedPanel.add(panelImage, c);




        panel.add(selectedPanel);
        add(panel);
        renderFormComponent();


    }

    public void renderFormComponent() {
        labelAdresse.setText("Adresse : " + selectedIntervention.getAddress());
        labelEmail.setText("Email : " + selectedIntervention.getEmail());
        labelNom.setText("Nom : " + selectedIntervention.getName());
        labelTelephone.setText("Telephone : " + selectedIntervention.getPhone());
        setMapData();
        setInterventionPhoto();
    }

    public void setMapData() {
        geoPosition = new GeoPosition(selectedIntervention.getLatitude(), selectedIntervention.getLongitude());
        jXMapKit.getMainMap().setCenterPosition(geoPosition);
        jXMapKit.getMainMap().setZoom(15);
        jXMapKit.getMainMap().setAddressLocation(geoPosition);
        jXMapKit.getMainMap().setToolTipText(selectedIntervention.getName());
        tooltip.setTipText(selectedIntervention.getName());
        jXMapKit.getMainMap().add(tooltip);
    }

    public void setInterventionPhoto(){
        try {
            URL url = new URL(selectedIntervention.getImage());
            Image image = ImageIO.read(url);
            imageLabel.setIcon(new ImageIcon(image));
        }
        catch (IOException e) {
        }
    }

}
