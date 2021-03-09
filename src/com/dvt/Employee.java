package com.dvt;

public class Employee {
    private String id = "";
    private String name = "";
    private double annualGrossSalary = 0.0;
    private TaxProfileInterface taxProfile = null;
    private InsuranceProfileInterface insuranceProfile = null;

    public Employee() {
    }

    public Employee(String id, String name, double annualGrossSalary, TaxProfileInterface taxProfile, InsuranceProfileInterface insuranceProfile) {
        this.id = id;
        this.name = name;
        this.annualGrossSalary = annualGrossSalary;

        this.taxProfile = taxProfile;
        this.taxProfile.calculateTaxAmountDue(this.annualGrossSalary);

        this.insuranceProfile = insuranceProfile;
        this.insuranceProfile.calculateInsuranceContribution(this.annualGrossSalary);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAnnualGrossSalary() {
        return annualGrossSalary;
    }

    public TaxProfileInterface getTaxProfile() {
        return taxProfile;
    }

    public InsuranceProfileInterface getInsuranceProfile() {
        return insuranceProfile;
    }
}
