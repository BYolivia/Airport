package org.BYolivia.DAO;

import jakarta.persistence.TypedQuery;
import org.BYolivia.entities.AirplaneType;
import org.BYolivia.entities.Airport;
import org.BYolivia.DAO.util.HibernateSessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class AirportDAO {

    private static final AirportDAO instance = new AirportDAO();
    private AirportDAO(){}
    public static AirportDAO getInstance() {return instance;}

    //read method
    public List<Airport> loadAirports(){

        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            TypedQuery<Airport> query = session.createNativeQuery("select * FROM AIRPORT", Airport.class);
            List<Airport> airports = query.getResultList();
            return airports;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    // delete method
    public void deleteAirport(Integer AirportID) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Airport airport = (Airport) session.get(Airport.class, AirportID);
            // Hibernate will delete EMP_CERT relationships

            session.remove(airport);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public void save(Airport airport) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(airport);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public void update(Airport airport) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(airport);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteAll(){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            session.clear();
            tx = session.beginTransaction();
            List<Airport> airports = session.createQuery("FROM Airport", Airport.class).getResultList();
            for (Airport airport : airports) {
                Hibernate.initialize(airport.getAirplanes());
                Hibernate.initialize(airport.getNeighbour());
                airport.getNeighbour().clear();
            }
            session.flush();
            for (Airport airport : airports) {
                session.remove(airport);
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    public Airport findById(Integer id) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            Airport airport = session.get(Airport.class, id);
            return airport;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  List<Airport> findAllWithAirplanes() {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            List<Airport> airports = session.createQuery("FROM Airport", Airport.class).getResultList();
            for (Airport airport : airports) {
                Hibernate.initialize(airport.getAirplanes());
            }
            return airports;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

}