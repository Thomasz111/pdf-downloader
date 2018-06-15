import HibUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class dbUtil {

    public static void saveAuthor(Author author){
        save(author);
    }

    public static void saveThesis(Thesis thesis){
        save(thesis);
    }

    private static void  save(Object object){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        session.save(object);

        transaction.commit();
        session.close();
    }
}
