package com.dvt;

public interface SalarySlipInterface {
    void calculateInsuranceAmountDue();
    void convertSalaryToMonthly();
    double getMonthlyInsuranceContribution();
    double getInsuranceContributionRate();
    Employee getEmployee();
    double getMonthlyGrossSalary();
}
