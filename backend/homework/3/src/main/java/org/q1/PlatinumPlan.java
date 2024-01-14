package org.q1;

public class PlatinumPlan extends HealthInsurancePlan{

    public PlatinumPlan(){
        this.setCoverage(0.9);
        this.setDiscount(50);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary*0.08;
    }

    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

}
