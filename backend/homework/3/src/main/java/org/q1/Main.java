package org.q1;


public class Main {

    public static void main(String[] args) {
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        Patient patient = new Patient();
        patient.setInsurancePlan(insurancePlan);

        double[] payments = Billing.computePaymentAmount(patient, 1000.0);
        Logger.infoMessage(payments[0]);
        Logger.infoMessage(payments[1]);

        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan2 = new PlatinumPlan();

        insurancePlan2.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan2);
        double amount=insurancePlan2.computeMonthlyPremium(5000, 56, true);
        Logger.infoMessage(amount);

    }
}
