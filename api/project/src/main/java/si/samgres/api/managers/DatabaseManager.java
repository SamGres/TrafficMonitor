package si.samgres.api.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

public class DatabaseManager {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static Session session;

    public static void initialize() {
        if (configuration == null) { //ensure singleton
            configuration = new Configuration().configure("hibernate.cfg.xml");

            //add entities
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Post.class);
        }
        if (sessionFactory == null) { //ensure singleton
            sessionFactory = configuration.buildSessionFactory();
        }
    }

    public static boolean add(Object newObject) {
        initialize(); //ensure objects

        //try adding
        try {
            session = sessionFactory.openSession();

            //create transaction
            Transaction tx = session.beginTransaction();
            session.save(newObject);
            tx.commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
