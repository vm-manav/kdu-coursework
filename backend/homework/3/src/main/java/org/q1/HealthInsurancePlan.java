package org.q1;

public abstract class HealthInsurancePlan {
    private InsuranceBrand offeredBy;
    private double coverage;
    private int discount;

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
    public double getCoverage(){
        return coverage;
    }
    public void setCoverage(double coverage){
        this.coverage=coverage;
    }
    public int getDiscount(){
        return discount;
    }
    public void setDiscount(int discount){
        this.discount=discount;
    }

    public abstract double computeMonthlyPremium(double salary);
    public abstract double computeMonthlyPremium(double salary,int age,boolean smoking);
}
