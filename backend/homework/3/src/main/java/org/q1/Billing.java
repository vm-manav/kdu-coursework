package org.q1;

public class Billing {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        double amountPaidByPatient=0.0;
        double amountPaidByInsuranceCompany=0.0;

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if(patientInsurancePlan instanceof PlatinumPlan || patientInsurancePlan instanceof  GoldPlan ||
                patientInsurancePlan instanceof SilverPlan || patientInsurancePlan instanceof BronzePlan){
            amountPaidByPatient=amount-amount*patientInsurancePlan.getCoverage()-patientInsurancePlan.getDiscount();
            amountPaidByInsuranceCompany=amount*patientInsurancePlan.getCoverage();

        }else {
            amountPaidByInsuranceCompany=0;
            amountPaidByPatient=amount-20;
        }

        if(amountPaidByPatient<0){
            payments[1]=0;
        }
        else{
            payments[1]=amountPaidByPatient;
        }

        if(amountPaidByInsuranceCompany<0){
            payments[0]=0;
        }else{
            payments[0]=amountPaidByInsuranceCompany;
        }

        return payments;
    }
}
