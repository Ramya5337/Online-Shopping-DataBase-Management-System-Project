package OnlineShopping.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import OnlineShopping.entity.Admin;
public class AdminServices {
	private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public boolean login(String username, String password) {
        boolean isValidUser = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Admin admin = (Admin) session.createQuery("FROM Admin WHERE username = :username AND password = :password")
                                         .setParameter("username", username)
                                         .setParameter("password", password) // Use hashed password
                                         .uniqueResult();
            isValidUser = admin != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return isValidUser;
    }
}