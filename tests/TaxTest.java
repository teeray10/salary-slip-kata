import com.dvt.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaxTest {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;
    private TaxInterface tax = null;

    // UTILITIES
    private void assertTaxPayable(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxPayable(), 0.00);
    }

    private void assertTaxableIncome(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxableIncome(), 0.00);
    }

    private void assertTaxFreeAllowance(double expected) {
        assertEquals(expected, salarySlip.getEmployee().getTaxProfile().getMonthlyTaxFreeAllowance(), 0.00);
    }

    @Before
    public void setup() throws Exception {
        tax = new Tax();
        employee = new Employee("111", "Taylor", 50000.00, tax);
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
        employee = new Employee("111", "taylor", 12000.00, tax);
        assertTaxPayable(16.67);
    }

}
