package edu.pjwstk.jps.model;

public class Training {

    String name;
    TrainingCompany trainingCompany;
    TrainingProduct trainingProduct;
    Integer lenghtInDays;

    public Training(String name, TrainingCompany trainingCompany, TrainingProduct trainingProduct, Integer lenghtInDays) {
        super();
        this.name = name;
        this.trainingCompany = trainingCompany;
        this.trainingProduct = trainingProduct;
        this.lenghtInDays = lenghtInDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingCompany getTrainingCompany() {
        return trainingCompany;
    }

    public void setTrainingCompany(TrainingCompany trainingCompany) {
        this.trainingCompany = trainingCompany;
    }

    public Integer getLenghtInDays() {
        return lenghtInDays;
    }

    public void setLenghtInDays(Integer lenghtInDays) {
        this.lenghtInDays = lenghtInDays;
    }
    public TrainingProduct getTrainingProduct() {
        return trainingProduct;
    }

    public void setTrainingProduct(TrainingProduct trainingProduct) {
        this.trainingProduct = trainingProduct;
    }


    @Override
    public String toString() {
        return "Training [name=" + name + ", trainingCompany=" + trainingCompany + ", lenghtInDays=" + lenghtInDays
                + "]";
    }

}
