package edu.pjwstk.jps.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import pl.wcislo.sbql4j.java.model.runtime.Struct;

import com.db4o.ObjectContainer;

import edu.pjwstk.jps.DBConnector;
import edu.pjwstk.jps.GUIQueries;
import edu.pjwstk.jps.model.Car;
import edu.pjwstk.jps.model.Company;
import edu.pjwstk.jps.model.CompanyBranch;
import edu.pjwstk.jps.model.Labor;
import edu.pjwstk.jps.model.RentCar;
import edu.pjwstk.jps.model.Training;
import edu.pjwstk.jps.model.TrainingAssignment;

public class CreateInputPanel extends JPanel implements ActionListener, ItemListener{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GridBagConstraints c;
    private GUIQueries queryGUI;
    private Collection<?> resultSet;
    private JComboBox<String> comboType, comboHQ, comboBranch, comboDep, comboSec;
    private JLabel errorMsg, errorAssign ;
    private JTextField companyName;
    private ArrayList<String> companySec, companyHQ, companyBranch, companyDep;
    private String selection;
    private JComboBox<String> laborsCB, carsCB, trainingCB;
    private String[] laborsNames, trainingsNames, carsNames;
    private JXDatePicker pickDate;
    
    public CreateInputPanel(){
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    
    public void createInputCompany(){
        queryGUI = new GUIQueries();
        queryGUI.init();
      
        ArrayList<String> companyType = new ArrayList<>((Collection<String>) getGUIQueriesResult(queryGUI, "getCompanyBranch",null,null));       
        JLabel textType = new JLabel("Choose type of company you want to add: ") ;
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
        errorMsg.setForeground(Color.RED);
        errorMsg.setVisible(false);
        companyName = new JTextField();
        
        JButton addButton = new JButton("ADD");
        addButton.setName("addButton");
        addButton.addActionListener(this);

        addToPane(20,50, 0.0, 2, 2, 0, errorMsg);
        addToPane(20,50, 0.0, 2, 0, 1, textCompanyName);
        addToPane(20,50, 0.0, 2, 2, 1, companyName);
        
        addToPane(20,50, 0.0, 2, 0, 2, textType);
        addToPane(20,50, 0.0, 1, 2, 2, comboType);
        
        addToPane(20,50, 0.0, 1, 0, 3, textHQ);
        addToPane(20,50, 0.0, 1, 0, 4, comboHQ);
        
        addToPane(20,50, 0.0, 1, 1, 3, textBranch);
        addToPane(20,50, 0.0, 1, 1, 4, comboBranch);
        
        addToPane(20,50, 0.0, 1, 2, 3, textDep);
        addToPane(20,50, 0.0, 1, 2, 4, comboDep);
        
        addToPane(20,50, 0.0, 1, 3, 3, textSec);
        addToPane(20,50, 0.0, 1, 3, 4, comboSec);
        
        addToPane(20,50, 0.0, 4, 0, 5, addButton);
       
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
    
    public Collection<?> getGUIQueriesResult(GUIQueries query, String methodName, Object param, Object param1){
            try {
                if(param==null && param1==null){
                    Method guiMethod = query.getClass().getMethod(methodName);
                    resultSet = (Collection<?>)guiMethod.invoke(query);
                }else if(param!=null && param1!=null){
                    Method guiMethod = query.getClass().getMethod(methodName,param.getClass(), param1.getClass());
                    resultSet = (Collection<?>)guiMethod.invoke(query,param, param1);
                }
                else{
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
                e.printStackTrace();
            }
        return resultSet;        
    }

    public void createAssign(String assign){
        queryGUI = new GUIQueries();
        queryGUI.init();
        
        ArrayList<?> tmpLaborsNames = new ArrayList(getGUIQueriesResult(queryGUI, "getLaborName",null,null));      
            Struct tmpStruct = (Struct) tmpLaborsNames.get(0);
            laborsNames = new String[tmpLaborsNames.size()];
            for (int i = 0; i < tmpLaborsNames.size(); i++) {
                tmpStruct = (Struct) tmpLaborsNames.get(i);
                laborsNames[i] = (String) tmpStruct.getValue(0) +" "+ tmpStruct.getValue(1);               
            }
            Arrays.sort(laborsNames);

        JLabel textLabor = new JLabel("Choose labor: ") ;
        laborsCB = new JComboBox<String>(laborsNames);
        laborsCB.setSelectedItem(null);
        
        JLabel textDate = new JLabel("Choose a day");
        pickDate = new JXDatePicker();
        pickDate.setDate(Calendar.getInstance().getTime());
        pickDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        if(assign=="car"){
            ArrayList<?> tmpCarsNames = new ArrayList<>(getGUIQueriesResult(queryGUI, "getCarName", null,null));
            Struct tmpStructCar = (Struct) tmpCarsNames.get(0);
            carsNames = new String[tmpCarsNames.size()];
            for (int i = 0; i < tmpCarsNames.size(); i++) {
                tmpStructCar = (Struct) tmpCarsNames.get(i);
                carsNames[i] = (String) String.valueOf(tmpStructCar.getValue(0)) +" "+ tmpStructCar.getValue(1) +" "+ tmpStructCar.getValue(2);               
            }
            Arrays.sort(carsNames);
            JLabel textCar = new JLabel("Choose a car");
            carsCB=new JComboBox<String>(carsNames);
            carsCB.setSelectedItem(null);
            
            JButton addBTc = new JButton("ASSIGN");
            addBTc.setName("assignCar");
            addBTc.addActionListener(this);
            addToPane(20,50, 0.0, 1, 2, 0, textCar);
            addToPane(20,50, 0.0, 1, 2, 1, carsCB);
            addToPane(20,50, 0.0, 3, 0, 2, addBTc);
        }else if(assign=="training"){
            ArrayList<?> tmpTrainingsNames = new ArrayList<>(getGUIQueriesResult(queryGUI, "getTrainingName",null, null));
            trainingsNames = new String[tmpTrainingsNames.size()];
            tmpTrainingsNames.toArray(trainingsNames);
            Arrays.sort(trainingsNames);
            JLabel textTraining = new JLabel("Choose a training");
            trainingCB=new JComboBox<String>(trainingsNames);
            trainingCB.setSelectedItem(null);
    
            JButton addBTt = new JButton("ASSIGN");
            addBTt.setName("assignTraining");
            addBTt.addActionListener(this);
            
            addToPane(20,50, 0.0, 1, 2, 0, textTraining);
            addToPane(20,50, 0.0, 1, 2, 1, trainingCB);
            addToPane(20,50, 0.0, 3, 0, 2, addBTt);
        }else{
            System.out.println("You make smth wrong with code");
        }
        errorAssign = new JLabel();
        errorAssign.setForeground(Color.RED);
        errorAssign.setVisible(false);
        addToPane(20,50, 0.0, 1, 0, 0, textLabor);
        addToPane(20,50, 0.0, 1, 0, 1, laborsCB);
        addToPane(20,50, 0.0, 1, 1, 0, textDate);
        addToPane(20,50, 0.0, 1, 1, 1, pickDate);
        addToPane(20,50, 0.0, 3, 0, 3, errorAssign);
   
    }
    private Labor getSelectedLabor(){
        String strLaborsCB = (String) laborsCB.getSelectedItem();
        String arrLaborsCB[] = strLaborsCB.split(" ");
        String last = arrLaborsCB[0];
        String first = arrLaborsCB[1];
        ArrayList<Labor> tmpLabor = new ArrayList<Labor>((Collection<Labor>)getGUIQueriesResult(queryGUI, "getLaborClass", last, first));
        return tmpLabor.get(0);
    }
    
    public void getHQselection(JComboBox<String> source, JComboBox<String> target, ArrayList<String> list){
        selection = source.getSelectedItem().toString();
        list = new ArrayList<String>(((Collection<String>) getGUIQueriesResult(queryGUI, "getInnerCompany",selection,null)));
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
    @Override
    public void actionPerformed(ActionEvent e) {
        
           JButton bt = (JButton)e.getSource();
           if (bt.getName().equals("assignCar")){
               errorAssign.setVisible(false);
               if(carsCB.getSelectedItem()==null || laborsCB.getSelectedItem() ==null){
                   errorAssign.setVisible(true);
                   errorAssign.setText("You must choose all fields");
               }else{
                   
                   String strCarsCB = (String) carsCB.getSelectedItem();
                   String arrCarsCB[] = strCarsCB.split(" ");
                   Integer number = Integer.valueOf(arrCarsCB[0]);

                   ArrayList<Car> tmpCar = new ArrayList<Car>((Collection<Car>)getGUIQueriesResult(queryGUI, "getCarClass",number,null));
                   
                   RentCar toAdd = new RentCar(getSelectedLabor(), tmpCar.get(0), pickDate.getDate() );
                   System.out.println(toAdd);
                   DBConnector dbconn = new DBConnector();
                   ObjectContainer db = dbconn.getConnection();
                   db.store(toAdd);
                   db.commit();  
                   errorAssign.setText("Poprawnie dodano obiekt: "+ toAdd + " do bazy danych.");
                   errorAssign.setVisible(true);
               }
           }
           else if(bt.getName().equals("assignTraining")){
               errorAssign.setVisible(false);
               if(trainingCB.getSelectedItem()==null || laborsCB.getSelectedItem() ==null){
                   errorAssign.setVisible(true);
                   errorAssign.setText("You must choose all fields");
               }else{
                   ArrayList<Training> tmpTraining = new ArrayList<Training>((Collection<Training>)getGUIQueriesResult(queryGUI, "getTrainingClass",trainingCB.getSelectedItem(),null ));
                   TrainingAssignment toAdd = new TrainingAssignment(getSelectedLabor(), tmpTraining.get(0), pickDate.getDate()) ;
                   System.out.println(toAdd);
                   DBConnector dbconn = new DBConnector();
                   ObjectContainer db = dbconn.getConnection();
                   db.store(toAdd);
                   db.commit();  
                   errorAssign.setText("Poprawnie dodano obiekt: "+ toAdd + " do bazy danych.");
                   errorAssign.setVisible(true);
               }
           }
           else if(bt.getName().equals("addButton")){
               errorMsg.setVisible(false);
               String name = companyName.getText();
               String partOfString = "";
               Company partOf;
               
               if(comboType.getSelectedItem()==null)
               {
                errorMsg.setText("You need to select type of organization unit you wish to add.");
                errorMsg.setVisible(true);
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
               arComp = new ArrayList<Company>(((Collection<Company>)getGUIQueriesResult(queryGUI, "getCompanyClass", partOfString,null)));
               partOf = arComp.get(0);
               }else{
                   partOf=null;
               }
               ArrayList<CompanyBranch> arCompBr; 
               String typ = comboType.getSelectedItem().toString();
               arCompBr = new ArrayList<CompanyBranch>(((Collection<CompanyBranch>)getGUIQueriesResult(queryGUI, "getCompanyBranchClass", typ,null)));
               
               Company toAdd = new Company(name, partOf, arCompBr.get(0));
               System.out.println(toAdd);
               DBConnector dbconn = new DBConnector();
               ObjectContainer db = dbconn.getConnection();
               db.store(toAdd);
               db.commit();  
               errorMsg.setText("Poprawnie dodano obiekt: "+ name + " do bazy danych.");
               errorMsg.setVisible(true);
               
           }
           }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        errorMsg.setVisible(false);
        JComboBox<?> cb = (JComboBox<?>)e.getSource();
        switch(cb.getName()){
        case "cbType":
            companyHQ = new ArrayList<String>(((Collection<String>) getGUIQueriesResult(queryGUI, "getCompany","headquaters",null)));
            comboHQ.removeItemListener(this);
            comboBranch.removeItemListener(this);
            comboDep.removeItemListener(this);
            comboSec.removeItemListener(this);
            if(comboBranch.getItemCount()!=0){comboBranch.removeAllItems();};
            if(comboDep.getItemCount()!=0){comboDep.removeAllItems();};
            if(comboSec.getItemCount()!=0){comboSec.removeAllItems();};
            if(comboHQ.getItemCount()!=0){comboHQ.removeAllItems();};
            comboBranch.setEnabled(false);
            comboDep.setEnabled(false);
            comboSec.setEnabled(false);
            comboHQ.setEnabled(false);
            for(int i=0;i<companyHQ.size();i++){
                comboHQ.addItem(companyHQ.get(i));
                }
            comboHQ.setSelectedItem(null);
            comboHQ.addItemListener(this);
            comboBranch.addItemListener(this);
            comboDep.addItemListener(this);
            comboSec.addItemListener(this);
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
}

 