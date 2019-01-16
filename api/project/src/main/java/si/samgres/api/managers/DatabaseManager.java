package si.samgres.api.managers;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

import java.util.List;

public class DatabaseManager {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() {
        initialize(); //ensure objects

        return sessionFactory.openSession();
    }

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

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }

    public static <T> List getAll(Class<T> type) {
        initialize(); //ensure objects

        //try getting
        List objects = null;
        try {
            session = sessionFactory.openSession();

            //get objects
            objects = (List<T>)session.createSQLQuery("select * from " + type.getSimpleName()).addEntity(type).list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    public static boolean update(Object object) {
        initialize(); //ensure objects

        //try adding
        try {
            session = sessionFactory.openSession();

            //create transaction
            Transaction tx = session.beginTransaction();
            session.update(object);
            tx.commit();

            //ok
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; //fail
        }
    }
}
