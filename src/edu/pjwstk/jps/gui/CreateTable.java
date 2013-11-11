package edu.pjwstk.jps.gui;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

import pl.wcislo.sbql4j.java.model.runtime.Struct;

enum classTypes {Car,Company,CompanyBranch,Labor,RentCarmTitle,Training,TrainingAssignment,TrainingCompany,TrainingProduct}

public class CreateTable  extends AbstractTableModel{

    private static final long serialVersionUID = -3728561007770961189L;
    private boolean DEBUG = false;
    private String[] columnNames;
    private Object[][] data;
    private ArrayList<?> resultSet;
    private Object result;
    private Field[] pola;
    private Method[] metody;
    private Struct tmpStruct;

    /*
     * Tworzenie tabel niezależnie od otrzymywanych obiektów w kolekcjach, dzieki temu ta sama
     * klasa moze byc użyta do listowania kazdej naszej klasy (labor, car, company ...)
     * 
     * 
     */
    public CreateTable(Collection<?> resultSet1){
        resultSet=(ArrayList<?>)resultSet1;
        // nazwa klasy poszczególnych elementów kolekcji
      if(!resultSet.isEmpty()){
       if (contains(resultSet.get(0).getClass().getName())){
           metody = resultSet.get(0).getClass().getMethods();
           pola = resultSet.get(0).getClass().getDeclaredFields();
           setColumnNames();
           setDataModel();
       }
       else if (resultSet.get(0).getClass().getName().equals("pl.wcislo.sbql4j.java.model.runtime.Struct"))
       {           
           tmpStruct = (Struct)resultSet.get(0);
           data=new Object[resultSet.size()][tmpStruct.size()];
           columnNames=new String[tmpStruct.size()];
           for(int i=0;i<resultSet.size();i++){ 
               tmpStruct = (Struct)resultSet.get(i);
               for(int j=0;j<tmpStruct.size();j++)
               {
                   data[i][j]=tmpStruct.getValue(j);
               }
           }
       }
       else
       {
          columnNames = new String[1];
          columnNames[0]="";
          data=new Object[1][1];
          data[0][0]=new String("Nieobsugiwana klasa raportu!");
       }
      }else
      {
          columnNames = new String[1];
          columnNames[0]="";
          data=new Object[1][1];
          data[0][0]=new String("Nie istnieja rekordy odpowiadajace zapytaniu");
      }
    }
    public CreateTable(Object result){
        this.result=result;
        columnNames = new String[1];
        columnNames[0]="Wynik";
        data = new Object[1][1];
        data[0][0]=this.result;
        
    }
    public static boolean contains(String test) {

        for (classTypes c : classTypes.values()) {
            String tmp = "edu.pjwstk.jps.model."+c.name();
            if (tmp.equals(test)) {
                return true;
            }
        }

        return false;
    }
    
    private void setColumnNames() {
        columnNames = new String[pola.length];  
        for (int i = 0; i<pola.length;i++){           
            columnNames[i] = pola[i].getName();
        }
    }
    
    private void setDataModel() {
        data = new Object[resultSet.size()][pola.length];
        for(int i=0;i<resultSet.size();i++){
            for(int j=0;j<pola.length;j++)
            {             
                for(int x=0;x<metody.length;x++){
                    if ((metody[x].getName().startsWith("get")) && (metody[x].getName().length() == (pola[j].getName().length() + 3)))
                    {
                        if (metody[x].getName().toLowerCase().endsWith(pola[j].getName().toLowerCase()))
                        {
                            try {
                                data[i][j] = metody[x].invoke(resultSet.get(i));
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
// ALternatywna metoda dostpu do zmiennych, niestety w tym wypadku zmienne musza byc publiczne
//              try {
//                  fieldValue = pola[j].get(resultSet.get(i));
//                  data[i][j]=fieldValue;
//              } catch (IllegalArgumentException e) {
//                  // TODO Auto-generated catch block
//                  e.printStackTrace();
//              } catch (IllegalAccessException e) {
//                  // TODO Auto-generated catch block
//                  e.printStackTrace();
//              }  
            }     
        }     
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Class getColumnClass(int c) {
        Class klasa;
        try{
            klasa = getValueAt(0, c).getClass();
        }catch(NullPointerException e){
            klasa = String.class;            
        }
        return klasa;
    }
    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }
    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
