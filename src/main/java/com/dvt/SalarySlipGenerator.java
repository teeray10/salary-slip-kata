package com.dvt;

public class SalarySlipGenerator {
    private SalarySlipInterface salarySlip = null;

    public SalarySlipGenerator(){
    }

    public SalarySlipInterface generateSalarySlip(Employee employee) {
        salarySlip = new SalarySlip(employee);
        return salarySlip;
    }
}
