package edu.pjwstk.jps.gui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import edu.pjwstk.jps.DBReport;

public class MenuMain implements ActionListener {
    public JScrollPane scrollPane;
    public CreatePanel newPanel;
    public CreateInputPanel newInputPanel;
    public JPanel contentPane;
    static JFrame frame;
    private DBReport report;
    private Object obj;
    private Object[] objArr;
    private Method[] metody;
    private JMenuItem menuItem;
    private UserInput userInput;

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, menu2, menu3, menu4, submenu, submenu2, submenu3, submenu4;

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
        createReportButton("Lista oddziałów w których pracują kobiety młodsze niż x lat i ich liczba.", "report11", submenu);
        createReportButton("Oddziały firmy, w których żaden pracownik nie ma szkolenia x.", "report1", submenu);
        createReportButton("Lista zaplanowanych wypozyczen samochodów oddzialu x na miesiąc y.", "report21", submenu);
        createReportButton("Lista zaplanowanych kursów w oddziale x na rok y z rozbiciem na pracowników.", "report22", submenu);
        createReportButton("Liczba szkoleń na pracownika oddziału w danym roku..", "report12", submenu);
        // adding submenu of reports to company menu
        menu.add(submenu);

        // creating menu of Persons
        menu2 = new JMenu("Persons");
        menu2.setMnemonic(KeyEvent.VK_A);
        // adding menu with persons to menu bar
        menuBar.add(menu2);

        createReportButton("Add Person", "addPerson", menu2);

        menu2.addSeparator();
        // a submenu of reports for persons
        submenu2 = new JMenu("Reports");
        submenu2.setMnemonic(KeyEvent.VK_S);
        // a list of reports to persons
        createReportButton("Lista pracowników na stanowiskach zarządczych", "report8", submenu2);
        createReportButton("Wylistuj pracowników którzy nie mieli awansu od x lat.", "report7", submenu2);
        createReportButton("Średnia długość stażu pracownika z rozbiciem na oddziały.", "report5", submenu2);
        createReportButton("Średni wiek pracownika na podanym stanowisku wg oddziału.", "report16", submenu2);
        createReportButton("Historia użytkowania samochodu przez pracownika x.", "report17", submenu2);
        createReportButton("Historia uczęszczanych kursów przez pracownika x.", "report18", submenu2);
        // adding submenu with persons reports to persons menu
        menu2.add(submenu2);

        // creating menu with cars
        menu3 = new JMenu("Cars");
        menu3.setMnemonic(KeyEvent.VK_A);
        // adding menu with cars to menu bar
        menuBar.add(menu3);

        createReportButton("Add Car", "addCar", menu3);
        createReportButton("Assign Car", "assignCar", menu3);

        menu3.addSeparator();
        // a submenu of cars with reports
        submenu3 = new JMenu("Reports");
        submenu3.setMnemonic(KeyEvent.VK_S);
        // a list of reports to cars
        createReportButton("Lista samochodów starszych niż x lat w poszczególnych oddziałach firmy", "report10",
                submenu3);
        createReportButton("Utylizacja samochodów w danym oddziale dla miesiąca x.", "report3",
                submenu3);
        createReportButton("Liczba wypożyczonych samochodow w oddzialach w danym dniu miesiaca.", "report2",
                submenu3);
        createReportButton("Historia użytkowania samochodu x.", "report19",
                submenu3);
        createReportButton("Lista użytkowników samochodu x na miesiąc y.", "report20",
                submenu3);
        createReportButton("Liczba wypożyczeń samochodów w roku x ze względu na marke.", "report23",
                submenu3);
        // adding submenu of car reports to menu of cars
        menu3.add(submenu3);

     // creating menu with cars
        menu4 = new JMenu("Training");
        menu4.setMnemonic(KeyEvent.VK_A);
        // adding menu with cars to menu bar
        menuBar.add(menu4);

        createReportButton("Add Training", "addTraining", menu4);
        createReportButton("Assign Training", "assignTraining", menu4);

