package org.BYolivia.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AIRPLANE")
public class Airplane {

    public Airplane() {
    }
    public Airplane(AirplaneType fk_airplanetype, Airport fk_airport) {
        this.fk_airplanetype = fk_airplanetype;
        this.fk_airport = fk_airport;
    }
    public Airplane(Airport fk_airport, AirplaneType fk_airplanetype) {
        this.fk_airport = fk_airport;
        this.fk_airplanetype = fk_airplanetype;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regno")
    private Integer regno;

    @ManyToOne
    @JoinColumn(name = "fk_airplanetype")
    private AirplaneType fk_airplanetype;

    @ManyToOne
    @JoinColumn(name = "fk_airport")
    private Airport fk_airport;

    public Integer getRegno() {
        return regno;
    }

    public void setRegno(Integer regno) {
        this.regno = regno;
    }

    public AirplaneType getAirplaneType() {
        return fk_airplanetype;
    }

    public void setAirplaneType(AirplaneType fk_airplanetype) {
        this.fk_airplanetype = fk_airplanetype;
    }

    public Airport getAirport() {
        return fk_airport;
    }

    public void setAirport(Airport fk_airport) {
        this.fk_airport = fk_airport;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;
        if (regno != null && airplane.regno != null) {
            return Objects.equals(regno, airplane.regno) ;
        } else {
            return Objects.equals(fk_airplanetype, airplane.fk_airplanetype) && Objects.equals(fk_airport, airplane.fk_airport);
        }
    }

    @Override
    public int hashCode() {

        if (regno != null) {
            return regno.hashCode();
        }
        return (fk_airplanetype == null && fk_airport == null)? 0: Objects.hash(fk_airplanetype, fk_airport);
    }

    @Override
    public String toString() {
        String[] headers = {"Regno", "Type", "Airport ID"};
        int[] widths = {headers[0].length(), headers[1].length(), headers[2].length()};

            String typeName = (this.fk_airplanetype != null) ? String.valueOf(this.fk_airplanetype.getName()) : "null";
            String airportId = (this.fk_airport != null) ? String.valueOf(this.fk_airport.getId()) : "null";
            widths[0] = Math.max(widths[0], String.valueOf(this.regno).length());
            widths[1] = Math.max(widths[1], typeName.length());
            widths[2] = Math.max(widths[2], airportId.length());

        String format = "| %-" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s |%n";
        String separator = "+" + "-".repeat(widths[0] + 2) + "+" + "-".repeat(widths[1] + 2) + "+" + "-".repeat(widths[2] + 2) + "+";
        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format(format, headers[0], headers[1], headers[2]));
        sb.append(separator).append("\n");

            String typeName2 = (this.fk_airplanetype != null) ? String.valueOf(this.fk_airplanetype.getName()) : "null";
            String airportId2 = (this.fk_airport != null) ? String.valueOf(this.fk_airport.getId()) : "null";
            sb.append(String.format(format, this.regno, typeName2, airportId2));

        sb.append(separator);
        return sb.toString();

    }

    public static String toTable(List<Airplane> airplanes) {
        String[] headers = {"Regno", "Type", "Airport ID"};
        int[] widths = {headers[0].length(), headers[1].length(), headers[2].length()};
        for (Airplane a : airplanes) {
            String typeName = (a.fk_airplanetype != null) ? String.valueOf(a.fk_airplanetype.getName()) : "null";
            String airportId = (a.fk_airport != null) ? String.valueOf(a.fk_airport.getId()) : "null";
            widths[0] = Math.max(widths[0], String.valueOf(a.regno).length());
            widths[1] = Math.max(widths[1], typeName.length());
            widths[2] = Math.max(widths[2], airportId.length());
        }
        String format = "| %-" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s |%n";
        String separator = "+" + "-".repeat(widths[0] + 2) + "+" + "-".repeat(widths[1] + 2) + "+" + "-".repeat(widths[2] + 2) + "+";
        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format(format, headers[0], headers[1], headers[2]));
        sb.append(separator).append("\n");
        for (Airplane a : airplanes) {
            String typeName = (a.fk_airplanetype != null) ? String.valueOf(a.fk_airplanetype.getName()) : "null";
            String airportId = (a.fk_airport != null) ? String.valueOf(a.fk_airport.getId()) : "null";
            sb.append(String.format(format, a.regno, typeName, airportId));
        }
        sb.append(separator);
        return sb.toString();
    }


}