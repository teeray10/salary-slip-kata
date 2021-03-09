import com.dvt.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;

    //UTILITIES
    private void assertName(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getName());
    }

    private void assertId(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getId());
    }

    private void assertTaxPayable(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxPayable(), 0.00);
    }

    private void assertTaxableIncome(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxableIncome(), 0.00);
    }

    private void assertTaxFreeAllowance(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxFreeAllowance(), 0.00);
    }

    private void assertGrossSalary(double expected) {
        assertEquals(expected, salarySlip.getMonthlyGrossSalary(), 0.00);
    }

    private void assertInsuranceContribution(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getInsuranceProfile().getMonthlyInsuranceContribution(), 0.00);
    }

    private void assertInsuranceRate(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getInsuranceProfile().getInsuranceRate(), 0.0);
    }

    @Test //Iteration 1
    public void calculateMonthlyGrossSalary() throws Exception {
        employee = new Employee("12345", "John J Doe", 5000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(416.67);
    }

    @Test //Iteration 2
    public void calculateInsuranceContributionWhenApplicable() throws Exception {
        employee = new Employee("12345", "John J Doe", 9060.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(755.0);
        assertInsuranceContribution(10.00);
        assertInsuranceRate(0.12);
    }

    @Test //Iteration 3
    public void checkTaxPayableWhenApplicable() throws Exception {
        employee = new Employee("12345", "John J Doe", 12000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(1000.00);
        assertInsuranceContribution(39.40);
        assertTaxFreeAllowance(916.67);
        assertTaxableIncome(83.33);
        assertTaxPayable(16.67);
    }

    @Test //Iteration 4
    public void calculateLevel2GrossSalary() throws Exception {
        employee = new Employee("12345", "John J Doe", 45000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(3750.00);
        assertInsuranceContribution(352.73);
        assertTaxFreeAllowance(916.67);
        assertTaxableIncome(2833.33);
        assertTaxPayable(600.00);
    }

    @Test //Iteration 5 #1
    public void calculateLevel3GrossSalary1() throws Exception {
        employee = new Employee("12345", "John J Doe", 101000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(8416.67);
        assertInsuranceContribution(446.07);
        assertTaxFreeAllowance(875.00);
        assertTaxableIncome(7541.67);
        assertTaxPayable(2483.33);
    }

    @Test //Iteration 5 #2
    public void calculateLevel3GrossSalary2() throws Exception {
        employee = new Employee("12345", "John J Doe", 111000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(9250.00);
        assertInsuranceContribution(462.73);
        assertTaxFreeAllowance(458.33);
        assertTaxableIncome(8791.67);
        assertTaxPayable(2983.33);
    }

    @Test //Iteration 5 #3
    public void calculateLevel3GrossSalary3() throws Exception {
        employee = new Employee("12345", "John J Doe", 122000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(10166.67);
        assertInsuranceContribution(481.07);
        assertTaxFreeAllowance(0.00);
        assertTaxableIncome(10166.67);
        assertTaxPayable(3533.33);
    }

    @Test //Iteration 5 #4
    public void calculateLevel3GrossSalary4() throws Exception {
        employee = new Employee("12345", "John J Doe", 150000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(12500.00);
        assertInsuranceContribution(527.73);
        assertTaxFreeAllowance(0.00);
        assertTaxableIncome(12500.00);
        assertTaxPayable(4466.67);
    }

    @Test //Iteration 6
    public void calculateLevel4GrossSalary() throws Exception {
        employee = new Employee("12345", "John J Doe", 160000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(13333.33);
        assertInsuranceContribution(544.40);
        assertTaxFreeAllowance(0.00);
        assertTaxableIncome(13333.33);
        assertTaxPayable(4841.67);
    }
}
