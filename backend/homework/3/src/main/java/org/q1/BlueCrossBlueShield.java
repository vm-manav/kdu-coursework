package org.q1;

public class BlueCrossBlueShield implements InsuranceBrand{
    private long id;
    private String name;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double computeMonthlyPremium(HealthInsurancePlan healthInsurancePlan, int age, boolean smoking) {

        if(healthInsurancePlan instanceof PlatinumPlan){
            double premiumValue=0.0;
            if(age>55){
                premiumValue+=200;
            }
            if(smoking){
                premiumValue+=100;
            }
            return premiumValue;
        } else if (healthInsurancePlan instanceof GoldPlan) {
            double premiumValue=0.0;
            if(age>55){
                premiumValue+=150;
            }
            if(smoking){
                premiumValue+=90;
            }
            return premiumValue;
        } else if (healthInsurancePlan instanceof SilverPlan) {
            double premiumValue=0.0;
            if(age>55){
                premiumValue+=100;
            }
            if(smoking){
                premiumValue+=80;
            }
            return premiumValue;
        } else if (healthInsurancePlan instanceof BronzePlan) {
            double premiumValue=0.0;
            if(age>55){
                premiumValue+=50;
            }
            if(smoking){
                premiumValue+=70;
            }
            return premiumValue;
        }else {
            return 0;
        }


    }
}
