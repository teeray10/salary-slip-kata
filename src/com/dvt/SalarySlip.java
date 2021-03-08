package com.dvt;

import static java.lang.Double.valueOf;

public class SalarySlip implements SalarySlipInterface {
    private Employee employee = null;
    private double annualGrossSalary = 0.00;
    private double monthlyGrossSalary = 0.00;

    private final static double ANNUAL_TAX_LIMIT_LEVEL_1 = 11000.00;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_2 = 43000.00;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_3 = 100000.00;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_4 = 150000.00;
    private final static double TAX_RATE_LEVEL_1 = 0.2;
    private final static double TAX_RATE_LEVEL_2 = 0.4;
    private final static double TAX_RATE_LEVEL_3 = 0.45;
    private double annualTaxableIncome = 0.00;
    private double monthlyTaxableIncome = 0.00;
    private double annualTaxPayable = 0.00;
    private double monthlyTaxPayable = 0.00;
    private double monthlyTaxFreeAllowance = 0.00;
    private double annualTaxFreeAllowance = ANNUAL_TAX_LIMIT_LEVEL_1;

    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1 = 8060.00;
    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 = 43000.00;
    private double annualInsuranceContribution = 0.00;
    private double monthlyInsuranceContribution = 0.00;

    public SalarySlip() {
    }

    public SalarySlip(Employee employee) {
        this.employee = employee;

        calculateTaxAmountDue();
        calculateInsuranceAmountDue();
        convertSalaryToMonthly();
    }

    public void calculateTaxAmountDue() {
        annualGrossSalary = this.employee.getAnnualGrossSalary();
        double tempSalary = annualGrossSalary;

        if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_4) {
            calculateLevel4Tax();
        }
        if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_2) {
            calculateLevel2Tax();
            if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_3) {
                calculateLevel3Tax(tempSalary);
            }
        } else if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_1)
            calculateLevel1Tax();
        if (annualTaxableIncome > 0.0) {
            convertAnnualTaxToMonthly(annualTaxableIncome, annualTaxPayable, annualTaxFreeAllowance);
        }
    }

    private void calculateLevel1Tax() {
        annualTaxableIncome = annualGrossSalary - ANNUAL_TAX_LIMIT_LEVEL_1;
        annualTaxPayable = annualTaxableIncome * TAX_RATE_LEVEL_1;
    }

    private void calculateLevel2Tax() {
        annualTaxableIncome += (annualGrossSalary - ANNUAL_TAX_LIMIT_LEVEL_2) + (ANNUAL_TAX_LIMIT_LEVEL_2 - ANNUAL_TAX_LIMIT_LEVEL_1);
        annualTaxPayable += ((annualGrossSalary - ANNUAL_TAX_LIMIT_LEVEL_2) * TAX_RATE_LEVEL_2) +
                ((ANNUAL_TAX_LIMIT_LEVEL_2 - ANNUAL_TAX_LIMIT_LEVEL_1) * TAX_RATE_LEVEL_1);
    }

    private void calculateLevel3Tax(double tempSalary) {
        while (tempSalary > ANNUAL_TAX_LIMIT_LEVEL_3) {
            decrementAllowance();
            incrementTax();
            tempSalary -= 2.00;
        }
    }

    private void calculateLevel4Tax() {
        annualTaxableIncome = annualGrossSalary - ANNUAL_TAX_LIMIT_LEVEL_4;
        annualTaxPayable = annualTaxableIncome * TAX_RATE_LEVEL_3;
    }

    private void decrementAllowance() {
        if (annualTaxFreeAllowance > 0.00)
            annualTaxFreeAllowance -= 1.00;
    }

    private void incrementTax() {
        if (annualTaxableIncome < annualGrossSalary) {
            annualTaxableIncome += 1.00;
            annualTaxPayable += TAX_RATE_LEVEL_2;
        }
    }

    private void convertAnnualTaxToMonthly(double annualTaxableIncome, double annualTaxPayable, double annualTaxFreeAllowance) {
        this.monthlyTaxableIncome = annualTaxableIncome / 12.00;
        this.monthlyTaxPayable = annualTaxPayable / 12.00;
        monthlyTaxFreeAllowance = annualTaxFreeAllowance / 12.00;
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
        this.monthlyInsuranceContribution = annualInsuranceContribution / 12.00;
    }

    private void calculateLevel1InsuranceContribution() {
        this.annualInsuranceContribution += (employee.getAnnualGrossSalary() - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * 0.12;
    }

    private void calculateLevel2InsuranceContribution() {
        this.annualInsuranceContribution += (employee.getAnnualGrossSalary() - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2) * 0.02;
        this.annualInsuranceContribution += (ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * 0.12;
    }

    public void convertSalaryToMonthly() {
        this.monthlyGrossSalary = this.employee.getAnnualGrossSalary() / 12.00;
    }

    public double getMonthlyTaxPayable() {
        return format(this.monthlyTaxPayable);
    }

    public double getMonthlyTaxableIncome() {
        return format(monthlyTaxableIncome);
    }

    public double getMonthlyTaxFreeAllowance() {
        if (monthlyTaxFreeAllowance <= 0.00)
            return 0.00;
        return format(monthlyTaxFreeAllowance);
    }

    public double getAnnualInsuranceContribution() {
        return format(this.annualInsuranceContribution);
    }

    public double getMonthlyInsuranceContribution() {
        return format(monthlyInsuranceContribution);
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
