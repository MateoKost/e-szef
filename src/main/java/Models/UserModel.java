package Models;


import Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class UserModel  {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public User getUser(String email){
        User user;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            user = (User) session.createQuery("from User WHERE email = :email")
                    .setParameter("email", email)
                    .getSingleResult();

            transaction.commit();

        } catch (Exception e) {
            user = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return user;
    }
    public User getUser(String email,String password)
    {
        User user;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            user = (User) session.createQuery("from User WHERE email = :email AND password =:password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

            transaction.commit();

        } catch (Exception e) {
            user = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return user;
    }

    public void createUser(String email,String password,String name,String lastName)
    {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setLastName(lastName);
            session.save(user);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


}
