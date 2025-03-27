package employeeandsalarysystem;

public class PaymentMethod {
    public void processPayment(Employee emp, double amount) {
        System.out.println("Processing payment of Php " + amount + " for " + emp.name);
    }
}
