import com.dvt.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaxTest {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;

    // UTILITIES
    private void assertTaxPayable(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxPayable(), 0.00);
    }

    private void assertTaxableIncome(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxableIncome(), 0.00);
    }

    private void assertTaxFreeAllowance(double expected) {
        assertEquals(expected, salarySlip.getMonthlyTaxFreeAllowance(), 0.00);
    }

    @Before
    public void setup() throws Exception {
        employee = new Employee("111", "Taylor", 50000.00);
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);
    }

    @Test
    public void nothing() throws Exception {
    }

    @Test
    public void taxCreated() throws Exception {
        Tax tax = new Tax();
        assertNotNull(tax);
    }

    @Test
    public void taxInterfaceCreated() throws Exception {
        TaxInterface taxInterface = new Tax();
        assertNotNull(taxInterface);
    }

    @Test
    public void taxAmountDue() throws Exception {
        assertTaxPayable(16.67);
    }

}
