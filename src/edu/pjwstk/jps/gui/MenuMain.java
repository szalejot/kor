package edu.pjwstk.jps.gui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import edu.pjwstk.jps.DBReport;

public class MenuMain implements ActionListener {
    public JScrollPane scrollPane;
    public CreatePanel newPanel;
    public JPanel contentPane;
    static JFrame frame;
    private DBReport report;
    private Integer i;
    private Method[] metody;
    private JMenuItem menuItem;

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, menu2, menu3, submenu, submenu2, submenu3;

        // create the menu bar
        menuBar = new JMenuBar();

        // create a company menu
        menu = new JMenu("Company");
        menu.setMnemonic(KeyEvent.VK_A);
        // adding company menu to menu bar
        menuBar.add(menu);

        createReportButton("Add Company", "addCompany", menu);

        menu.addSeparator();
        // a submenu of reports for company
        submenu = new JMenu("Reports");
        submenu.setMnemonic(KeyEvent.VK_S);
        // list of company reports
        createReportButton("Oddziały firmy których dyrektor pracuje dłużej niż x lat", "report4", submenu);
        createReportButton("Liczba pracowników w poszczególnych oddziałach firmy", "report6", submenu);

        // adding submenu of reports to company menu
        menu.add(submenu);

        // creating menu of Persons
        menu2 = new JMenu("Persons");
        menu2.setMnemonic(KeyEvent.VK_A);
        // adding menu with persons to menu bar
        menuBar.add(menu2);

        createReportButton("AddItem", "addDepartment", menu2);

        menu2.addSeparator();
        // a submenu of reports for persons
        submenu2 = new JMenu("Reports");
        submenu2.setMnemonic(KeyEvent.VK_S);
        // a list of reports to persons
        createReportButton("Lista pracowników na stanowiskach zarządczych", "report8", submenu2);
        createReportButton("Wylistuj pracowników którzy nie mieli awansu od x lat.", "report7", submenu2);

        // adding submenu with persons reports to persons menu
        menu2.add(submenu2);

        // creating menu with cars
        menu3 = new JMenu("Cars");
        menu3.setMnemonic(KeyEvent.VK_A);
        // adding menu with cars to menu bar
        menuBar.add(menu3);

        createReportButton("AddItem", "addCar", menu3);

        menu3.addSeparator();
        // a submenu of cars with reports
        submenu3 = new JMenu("Reports");
        submenu3.setMnemonic(KeyEvent.VK_S);
        // a list of reports to cars
        createReportButton("Lista samochodów starszych niż x lat w poszczególnych oddziałach firmy", "report10",
                submenu3);

        // adding submenu of car reports to menu of cars
        menu3.add(submenu3);

        return menuBar;
    }

    private void createReportButton(String text, String bName, JMenu menu) {
        menuItem = new JMenuItem(text);
        menuItem.setName(bName);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());

        switch (source.getName()) {
        case "report4":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 4, true);
            repaintFrame(newPanel);
            break;
        case "report6":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 6, false);
            repaintFrame(newPanel);
            break;
        case "report7":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 7, true);
            repaintFrame(newPanel);
            break;
        case "report8":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 8, false);
            repaintFrame(newPanel);
            break;
        case "report10":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 10, true);
            repaintFrame(newPanel);
            break;
        }
    }

    private void doReport(CreatePanel newPanel, Integer reportNr, Boolean isParameter) {
        report = new DBReport();
        metody = report.getClass().getMethods();
        if (isParameter) {
            for (int x = 0; x < metody.length; x++) {
                if ((metody[x].getName().equals("report" + reportNr))) {
                    i = getUserInput();
                    report.init();
                    try {
                        Collection<?> resultSet = (Collection<?>) metody[x].invoke(report, i);
                        newPanel.AddTableToPanel(resultSet);
                    } catch (IllegalAccessException e) {
                        newPanel.AddTableToPanel("Niestety program probowal siegnac tam gdzie nie powinien!");
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        newPanel.AddTableToPanel("Niestety program dostal niedozwolone argumenty!");
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        newPanel.AddTableToPanel("Niestety program przestal dzialac bo zapytanie SBQLa sie wywalilo!");
                        e.printStackTrace();
                    } finally {
                        report.close();
                    }
                }
            }
        } else {
            for (int x = 0; x < metody.length; x++) {
                if ((metody[x].getName().equals("report" + reportNr))) {
                    report.init();
                    try {
                        Collection<?> resultSet = (Collection<?>) metody[x].invoke(report);
                        newPanel.AddTableToPanel(resultSet);
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        report.close();
                    }
                }
            }
        }
    }

    private void repaintFrame(JPanel newPanel) {
        frame.setContentPane(newPanel);
        frame.revalidate();
        frame.repaint();
    }

    //need to change this method becouse canceling input also shows it is not a number you ....
    public int getUserInput() {
        Boolean number = false;
        Integer i = 0;
        while (!number) {
            String inputParameter = JOptionPane.showInputDialog(frame, "Input number of years:", 0);
            try {
                i = Integer.parseInt(inputParameter);
                number = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "It is not a number you provided, pls try again");
                number = false;
            }
        }
        return i;
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        frame = new JFrame("SBQL Reports for KOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);

        // Create and set up the content pane.
        MenuMain mainMenu = new MenuMain();
        frame.setJMenuBar(mainMenu.createMenuBar());
        // frame.setContentPane(demo.createContentPane());

        // Display the window.
        frame.setSize(1000, 700);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
