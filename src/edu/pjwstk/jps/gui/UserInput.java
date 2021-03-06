package edu.pjwstk.jps.gui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import pl.wcislo.sbql4j.java.model.runtime.Struct;

import edu.pjwstk.jps.GUIQueries;
import edu.pjwstk.jps.model.Labor;

public class UserInput extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GUIQueries queryGUI;
    private Calendar cal;
    private Integer[] years = new Integer[30];
    private final Integer[] months = {1,2,3,4,5,6,7,8,9,10,11,12}; 
    private JFrame frame;
    
    public UserInput(JFrame frame){
        this.frame=frame;
        queryGUI = new GUIQueries();
        queryGUI.init();
        cal = Calendar.getInstance();
        for (int i=0;i<30;i++){
            years[i]=cal.get(Calendar.YEAR)-i;
        }
    }
    public Date createDateYear(){
        Integer choosed;
        choosed = (Integer)JOptionPane.showInputDialog(frame, "Pick a Year:"
                , "Choose", JOptionPane.QUESTION_MESSAGE
                , null, years, years[0] );
        if(choosed==JOptionPane.CANCEL_OPTION||choosed==JOptionPane.CLOSED_OPTION){
            return null;
        }else{
        Calendar cal = Calendar.getInstance();
        cal.set(choosed,0, 1);
        
        Date data; 
        data = new Date(cal.getTimeInMillis());
        return data;}
    }
    public Date createDateMonth(){
        JComboBox<Integer> yearsCB, monthsCB;
        yearsCB = new JComboBox<>(years);
        monthsCB= new JComboBox<>(months);
        JComponent[] input = new JComponent[]{
                new JLabel("Year:"),
                yearsCB,
                new JLabel("Month:"),
                monthsCB,
        };
        Object[] options = {"Ok","Cancel"};
        Integer choice = JOptionPane.showOptionDialog(frame, input,"Choose paramters for report.",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
        if(choice == JOptionPane.NO_OPTION||choice == JOptionPane.CLOSED_OPTION){
            return null;
        }else{
        Calendar cal = Calendar.getInstance();
        cal.set((int)yearsCB.getSelectedItem(),(int)monthsCB.getSelectedItem(), 1);    
        Date data; 
        data = new Date(cal.getTimeInMillis());       
        return data;   
    }}
    public Date createDateDay(){
        JXDatePicker pickDate;
        pickDate = new JXDatePicker();
        pickDate.setDate(Calendar.getInstance().getTime());
        pickDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        JComponent[] input = new JComponent[]{
                new JLabel("Choose a day:"),
                pickDate,
        };
        Object[] options = {"Ok","Cancel"};
        Integer choice = JOptionPane.showOptionDialog(frame, input,"Choose paramters for report.",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
        if(choice == JOptionPane.NO_OPTION||choice == JOptionPane.CLOSED_OPTION){
            return null;
        }else{
        
        return pickDate.getDate(); }      
    }
    public String createCompanyDialog(){
        ArrayList<String> companyName;
        companyName = new ArrayList<String>(queryGUI.getCompanyName());
        String choosed;
        choosed = (String)JOptionPane.showInputDialog(frame, "Pick a Company:"
                , "Company", JOptionPane.QUESTION_MESSAGE
                , null, companyName.toArray(), companyName.toArray()[0] );
        if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
            return null;
        }else{
        return choosed;}
    }
    public Object[] createCompanyDateDialog(Integer option){
        JComboBox<Integer> yearsCB, monthsCB;
        JComboBox<String> companyCB;
        yearsCB = new JComboBox<>(years);
        monthsCB= new JComboBox<>(months);
        ArrayList<String> tmpCompanyName = new ArrayList<String>(queryGUI.getCompanyName());
        String[] companyName = new String[tmpCompanyName.size()];
        tmpCompanyName.toArray(companyName);
        companyCB = new JComboBox<String>(companyName);
        JComponent[] input = new JComponent[]{
                new JLabel("Year:"),
                yearsCB,
                new JLabel("Month:"),
                monthsCB,
                new JLabel("ChooseCompany"),
                companyCB
        };
        if(option==0){
            yearsCB.setVisible(false);
            monthsCB.setVisible(false);
        }else if(option==1){
            monthsCB.setVisible(false);
        }else{
            
        }
        Object[] options = {"Ok","Cancel"};
        Integer choice = JOptionPane.showOptionDialog(frame, input,"Choose paramters for report.",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
        if(choice == JOptionPane.NO_OPTION||choice == JOptionPane.CLOSED_OPTION){
            return null;
        }else{
        Calendar cal = Calendar.getInstance();
        cal.set((int)yearsCB.getSelectedItem(),(int)monthsCB.getSelectedItem(), 1);
        
        Date data; 
        data = new Date(cal.getTimeInMillis());
        
        Object[] obj = new Object[2];
        obj[0]=companyCB.getSelectedItem();
        obj[1]=data;
        return obj;}
    }
    public String createCarDialog(){
        String[] carsNames;
        ArrayList<?> tmpCarsNames = new ArrayList<>(queryGUI.getCarName());
        Struct tmpStructCar = (Struct) tmpCarsNames.get(0);
        carsNames = new String[tmpCarsNames.size()];
        for (int i = 0; i < tmpCarsNames.size(); i++) {
            tmpStructCar = (Struct) tmpCarsNames.get(i);
            carsNames[i] = (String) String.valueOf(tmpStructCar.getValue(0)) +" "+ tmpStructCar.getValue(1) +" "+ tmpStructCar.getValue(2);               
        }
        Arrays.sort(carsNames);     
        String choosed = (String)JOptionPane.showInputDialog(frame, "Pick a car:"
                , "Trainings", JOptionPane.QUESTION_MESSAGE
                , null, carsNames, carsNames[0] );

        if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
            return null;
        }else{
        String arrCarsCB[] = choosed.split(" ");
        String toReturn = arrCarsCB[0];
        return toReturn;}
}
    public Object[] createCarDateDialog(){
        JComboBox<Integer> yearsCB, monthsCB;
        JComboBox<String> carCB;
        yearsCB = new JComboBox<>(years);
        monthsCB= new JComboBox<>(months);
        
        String[] carsNames;
        ArrayList<?> tmpCarsNames = new ArrayList<>(queryGUI.getCarName());
        Struct tmpStructCar = (Struct) tmpCarsNames.get(0);
        carsNames = new String[tmpCarsNames.size()];
        for (int i = 0; i < tmpCarsNames.size(); i++) {
            tmpStructCar = (Struct) tmpCarsNames.get(i);
            carsNames[i] = (String) String.valueOf(tmpStructCar.getValue(0)) +" "+ tmpStructCar.getValue(1) +" "+ tmpStructCar.getValue(2);               
        }
        Arrays.sort(carsNames);     
        carCB = new JComboBox<String>(carsNames);
        JLabel error = new JLabel();
        error.setForeground(Color.RED);
        JComponent[] input = new JComponent[]{
                new JLabel("Year:"),
                yearsCB,
                new JLabel("Month:"),
                monthsCB,
                error,
                new JLabel("Choose Car"),
                carCB
        };
        Object[] options = {"Ok","Cancel"};
        Integer choice = JOptionPane.showOptionDialog(frame, input,"Choose paramters for report.",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
        if(choice == JOptionPane.NO_OPTION||choice == JOptionPane.CLOSED_OPTION){
            return null;
        }else{
        Calendar cal = Calendar.getInstance();
        cal.set((int)yearsCB.getSelectedItem(),(int)monthsCB.getSelectedItem(), 1);
        
        Date data; 
        data = new Date(cal.getTimeInMillis());
        String arrCarsCB[] =((String)carCB.getSelectedItem()).split(" ");
        Object[] obj = new Object[2];
        obj[0]=arrCarsCB[0];
        obj[1]=data;
        return obj;}
    
}
    public String createTitleDialog(){
        ArrayList<String> titleName;
        titleName = new ArrayList<String>(queryGUI.getTitleName());
        String choosed;
        choosed = (String)JOptionPane.showInputDialog(frame, "Pick a Title:"
                , "Trainings", JOptionPane.QUESTION_MESSAGE
                , null, titleName.toArray(), titleName.toArray()[0] );
        if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
            return null;
        }else{
        return choosed;}
}
public String createTrainingDialog(){
    ArrayList<String> trainingName;
    trainingName = new ArrayList<String>(queryGUI.getTrainingName());
    String choosed;
    choosed = (String)JOptionPane.showInputDialog(frame, "Pick a Training:"
            , "Trainings", JOptionPane.QUESTION_MESSAGE
            , null, trainingName.toArray(), trainingName.toArray()[0] );
    if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
        return null;
    }else{
    return choosed;}
    }
