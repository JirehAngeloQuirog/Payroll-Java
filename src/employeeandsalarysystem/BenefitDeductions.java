package employeeandsalarysystem;

public class BenefitDeductions {
    private double sssDeduction;
    private double philhealthDeduction;
    private double pagIbigDeduction;
    
    public double calculateTotalDeductions(double monthlySalary) {
        // SSS Calculation (simplified)
        this.sssDeduction = Math.min(monthlySalary * 0.045, 1350); // 4.5% up to max
        
        // Philhealth Calculation (simplified)
        this.philhealthDeduction = monthlySalary * 0.03; // 3% of monthly salary
        
        // Pag-ibig Calculation
        this.pagIbigDeduction = (monthlySalary >= 5000) ? 100 : monthlySalary * 0.02;
        
        return sssDeduction + philhealthDeduction + pagIbigDeduction;
    }
    
    // Getters
    public double getSssDeduction() { return sssDeduction; }
    public double getPhilhealthDeduction() { return philhealthDeduction; }
    public double getPagIbigDeduction() { return pagIbigDeduction; }
}