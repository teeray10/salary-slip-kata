package com.dvt;

public class Employee {
    private String id = "";
    private String name = "";
    private double annualGrossSalary = 0.00;
    private double insuranceContribution = 0.00;

    public Employee() {
    }

    public Employee(String id, String name, double annualGrossSalary) {
        this.id = id;
        this.name = name;
        this.annualGrossSalary = annualGrossSalary;

        calculateInsuranceContribution();
    }

    public void calculateInsuranceContribution() {
        if (annualGrossSalary >= 43000.00)
            insuranceContribution = 0.02;
        else if (this.annualGrossSalary >= 8060.00)
            this.insuranceContribution = 0.12;
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

    public double getInsuranceContribution() {
        return insuranceContribution;
    }
}
