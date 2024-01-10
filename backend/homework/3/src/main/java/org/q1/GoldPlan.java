package org.q1;

public class GoldPlan extends HealthInsurancePlan{
    public GoldPlan(){
        this.setCoverage(0.8);
        this.setDiscount(40);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary*0.07;
    }
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

}
