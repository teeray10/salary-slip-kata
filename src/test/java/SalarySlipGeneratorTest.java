import com.dvt.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SalarySlipGeneratorTest {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;
    private TaxProfileInterface taxProfile = null;

    //UTILITIES
    private void assertName(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getName());
    }

    private void assertId(String expected) {
        assertEquals(expected, salarySlip.getEmployee().getId());
    }

    private void assertGrossSalary(double expected) {
        assertEquals(expected, salarySlip.getMonthlyGrossSalary(), 0.00);
    }

    @Before
    public void setup() {
        taxProfile = new TaxProfile();
        employee = new Employee("0001", "Taylor Ray", 10000.00, new TaxProfile(), new InsuranceProfile());
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
        assertId("0001");
        assertName("Taylor Ray");
        assertGrossSalary(833.33);
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
    public void salarySlipReceivesEmployee() throws Exception {
        assertEquals(employee, salarySlip.getEmployee());
    }
}
