package com.dvt;

public interface TaxInterface {
    double getMonthlyTaxPayable();
    double getMonthlyTaxableIncome();
    double getMonthlyTaxFreeAllowance();
    void calculateTaxAmountDue(double annualGrossSalary);
}
