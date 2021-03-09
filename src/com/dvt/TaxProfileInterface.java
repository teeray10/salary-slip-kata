package com.dvt;

public interface TaxProfileInterface {
    double getMonthlyTaxPayable();
    double getMonthlyTaxableIncome();
    double getMonthlyTaxFreeAllowance();
    void calculateTaxAmountDue(double annualGrossSalary);
}
