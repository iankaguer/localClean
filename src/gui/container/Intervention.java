package gui.container;

import model.InterventionAdapter;
import org.jdesktop.swingx.JXDatePicker;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
                "Identifiant","Nom", "Adresse", "Telephone", "Email", "Latitude", "Longitude", "Date"
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
            data[i][7] = String.valueOf(list.get(i).getDate());

        }

        JTable table = new JTable(data,colNames);
        table.setAutoCreateRowSorter(true);

        final JScrollPane scrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(event -> {
            //System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            for (model.Intervention intervention : list) {
                if (intervention.getId() == Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())) {
                    selectedIntervention = intervention;
                    renderFormComponent();
                }
            }
        });

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        Dimension d = table.getPreferredSize();
        scrollPane.setPreferredSize(
                new Dimension(d.width,table.getRowHeight()*13));
        final JButton next = new JButton("Suivant >>");
        final JButton prev = new JButton("<< Précédent");

        ActionListener al = e -> {
            Rectangle rect = scrollPane.getVisibleRect();
            JScrollBar  bar = scrollPane.getVerticalScrollBar();
            int blockIncr = scrollPane.getViewport().getViewRect().height;
            if (e.getSource() == next) {
                bar.setValue(bar.getValue() + blockIncr);
            } else if (e.getSource() == prev) {
                bar.setValue(bar.getValue() - blockIncr);
            }
            scrollPane.scrollRectToVisible(rect);
        };

        next.addActionListener(al);
        prev.addActionListener(al);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prev);
        buttonPanel.add(next);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);


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
        //set height of jXMapKit
        jXMapKit.setSize(new Dimension(300, 200));
        tooltip = new JToolTip();
        setMapData();
        jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                //ignore
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

        //create a input type date and add it to the panel
        JPanel panelDate = new JPanel(new BorderLayout());
        JLabel labelDate = new JLabel("Date : ");

        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());

        panelDate.add(labelDate, BorderLayout.WEST);
        panelDate.add(picker, BorderLayout.CENTER);
        panelDate.add(timeSpinner, BorderLayout.EAST);

        JPanel selectedPanel = new JPanel();
        selectedPanel.setLayout(new GridLayout(1, 3));

        JPanel selectedInfoPanel = new JPanel();
        selectedInfoPanel.setLayout(new GridLayout(16, 1));
        selectedInfoPanel.add(panelNom);
        selectedInfoPanel.add(panelAdresse);
        selectedInfoPanel.add(panelTelephone);
        selectedInfoPanel.add(panelEmail);
        selectedInfoPanel.add(panelDate);
        //create button save and add it to the panel
        JPanel panelButton = new JPanel();
        JButton buttonSave = new JButton("Enregistrer");
        buttonSave.addActionListener(e -> {
            Calendar cals = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            formater.format(picker.getDate());
            String date = formater.format(cals.getTime());
            //get time in format HH:mm:ss from timeSpinner
            SimpleDateFormat formater2 = new SimpleDateFormat("HH:mm:ss");
            formater2.format(timeSpinner.getValue());
            String time = formater2.format(cals.getTime());



            //System.out.println(formater.format(cals.getTime()));
            selectedIntervention.setDate(date + " " + time);
            //refresh table
            if (table.getSelectedRow() == -1) {
                table.setRowSelectionInterval(0, 0);
            }
            table.setValueAt(selectedIntervention.getDate(), table.getSelectedRow(), 7);
            //System.out.println(table.getSelectedRow());
        });
        panelButton.add(buttonSave);
        selectedInfoPanel.add(panelButton);


        selectedPanel.add(selectedInfoPanel);
        selectedPanel.add(panelImage);
        selectedPanel.add(panelMap);








        panel.add(selectedPanel, BorderLayout.SOUTH);
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
        jXMapKit.getMainMap().setZoom(6);
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
            System.out.println("Error: " + e.getMessage());
        }
    }

}
