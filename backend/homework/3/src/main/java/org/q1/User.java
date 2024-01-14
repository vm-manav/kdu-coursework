package org.q1;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private int age;
    private boolean smoking;

    HealthInsurancePlan insurancePlan=null;

    public HealthInsurancePlan getInsurancePlan(){
        return insurancePlan;
    }
    public void setInsurancePlan(HealthInsurancePlan insurancePlan){
        this.insurancePlan=insurancePlan;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public boolean getSmoking(){
        return smoking;
    }
    public void setSmoking(boolean smoking){
        this.smoking=smoking;
    }
}
