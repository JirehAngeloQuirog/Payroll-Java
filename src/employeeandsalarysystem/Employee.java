package employeeandsalarysystem;

public abstract class Employee implements SalaryCalculator {
    protected int employeeID;
    protected String name;
    protected double salary;
    protected String position;  // Now properly used throughout the class
    
    public Employee(int employeeID, String name, double salary, String position) {
        this.employeeID = employeeID;
        this.name = name;
        this.salary = salary;
        this.position = position;  // Actually using the position field
    }
    
    public abstract double calculatePay();
    
    public String getDetails() {
        return String.format("ID: %d, Name: %s, Position: %s", employeeID, name, position);
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public int getEmployeeID() { 
        return employeeID; 
    }
    
    public double getSalary() { 
        return salary; 
    }
}