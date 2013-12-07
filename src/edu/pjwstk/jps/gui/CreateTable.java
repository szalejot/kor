package edu.pjwstk.jps.gui;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

import pl.wcislo.sbql4j.java.model.runtime.Struct;

enum classTypes {
    Car, Company, CompanyBranch, Labor, RentCar, Title, Training, TrainingAssignment, TrainingCompany, TrainingProduct
}

public class CreateTable extends AbstractTableModel {

    private static final long serialVersionUID = -3728561007770961189L;
    private boolean DEBUG = false;
    private String[] columnNames;
    private Object[][] tableData;
    private ArrayList<?> resultSet;
    private Object result;
    private Field[] classFields;
    private Method[] classMethods;
    private Struct tmpStruct;

    public CreateTable(Collection<?> resultSet1) {
        resultSet = new ArrayList<>(resultSet1);

        // if resultSet is not empty then check, otherwise print 'Sorry, there
        // are no records that match your search'
        if (!resultSet.isEmpty()) {
            // if objects in collection are one of our class (Labor, Department
            // etc) then get methods
            // and fields for that class and search for column names and data to
            // display
            if (contains(resultSet.get(0).getClass().getName())) {
                classMethods = resultSet.get(0).getClass().getMethods();
                classFields = resultSet.get(0).getClass().getDeclaredFields();
                setColumnNames();
                setDataModel();
            }
            // if result set has objects of type Struct (SBQL) then create
            // columns without names
            // determine number of columns out of struct.size, after that use
            // struct.getValue to obtain
            // struct objects values and put them into jTable data
            else if (resultSet.get(0).getClass().getName().equals("pl.wcislo.sbql4j.java.model.runtime.Struct")) {
                tmpStruct = (Struct) resultSet.get(0);
                tableData = new Object[resultSet.size()][tmpStruct.size()];
                columnNames = new String[tmpStruct.size()];
                for (int i = 0; i < resultSet.size(); i++) {
                    tmpStruct = (Struct) resultSet.get(i);
                    for (int j = 0; j < tmpStruct.size(); j++) {
                        tableData[i][j] = tmpStruct.getValue(j);
                    }
                }
            }
            // if objects of collection are other types then abowe then print
            // 'Unsupported class report!'
            // at this place can be added others supported types of collection
            // objects
            else {
                columnNames = new String[1];
                columnNames[0] = "";
                tableData = new Object[1][1];
                tableData[0][0] = new String("Unsupported class report!");
            }
        } else {
            columnNames = new String[1];
            columnNames[0] = "";
            tableData = new Object[1][1];
            tableData[0][0] = new String("Sorry, there are no records that match your search");
        }
    }

    // this method if for determining if class if one of our class from enum
    // classTypes
    public static boolean contains(String test) {

        for (classTypes c : classTypes.values()) {
            String tmp = "edu.pjwstk.jps.model." + c.name();
            if (tmp.equals(test)) {
                return true;
            }
        }

        return false;
    }

    // this is method to create single result row if needed for any Object type
    // (String, Integer etc)
    public CreateTable(Object result) {
        this.result = result;
        columnNames = new String[1];
        columnNames[0] = "Result";
        tableData = new Object[1][1];
        tableData[0][0] = this.result;
    }

    // method for setting column names
    private void setColumnNames() {
        columnNames = new String[classFields.length];
        for (int i = 0; i < classFields.length; i++) {
            columnNames[i] = classFields[i].getName();
        }
    }

    // method for inputing data into jTable cells
    private void setDataModel() {
        tableData = new Object[resultSet.size()][classFields.length];
        // for every object from collection of objects
        for (int i = 0; i < resultSet.size(); i++) {
            // for every fields from class of object
            for (int j = 0; j < classFields.length; j++) {
                // for every method from class of object
                for (int x = 0; x < classMethods.length; x++) {
                    // if class name start with 'get' and class method name
                    // lenght is equal to field name + 3 (becouse of get word)
                    if ((classMethods[x].getName().startsWith("get"))
                            && (classMethods[x].getName().length() == (classFields[j].getName().length() + 3))) {
                        // if class name to lowercase ends with field name to
                        // lower case
                        if (classMethods[x].getName().toLowerCase().endsWith(classFields[j].getName().toLowerCase())) {
                            // then try to invoke that getter
                            try {
                                tableData[i][j] = classMethods[x].invoke(resultSet.get(i));
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
                // This is alternative method for obtaining getter for field but
                // it will need public fields
                // try {
                // fieldValue = pola[j].get(resultSet.get(i));
                // data[i][j]=fieldValue;
                // } catch (IllegalArgumentException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // } catch (IllegalAccessException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
            }
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return tableData.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Class getColumnClass(int c) {
        Class columnClass;
        try {
            columnClass = getValueAt(0, c).getClass();
        } catch (NullPointerException e) {
            columnClass = String.class;
        }
        return columnClass;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
    }
}
