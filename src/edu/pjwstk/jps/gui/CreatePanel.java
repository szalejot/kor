package edu.pjwstk.jps.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;

public class CreatePanel extends JPanel {

    private JLabel count;

    private static final long serialVersionUID = -4225320954622494059L;
    private GridBagConstraints c;

    public CreatePanel() {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        count = new JLabel("Loading... ");
        add(count, c);
    }

    // method to create table from collection results, accept any type of
    // objects in collection
    public void AddTableToPanel(Collection<?> resultSet) {
        // creating a jtable with results from resultSet
        JTable table = new JTable(new CreateTable(resultSet));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        // count number of rows in returned table and put it in JLabel
        count.setText("Rows :" + table.getRowCount());

        JScrollPane scrollPane = new JScrollPane(table);

        // make a panel with 1 kolumn
        c.gridx = 0;
        c.gridy = 1;
        // allow objects to grow
        c.weighty = 1.0;
        c.weightx = 1.0;
        // allow object to fill constraints in both height and width
        c.fill = GridBagConstraints.BOTH;
        // add scroll pane with jtable to panel
        add(scrollPane, c);
    }

    // method to create table from objects, single results like String, Integer
    // etc
    public void AddTableToPanel(Object result) {

        JTable table = new JTable(new CreateTable(result));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);
    }
}