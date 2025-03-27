package employeeandsalarysystem;

public class PartTimeEmployee extends Employee {

    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int employeeID, String name, double hourlyRate, int hoursWorked, String position) {
        super(employeeID, name, hourlyRate * hoursWorked, position);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateBasicPay() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public double calculateDeductions() {
        return calculateBasicPay() * 0.05; // 5% deduction
    }

    @Override
    public double calculateBonuses() {
        return calculateBasicPay() * 0.02; // 2% bonus
    }

    @Override
    public double calculatePay() {
        return calculateBasicPay() - calculateDeductions() + calculateBonuses();
    }
}
