package edu.pjwstk.jps.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
 
/** 
 * SimpleTableSelectionDemo is just like SimpleTableDemo, 
 * except that it detects selections, printing information
 * about the current selection to standard output.
 */
public class CreatePanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4225320954622494059L;
    public CreatePanel() {
        super(new GridLayout(1,0));
    }
    public void AddTableToPanel(Collection<?> resultSet) {
    
    JTable table = new JTable(new CreateTable(resultSet));
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);

    //Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    //Add the scroll pane to this panel.
    add(scrollPane);
    }
    public void AddTableToPanel(Object result) {
        
        JTable table = new JTable(new CreateTable(result));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        }
}