package employeeandsalarysystem;

public class PayrollProcessor {
    private PaymentMethod paymentMethod;
    private TaxDeduction taxDeduction;
    private BenefitDeductions benefitDeductions;
    
    public PayrollProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.taxDeduction = new TaxDeduction();
        this.benefitDeductions = new BenefitDeductions();
    }
    
    public void processPayroll(Employee emp) {
        // Calculate gross monthly pay
        double grossMonthly = emp.calculatePay();
        
        // Calculate benefit deductions (SSS, Philhealth, Pag-ibig)
        double totalDeductions = benefitDeductions.calculateTotalDeductions(grossMonthly);
        
        // Calculate taxable income
        double taxableIncome = grossMonthly - totalDeductions;
        
        // Calculate tax
        double tax = taxDeduction.computeTax(taxableIncome);
        
        // Calculate net pay
        double netPay = grossMonthly - totalDeductions - tax;
        
        // Process payment
        paymentMethod.processPayment(emp, netPay);
        
        // Generate payslip
        generatePayslip(emp, grossMonthly, totalDeductions, taxableIncome, tax, netPay);
    }
    
    private void generatePayslip(Employee emp, double gross, double deductions, 
                               double taxableIncome, double tax, double netPay) {
        System.out.println("\n========== PAYSLIP ==========");
        System.out.printf("Employee: %s\n", emp.getName());
        System.out.printf("Employee ID: %d\n", emp.getEmployeeID());
        System.out.printf("Gross Monthly Salary: ₱%,.2f\n", gross);
        System.out.println("\n--- Deductions ---");
        System.out.printf("SSS: ₱%,.2f\n", benefitDeductions.getSssDeduction());
        System.out.printf("Philhealth: ₱%,.2f\n", benefitDeductions.getPhilhealthDeduction());
        System.out.printf("Pag-ibig: ₱%,.2f\n", benefitDeductions.getPagIbigDeduction());
        System.out.printf("Total Deductions: ₱%,.2f\n", deductions);
        System.out.printf("\nTaxable Income: ₱%,.2f\n", taxableIncome);
        System.out.printf("Withholding Tax: ₱%,.2f\n", tax);
        System.out.printf("\nNET PAY: ₱%,.2f\n", netPay);
        System.out.println("============================");
    }
}