package org.BYolivia;

import org.BYolivia.DAO.AirplaneDAO;
import org.BYolivia.DAO.AirplaneTypeDAO;
import org.BYolivia.DAO.AirportDAO;
import org.BYolivia.entities.Airplane;
import org.BYolivia.entities.AirplaneType;
import org.BYolivia.entities.Airport;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AirplaneTypeDAO airplaneTypeInstance = AirplaneTypeDAO.getInstance();
        AirportDAO airportInstance = AirportDAO.getInstance();
        AirplaneDAO airplaneInstance = AirplaneDAO.getInstance();

        airplaneInstance.loadAirplanes();
        airportInstance.loadAirports();
        airplaneTypeInstance.loadAirplaneTypes();

        // CREADO DE datos en memoria tipos

        AirplaneType type1 = new AirplaneType();
        type1.setName("Boeing 747");
        type1.setSize(400.0);
        airplaneTypeInstance.save(type1);

        AirplaneType type2 = new AirplaneType();
        type2.setName("Airbus A320");
        type2.setSize(180.0);
        airplaneTypeInstance.save(type2);

        AirplaneType type3 = new AirplaneType();
        type3.setName("Cessna 172");
        type3.setSize(4.0);
        airplaneTypeInstance.save(type3);

        // aeropuertos
        Airport airport1 = new Airport(100);

        Airport airport2 = new Airport(200);

        Airport airport3 = new Airport(300);

        airport1.setNeighbour(Set.of(airport2, airport3));
        airport2.setNeighbour(Set.of(airport1, airport3));
        airport3.setNeighbour(Set.of(airport1, airport2));

        airportInstance.save(airport1);
        airportInstance.save(airport2);
        airportInstance.save(airport3);

        // aviones
        Airplane airplane1 = new Airplane(type1, airport1);
        Airplane airplane2 = new Airplane(type2, airport3);
        Airplane airplane3 = new Airplane(type3, airport2);
    }
}
