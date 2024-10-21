package OnlineShopping.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import OnlineShopping.entity.Payment;

public class PaymentDAO {
    // Optionally, you can keep a reference to the SessionFactory if you use it often
    private SessionFactory sessionFactory;

    public PaymentDAO() {
        // Initialize the SessionFactory from hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void savePayment(Payment payment) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }
    }
}


