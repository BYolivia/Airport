package org.BYolivia.DAO;

import jakarta.persistence.TypedQuery;import org.BYolivia.entities.AirplaneType;
import org.BYolivia.entities.AirplaneType;
import org.BYolivia.DAO.util.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AirplaneTypeDAO {
    private static final AirplaneTypeDAO instance = new AirplaneTypeDAO();
    private AirplaneTypeDAO(){}
    public static AirplaneTypeDAO getInstance() {return instance;}

    //read method
    public List<AirplaneType> loadAirplaneTypes(){

        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            TypedQuery<AirplaneType> query = session.createNativeQuery("select * FROM AIRPLANETYPE", AirplaneType.class);
            List<AirplaneType> airplaneTypes = query.getResultList();
            return airplaneTypes;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    // delete method
    public void deleteAirplaneType(Integer AirplaneTypeID) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            AirplaneType airplaneTypes = (AirplaneType) session.get(AirplaneType.class, AirplaneTypeID);


            session.remove(airplaneTypes);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }



    public void save(AirplaneType airplaneType) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(airplaneType);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }


    }

    public AirplaneType findById(Integer id) {
        Session session = HibernateSessionFactory.getSessionSingleton();
        try {
            AirplaneType airplaneType = session.get(AirplaneType.class, id);
            return airplaneType;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAll(){
        Session session = HibernateSessionFactory.getSessionSingleton();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<AirplaneType> airplaneTypes = loadAirplaneTypes();
            for (AirplaneType airplaneType : airplaneTypes) {
                session.remove(airplaneType);
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }
}