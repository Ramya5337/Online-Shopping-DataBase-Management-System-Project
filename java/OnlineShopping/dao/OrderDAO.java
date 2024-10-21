package OnlineShopping.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import OnlineShopping.entity.Order;

public class OrderDAO {
    private SessionFactory sessionFactory;

    public OrderDAO() {
        // Build the SessionFactory from the hibernate.cfg.xml configuration
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void saveOrder(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Close the session explicitly
            }
        }
    }

    public Order getOrder(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return (Order) session.get(Order.class, id);
        } finally {
            if (session != null) {
                session.close(); // Close the session explicitly
            }
        }
    }
}
