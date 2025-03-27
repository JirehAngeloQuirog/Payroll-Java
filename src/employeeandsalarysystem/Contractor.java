package employeeandsalarysystem;

public class Contractor extends Employee {

    public Contractor(int employeeID, String name, double salary, String position) {
        super(employeeID, name, salary, position);
    }

    @Override
    public double calculateBasicPay() {
        // Contractors typically get their full salary as basic pay
        return salary;
    }

    @Override
    public double calculateBonuses() {
        // Contractors usually don't get bonuses
        return 0;
    }

    @Override
    public double calculateDeductions() {
        // Contractors might have different deduction rules
        // Example: Only tax deduction, no benefits
        return salary * 0.10; // 10% tax as example
    }

    @Override
    public double calculatePay() {
        // Net pay after deductions
        return calculateBasicPay() - calculateDeductions();
    }
}