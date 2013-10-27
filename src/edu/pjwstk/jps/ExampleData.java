package edu.pjwstk.jps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class ExampleData {

    private List<Car> cars;
    private List<Company> companies;
    private List<CompanyBranch> companyBranches;
    private List<Labor> labors;
    private List<RentCar> rentCars;
    private List<Title> titles;
    private List<Training> trainings;
    private List<TrainingAssignment> trainingAssignments;
    private List<TrainingCompany> trainingCompanies;
    private List<TrainingProduct> trainingProducts;

    public ExampleData() {
        initData();
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public List<CompanyBranch> getCompanyBranches() {
        return companyBranches;
    }

    public List<Labor> getLabors() {
        return labors;
    }

    public List<RentCar> getRentCars() {
        return rentCars;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public List<TrainingAssignment> getTrainingAssignments() {
        return trainingAssignments;
    }

    public List<TrainingCompany> getTrainingCompanies() {
        return trainingCompanies;
    }

    public List<TrainingProduct> getTrainingProducts() {
        return trainingProducts;
    }

    private void initData() {
        cars = new ArrayList<Car>();
        companies = new ArrayList<Company>();
        companyBranches = initBranches();
        labors = new ArrayList<Labor>();
        rentCars = new ArrayList<RentCar>();
        titles = initTitles();
        trainingCompanies = initTrainingCompanies();
        trainingProducts = initTrainingProducts();
        trainings = initTrainings(trainingProducts, trainingCompanies);
        trainingAssignments = new ArrayList<TrainingAssignment>();
    }
    
    private List<CompanyBranch> initBranches() {
        List<CompanyBranch> result = new ArrayList<CompanyBranch>();
        
        result.add(new CompanyBranch("headquaters"));
        result.add(new CompanyBranch("branch"));
        result.add(new CompanyBranch("department"));
        result.add(new CompanyBranch("section"));
        result.add(new CompanyBranch("office"));
        
        return result;
    }
    
    private List<Title> initTitles() {
        List<Title> result = new ArrayList<Title>();
        
        result.add(new Title("Managing Director"));
        result.add(new Title("Senior Manager"));
        result.add(new Title("Manager"));
        result.add(new Title("Salesman"));
        result.add(new Title("Senior Technicial"));
        result.add(new Title("Technician"));
        result.add(new Title("Assistant"));
        result.add(new Title("Senior Specialist"));
        result.add(new Title("Specialist"));
        
        return result;
    }
    
    private List<TrainingCompany> initTrainingCompanies() {
        List<TrainingCompany> result = new ArrayList<TrainingCompany>();
        
        result.add(new TrainingCompany("ABC Trainings", "info@abct.com", "555-123-345"));
        result.add(new TrainingCompany("Workshop factory", "contact@wf.com", "555-987-567"));
        result.add(new TrainingCompany("Skills develop", "info@skdv.com", "555-765-234"));
        result.add(new TrainingCompany("Learning center", "contact@lc.com", "555-675-357"));
        
        return result;
    }
    
    private List<TrainingProduct> initTrainingProducts() {
        List<TrainingProduct> result = new ArrayList<TrainingProduct>();
        
        result.add(new TrainingProduct(1 + "", "Consulting"));
        result.add(new TrainingProduct(2 + "", "Saling"));
        result.add(new TrainingProduct(3 + "", "Presenting"));
        result.add(new TrainingProduct(4 + "", "Negotiating"));
        result.add(new TrainingProduct(5 + "", "Managing"));
        
        return result;
    }
    
    private List<Training> initTrainings(List<TrainingProduct> products, List<TrainingCompany> companies) {
        List<Training> result = new ArrayList<Training>();
        Random rand = new Random();
        
        ArrayList<String> firstPart = new ArrayList<String>();
        firstPart.add("Entry");
        firstPart.add("Beginners");
        firstPart.add("Semi-intermidiate");
        firstPart.add("Intermidiate");
        firstPart.add("Advanced");
        firstPart.add("Expert");
        
        ArrayList<String> thirdPart = new ArrayList<String>();
        thirdPart.add("Course");
        thirdPart.add("Workshop");
        thirdPart.add("Training");
        
        ArrayList<TrainingCompany> locCompanies = new ArrayList<TrainingCompany>(companies);
        for (String fp : firstPart) {
            for (TrainingProduct prod : products) {
                for (String tp : thirdPart) {
                    result.add(new Training(fp + " " + prod.getFullName() + " " + tp,
                            locCompanies.get(rand.nextInt(locCompanies.size() - 1)),
                            prod, rand.nextInt(13) + 1));
                }
            }
        }

        return result;
    }
}
