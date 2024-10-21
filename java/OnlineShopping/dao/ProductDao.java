package OnlineShopping.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import OnlineShopping.entity.Product;

public class ProductDao {
    private SessionFactory sessionFactory;
   
    public ProductDao() {
        // Initialize SessionFactory using hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(Product product) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close(); // Close the session manually
        }
    }

    public Product getById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return (Product) session.get(Product.class, id);
        } finally {
            session.close(); // Close the session manually
        }
    }

    public void update(Product product) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close(); // Close the session manually
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Product product = (Product) session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close(); // Close the session manually
        }
    }

        public List<Product> getAllProducts() {
            Transaction transaction = null;
            List<Product> products = null;
            Session session = sessionFactory.openSession();
            try{
                transaction = session.beginTransaction();
                Criteria criteria = session.createCriteria(Product.class);
                products = criteria.list();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
            
            finally {
                session.close(); // Close the session manually
            }
            return products;
       
    }
}
