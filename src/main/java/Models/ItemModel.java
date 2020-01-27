package Models;

import Entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;


public class ItemModel  {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public ObservableList getListOfItem(int idRoom)
    {
        ObservableList items;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            items = FXCollections.observableArrayList(session.createQuery("from Item WHERE idRoom = :idRoom").setParameter("idRoom", idRoom).getResultList());

            transaction.commit();

        } catch (Exception e) {
            items = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return items;
    }
    public ObservableList getListOfAllItem()
    {
        ObservableList items;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            items = FXCollections.observableArrayList(session.createQuery("FROM Item").getResultList());

            transaction.commit();

        } catch (Exception e) {
            items = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return items;
    }

    public void createItem(Item item)
    {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(item);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public void deleteItem(Item item)
    {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Item WHERE idItem = :idItem";
            Query query = session.createQuery(hql);
            query.setParameter("idItem", item.getIdItem());
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


}
