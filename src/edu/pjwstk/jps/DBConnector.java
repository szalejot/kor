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

    private ObjectContainer dbConn;

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
        ObjectSet<Car> cars = con.query(Car.class);
        if (cars.isEmpty()) {
            for (Car c : data.getCars()) {
                con.store(c);
            }
        }
        ObjectSet<Company> companies = con.query(Company.class);
        if (companies.isEmpty()) {
            for (Company c : data.getCompanies()) {
                con.store(c);
            }
        }
        ObjectSet<CompanyBranch> companyBranches = con.query(CompanyBranch.class);
        if (companyBranches.isEmpty()) {
            for (CompanyBranch c : data.getCompanyBranches()) {
                con.store(c);
            }
        }
        ObjectSet<Labor> labors = con.query(Labor.class);
        if (labors.isEmpty()) {
            for (Labor c : data.getLabors()) {
                con.store(c);
            }
        }
        ObjectSet<RentCar> rentCars = con.query(RentCar.class);
        if (rentCars.isEmpty()) {
            for (RentCar c : data.getRentCars()) {
                con.store(c);
            }
        }
        ObjectSet<Title> titles = con.query(Title.class);
        if (titles.isEmpty()) {
            for (Title c : data.getTitles()) {
                con.store(c);
            }
        }
        ObjectSet<Training> trainings = con.query(Training.class);
        if (trainings.isEmpty()) {
            for (Training c : data.getTrainings()) {
                con.store(c);
            }
        }
        ObjectSet<TrainingAssignment> trainingAssingments = con.query(TrainingAssignment.class);
        if (trainingAssingments.isEmpty()) {
            for (TrainingAssignment c : data.getTrainingAssignments()) {
                con.store(c);
            }
        }
        ObjectSet<TrainingCompany> trainingCompanies = con.query(TrainingCompany.class);
        if (trainingCompanies.isEmpty()) {
            for (TrainingCompany c : data.getTrainingCompanies()) {
                con.store(c);
            }
        }
        ObjectSet<TrainingProduct> trainigProducts = con.query(TrainingProduct.class);
        if (trainigProducts.isEmpty()) {
            for (TrainingProduct c : data.getTrainingProducts()) {
                con.store(c);
            }
        }
        con.commit();
    }
}
