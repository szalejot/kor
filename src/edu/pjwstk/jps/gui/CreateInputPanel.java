package edu.pjwstk.jps.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import edu.pjwstk.jps.DBConnector;
import edu.pjwstk.jps.GUIQueries;
import edu.pjwstk.jps.model.Company;
import edu.pjwstk.jps.model.CompanyBranch;

public class CreateInputPanel extends JPanel implements ActionListener, ItemListener{
    private GridBagConstraints c;
    private GUIQueries queryGUI;
    private Collection<?> resultSet;
    private JComboBox<String> comboType, comboHQ, comboBranch, comboDep, comboSec;
    private JLabel errorMsg ;
    private JTextField companyName;
    private ArrayList<String> companySec, companyHQ, companyBranch, companyDep;
    private String selection;

    public CreateInputPanel(){
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    
    public void createInputCompany(){
        queryGUI = new GUIQueries();
        queryGUI.init();
      
        ArrayList<String> companyType = new ArrayList<>((Collection<String>) getGUIQueriesResult(queryGUI, "getCompanyBranch",null));       
        JLabel textType = new JLabel("Choose type of company you want to add/update: ") ;
        comboType = new JComboBox<String>();
        for(int i=0;i<companyType.size();i++){
            comboType.addItem(companyType.get(i));
        }
        comboType.setName("cbType");
        comboType.setSelectedItem(null);
        comboType.addItemListener(this);
           
        JLabel textHQ = new JLabel("Headquaters: ") ;
       
        comboHQ = new JComboBox<String>();
        comboHQ.setName("cbHQ");
        comboHQ.addItemListener(this);
        comboHQ.setEnabled(false);
        
        JLabel textBranch = new JLabel("Branch: ") ;
        comboBranch = new JComboBox<String>();
        comboBranch.setName("cbBranch");
        comboBranch.addItemListener(this);
        comboBranch.setEnabled(false);
        
        JLabel textDep = new JLabel("Department: ") ;
        comboDep = new JComboBox<String>();
        comboDep.setName("cbDep");
        comboDep.addItemListener(this);
        comboDep.setEnabled(false);
        
        JLabel textSec = new JLabel("Section: ") ;
        comboSec = new JComboBox<String>();
        comboSec.setName("cbSec");
        comboSec.addItemListener(this);
        comboSec.setEnabled(false);
        
        JLabel textCompanyName = new JLabel("Insert a company name:");
        errorMsg = new JLabel();
        errorMsg.setVisible(false);
        companyName = new JTextField();
        
        JButton addButton = new JButton("ADD");
        addButton.setName("addButton");
        addButton.addActionListener(this);
        JButton updateButton= new JButton("UPDATE");
        updateButton.setName("updateButton");
        updateButton.addActionListener(this);

        addToPane(20,50, 0.0, 2, 0, 0, textCompanyName);
        addToPane(20,50, 0.0, 2, 2, 0, companyName);
        
        addToPane(20,50, 0.0, 2, 0, 1, textType);
        addToPane(20,50, 0.0, 1, 2, 1, comboType);
        
        addToPane(20,50, 0.0, 1, 0, 2, textHQ);
        addToPane(20,50, 0.0, 1, 0, 3, comboHQ);
        
        addToPane(20,50, 0.0, 1, 1, 2, textBranch);
        addToPane(20,50, 0.0, 1, 1, 3, comboBranch);
        
        addToPane(20,50, 0.0, 1, 2, 2, textDep);
        addToPane(20,50, 0.0, 1, 2, 3, comboDep);
        
        addToPane(20,50, 0.0, 1, 3, 2, textSec);
        addToPane(20,50, 0.0, 1, 3, 3, comboSec);
        
        addToPane(20,50, 0.0, 2, 0, 4, addButton);
        addToPane(20,50, 0.0, 2, 2, 4, updateButton);         
    }
    public void addToPane(int ipady, int ipadx, double weightx, int gridwidth, int gridx, int gridy, Component obj){
        /*
        c.fill = GridBagConstraints.HORIZONTAL; wypelnienie horyzontalne
        c.ipady = 40;      //ipad x, y okresla wysokosc i dlugosc elementy
        c.weightx = 0.0; // okresla czy element moze byc rozszerzany
        c.gridwidth = 3; // okresla na ile kolumn bedzie element
        c.gridx = 0; //numer kolumny
        c.gridy = 1; // numer wiersza
        pane.add(button, c); 
        */
        c.fill = GridBagConstraints.HORIZONTAL;// wypelnienie horyzontalne
        c.ipady = ipady;      //ipad x, y okresla wysokosc i dlugosc elementy
        c.ipadx = ipadx;
        c.weightx = weightx; // okresla czy element moze byc rozszerzany
        c.gridwidth = gridwidth; // okresla na ile kolumn bedzie element
        c.gridx = gridx; //numer kolumny
        c.gridy = gridy; // numer wiersza
        this.add(obj, c); 
        
    }
    
    public Collection<?> getGUIQueriesResult(GUIQueries query, String methodName, Object param){
            try {
                if(param==null){
                    Method guiMethod = query.getClass().getMethod(methodName);
                    resultSet = (Collection<?>)guiMethod.invoke(query);
                }else{
                    Method guiMethod = query.getClass().getMethod(methodName,param.getClass());
                    resultSet = (Collection<?>)guiMethod.invoke(query,param);
                }
                
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                ArrayList<String> error = new ArrayList<>();
                error.add("SBQL Query Error");
                resultSet = (Collection<String>)error;
                //e.printStackTrace();
            }
        return resultSet;        
    }
    public void createInputLabor(){
        
    }
    public void createInputCar(){
        
    }
    public void createInputRentCar(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
           JButton bt = (JButton)e.getSource();
           if(bt.getName().equals("addButton")){
               String name = companyName.getText();
               String partOfString = "";
               Company partOf;
               
               if(comboType.getSelectedItem()==null)
               {
            //dodac obsluge jak null bedzie       
               }else{
               switch(comboType.getSelectedItem().toString()){
             case "headquaters":
                 partOfString=null;
                 break;
             case "branch":
                 partOfString = comboHQ.getSelectedItem().toString();
                 break;
             case "department":
                 partOfString = comboBranch.getSelectedItem().toString();
                 break;
             case "section":
                 partOfString = comboDep.getSelectedItem().toString();
                 break;
             case "office":
                 partOfString = comboSec.getSelectedItem().toString();
                 break;
               }
               if(partOfString != null){
               ArrayList<Company> arComp; 
               arComp = new ArrayList<Company>(((Collection<Company>)getGUIQueriesResult(queryGUI, "getCompanyClass", partOfString)));
               partOf = arComp.get(0);
               }else{
                   partOf=null;
               }
               ArrayList<CompanyBranch> arCompBr; 
               String typ = comboType.getSelectedItem().toString();
               arCompBr = new ArrayList<CompanyBranch>(((Collection<CompanyBranch>)getGUIQueriesResult(queryGUI, "getCompanyBranchClass", typ)));
               
               Company toAdd = new Company(name, partOf, arCompBr.get(0));
               System.out.println(toAdd);
               DBConnector dbconn = new DBConnector();
               ObjectContainer db = dbconn.getConnection();
               db.store(toAdd);
               db.commit();               
           }
           }
           else if (bt.getName().equals("updateButton")){
               
           }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox<?> cb = (JComboBox<?>)e.getSource();
        switch(cb.getName()){
        case "cbType":
            companyHQ = new ArrayList<String>(((Collection<String>) getGUIQueriesResult(queryGUI, "getCompany","headquaters")));
            comboHQ.removeItemListener(this);
            if(comboBranch.getItemCount()!=0){comboBranch.removeAllItems();};
            if(comboDep.getItemCount()!=0){comboDep.removeAllItems();};
            if(comboSec.getItemCount()!=0){comboSec.removeAllItems();};
            if(comboHQ.getItemCount()!=0){comboHQ.removeAllItems();};
//            comboDep.removeAllItems();
//            comboSec.removeAllItems();
//            comboHQ.removeAllItems();
            comboBranch.setEnabled(false);
            comboDep.setEnabled(false);
            comboSec.setEnabled(false);
            comboHQ.setEnabled(false);
            for(int i=0;i<companyHQ.size();i++){
                comboHQ.addItem(companyHQ.get(i));
                }
            comboHQ.setSelectedItem(null);
            comboHQ.addItemListener(this);
            switch(comboType.getSelectedItem().toString()){
            case "branch":
                comboHQ.setEnabled(true);              
                break;
            case "department":
                comboHQ.setEnabled(true);
                comboBranch.setEnabled(true);
                break;
            case "section":
                comboHQ.setEnabled(true);
                comboBranch.setEnabled(true);
                comboDep.setEnabled(true);
                break;
            case "office":
                comboHQ.setEnabled(true);
                comboBranch.setEnabled(true);
                comboDep.setEnabled(true);
                comboSec.setEnabled(true);
                break;
            }
            break;
        case "cbHQ":
            getHQselection(comboHQ, comboBranch, companyBranch);
            break;
        case "cbBranch":
            getHQselection(comboBranch, comboDep, companyDep);
            break;
        case "cbDep":
            getHQselection(comboDep, comboSec, companySec);
            break;
        case "cbSec":
            break;
        }
        
    }
    public void getHQselection(JComboBox<String> source, JComboBox<String> target, ArrayList<String> list){
        selection = source.getSelectedItem().toString();
        list = new ArrayList<String>(((Collection<String>) getGUIQueriesResult(queryGUI, "getInnerCompany",selection)));
        target.removeItemListener(this);
        target.removeAllItems();
        if(!list.isEmpty())
        {
        for(int i=0;i<list.size();i++){
        target.addItem(list.get(i));
        }
        target.setSelectedItem(null);
        target.addItemListener(this);
        target.revalidate();
    }}
}

 