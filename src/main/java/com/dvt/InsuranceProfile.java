package com.dvt;

public class InsuranceProfile implements InsuranceProfileInterface {
    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1 = 8060.0;
    private final static double ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 = 43000.0;
    private final static double INSURANCE_RATE_LEVEL_1 = 0.12;
    private final static double INSURANCE_RATE_LEVEL_2 = 0.02;
    private double annualInsuranceContribution = 0.0;
    private double monthlyInsuranceContribution = 0.0;

    private double annualGrossSalary = 0.0;

    public void calculateInsuranceContribution(double annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
        if (annualGrossSalary > ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2) {
            calculateLevel2InsuranceContribution();
        } else if ((annualGrossSalary > ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1))
            calculateLevel1InsuranceContribution();

        if (annualInsuranceContribution > 0.0)
            convertInsuranceToMonthly();
    }

    public double getMonthlyInsuranceContribution() {
        return format(monthlyInsuranceContribution);
    }

    public double getInsuranceRate() {
        if (annualGrossSalary > 43000.0)
            return format(INSURANCE_RATE_LEVEL_2);
        else if (annualGrossSalary > 8060.0)
            return format(INSURANCE_RATE_LEVEL_1);

        return 0.0;
    }

    private double format(double preFormat) {
        return FormatDecimals.calculate(preFormat);
    }

    private void convertInsuranceToMonthly() {
        monthlyInsuranceContribution = annualInsuranceContribution / 12.0;
    }

    private void calculateLevel1InsuranceContribution() {
        annualInsuranceContribution += (annualGrossSalary - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * INSURANCE_RATE_LEVEL_1;
    }

    private void calculateLevel2InsuranceContribution() {
        annualInsuranceContribution += (annualGrossSalary - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2) * INSURANCE_RATE_LEVEL_2;
        annualInsuranceContribution += (ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_2 - ANNUAL_INSURANCE_STARTING_LIMIT_LEVEL_1) * INSURANCE_RATE_LEVEL_1;
    }
}
