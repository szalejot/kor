package edu.pjwstk.jps;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import edu.pjwstk.jps.model.Car;
import edu.pjwstk.jps.model.Company;
import edu.pjwstk.jps.model.CompanyBranch;
import edu.pjwstk.jps.model.Labor;
import edu.pjwstk.jps.model.RentCar;
import edu.pjwstk.jps.model.Title;
import edu.pjwstk.jps.model.Training;
import edu.pjwstk.jps.model.TrainingAssignment;
import edu.pjwstk.jps.model.TrainingCompany;
import edu.pjwstk.jps.model.TrainingProduct;

public class DBConnector {

    private static ObjectContainer dbConn = null;

    public ObjectContainer getConnection() {
        if (dbConn == null) {
            dbConn = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "test.db");
        }
        return dbConn;
    }
    
    public void close() {
        getConnection().close();
    }

    public void initializeDB() {
        ObjectContainer con = getConnection();
        ExampleData data = new ExampleData();
        ObjectSet<Company> companies = con.query(Company.class);
        if (companies.isEmpty()) {
            for (Car c : data.getCars()) {
                con.store(c);
            }
            for (Company c : data.getCompanies()) {
                con.store(c);
            }
            for (CompanyBranch c : data.getCompanyBranches()) {
                con.store(c);
            }
            for (Labor c : data.getLabors()) {
                con.store(c);
            }
            for (RentCar c : data.getRentCars()) {
                con.store(c);
            }
            for (Title c : data.getTitles()) {
                con.store(c);
            }
            for (Training c : data.getTrainings()) {
                con.store(c);
            }
            for (TrainingAssignment c : data.getTrainingAssignments()) {
                con.store(c);
            }
            for (TrainingCompany c : data.getTrainingCompanies()) {
                con.store(c);
            }
            for (TrainingProduct c : data.getTrainingProducts()) {
                con.store(c);
            }
            con.commit();
        }
    }
}