public String createTrainingProductDialog(){
    ArrayList<String> trainingProductName;
    trainingProductName = new ArrayList<String>(queryGUI.getTrainingProductName());
    String choosed;
    choosed = (String)JOptionPane.showInputDialog(frame, "Pick a Training Product:"
            , "Trainings", JOptionPane.QUESTION_MESSAGE
            , null, trainingProductName.toArray(), trainingProductName.toArray()[0] );
    if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
        return null;
    }else{
    return choosed;}
}
public Labor createLaborDialog(){
    String[] laborsNames;
    ArrayList<?> tmpLaborsNames = new ArrayList<>(queryGUI.getLaborName());      
    Struct tmpStruct = (Struct) tmpLaborsNames.get(0);
    laborsNames = new String[tmpLaborsNames.size()];
    for (int i = 0; i < tmpLaborsNames.size(); i++) {
        tmpStruct = (Struct) tmpLaborsNames.get(i);
        laborsNames[i] = (String) tmpStruct.getValue(0) +" "+ tmpStruct.getValue(1);               
    }
    Arrays.sort(laborsNames);    
    String choosed = (String)JOptionPane.showInputDialog(frame, "Pick a Labor:"
            , "Labors", JOptionPane.QUESTION_MESSAGE
            , null, laborsNames, laborsNames[0] );;
    if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
        return null;
    }else{
    String arrCarsCB[] = choosed.split(" ");
    ArrayList<Labor> tmpLaborClass = new ArrayList<Labor>(queryGUI.getLaborClass(arrCarsCB[0], arrCarsCB[1]));
    return tmpLaborClass.get(0);}
}
public Integer createIntegerDialog(){
    Boolean number = false;
    Integer i = null;
    while (!number) {
        String choosed = JOptionPane.showInputDialog(frame, "Input number of years:", 0);
        if(choosed==String.valueOf(JOptionPane.CANCEL_OPTION)||choosed==String.valueOf(JOptionPane.CLOSED_OPTION)){
            return null;
        }else{
            try {
            i = Integer.parseInt(choosed);
            number = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "It is not a number you provided, pls try again");
            number = false;
        }}                   
    }
    return i;
}
}
