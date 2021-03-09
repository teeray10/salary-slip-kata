import com.dvt.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaxProfileTest {
    private SalarySlipInterface salarySlip = null;
    private Employee employee = null;
    private TaxProfileInterface taxProfile = null;

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
        taxProfile = new TaxProfile();
        employee = new Employee("111", "Taylor", 12000.00, taxProfile, new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);

    }

    @Test
    public void taxProfileCreated() throws Exception {
        TaxProfileInterface taxProfile = new TaxProfile();
        assertNotNull(taxProfile);
    }

    @Test
    public void calculateTaxFreeAllowance() throws Exception {
        assertTaxFreeAllowance(916.67);
    }

    @Test
    public void calculateTaxableIncome() throws Exception {
        assertTaxableIncome(83.33);
    }

    @Test
    public void calculateTaxPayableWhenNotApplicable() throws Exception {
        employee = new Employee("12345", "John J Doe", 11000.00, new TaxProfile(), new InsuranceProfile());
        salarySlip = new SalarySlipGenerator().generateSalarySlip(employee);
        assertTaxPayable(0.0);
    }

    @Test
    public void calculateTaxPayableWhenApplicable() throws Exception {
        assertTaxPayable(16.67);
    }

}
