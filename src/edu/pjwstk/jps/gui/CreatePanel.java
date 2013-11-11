package edu.pjwstk.jps.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
 
/** 
 * SimpleTableSelectionDemo is just like SimpleTableDemo, 
 * except that it detects selections, printing information
 * about the current selection to standard output.
 */
public class CreatePanel extends JPanel {
    
    private JLabel count;
    /**
     * 
     */
    private static final long serialVersionUID = -4225320954622494059L;
    GridBagConstraints c;
    public CreatePanel() {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.BOTH;
        count = new JLabel("Rows: ");
        add(count,c);
    }
    public void AddTableToPanel(Collection<?> resultSet) {
    
    JTable table = new JTable(new CreateTable(resultSet));
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);
    count.setText("Rows :" + table.getRowCount());

    //Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    //Add the scroll pane to this panel.
    c.gridx=0;
    c.gridy=1;
    c.weighty = 1.0;
    c.weightx = 1.0;
    c.fill = GridBagConstraints.BOTH;
    add(scrollPane, c);
    }
    public void AddTableToPanel(Object result) {
        
        JTable table = new JTable(new CreateTable(result));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        c.gridx=0;
        c.gridy=1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);
        }
}