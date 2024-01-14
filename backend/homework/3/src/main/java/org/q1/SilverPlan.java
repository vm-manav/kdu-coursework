package org.q1;

public class SilverPlan extends HealthInsurancePlan{
    public SilverPlan(){
        this.setCoverage(0.7);
        this.setDiscount(30);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary*0.06;
    }
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

}
