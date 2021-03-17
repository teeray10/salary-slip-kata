package com.dvt;

public interface InsuranceProfileInterface {
    void calculateInsuranceContribution(double annualGrossSalary);
    double getInsuranceRate();
    double getMonthlyInsuranceContribution();
}
