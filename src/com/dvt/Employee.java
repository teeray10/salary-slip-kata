package com.dvt;

public class Employee {
    private String id = "";
    private String name = "";
    private double annualGrossSalary = 0.00;
    private TaxInterface taxProfile = null;

    public Employee() {
    }

    public Employee(String id, String name, double annualGrossSalary, TaxInterface taxProfile) {
        this.id = id;
        this.name = name;
        this.annualGrossSalary = annualGrossSalary;
        this.taxProfile = taxProfile;

        taxProfile.calculateTaxAmountDue(this.annualGrossSalary);
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

    public TaxInterface getTaxProfile() {
        return taxProfile;
    }
}
