package edu.pjwstk.jps.gui;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import edu.pjwstk.jps.DBReport;

 
public class MenuMain implements ActionListener {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    CreatePanel newPanel;
    JPanel contentPane;
    static JFrame frame;
    DBReport report;
    Integer i;
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu,menu2,menu3, submenu,submenu2,submenu3;
        JMenuItem menuItem,menuItem2,menuItem3;

        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("Company");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
 
        //a group of JMenuItems
        menuItem = new JMenuItem("AddItem",
                                 KeyEvent.VK_T);
        menuItem.setName("CompanyAddItem");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);       
 
        //a submenu
        menu.addSeparator();
        submenu = new JMenu("Reports");
        submenu.setMnemonic(KeyEvent.VK_S);
 
        menuItem = new JMenuItem("Oddziały firmy których dyrektor pracuje dłużej niż x lat");
        menuItem.setName("report4");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);
 
        menuItem = new JMenuItem("Liczba pracowników w poszczególnych oddziałach firmy");
        menuItem.setName("report6");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);
        
        menu2 = new JMenu("Persons");
        menu2.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu2);
 
        //a group of JMenuItems
        menuItem2 = new JMenuItem("AddItem",
                                 KeyEvent.VK_T);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem2.addActionListener(this);
        menu2.add(menuItem2);       
 
        //a submenu
        menu2.addSeparator();
        submenu2 = new JMenu("Reports");
        submenu2.setMnemonic(KeyEvent.VK_S);
 
        menuItem2 = new JMenuItem("Lista pracowników na stanowiskach zarządczych");
        menuItem2.setName("report8");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem2.addActionListener(this);
        submenu2.add(menuItem2);
 
        menuItem2 = new JMenuItem("Wylistuj pracowników którzy nie mieli awansu od x lat.");
        menuItem2.setName("report7");
        menuItem2.addActionListener(this);
        submenu2.add(menuItem2);
        menu2.add(submenu2);
        
        menu3 = new JMenu("Cars");
        menu3.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu3);
 
        //a group of JMenuItems
        menuItem3 = new JMenuItem("AddItem",
                                 KeyEvent.VK_T);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem3.addActionListener(this);
        menu3.add(menuItem3);       
 
        //a submenu
        menu3.addSeparator();
        submenu3 = new JMenu("Reports");
        submenu3.setMnemonic(KeyEvent.VK_S);
 
        menuItem3 = new JMenuItem("Lista samochodów starszych niż x lat w poszczególnych oddziałach firmy");
        menuItem3.setName("report10");
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem3.addActionListener(this);
        submenu3.add(menuItem3);
 
        menuItem3 = new JMenuItem("Report2");
        menuItem3.addActionListener(this);
        submenu3.add(menuItem3);
        menu3.add(submenu3);
        
        return menuBar;
    }
 
    public Container createContentPane() {
        //Create the content-pane-to-be.
        contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        
        switch (source.getName()){
        case "report4":
            newPanel = new CreatePanel();
            report = new DBReport();
            i = getUserInput();
            report.init();

            try {
                Collection<?> resultSet = report.report4(i);
                newPanel.AddTableToPanel(resultSet);
            } 
            finally {
                report.close();
            }
            frame.setContentPane(newPanel);
            frame.revalidate();
            frame.repaint();
            break;
        case "report6":
            newPanel = new CreatePanel();
            report = new DBReport();
            report.init();
            try {
                Collection<?> resultSet = report.report6();
                newPanel.AddTableToPanel(resultSet);
            } 
            finally {
                report.close();
            }
            frame.setContentPane(newPanel);
            frame.revalidate();
            frame.repaint();
            break;
        case "report7":
            newPanel = new CreatePanel();
            report = new DBReport();
            i = getUserInput();
            report.init();

            try {
                Collection<?> resultSet = report.report7(i);
                newPanel.AddTableToPanel(resultSet);
            } 
            finally {
                report.close();
            }
            frame.setContentPane(newPanel);
            frame.revalidate();
            frame.repaint();
            break;
        case "report8":
            newPanel = new CreatePanel();
            report = new DBReport();
            report.init();
            try {
                Collection<?> resultSet = report.report8();
                newPanel.AddTableToPanel(resultSet);
            } 
            finally {
                report.close();
            }
            frame.setContentPane(newPanel);
            frame.revalidate();
            frame.repaint();
            break;
        case "report10":
            newPanel = new CreatePanel();
            report = new DBReport();
            i = getUserInput();
            report.init();
            try {
                Collection<?> resultSet = report.report10(10);
                newPanel.AddTableToPanel(resultSet);
            } 
            finally {
                report.close();
            }
            frame.setContentPane(newPanel);
            frame.revalidate();
            frame.repaint();
            break;
    }
    }
 
    public int getUserInput()
    {
        Boolean number=false;
        Integer i = 0;
        while(!number){
        String inputParameter = JOptionPane.showInputDialog(frame, "Input number of years:", 0);
        try
        {
            i = Integer.parseInt(inputParameter);
            number = true;
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(frame, "It is not a number you provided, pls try again");
            number = false;
        }
        }
        return i;
    }
    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        MenuMain demo = new MenuMain();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
