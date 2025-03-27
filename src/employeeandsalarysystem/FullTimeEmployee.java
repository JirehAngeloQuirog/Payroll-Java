package employeeandsalarysystem;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int employeeID, String name, double salary, String position) {
        super(employeeID, name, salary, position);
    }

    @Override
    public double calculateBasicPay() {
        return salary;
    }

    @Override
    public double calculateDeductions() {
        return salary * 0.10; // 10% deduction for benefits
    }

    @Override
    public double calculateBonuses() {
        return salary * 0.05; // 5% bonus
    }

    @Override
    public double calculatePay() {
        return calculateBasicPay() - calculateDeductions() + calculateBonuses();
    }
}
