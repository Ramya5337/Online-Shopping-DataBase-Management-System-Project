package OnlineShopping.Services;



import OnlineShopping.dao.PaymentDAO;
import OnlineShopping.entity.Payment;

public class PaymentService {
    private PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO(); // Instantiate the PaymentDAO
    }

    public void processPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

        // Additional validation logic can be implemented here
        validatePayment(payment);
        
        // Save the payment using the DAO
        paymentDAO.savePayment(payment);
    }

    private void validatePayment(Payment payment) {
        // Implement validation rules such as checking amount, payment method, etc.
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
       
    }
}

