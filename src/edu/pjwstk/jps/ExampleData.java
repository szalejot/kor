package edu.pjwstk.jps;

import java.util.ArrayList;
import java.util.Date;
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
    
    private static int NUM_OF_LABOR_PER_COMP = 30;
    private static int NUM_OF_CARS_PER_COMP = 10;
    private static int NUM_OF_RENTS_PER_COMP = 10000;

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
    
    private Random rand;

    public ExampleData() {
        rand = new Random();
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
        System.out.println("Starting example data initialization...");
        System.out.println("-- cars");
        cars = new ArrayList<Car>();
        System.out.println("-- companyBranches");
        companyBranches = initBranches();
        System.out.println("-- labors");
        labors = new ArrayList<Labor>();
        System.out.println("-- rentCars");
        rentCars = new ArrayList<RentCar>();
        System.out.println("-- titles");
        titles = initTitles();
        System.out.println("-- companies");
        companies = initCompanies(0, null, 3);
        System.out.println("-- trainingCompanies");
        trainingCompanies = initTrainingCompanies();
        System.out.println("-- trainingProducts");
        trainingProducts = initTrainingProducts();
        System.out.println("-- trainings");
        trainings = initTrainings();
        System.out.println("-- trainingAssignments");
        trainingAssignments = initTrainingAssignments();
        System.out.println("... initialization completed.");
    }
    
    private List<TrainingAssignment> initTrainingAssignments() {
        List <TrainingAssignment> result = new ArrayList<TrainingAssignment>();
        
        int numOfTrainings = labors.size() * 5;
        long milisInDay = 24 * 3600 * 1000;
        Date today = new Date();
        for (int i = 0; i < numOfTrainings; i++) {
            Labor labor = labors.get(rand.nextInt(labors.size() - 1));
            Training training = trainings.get(rand.nextInt(trainings.size() - 1));
            Date date = new Date(today.getTime() - milisInDay * rand.nextInt(365));
            result.add(new TrainingAssignment(labor, training, date));
        }
        
        return result;
    }
    
    private List<Company> initCompanies(int lvl, Company parent, int branchesPerLevel) {
        List<Company> result = new ArrayList<Company>();
        
        String name = companyBranches.get(lvl).getName() + " nr " + rand.nextInt(1000);
        
        Company comp = new Company(name, parent, companyBranches.get(lvl));
        if (parent == null) {
            comp.setPartOf(comp);
        }
        result.add(comp);
        
        List<Car> compCars = initCarsPerCompany(comp);
        List<Labor> compLabor = initLaborsPerCompany(comp);
        
        Labor director = compLabor.get(0);
        director.setTitle(titles.get(0));
        labors.addAll(compLabor);
        cars.addAll(compCars);
        
        long milisInDay = 24 * 3600 * 1000;
        Date today = new Date();
        for (int i = 0; i < NUM_OF_RENTS_PER_COMP; i++) {
            Labor labor = compLabor.get(rand.nextInt(compLabor.size() - 1));
            Car car = compCars.get(rand.nextInt(compCars.size() - 1));
            Date date = new Date(today.getTime() - milisInDay * rand.nextInt(365));
            rentCars.add(new RentCar(labor, car, date));
        }
        
        if (lvl < companyBranches.size() - 1) {
            for (int i = 0; i < branchesPerLevel; i++) {
                result.addAll(initCompanies(lvl + 1, comp, branchesPerLevel));
            }
        }
        
        return result;
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
    
    private List<Training> initTrainings() {
        List<Training> result = new ArrayList<Training>();
        
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
        
        for (String fp : firstPart) {
            for (TrainingProduct prod : trainingProducts) {
                for (String tp : thirdPart) {
                    result.add(new Training(fp + " " + prod.getFullName() + " " + tp,
                            trainingCompanies.get(rand.nextInt(trainingCompanies.size() - 1)),
                            prod, rand.nextInt(13) + 1));
                }
            }
        }

        return result;
    }
    
    private List<Car> initCarsPerCompany(Company company) {
        List<Car> result = new ArrayList<Car>();
        
        List<String> manufacturers = new ArrayList<String>();
        manufacturers.add("Ford");
        manufacturers.add("Kia");
        manufacturers.add("Fiat");
        manufacturers.add("Toyota");
        manufacturers.add("Volkswagen");
        manufacturers.add("Opel");
        
        List<String> types = new ArrayList<String>();
        types.add("sedan");
        types.add("hatchback");
        types.add("combi");
        types.add("van");
        types.add("limousine");
        
        List<String> colors = new ArrayList<String>();
        colors.add("black");
        colors.add("white");
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("silver");
        
        long milisInDay = 24 * 3600 * 1000;
        Date today = new Date();
        
        for (int i = 0; i < NUM_OF_CARS_PER_COMP; i++) {
            int nr = rand.nextInt(100);
            String manufacturer = manufacturers.get(rand.nextInt(manufacturers.size() - 1));
            String type = types.get(rand.nextInt(types.size() - 1));
            String color = colors.get(rand.nextInt(colors.size() - 1));
            Date prodDate = new Date(today.getTime() - rand.nextInt(5000) * milisInDay);
            result.add(new Car(nr, manufacturer, type, color, prodDate, company));
        }
        
        return result;
    }
    
    private List<Labor> initLaborsPerCompany(Company company) {
        List<Labor> result = new ArrayList<Labor>();
        
        for (int i = 0; i < NUM_OF_LABOR_PER_COMP; i++) {
            result.add(initLabor(company));
        }

        return result;
    }
    
    private Labor initLabor(Company company) {
        List<String> femaleLNames = new ArrayList<String>();
        femaleLNames.add("NOWAK");
        femaleLNames.add("KOWALSKA");
        femaleLNames.add("WI\u0139\u0161NIEWSKA");
        femaleLNames.add("W\u00d3JCIK");
        femaleLNames.add("KOWALCZYK");
        femaleLNames.add("KAMI\u0143SKA");
        femaleLNames.add("LEWANDOWSKA");
        femaleLNames.add("ZIELI\u0143SKA");
        femaleLNames.add("SZYMA\u0143SKA");
        femaleLNames.add("WO\u0139\u00bbNIAK");
        femaleLNames.add("D\u0104BROWSKA");
        femaleLNames.add("KOZ\u0143OWSKA");
        femaleLNames.add("JANKOWSKA");
        femaleLNames.add("MAZUR");
        femaleLNames.add("WOJCIECHOWSKA");
        femaleLNames.add("KWIATKOWSKA");
        femaleLNames.add("KRAWCZYK");
        femaleLNames.add("PIOTROWSKA");
        femaleLNames.add("KACZMAREK");
        femaleLNames.add("GRABOWSKA");
        femaleLNames.add("PAW\u0143OWSKA");
        femaleLNames.add("MICHALSKA");
        femaleLNames.add("ZAJ\u0104C");
        femaleLNames.add("KR\u00d3L");
        femaleLNames.add("JAB\u0143O\u0143SKA");
        femaleLNames.add("WIECZOREK");
        femaleLNames.add("NOWAKOWSKA");
        femaleLNames.add("WR\u00d3BEL");
        femaleLNames.add("MAJEWSKA");
        femaleLNames.add("OLSZEWSKA");
        femaleLNames.add("ST\u0118PIE\u0143");
        femaleLNames.add("JAWORSKA");
        femaleLNames.add("MALINOWSKA");
        femaleLNames.add("ADAMCZYK");
        femaleLNames.add("NOWICKA");
        femaleLNames.add("G\u00d3RSKA");
        femaleLNames.add("DUDEK");
        femaleLNames.add("PAWLAK");
        femaleLNames.add("WITKOWSKA");
        femaleLNames.add("WALCZAK");
        femaleLNames.add("RUTKOWSKA");
        femaleLNames.add("SIKORA");
        femaleLNames.add("BARAN");
        femaleLNames.add("MICHALAK");
        femaleLNames.add("SZEWCZYK");
        femaleLNames.add("OSTROWSKA");
        femaleLNames.add("TOMASZEWSKA");
        femaleLNames.add("PIETRZAK");
        femaleLNames.add("JASI\u0143SKA");
        femaleLNames.add("WR\u00d3BLEWSKA");
        List<String> femaleFNames = new ArrayList<String>();
        femaleFNames.add("ANNA");
        femaleFNames.add("MARIA");
        femaleFNames.add("KATARZYNA");
        femaleFNames.add("MA\u0143GORZATA");
        femaleFNames.add("AGNIESZKA");
        femaleFNames.add("KRYSTYNA");
        femaleFNames.add("BARBARA");
        femaleFNames.add("EWA");
        femaleFNames.add("EL\u0139\u00bbBIETA");
        femaleFNames.add("ZOFIA");
        femaleFNames.add("JANINA");
        femaleFNames.add("TERESA");
        femaleFNames.add("JOANNA");
        femaleFNames.add("MAGDALENA");
        femaleFNames.add("MONIKA");
        femaleFNames.add("JADWIGA");
        femaleFNames.add("DANUTA");
        femaleFNames.add("IRENA");
        femaleFNames.add("HALINA");
        femaleFNames.add("HELENA");
        femaleFNames.add("BEATA");
        femaleFNames.add("ALEKSANDRA");
        femaleFNames.add("MARTA");
        femaleFNames.add("DOROTA");
        femaleFNames.add("MARIANNA");
        femaleFNames.add("GRA\u0139\u00bbYNA");
        femaleFNames.add("JOLANTA");
        femaleFNames.add("STANIS\u0143AWA");
        femaleFNames.add("IWONA");
        femaleFNames.add("KAROLINA");
        femaleFNames.add("BO\u0139\u00bbENA");
        femaleFNames.add("URSZULA");
        femaleFNames.add("JUSTYNA");
        femaleFNames.add("RENATA");
        femaleFNames.add("ALICJA");
        femaleFNames.add("PAULINA");
        femaleFNames.add("SYLWIA");
        femaleFNames.add("NATALIA");
        femaleFNames.add("WANDA");
        femaleFNames.add("AGATA");
        femaleFNames.add("ANETA");
        femaleFNames.add("IZABELA");
        femaleFNames.add("EWELINA");
        femaleFNames.add("MARZENA");
        femaleFNames.add("WIES\u0143AWA");
        femaleFNames.add("GENOWEFA");
        femaleFNames.add("PATRYCJA");
        femaleFNames.add("KAZIMIERA");
        femaleFNames.add("EDYTA");
        femaleFNames.add("STEFANIA");
        List<String> maleLNames = new ArrayList<String>();
        maleLNames.add("NOWAK");
        maleLNames.add("KOWALSKI");
        maleLNames.add("WI\u0139\u0161NIEWSKI");
        maleLNames.add("W\u00d3JCIK");
        maleLNames.add("KOWALCZYK");
        maleLNames.add("KAMI\u0143SKI");
        maleLNames.add("LEWANDOWSKI");
        maleLNames.add("ZIELI\u0143SKI");
        maleLNames.add("WO\u0139\u00bbNIAK");
        maleLNames.add("SZYMA\u0143SKI");
        maleLNames.add("D\u0104BROWSKI");
        maleLNames.add("KOZ\u0143OWSKI");
        maleLNames.add("JANKOWSKI");
        maleLNames.add("MAZUR");
        maleLNames.add("WOJCIECHOWSKI");
        maleLNames.add("KWIATKOWSKI");
        maleLNames.add("KRAWCZYK");
        maleLNames.add("KACZMAREK");
        maleLNames.add("PIOTROWSKI");
        maleLNames.add("GRABOWSKI");
        maleLNames.add("ZAJ\u0104C");
        maleLNames.add("PAW\u0143OWSKI");
        maleLNames.add("KR\u00d3L");
        maleLNames.add("MICHALSKI");
        maleLNames.add("WR\u00d3BEL");
        maleLNames.add("WIECZOREK");
        maleLNames.add("JAB\u0143O\u0143SKI");
        maleLNames.add("NOWAKOWSKI");
        maleLNames.add("MAJEWSKI");
        maleLNames.add("ST\u0118PIE\u0143");
        maleLNames.add("OLSZEWSKI");
        maleLNames.add("JAWORSKI");
        maleLNames.add("MALINOWSKI");
        maleLNames.add("DUDEK");
        maleLNames.add("ADAMCZYK");
        maleLNames.add("PAWLAK");
        maleLNames.add("G\u00d3RSKI");
        maleLNames.add("NOWICKI");
        maleLNames.add("SIKORA");
        maleLNames.add("WALCZAK");
        maleLNames.add("WITKOWSKI");
        maleLNames.add("BARAN");
        maleLNames.add("RUTKOWSKI");
        maleLNames.add("MICHALAK");
        maleLNames.add("SZEWCZYK");
        maleLNames.add("OSTROWSKI");
        maleLNames.add("TOMASZEWSKI");
        maleLNames.add("PIETRZAK");
        maleLNames.add("ZALEWSKI");
        maleLNames.add("WR\u00d3BLEWSKI");
        List<String> maleFNames = new ArrayList<String>();
        maleFNames.add("JAN");
        maleFNames.add("ANDRZEJ");
        maleFNames.add("PIOTR");
        maleFNames.add("KRZYSZTOF");
        maleFNames.add("STANIS\u0143AW");
        maleFNames.add("TOMASZ");
        maleFNames.add("PAWE\u0141");
        maleFNames.add("J\u00d3ZEF");
        maleFNames.add("MARCIN");
        maleFNames.add("MAREK");
        maleFNames.add("MICHA\u0143");
        maleFNames.add("GRZEGORZ");
        maleFNames.add("JERZY");
        maleFNames.add("TADEUSZ");
        maleFNames.add("ADAM");
        maleFNames.add("\u0143UKASZ");
        maleFNames.add("ZBIGNIEW");
        maleFNames.add("RYSZARD");
        maleFNames.add("DARIUSZ");
        maleFNames.add("HENRYK");
        maleFNames.add("MARIUSZ");
        maleFNames.add("KAZIMIERZ");
        maleFNames.add("WOJCIECH");
        maleFNames.add("ROBERT");
        maleFNames.add("MATEUSZ");
        maleFNames.add("MARIAN");
        maleFNames.add("RAFA\u0143");
        maleFNames.add("JACEK");
        maleFNames.add("JANUSZ");
        maleFNames.add("MIROS\u0143AW");
        maleFNames.add("MACIEJ");
        maleFNames.add("S\u0143AWOMIR");
        maleFNames.add("JAROS\u0143AW");
        maleFNames.add("KAMIL");
        maleFNames.add("WIES\u0143AW");
        maleFNames.add("ROMAN");
        maleFNames.add("W\u0143ADYS\u0143AW");
        maleFNames.add("JAKUB");
        maleFNames.add("ARTUR");
        maleFNames.add("ZDZIS\u0143AW");
        maleFNames.add("EDWARD");
        maleFNames.add("MIECZYS\u0143AW");
        maleFNames.add("DAMIAN");
        maleFNames.add("DAWID");
        maleFNames.add("PRZEMYS\u0141AW");
        maleFNames.add("SEBASTIAN");
        maleFNames.add("CZES\u0143AW");
        maleFNames.add("LESZEK");
        maleFNames.add("DANIEL");
        maleFNames.add("WALDEMAR");
        
        String fName;
        String lName;
        Character sex;
        long milisInDay = 24 * 3600 * 1000;

        if (rand.nextBoolean()) {
            sex = 'F';
            fName = femaleFNames.get(rand.nextInt(femaleFNames.size() - 1));
            lName = femaleLNames.get(rand.nextInt(femaleLNames.size() - 1));
        } else {
            sex = 'M';
            fName = maleFNames.get(rand.nextInt(maleFNames.size() - 1));
            lName = maleLNames.get(rand.nextInt(maleLNames.size() - 1));
        }
        Date today = new Date();
        Date birthDate = new Date(today.getTime() - 18 * 365 * milisInDay - milisInDay * rand.nextInt(16425));
        Date workSince = new Date(today.getTime() - milisInDay * rand.nextInt(8000));
        Date hasTitleSince = new Date(today.getTime() - milisInDay * rand.nextInt(4000));
        // nie chcemy roli dyrektora, dyrektora stworzymy podczas tworzenia
        // nowej company
        Title title = titles.get(rand.nextInt(titles.size() - 2) + 1);
        return new Labor(fName, lName, birthDate, sex, company, title, rand.nextInt(10000) + 2000d, hasTitleSince,
                workSince);

    }
}
