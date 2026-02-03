package org.BYolivia;

import org.BYolivia.DAO.AirplaneDAO;
import org.BYolivia.DAO.AirplaneTypeDAO;
import org.BYolivia.DAO.AirportDAO;
import org.BYolivia.entities.Airplane;
import org.BYolivia.entities.AirplaneType;
import org.BYolivia.entities.Airport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static AirplaneTypeDAO airplaneTypeInstance = AirplaneTypeDAO.getInstance();
    private static AirportDAO airportInstance = AirportDAO.getInstance();
    private static AirplaneDAO airplaneInstance = AirplaneDAO.getInstance();
    public static void main(String[] args) {


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

        save(airport1, airport2, airport3);

        System.out.println("\n=== Aeropuertos guardados (sin vecinos) ===");

        airport1.setNeighbour(new HashSet<>(Set.of(airport2, airport3)));
        airport2.setNeighbour(new HashSet<>(Set.of(airport1, airport3)));
        airport3.setNeighbour(new HashSet<>(Set.of(airport1, airport2)));

        airportInstance.update(airport1);
        airportInstance.update(airport2);
        airportInstance.update(airport3);

        // Create airplanes and assign types and airports

        //Airplane 1
        Airplane airplane1 = new Airplane();
        airplane1.setAirplaneType(type1);
        airplane1.setAirport(airport1);

        //Airplane 2
        Airplane airplane2 = new Airplane();
        airplane2.setAirplaneType(type2);
        airplane2.setAirport(airport3);

        //Airplane 3
        Airplane airplane3 = new Airplane();
        airplane3.setAirplaneType(type3);
        airplane3.setAirport(airport2);


        save(airplane1, airplane2, airplane3);

        print();


        // delete all aiports
        airportInstance.deleteAll();


        print();

    }

    private static void print(){

        // List all airplanes with their types and airports
        {
            List<Airplane> airplanes = airplaneInstance.findAllWithRelations();
            System.out.println("\n=== Aviones con sus tipos y aeropuertos ===");
            System.out.println(Airplane.toTable(airplanes));
        }

        // List all airports with their airplanes
        {
            List<Airport> airports = airportInstance.findAllWithAirplanes();
            System.out.println("\n=== Aeropuertos con sus aviones ===");
            System.out.println(Airport.toTable(airports));
        }

    }


    private static void save(Object ...entities) {
        for (Object entity : entities) {
            if (entity instanceof Airplane) {
                airplaneInstance.save((Airplane) entity);
            } else if (entity instanceof Airport) {
                airportInstance.save((Airport) entity);
            } else if (entity instanceof AirplaneType) {
                airplaneTypeInstance.save((AirplaneType) entity);
            }
        }
    }
}
