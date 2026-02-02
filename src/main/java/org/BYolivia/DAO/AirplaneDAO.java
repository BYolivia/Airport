package org.BYolivia.DAO;
import jakarta.persistence.TypedQuery;
import org.BYolivia.entities.Airplane;
import org.BYolivia.DAO.util.HibernateSessionFactory;
import org.BYolivia.entities.Airport;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirplaneDAO {

    private static final AirplaneDAO instance = new AirplaneDAO();

    private AirplaneDAO() {
    }

    public static AirplaneDAO getInstance() {
        return instance;
    }


    //read method
    public List<Airplane> loadAirplanes() {

        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            TypedQuery<Airplane> query = session.createNativeQuery("select * FROM Airplane", Airplane.class);
            List<Airplane> airplanes = query.getResultList();
            return airplanes;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    // delete method
    public void deleteAirplane(Integer AirplaneID) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Airplane airplanes = (Airplane) session.get(Airplane.class, AirplaneID);
            // Hibernate will delete EMP_CERT relationships

            session.remove(airplanes);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Airplane> airplanes = loadAirplanes();
            for (Airplane airplane : airplanes) {
                session.remove(airplane);
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public void save(Airplane airplane) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(airplane);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public Airplane findById(Integer id) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            Airplane airplane = session.get(Airplane.class, id);
            return airplane;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Airplane> findAllWithRelations() {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            List<Airplane> airplanes = session.createQuery("FROM Airplane", Airplane.class).getResultList();
            for (Airplane airplane : airplanes) {
                Hibernate.initialize(airplane.getFk_airplanetype());
                Hibernate.initialize(airplane.getFk_airport());
            }
            return airplanes;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}