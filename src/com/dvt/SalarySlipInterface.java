package com.dvt;

public interface SalarySlipInterface {
    void calculateInsuranceAmountDue();
    void calculateTaxAmountDue();
    void convertSalaryToMonthly();
    double getMonthlyTaxPayable();
    double getMonthlyTaxableIncome();
    double getMonthlyTaxFreeAllowance();
    double getAnnualInsuranceContribution();
    double getMonthlyInsuranceContribution();
    Employee getEmployee();
    double getMonthlyGrossSalary();
}