        menu4.addSeparator();
        // a submenu of cars with reports
        submenu4 = new JMenu("Reports");
        submenu4.setMnemonic(KeyEvent.VK_S);
        // a list of reports to cars
        createReportButton("Średnia liczba szkoleń na pracownika w poszczególnych oddziałach firmy.", "report9",
                submenu4);
        createReportButton("Liczba przeprowadzonych szkoleń w roku względem produktu.", "report13",
                submenu4);
        createReportButton("Liczba przeprowadzonych szkoleń w roku względem szkolenia.", "report14",
                submenu4);
        createReportButton("Liczba przeprowadzonych szkoleń w roku względem firmy szkoleniowej.", "report15",
                submenu4);
        createReportButton("Lista firm przeprowadzających szkolenia z danego produktu.", "report24",
                submenu4);
        // adding submenu of car reports to menu of cars
        menu4.add(submenu4);
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
        case "addCompany":
            newInputPanel = new CreateInputPanel();
            newInputPanel.createInputCompany();
            repaintFrame(newInputPanel);
            break;
        case "assignTraining":
            newInputPanel = new CreateInputPanel();
            newInputPanel.createAssign("training");
            repaintFrame(newInputPanel);
            break;
        case "assignCar":
            newInputPanel = new CreateInputPanel();
            newInputPanel.createAssign("car");
            repaintFrame(newInputPanel);
            break;
        case "report1":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 1, true,false);
            repaintFrame(newPanel);
            break;
        case "report2":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 2, true,false);
            repaintFrame(newPanel);
            break;
        case "report3":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 3, true,true);
            repaintFrame(newPanel);
            break;
        case "report4":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 4, true,false);
            repaintFrame(newPanel);
            break;
        case "report5":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 5, false,false);
            repaintFrame(newPanel);
            break; 
        case "report6":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 6, false,false);
            repaintFrame(newPanel);
            break;
        case "report7":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 7, true,false);
            repaintFrame(newPanel);
            break;
        case "report8":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 8, false,false);
            repaintFrame(newPanel);
            break;
        case "report9":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 9, false,false);
            repaintFrame(newPanel);
            break;
        case "report10":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 10, true,false);
            repaintFrame(newPanel);
            break;
        case "report11":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 11, true,false);
            repaintFrame(newPanel);
            break;
        case "report12":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 12, true,true);
            repaintFrame(newPanel);
            break;
        case "report13":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 13, true,false);
            repaintFrame(newPanel);
            break;
        case "report14":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 14, true,false);
            repaintFrame(newPanel);
            break;
        case "report15":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 15, true,false);
            repaintFrame(newPanel);
            break;
        case "report16":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 16, true,false);
            repaintFrame(newPanel);
            break;
        case "report17":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 17, true,false);
            repaintFrame(newPanel);
            break;
        case "report18":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 18, true,false);
            repaintFrame(newPanel);
            break;
        case "report19":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 19, true,false);
            repaintFrame(newPanel);
            break;
        case "report20":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 20, true,true);
            repaintFrame(newPanel);
            break;
        case "report21":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 21, true,true);
            repaintFrame(newPanel);
            break;
        case "report22":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 22, true,true);
            repaintFrame(newPanel);
            break;
        case "report23":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 23, true,false);
            repaintFrame(newPanel);
            break;
        case "report24":
            newPanel = new CreatePanel();
            repaintFrame(newPanel);
            doReport(newPanel, 24, true,false);
            repaintFrame(newPanel);
            break;
        }
    }

    private void doReport(CreatePanel newPanel, Integer reportNr, Boolean firstParameter, Boolean secondParameter) {
        report = new DBReport();
        metody = report.getClass().getMethods();
        if (firstParameter&&!secondParameter) {
            for (int x = 0; x < metody.length; x++) {
                if ((metody[x].getName().equals("report" + reportNr))) {
                    obj = getUserInput(reportNr);
                    report.init();
                    if(obj!=null){
                    try {
                        Collection<?> resultSet = (Collection<?>) metody[x].invoke(report, obj);
                        newPanel.AddTableToPanel(resultSet);
                    } catch (IllegalAccessException e) {
                        newPanel.AddTableToPanel("Niestety program probowal siegnac tam gdzie nie powinien!");
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        newPanel.AddTableToPanel("Niestety program dostal niedozwolone argumenty!");
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        newPanel.AddTableToPanel("Niestety program przestal dzialac bo zapytanie SBQLa sie wywalilo!");
                        e.printStackTrace();
                    } catch (NullPointerException e){
                        newPanel.AddTableToPanel("Nie znaleziono rekordów speniajcych zapytanie");
                    }}else{
                     newPanel.getCount().setText("");
                    }
                }
            }
            }else if(firstParameter&&secondParameter){
                for (int x = 0; x < metody.length; x++) {
                    if ((metody[x].getName().equals("report" + reportNr))) {
                        objArr = new Object[2];
                        objArr = getUserInput(reportNr, true);
                        if(objArr!=null){
                        Object obj1, obj2;
                        
                        obj1=objArr[0];
                        obj2=objArr[1];
                        report.init();
                        try {          
                            Collection<?> resultSet = (Collection<?>) metody[x].invoke(report, (String)obj1,(Date)obj2);
                            newPanel.AddTableToPanel(resultSet);
                        } catch (IllegalAccessException e) {
                            newPanel.AddTableToPanel("Niestety program probowal siegnac tam gdzie nie powinien!");
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            newPanel.AddTableToPanel("Niestety program dostal niedozwolone argumenty!");
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            newPanel.AddTableToPanel("Niestety program przestal dzialac bo zapytanie SBQLa sie wywalilo!");
                            e.printStackTrace();
                        } catch (NullPointerException e){
                            newPanel.AddTableToPanel("Nie znaleziono rekordów speniajcych zapytanie");
                        }}}else{
                            newPanel.getCount().setText("");
                        }   
                        }
            }else if(!firstParameter&&secondParameter){
                newPanel.AddTableToPanel("Jak moze istniec drugi parametr bez pierwszego, programisto popraw kod");
        } else {
            for (int x = 0; x < metody.length; x++) {
                if ((metody[x].getName().equals("report" + reportNr))) {
                    report.init();
                    try {
                        Collection<?> resultSet = (Collection<?>) metody[x].invoke(report);
                        newPanel.AddTableToPanel(resultSet);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
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
    //need to change this method because canceling input also shows it is not a number you ....
    public Object[] getUserInput(int nrReport, boolean a){
        Object[] arr = new Object[2];
        userInput=new UserInput(frame);
        switch (nrReport){
        case 3:
        case 21:
            arr = userInput.CreateCompanyDateDialog(2);
            break;
        case 12:
        case 22:
            arr = userInput.CreateCompanyDateDialog(1);
            break;
        case 20:
            arr = userInput.CreateCarDateDialog();
            break;
        }
        return arr;
    }
        
    public Object getUserInput(int nrReport) {
        Object ob = null;
        userInput = new UserInput(frame);
        switch (nrReport){
        case 1:
            ob = userInput.CreateTrainingDialog();
            break;
        case 2:
            ob = userInput.CreateDataDay();
            break;
        case 13:
        case 14:
        case 15:
        case 23:
            ob = userInput.CreateDataYear();
            break;
        case 16:
            ob = userInput.CreateTitleDialog();
            break;
        case 17:
        case 18:
            ob = userInput.CreateLaborDialog();
            break;
        case 19:
            ob = userInput.CreateCarDialog();
            break;
        case 24:
            ob = userInput.CreateTrainingProductDialog();
            break;
        case 4:
        case 7:
        case 10:
        case 11:
            ob = userInput.CreateIntegerDialog();
        }
        return ob;
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        frame = new JFrame("SBQL Reports for KOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);

        // Create and set up the content pane.
        MenuMain mainMenu = new MenuMain();
        frame.setJMenuBar(mainMenu.createMenuBar());

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
