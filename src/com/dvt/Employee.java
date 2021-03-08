package com.dvt;

public class Employee {
    private String id = "";
    private String name = "";
    private double annualGrossSalary = 0.00;

    public Employee() {
    }

    public Employee(String id, String name, double annualGrossSalary) {
        this.id = id;
        this.name = name;
        this.annualGrossSalary = annualGrossSalary;
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
}
