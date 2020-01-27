package Models;

import API.controllers.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class SoldierModel{

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public ObservableList getListOfSoldier(int box)
    {
        ObservableList soldiers;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            soldiers = FXCollections.observableArrayList(session.createQuery("from Soldier WHERE idRoom = :idRoom").setParameter("idRoom", box).getResultList());

            transaction.commit();

        } catch (Exception e) {
            soldiers = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return soldiers;
    }

    public List getSoldier(String lastName)
    {
        List soldier = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            soldier = session.createQuery("from Soldier WHERE lastName = :lastName").setParameter("lastName", lastName).getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return soldier;
    }

}
