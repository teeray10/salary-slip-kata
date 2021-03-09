package com.dvt;

import static java.lang.Double.valueOf;

public class SalarySlip implements SalarySlipInterface {
    private Employee employee = null;
    private double monthlyGrossSalary = 0.0;

    public SalarySlip() {
    }

    public SalarySlip(Employee employee) {
        this.employee = employee;
        convertSalaryToMonthly();
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getMonthlyGrossSalary() {
        return format(monthlyGrossSalary);
    }

    private double format(double preFormat) {
        return FormatDecimals.calculate(preFormat);
    }

    private void convertSalaryToMonthly() {
        monthlyGrossSalary = employee.getAnnualGrossSalary() / 12.0;
    }
}
