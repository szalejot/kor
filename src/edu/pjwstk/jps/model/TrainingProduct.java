package edu.pjwstk.jps.model;

public class TrainingProduct {
    private String code;
    private String fullName;
    
    public TrainingProduct(String code, String fullName){
        this.code=code;
        this.fullName=fullName;
    }

    @Override
    public String toString() {
        return "TrainingProduct [code=" + code + ", fullName=" + fullName + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
