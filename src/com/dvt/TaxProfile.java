package com.dvt;

public class TaxProfile implements TaxProfileInterface {
    private final static double ANNUAL_TAX_LIMIT_LEVEL_1 = 11000.0;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_2 = 43000.0;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_3 = 100000.0;
    private final static double ANNUAL_TAX_LIMIT_LEVEL_4 = 150000.0;
    private final static double TAX_RATE_LEVEL_1 = 0.2;
    private final static double TAX_RATE_LEVEL_2 = 0.4;
    private final static double TAX_RATE_LEVEL_3 = 0.45;
    private double annualTaxableIncome = 0.0;
    private double monthlyTaxableIncome = 0.0;
    private double annualTaxPayable = 0.0;
    private double monthlyTaxPayable = 0.0;
    private double monthlyTaxFreeAllowance = 0.0;
    private double annualTaxFreeAllowance = ANNUAL_TAX_LIMIT_LEVEL_1;

    private double annualGrossSalary = 0.0;

    public void calculateTaxAmountDue(double annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;

        if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_4) {
            calculateLevel4Tax();
        }
        if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_2) {
            calculateLevel2Tax();
            if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_3) {
                calculateLevel3Tax(annualGrossSalary);
            }
        } else if (annualGrossSalary > ANNUAL_TAX_LIMIT_LEVEL_1)
            calculateLevel1Tax();
        if (annualTaxableIncome > 0.0) {
            convertAnnualTaxToMonthly(annualTaxableIncome, annualTaxPayable, annualTaxFreeAllowance);
        }
    }

    public double getMonthlyTaxPayable() {
        return format(monthlyTaxPayable);
    }

    public double getMonthlyTaxableIncome() {
        return format(monthlyTaxableIncome);
    }

    public double getMonthlyTaxFreeAllowance() {
        if (monthlyTaxFreeAllowance <= 0.0)
            return 0.0;
        return format(monthlyTaxFreeAllowance);
    }

    public double format(double preFormat) {
        return FormatDecimals.calculate(preFormat);
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
            tempSalary -= 2.0;
        }
    }

    private void calculateLevel4Tax() {
        annualTaxableIncome = annualGrossSalary - ANNUAL_TAX_LIMIT_LEVEL_4;
        annualTaxPayable = annualTaxableIncome * TAX_RATE_LEVEL_3;
    }

    private void decrementAllowance() {
        if (annualTaxFreeAllowance > 0.0)
            annualTaxFreeAllowance -= 1.0;
    }

    private void incrementTax() {
        if (annualTaxableIncome < annualGrossSalary) {
            annualTaxableIncome += 1.0;
            annualTaxPayable += TAX_RATE_LEVEL_2;
        }
    }

    private void convertAnnualTaxToMonthly(double annualTaxableIncome, double annualTaxPayable, double annualTaxFreeAllowance) {
        monthlyTaxableIncome = annualTaxableIncome / 12.0;
        monthlyTaxPayable = annualTaxPayable / 12.0;
        monthlyTaxFreeAllowance = annualTaxFreeAllowance / 12.0;
    }
}
