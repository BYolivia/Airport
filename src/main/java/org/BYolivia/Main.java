package org.BYolivia;

import org.BYolivia.DAO.AirplaneDAO;
import org.BYolivia.DAO.AirplaneTypeDAO;
import org.BYolivia.DAO.AirportDAO;
import org.BYolivia.entities.AirplaneType;
import org.BYolivia.entities.Airport;

public class Main {
    public static void main(String[] args) {
        AirplaneTypeDAO airplaneTypeDAO = AirplaneTypeDAO.getInstance();
        AirportDAO airportDAO = AirportDAO.getInstance();
        AirplaneDAO airplaneDAO = AirplaneDAO.getInstance();

        airplaneDAO.loadAirplanes();
        airportDAO.loadAirports();
        airplaneTypeDAO.loadAirplaneTypes();

        // CREADO DE datos en memoria

        AirplaneType type1 = new AirplaneType();
        type1.setName("Boeing 747");
        type1.setSize(400.0);
        airplaneTypeDAO.save(type1);

        AirplaneType type2 = new AirplaneType();
        type2.setName("Airbus A320");
        type2.setSize(180.0);
        airplaneTypeDAO.save(type2);

        AirplaneType type3 = new AirplaneType();
        type3.setName("Cessna 172");
        type3.setSize(4.0);
        airplaneTypeDAO.save(type3);
    }

    Airport airport1 = new Airport(100);


    Airport airport2 = new Airport(200);


    Airport airport3 = new Airport(300);


}
