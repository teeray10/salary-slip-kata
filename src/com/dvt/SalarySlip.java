package com.dvt;

import static java.lang.Double.valueOf;

public class SalarySlip implements SalarySlipInterface {
    private Employee employee = null;

    private double monthlyGrossSalary = 0.0;

    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1 = 8060.0;
    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 = 43000.0;
    private double annualInsuranceContribution = 0.0;
    private double monthlyInsuranceContribution = 0.0;
    private double insuranceContributionRate = 0.0;

    public SalarySlip() {
    }

    public SalarySlip(Employee employee) {
        this.employee = employee;

        calculateInsuranceAmountDue();
        calculateInsuranceContributionRate();
        convertSalaryToMonthly();
    }

    public void calculateInsuranceContributionRate() {
        if (employee.getAnnualGrossSalary() > 43000.0)
            insuranceContributionRate = 0.02;
        else if (employee.getAnnualGrossSalary() > 8060.0)
            insuranceContributionRate = 0.12;
    }

    public void calculateInsuranceAmountDue() {
        if (employee.getAnnualGrossSalary() > ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2) {
            calculateLevel2InsuranceContribution();
        } else if ((employee.getAnnualGrossSalary() > ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1))
            calculateLevel1InsuranceContribution();

        if (this.annualInsuranceContribution > 0.0)
            convertInsuranceToMonthly();
    }

    private void convertInsuranceToMonthly() {
        this.monthlyInsuranceContribution = annualInsuranceContribution / 12.0;
    }

    private void calculateLevel1InsuranceContribution() {
        this.annualInsuranceContribution += (employee.getAnnualGrossSalary() - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * 0.12;
    }

    private void calculateLevel2InsuranceContribution() {
        this.annualInsuranceContribution += (employee.getAnnualGrossSalary() - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2) * 0.02;
        this.annualInsuranceContribution += (ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * 0.12;
    }

    public void convertSalaryToMonthly() {
        this.monthlyGrossSalary = this.employee.getAnnualGrossSalary() / 12.0;
    }

    public double getMonthlyInsuranceContribution() {
        return format(monthlyInsuranceContribution);
    }

    public double getInsuranceContributionRate() {
        return format(insuranceContributionRate);
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public double getMonthlyGrossSalary() {
        return format(this.monthlyGrossSalary);
    }

    public double format(double preFormat) {
        return FormatDecimals.calculate(preFormat);
    }
}
