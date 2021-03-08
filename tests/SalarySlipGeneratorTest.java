import com.dvt.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SalarySlipGeneratorTest {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;
    private final boolean customAssertEquals = false;

    //UTILITIES
    private void assertName(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getName());
    }

    private void assertId(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getId());
    }

    private void assertTaxPayable(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxPayable(), 0.00);
    }

    private void assertTaxableIncome(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxableIncome(), 0.00);
    }

    private void assertTaxFreeAllowance(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxFreeAllowance(), 0.00);
    }

    private void assertInsuranceContribution(double expected) {
        assertEquals(expected, salarySlip.getMonthlyInsuranceContribution(), 0.00);
    }

    private void assertGrossSalary(double expected) {
        assertEquals(expected, salarySlip.getMonthlyGrossSalary(), 0.00);
    }

    @Before
    public void setup() {
        employee = new Employee("0001", "Taylor Ray", 10000.00);
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);
    }

    @Test
    public void mainExists() throws Exception {
        assertEquals("hello", Main.main(new String[]{"hello"}));
    }

    @Test
    public void createDummySalarySlip() throws Exception {
        SalarySlipInterface salarySlip = new SalarySlip();
        assertNotNull(salarySlip);
    }

    @Test
    public void createSalarySlipGenerator() throws Exception {
        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
        assertNotNull(salarySlipGenerator);
    }

    @Test
    public void createDummyEmployee() throws Exception {
        Employee employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    public void employeeDetailsAreSetWithSetup() throws Exception {
        assertEquals("0001", employee.getId());
        assertEquals("Taylor Ray", employee.getName());
        assertEquals(10000.00, employee.getAnnualGrossSalary(), 0.00);
        assertEquals(0.12, employee.getInsuranceContribution(), 0.00);
    }

    @Test
    public void createdSalarySlipGeneratorWithNoData() throws Exception {
        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
        assertNotNull(salarySlipGenerator);
    }

    @Test
    public void createdSalarySlipWithSalarySlipGenerator() throws Exception {
        assertNotNull(salarySlip);
    }

    @Test
    public void createSalarySlipWithNoData() throws Exception {
        SalarySlipInterface salarySlip = new SalarySlip();
        assertNotNull(salarySlip);
    }

    @Test
    public void salarySlipReceivesEmployeeAndDoesCalculations() throws Exception {
        assertEquals(employee, salarySlip.getEmployee());
        assertEquals(833.33, salarySlip.getMonthlyGrossSalary(), 0.00);
    }

    @Test //Iteration 1
    public void calculateMonthlyGrossSalary() throws Exception {
        employee = new Employee("12345", "John J Doe", 5000.00);
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(416.67);
    }

    @Test
    public void calculateInsuranceAmountDueWhenNotApplicable() throws Exception {
        Employee employee = new Employee("12345", "John J Doe", 8060.00);
        SalarySlipInterface salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertEquals(0.0, salarySlip.getAnnualInsuranceContribution(), 0.00);
        assertEquals(0.0, salarySlip.getMonthlyInsuranceContribution(), 0.00);
    }

    @Test //Iteration 2
    public void calculateInsuranceAmountDueWhenApplicable() throws Exception {
        employee = new Employee("12345", "John J Doe", 9060.00);
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertId("12345");
        assertName("John J Doe");
        assertGrossSalary(755.0);
        assertInsuranceContribution(10.00);
    }

    @Test
    public void checkTaxAmountDueWhenNotApplicable() throws Exception {
        Employee employee = new Employee("12345", "John J Doe", 11000.00);
        SalarySlipInterface salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

        assertEquals(0.0, salarySlip.getMonthlyTaxPayable(), 0.00);
    }

    @Test //Iteration 3
    public void checkTaxAmountDueWhenApplicable() throws Exception {
        employee = new Employee("12345", "John J Doe", 12000.00);
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
        employee = new Employee("12345", "John J Doe", 45000.00);
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
        employee = new Employee("12345", "John J Doe", 101000.00);
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
        employee = new Employee("12345", "John J Doe", 111000.00);
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
        employee = new Employee("12345", "John J Doe", 122000.00);
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
        employee = new Employee("12345", "John J Doe", 150000.00);
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
        employee = new Employee("12345", "John J Doe", 160000.00);
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
